package com.technibook.technibook.api;

import java.time.LocalDate;

import com.technibook.technibook.model.Sex;

import lombok.Data;
@Data
public class RegisterUserRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Sex sex;
}
