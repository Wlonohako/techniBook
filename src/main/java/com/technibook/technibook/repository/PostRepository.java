package com.technibook.technibook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technibook.technibook.model.Post;
import com.technibook.technibook.model.PostVisibility;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByDeletedAtIsNullAndVisibility(PostVisibility visibility);
}
