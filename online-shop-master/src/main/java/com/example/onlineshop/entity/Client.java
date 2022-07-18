package com.example.onlineshop.entity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "client")
public class Client extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

}
