package com.gamertx.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonalInfo {
    private String email;
    private String name;
    private String password;
    private String lastName;
    private String username;
    private Byte age;
    private LocalDate birthdate;
    private String imgProfile;
}
