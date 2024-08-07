package com.sdms.service;


import com.sdms.entity.UserDetails;
import com.sdms.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	

    @Autowired
    private UserRepository userRepository;

    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDetails> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public UserDetails saveUser(UserDetails user) {
    	user.setStatus("active");
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    public void uploadUsers() {
        
    }
}