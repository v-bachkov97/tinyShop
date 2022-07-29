package com.example.tinyshop.service;

import com.example.tinyshop.model.dtos.UserRegistrationDTO;
import com.example.tinyshop.model.entity.Cart;
import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.model.enums.RolesEnum;
import com.example.tinyshop.repository.RoleRepository;
import com.example.tinyshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationDTO registrationDTO) {
        User user = new User();
        Cart cart = new Cart();
        user.setFullName(registrationDTO.getFullName());
        user.setEmail(registrationDTO.getEmail());
        user.setBirthDate(registrationDTO.getBirthDate());
        user.setCart(cart);
        user.setGender(registrationDTO.getGender());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName(RolesEnum.ADMIN);
            user.setRoles(Set.of(adminRole));
            this.userRepository.save(user);
            return;
        }
        Role userRole = roleRepository.findByName(RolesEnum.USER);
        user.setRoles(Set.of(userRole));
        this.userRepository.save(user);

    }

    public boolean isEmailTaken(String email) {
        Optional<User> byEmail = this.userRepository.findByEmail(email);
        return byEmail.isPresent();
    }

    public void initializeRoles() {
        if (roleRepository.count() == 0) {
            Arrays.stream(RolesEnum.values()).forEach(currentRole -> {
                Role role = new Role();
                role.setName(currentRole);
                roleRepository.save(role);
            });
        }
    }
}
