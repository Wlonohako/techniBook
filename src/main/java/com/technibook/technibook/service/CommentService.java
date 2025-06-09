package com.technibook.technibook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.technibook.technibook.api.CommentRequest;
import com.technibook.technibook.api.CommentResponse;
import com.technibook.technibook.model.Comment;
import com.technibook.technibook.model.Post;
import com.technibook.technibook.model.User;
import com.technibook.technibook.repository.CommentRepository;
import com.technibook.technibook.repository.PostRepository;
import com.technibook.technibook.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Comment createComment(CommentRequest request, Integer userId) {
        Post post = postRepository.getById(request.getPostId());
        User user = userRepository.getById(userId);

        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .parentComment(request.getParentCommentId() != null
                        ? commentRepository.findById(request.getParentCommentId()).orElse(null)
                        : null)
                .build();
        commentRepository.save(comment);
        return comment;
    }

    public List<CommentResponse> getThreadedReplies(Integer parentCommentId) {
        List<Comment> directReplies = commentRepository.findByParentCommentIdAndDeletedAtIsNull(parentCommentId);
        List<CommentResponse> responses = new ArrayList<>();

        for (Comment reply : directReplies) {
            CommentResponse response = new CommentResponse(reply);
            // Recursively fetch replies
            response.setReplies(getThreadedReplies(reply.getId()));
            responses.add(response);
        }

        return responses;
    }

}
