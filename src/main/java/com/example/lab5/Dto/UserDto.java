package com.example.lab5.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String passwordConfirm;
}
