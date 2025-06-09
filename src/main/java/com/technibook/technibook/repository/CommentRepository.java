package com.technibook.technibook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technibook.technibook.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findById(Optional<Integer> id);
    List<Comment> findByParentCommentIdAndDeletedAtIsNull(Integer parentCommentId);
}
