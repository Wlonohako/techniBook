package com.technibook.technibook.controller;

import com.technibook.technibook.api.FriendRequest;
import com.technibook.technibook.api.FriendResponse;
import com.technibook.technibook.service.FriendService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;


    @PostMapping("/request")
    public ResponseEntity<String> sendRequest(@RequestBody FriendRequest requestDto) {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendService.sendRequest(userId, requestDto.getFriendId());
        return ResponseEntity.ok("Friend request sent");
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptRequest(@RequestBody FriendRequest requestDto) {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendService.acceptRequest(userId, requestDto.getRequestId());
        return ResponseEntity.ok("Friend request accepted");
    }

    @GetMapping
    public List<FriendResponse> getFriends() {
        Integer userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return friendService.getFriends(userId).stream()
                .map(FriendResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
