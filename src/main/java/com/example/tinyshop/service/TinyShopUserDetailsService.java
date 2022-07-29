package com.example.tinyshop.service;

import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.model.user.TinyShopUserDetails;
import com.example.tinyshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TinyShopUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public TinyShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.
                findByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(User user) {


        return new TinyShopUserDetails(
                user.getPassword(),
                user.getEmail(),
                user.getFullName(),
                user.getCart(),
                user.getId(),
                user.getRoles().stream().map(this::map).
                        toList()
        );

    }

    private GrantedAuthority map(Role userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getName().name());
    }
}
