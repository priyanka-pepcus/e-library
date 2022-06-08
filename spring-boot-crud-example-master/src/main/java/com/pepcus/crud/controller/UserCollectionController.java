package com.pepcus.crud.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.pepcus.crud.entity.UserCollection;

import com.pepcus.crud.service.UserCollectionService;

@RestController
@RequestMapping("/userCollection")
public class UserCollectionController {
	 @Autowired
	  private UserCollectionService service;

	

	  @PostMapping("/addUserCollection")
	  public UserCollection addUser(@RequestBody UserCollection user) {
	    return service.saveUserCollection(user);
	  }


	  @GetMapping("/")
	  public List<UserCollection> findAllUser() {
	    return service.findAllUsersCollection();
	  }

	  @GetMapping("/{id}")
	  public UserCollection findUserById(@PathVariable int id) {
	    return service.getUsersCollectionById(id);
	  }

	  @GetMapping("/{name}")
	  public UserCollection findUserByName(@PathVariable String name) {
	    return service.getUsersCollectionByName(name);
	  }

	  @PutMapping("/")
	  public UserCollection updateUser(@RequestBody UserCollection userCollection) {
	    return service.updateUsersCollection(userCollection);
	  }

	  @DeleteMapping("/{id}")
	  public String deleteUser(@PathVariable int id) {
	    return service.deleteUsersCollection(id);
	  }

}
