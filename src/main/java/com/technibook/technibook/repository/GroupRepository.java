package com.technibook.technibook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technibook.technibook.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findById(Integer id);
    
}
