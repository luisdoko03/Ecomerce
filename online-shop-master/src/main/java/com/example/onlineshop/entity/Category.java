package com.example.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category")
public class Category extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "photo_url")
    private String photoUrl;
}
