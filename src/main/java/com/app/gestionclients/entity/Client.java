package com.app.gestionclients.entity;

import com.app.gestionclients.enums.sexe;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "profile")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private Long id;

    @Column(
            nullable = false,
            length = 255,
            unique = true
    )
    private String email;

    @Column(
            nullable = false,
            length = 10
    )
    private int phone;


    @NotBlank(message = "Name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Size(min = 18, max = 64)
    private int age;

    @Enumerated(EnumType.ORDINAL)
    private sexe sexe;

    @Column(
            nullable = false
    )
    private Boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public com.app.gestionclients.enums.sexe getSexe() {
        return sexe;
    }

    public void setSexe(com.app.gestionclients.enums.sexe sexe) {
        this.sexe = sexe;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
