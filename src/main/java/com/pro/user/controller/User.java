package com.pro.user.controller;

import com.pro.user.model.UserModel;
import com.pro.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class User {
    private UserRepository repository;

    @Autowired
    public void User(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getuser")
    public List<UserModel> getAllData() {

        return repository.findAll();
    }

    @PostMapping("/addUser")
    public UserModel createData(@RequestBody UserModel model) {

        UserModel savedUser = repository.save(model);
        log.debug("User saved successfully: {}", savedUser);
        return savedUser;
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteData(@PathVariable String id) {
        log.debug("Received request to delete user with ID: {}", id);
        repository.deleteById(id);
        log.debug("User with ID {} deleted successfully", id);
        return "User deleted successfully";
    }

    @PutMapping("/updateUser/{id}")
    public UserModel updateData(@PathVariable String id, @RequestBody UserModel updatedUser) {
        log.debug("Received request to update user with ID: {}", id);
        Optional<UserModel> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            UserModel existingUser = userOptional.get();
            existingUser.setName(updatedUser.getName()); // Update the user's name
            UserModel updatedUserModel = repository.save(existingUser);
            log.debug("User with ID {} updated successfully: {}", id, updatedUserModel);
            return updatedUserModel;
        } else {
            log.debug("User with ID {} not found", id);
            return null; // Return null or throw an exception indicating user not found
        }
    }
}
