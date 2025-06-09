package com.technibook.technibook.api;

import java.util.Optional;

import lombok.Data;

@Data
public class GroupRequest {
    private String title;
    private Optional<String> description = Optional.empty();
}
