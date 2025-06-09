package com.technibook.technibook.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.technibook.technibook.model.Group;
import com.technibook.technibook.repository.GroupRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
    
    private final GroupRepository groupRepository;

    public Group findById(Integer id) {
    if (id == null) return null;
    return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Group not found"));
}
}
