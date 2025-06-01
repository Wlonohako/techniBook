package com.technibook.technibook.api;

import java.time.LocalDate;
import java.util.Optional;

import com.technibook.technibook.model.Sex;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private Optional<String> name = Optional.empty();
    private Optional<String> surname = Optional.empty();
    private Optional<LocalDate> birthDate = Optional.empty();
    private Optional<Sex> sex = Optional.empty();
}
