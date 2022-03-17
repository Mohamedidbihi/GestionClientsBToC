package com.app.gestionclients.dto;

import com.app.gestionclients.enums.sexe;


public class ClientDto {

    private  Long id;
    private  String email;
    private  int phone;
    private  String fullName;
    private  int age;
    private sexe sexe;
    private Boolean isActive;

    public ClientDto(Long id, String email, int phone, String fullName, int age, com.app.gestionclients.enums.sexe sexe, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.age = age;
        this.sexe = sexe;
        this.isActive = isActive;
    }

    public ClientDto(String email, int phone, String fullName, int age, com.app.gestionclients.enums.sexe sexe, Boolean isActive) {
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.age = age;
        this.sexe = sexe;
        this.isActive = isActive;
    }

    public ClientDto() {

    }

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
