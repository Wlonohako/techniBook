package com.technibook.technibook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technibook.technibook.api.AddGroupRequest;
import com.technibook.technibook.api.GroupRequest;
import com.technibook.technibook.model.Group;
import com.technibook.technibook.model.User;
import com.technibook.technibook.service.GroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        return ResponseEntity.ok(groupService.createGroup(request));
    }
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());}
    
    @PostMapping("/{groupId}/members")
    public ResponseEntity<AddGroupRequest> addMemberToGroup(@PathVariable Integer groupId,@RequestBody AddGroupRequest request) {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(groupService.addMemberToGroup(groupId,userId, request));}
}
