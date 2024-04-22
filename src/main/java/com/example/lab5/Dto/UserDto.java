package com.example.lab5.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@Data
public class UserDto {

    public interface registration{

    }
    public interface login{

    }

    @NotNull(groups = {registration.class, login.class}, message = "Поле не должно быть пустым!")
    private String username;


    @NotNull(groups = {registration.class, login.class}, message = "Поле не должно быть пустым!")
    private String password;


    @NotNull(groups = {registration.class}, message = "Поле не должно быть пустым!")
    private String passwordConfirm;
}
