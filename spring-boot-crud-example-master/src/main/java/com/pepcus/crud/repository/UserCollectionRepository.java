package com.pepcus.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepcus.crud.entity.UserCollection;

public interface UserCollectionRepository extends JpaRepository<UserCollection, Integer>{

}
