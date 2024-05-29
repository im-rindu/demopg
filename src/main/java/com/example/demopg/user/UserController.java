package com.example.demopg.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1/users")
public class UserController {
  UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping
  public Iterable<User> GetAllUser(){
    return userService.getAllUser();
  }

  @PostMapping
  public User addUser(@Valid @RequestBody User user){
    return userService.addUser(user);
  }
}
