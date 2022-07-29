package com.example.tinyshop.repository;

import com.example.tinyshop.model.entity.Role;
import com.example.tinyshop.model.enums.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RolesEnum admin);

}
