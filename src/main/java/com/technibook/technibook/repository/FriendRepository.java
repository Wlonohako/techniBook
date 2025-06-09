package com.technibook.technibook.repository;

import com.technibook.technibook.model.Friend;
import com.technibook.technibook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
    boolean existsByUserAndFriend(User user, User friend);
    Optional<Friend> findByIdAndFriend(Integer id, User friend);
    List<Friend> findByAcceptedIsTrueAndUserOrAcceptedIsTrueAndFriend(User user1, User user2);
}
