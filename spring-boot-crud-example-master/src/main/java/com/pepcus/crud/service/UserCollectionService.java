package com.pepcus.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.crud.entity.UserCollection;
import com.pepcus.crud.repository.UserCollectionRepository;

@Service
public class UserCollectionService {
	@Autowired
	private UserCollectionRepository repository;
	
	List<UserCollection> list = new ArrayList<UserCollection>();

	public List<UserCollection> findAllUsersCollection() {
		 return list = repository.findAll();
	
	}

	public UserCollection saveUserCollection(UserCollection user) {
		// TODO Auto-generated method stub
		return null;
	}


	public UserCollection getUsersCollectionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserCollection updateUsersCollection(UserCollection userCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserCollection getUsersCollectionByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteUsersCollection(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	     

}
