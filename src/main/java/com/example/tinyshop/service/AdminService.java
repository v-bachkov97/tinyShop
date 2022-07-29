package com.example.tinyshop.service;

import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.entity.User;
import com.example.tinyshop.model.enums.RolesEnum;
import com.example.tinyshop.model.view.AdminPanelUserView;
import com.example.tinyshop.repository.RoleRepository;
import com.example.tinyshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {
   private final UserRepository userRepository;
   private final ModelMapper modelMapper;
   private final RoleRepository roleRepository;


    public AdminService(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public List<AdminPanelUserView> findAll(long id) {
        List<User> users = userRepository.findAllByIdNot(id);
        List<AdminPanelUserView> view = new ArrayList<>();
        for (User user : users) {
            Set<Role> roles = user.getRoles();
            Role role = roleRepository.findByName(RolesEnum.MODERATOR);

            AdminPanelUserView currentUser = new AdminPanelUserView();
            currentUser.setId(user.getId());
            currentUser.setFullName(user.getFullName());
            if (roles.contains(role)){
                currentUser.setRole("MODERATOR");
            }else {
                currentUser.setRole("USER");
            }
            view.add(currentUser);

        }
        return view;
    }

    public void promote(long id) {
        Optional<User> user = userRepository.findById(id);
        Role moderator = roleRepository.findByName(RolesEnum.MODERATOR);
        Role userRole = roleRepository.findByName(RolesEnum.USER);
        user.get().getRoles().add(moderator);
        user.get().getRoles().remove(userRole);
        userRepository.save(user.get());
    }

    public void demote(long id) {
        Optional<User> user = userRepository.findById(id);
        Role moderator = roleRepository.findByName(RolesEnum.MODERATOR);

        user.get().getRoles().remove(moderator);

        userRepository.save(user.get());
    }
    public void deleteAccount(long id) {
        userRepository.deleteById(id);
    }
}
