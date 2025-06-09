package com.technibook.technibook.api;

import com.technibook.technibook.model.Friend;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendResponse {
    private Integer id;
    private String Name;
    private LocalDateTime acceptedAt;

    public static FriendResponse fromEntity(Friend friend) {
        FriendResponse api = new FriendResponse();
        api.setId(friend.getId());

        if (friend.getUser().getId().equals(friend.getFriend().getId())) {
            api.setName(friend.getUser().getName());
        } else {
            api.setName(friend.getUser().getName().equals("testuser")
                ? friend.getFriend().getName()
                : friend.getUser().getName());
        }

        api.setAcceptedAt(friend.getAcceptedAt());
        return api;
    }
}
