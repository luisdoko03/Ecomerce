package com.example.onlineshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveClient {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public SaveClient() {

    }

    @Builder
    public SaveClient(int id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
