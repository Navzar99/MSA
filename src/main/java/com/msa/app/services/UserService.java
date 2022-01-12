package com.msa.app.services;

import com.msa.app.dtos.UserDTO;
import com.msa.app.entities.User;
import com.msa.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// actions for users
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(UserDTO userDTO) {
        return userRepository.save(userDTO.mapToUser());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UserDTO userDTO) {
        userRepository.delete(userDTO.mapToUser());
    }
}
