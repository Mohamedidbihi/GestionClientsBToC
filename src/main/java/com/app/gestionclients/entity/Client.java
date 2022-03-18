package com.app.gestionclients.entity;

import com.app.gestionclients.enums.sexe;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @NotBlank(message = "Age is required")
    @Size(min = 18, max = 64)
    private int age;

    @Enumerated(EnumType.ORDINAL)
    private sexe sexe;

    @Column(
            nullable = false
    )
    private Boolean isActive = true;

}
