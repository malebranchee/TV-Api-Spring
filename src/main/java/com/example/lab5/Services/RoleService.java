package com.example.lab5.Services;

import com.example.lab5.Repositories.Entities.Role;
import com.example.lab5.Repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

}
