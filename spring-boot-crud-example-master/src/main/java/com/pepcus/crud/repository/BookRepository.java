package com.pepcus.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepcus.crud.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
