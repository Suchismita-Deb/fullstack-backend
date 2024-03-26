package com.example.fullstackbackend.controller;

import com.example.fullstackbackend.exception.UserNotFoundException;
import com.example.fullstackbackend.model.Users;
import com.example.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/user")
    public Users addUser(@RequestBody Users newUsers){
        return userRepository.save(newUsers);
    }
    @GetMapping("/allUsers")
    public List<Users> allUsers(){
        return userRepository.findAll();
    }
    @GetMapping("user/{id}")
    Users getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("user/{id}")
    Users updateUser(@RequestBody Users newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user->{
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setUsername(newUser.getUsername());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" has been deleted successfully.";
    }
}
