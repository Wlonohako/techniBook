package com.technibook.technibook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.technibook.technibook.api.PostRequest;
import com.technibook.technibook.api.UpdatePostRequest;
import com.technibook.technibook.model.Post;
import com.technibook.technibook.model.PostVisibility;
import com.technibook.technibook.repository.PostRepository;
import com.technibook.technibook.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final GroupService groupService;
    public List<Post> getVisiblePosts() {
        return postRepository.findByDeletedAtIsNullAndVisibility(PostVisibility.PUBLIC);
    }

    public Post createPost(PostRequest request, Integer userId) {
        
        Post post = Post.builder()
                .content(request.getContent())
                .visibility(request.getVisibility())
                .user(userRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("User not found")))
                .group(groupService.findById(request.getGroupId().orElse(null)))
                .build();
        postRepository.save(post);
        post.setUser(userService.getUserById(userId));
        return post;
    }

    public Post updatePost(Integer id, UpdatePostRequest request, Integer userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        if (!post.getUser().getId().equals(userId)) {
            throw new SecurityException("You do not have permission to update this post");
        }

        post.setContent(request.getContent());
        post.setVisibility(request.getVisibility());
        postRepository.save(post);
        post.setUser(userService.getUserById(userId));
        return post;
    }

    public Post softDeletePost(Integer id, Integer userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        if (post.getDeletedAt() != null) {
            throw new EntityNotFoundException("Post not found");
        }
        if (!post.getUser().getId().equals(userId)) {
            throw new SecurityException("You do not have permission to delete this post");
        }

        post.setDeletedAt(java.time.LocalDateTime.now());
        postRepository.save(post);
        post.setUser(userService.getUserById(userId));
        return post;
    }

}
