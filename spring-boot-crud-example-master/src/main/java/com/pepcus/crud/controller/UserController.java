package com.pepcus.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.crud.entity.Book;
import com.pepcus.crud.entity.User;
import com.pepcus.crud.service.UserService;
/*
 * @authour Priyanka Gupta
 * @since 8/05/2022
 * 
 *
 * 
*/
@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @PostMapping("/register")
  public User saveUser(@RequestBody User user) {
    return userService.saveUser(user);
  }
  
  @PutMapping("/deregister/{userId}")
  public String deregisterUser(@PathVariable int userId) {
    return userService.deregisterUserById(userId);
  }
  
  @PatchMapping("/issueBook/{userId}")
  private String issueBook(@PathVariable int userId, @RequestBody List<Book> book) {
    return userService.issueBook(userId, book);
  }
  
  @PatchMapping("/returnBook/{userId}")
  public String returnBook(@PathVariable int userId, @RequestBody List<Book> book) {
    return userService.returnBook(userId, book);
  }

}
