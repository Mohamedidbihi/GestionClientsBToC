package com.app.gestionclients.entity;

import com.app.gestionclients.enums.sexe;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @Email
    @Column(
            nullable = false,
            length = 255,
            unique = true
    )
    private String email;

    @Column(
            nullable = false
    )
    @Pattern(regexp = "(\\+212|1)(\\d){9}")
    private String phone;

    @NotBlank(message = "Name is required")
    private String fullName;

    @NotNull
    @Range(min=18, max=64)
    private int age;

    @Enumerated(EnumType.STRING)
    @NotNull
    private sexe sexe;

    @Column
    private Boolean isActive = true;

}
