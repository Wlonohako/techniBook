package com.technibook.technibook.service;

import org.springframework.stereotype.Service;

import com.technibook.technibook.api.AddGroupRequest;
import com.technibook.technibook.api.GroupRequest;
import com.technibook.technibook.model.Group;
import com.technibook.technibook.model.Member;
import com.technibook.technibook.model.User;
import com.technibook.technibook.repository.GroupRepository;
import com.technibook.technibook.repository.MemberRepository;
import com.technibook.technibook.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public Group findById(Integer id) {
    if (id == null) return null;
    return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Group not found"));
}

    public Group createGroup(GroupRequest request) {
        Group group = new Group();
        group.setTitle(request.getTitle());
        group.setDescription(request.getDescription().orElse(null));
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public AddGroupRequest addMemberToGroup(Integer groupId,Integer userId, AddGroupRequest request) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (memberRepository.existsByUserIdAndGroupId(request.getUserId(), groupId)) {
            throw new IllegalStateException("User is already a member of the group");
        }

        Member member = Member.builder()
                .user(user)
                .group(group)
                .permission(request.getPermission())
                .build();
        memberRepository.save(member);
        
        return request;
    }
}
