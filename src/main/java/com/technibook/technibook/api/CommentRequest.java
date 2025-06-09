package com.technibook.technibook.api;

import java.util.Optional;

import lombok.Data;

@Data
public class CommentRequest {
    private Integer postId;
    private String content;
    private Optional<Integer> parentCommentId = Optional.empty();
}
