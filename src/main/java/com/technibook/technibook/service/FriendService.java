package com.technibook.technibook.service;

import com.technibook.technibook.model.Friend;
import com.technibook.technibook.model.User;
import com.technibook.technibook.repository.FriendRepository;
import com.technibook.technibook.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public Friend sendRequest(Integer userId, Integer friendId) {
        User requester = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        if (requester.getId().equals(friendId)) {
            throw new EntityNotFoundException("Cannot send friend request to yourself.");
        }

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        if (friendRepository.existsByUserAndFriend(requester, friend)) {
            throw new IllegalStateException("Friend request already sent.");
        }

        Friend request = Friend.builder()
                .user(requester)
                .friend(friend)
                .accepted(false)
                .build();

        return friendRepository.save(request);
    }

    public Friend acceptRequest(Integer userId, Integer requestId) {
        User recipient = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        Friend friendRequest = friendRepository.findByIdAndFriend(requestId, recipient)
                .orElseThrow(() -> new EntityNotFoundException("Friend request not found."));

        friendRequest.setAccepted(true);
        return friendRepository.save(friendRequest);
    }

    public List<Friend> getFriends(Integer userId) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return friendRepository.findByAcceptedIsTrueAndUserOrAcceptedIsTrueAndFriend(currentUser, currentUser);
    }
}
