package com.example.onlineshop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "banner")
public class Banner extends BaseEntity{

    private String name;
    private String photoUrl;
}
