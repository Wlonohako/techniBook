package com.technibook.technibook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.technibook.technibook.api.PostRequest;
import com.technibook.technibook.api.UpdatePostRequest;
import com.technibook.technibook.model.Post;
import com.technibook.technibook.model.User;
import com.technibook.technibook.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getVisiblePosts() {
        return ResponseEntity.ok(postService.getVisiblePosts());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(postService.createPost(request, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody UpdatePostRequest request) {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(postService.updatePost(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Integer id) {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(postService.softDeletePost(id, userId));
    }

}
