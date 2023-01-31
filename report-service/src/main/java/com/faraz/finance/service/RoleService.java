package com.faraz.finance.service;

import com.faraz.finance.model.FGNR.Role;
import com.faraz.finance.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public Role create(Role role) {
        return roleRepository.save(role);
    }

}
