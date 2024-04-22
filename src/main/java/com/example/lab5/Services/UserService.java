package com.example.lab5.Services;

import com.example.lab5.Repositories.Entities.Role;
import com.example.lab5.Repositories.Entities.User;
import com.example.lab5.Repositories.RoleRepository;
import com.example.lab5.Repositories.UserRepository;
import com.example.lab5.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Lazy
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
        User user = new User();
        if (existsByUsername(username)) {
            user = userRepository.findByUsername(username).get();
        }
        return user;
    }



    public boolean existsByUsername(String username) {

        return roleRepository.findByName(username).isPresent();
    }

    public boolean saveUser(UserDto userdto) {

        if (existsByUsername(userdto.getUsername())) {
             return false;
        }

        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
        if (roleRepository.findByName("ROLE_USER").isPresent()){
            user.addRole(roleRepository.findByName("ROLE_USER").get());
        } else {
            user.addRole(new Role(1L, "ROLE_USER"));
        }
        userRepository.save(user);
        return true;
    }




}
