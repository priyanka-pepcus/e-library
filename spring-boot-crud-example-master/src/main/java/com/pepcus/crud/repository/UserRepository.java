package com.pepcus.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepcus.crud.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

 

}
