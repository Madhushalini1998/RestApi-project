package com.TestProject.TestProject.model;

import jakarta.persistence.*;  // For @Entity, @Id, etc.
import jakarta.validation.constraints.Email;     // For @Email
import jakarta.validation.constraints.NotBlank;  // For @NotBlank

@Entity  // Maps this class to a database table
public class Borrower {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name should not be blank
    @NotBlank(message = "Name is required")
    private String name;

    // Email should be valid and not blank
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    // === Getters and Setters ===
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
