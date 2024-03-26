package com.example.fullstackbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name;
    private String email;
}
