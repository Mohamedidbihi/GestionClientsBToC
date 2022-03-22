package com.app.gestionclients.dto;

import com.app.gestionclients.enums.sexe;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto {

    private  Long id;
    private  String email;
    private  String phone;
    private  String fullName;
    private  int age;
    private sexe sexe;
    private Boolean isActive;

}
