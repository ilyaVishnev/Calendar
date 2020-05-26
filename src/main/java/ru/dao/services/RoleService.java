package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dao.repositories.RoleRepository;
import ru.models.Role;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
