package com.technibook.technibook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technibook.technibook.api.CommentRequest;
import com.technibook.technibook.api.CommentResponse;
import com.technibook.technibook.model.Comment;
import com.technibook.technibook.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody CommentRequest request) {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentService.createComment(request, userId);
        return ResponseEntity.ok(new CommentResponse(comment));
    }
@GetMapping("/{commentId}")
public ResponseEntity<List<CommentResponse>> getReplies(@PathVariable Integer commentId) {
    return ResponseEntity.ok(commentService.getThreadedReplies(commentId));
}
}
