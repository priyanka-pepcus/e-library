package com.pepcus.crud.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.crud.entity.Book;
import com.pepcus.crud.entity.User;
import com.pepcus.crud.exceptionhandler.ResourceNotFoundException;
import com.pepcus.crud.repository.BookRepository;
import com.pepcus.crud.repository.UserRepository;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private UserRepository userRepository;

  public String saveIssueBook(int userId, List<Book> requestedBook) {
    Optional<User> existingUserOptional = userRepository.findById(userId);
    if (!existingUserOptional.isPresent()) {
      throw new ResourceNotFoundException("Not found User with id = " + userId);
    }
    List<Book> existingBookList = bookRepository.findAll();
    for (Book one : requestedBook) {
      for (Book two : existingBookList) {
        if (one.getBookName().equals(two.getBookName())) {
          User user = existingUserOptional.get();
          one.setIssueOn(LocalDateTime.now());

          List<Book> bookList = new ArrayList<>();
          bookList.add(one);
          user.setBook(bookList);
          bookRepository.save(one);
          userRepository.save(user);
          return "Book has been issued successfully !!!";

        }
      }
    }
    return "Some error Occured !!!";
  }

}
