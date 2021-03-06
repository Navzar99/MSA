package com.msa.app.services;

import com.msa.app.dtos.UserDTO;
import com.msa.app.entities.User;
import com.msa.app.repositories.UserRepository;
import com.msa.app.security.PasswordManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public Optional<User> editUser(UserDTO userDTO, Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
        {
            User newUser = user.get();
            newUser.setShopName(userDTO.shopName);
            newUser.setName(userDTO.name);
            newUser.setIsAdmin(userDTO.isAdmin);

            final PasswordManager passwordManager = new PasswordManager();
            final String hashedPassword = passwordManager.encodePassword(userDTO.password);
            newUser.setPassword(hashedPassword);

            return Optional.of(userRepository.save(newUser));
        }
        return Optional.empty();
    }
}
