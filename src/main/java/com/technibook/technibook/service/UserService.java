package com.technibook.technibook.service;

import com.technibook.technibook.api.UpdateUserRequest;
import com.technibook.technibook.model.User;
import com.technibook.technibook.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setPassword(null);
                    return user;
                })
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));

    }

    public User updateUser(Integer id, UpdateUserRequest updateUserRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
        updateUserRequest.getName().ifPresent(existingUser::setName);
        updateUserRequest.getSurname().ifPresent(existingUser::setSurname);
        updateUserRequest.getBirthDate().ifPresent(existingUser::setBirthDate);
        updateUserRequest.getSex().ifPresent(existingUser::setSex);

        return userRepository.save(existingUser);
    }
}
