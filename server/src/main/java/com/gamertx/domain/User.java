package com.gamertx.domain;

import com.gamertx.utilities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String username;
    @NotEmpty
    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
