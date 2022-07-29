package com.example.tinyshop.model.entity;

import com.example.tinyshop.model.enums.CollectionCategory;

import javax.persistence.*;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CollectionCategory type;

    public Collection() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public CollectionCategory getType() {
        return type;
    }

    public void setType(CollectionCategory type) {
        this.type = type;
    }
}
