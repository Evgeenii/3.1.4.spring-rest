package ru.adrenoxxxrom.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.adrenoxxxrom.security.model.Role;
import ru.adrenoxxxrom.security.repositories.RoleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private RoleRepository rolesRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Role> getRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public List<Role> getRoleById(Long id) {
        return rolesRepository.findAllById(id);
    }
}
