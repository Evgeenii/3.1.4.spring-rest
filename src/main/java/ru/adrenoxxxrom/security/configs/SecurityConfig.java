package ru.adrenoxxxrom.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.adrenoxxxrom.security.model.Role;
import ru.adrenoxxxrom.security.model.User;
import ru.adrenoxxxrom.security.services.RoleService;
import ru.adrenoxxxrom.security.services.UserService;

import javax.annotation.PostConstruct;
import java.util.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    @Autowired
    public SecurityConfig(SuccessUserHandler successUserHandler, UserService userService, RoleService roleService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index")
                .permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().successHandler(successUserHandler)
                .permitAll()
                .and().logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        auth.authenticationProvider(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @PostConstruct
    public void initDbUsers() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        Collections.addAll(adminRoles, roleAdmin, roleUser);
        Collections.addAll(userRoles, roleUser);

        User admin = new User();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Евгений");
        admin.setLastName("Казьмин");
        admin.setAge((byte) 30);
        admin.setRoles(adminRoles);

        userService.saveUser(admin);

        User user = new User();
        user.setId(2L);
        user.setUsername("user");
        user.setPassword("user");
        user.setFirstName("Василий");
        user.setLastName("Обухов");
        user.setAge((byte) 20);
        user.setRoles(userRoles);

        userService.saveUser(user);
    }
}
