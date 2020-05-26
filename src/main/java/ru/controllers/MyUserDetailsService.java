package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dao.repositories.WorkerRepository;
import ru.dao.services.RoleService;
import ru.dao.services.WorkerService;
import ru.models.Role;
import ru.models.Worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Role role = roleService.findByName(s);
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(s));
        return buildUserForAuthentication(role, authorities);
    }

    private User buildUserForAuthentication(Role role,
                                            List<GrantedAuthority> authorities) {
        String encoded = "{noop}" + role.getPassword();
        return new User(role.getName(),
                encoded, true,
                true, true, true, authorities);
    }
}
