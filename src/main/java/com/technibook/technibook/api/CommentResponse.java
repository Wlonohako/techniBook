package com.technibook.technibook.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.technibook.technibook.model.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Integer id;
    private String content;
    private String authorUsername;
    private LocalDateTime createdAt;
    private List<CommentResponse> replies = new ArrayList<>();

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.authorUsername = comment.getUser().getName();
        this.createdAt = comment.getCreatedAt();
    }
}
