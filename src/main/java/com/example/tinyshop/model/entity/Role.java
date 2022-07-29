package com.example.tinyshop.model.entity;

import com.example.tinyshop.model.enums.RolesEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private RolesEnum name;



    public Role() {
    }

    public RolesEnum getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(RolesEnum name) {
        this.name = name;
    }
}
