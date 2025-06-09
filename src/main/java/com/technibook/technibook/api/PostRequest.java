package com.technibook.technibook.api;
import java.util.Optional;

import com.technibook.technibook.model.PostVisibility;

import lombok.Data;

@Data
public class PostRequest {
    private String content;
    private PostVisibility visibility;
    private Optional<Integer> groupId = Optional.empty();
}
