package com.tech.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.entity.User;
import com.tech.exception.ResourceNotFoundException;
import com.tech.repository.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
		return user;
    }
}
