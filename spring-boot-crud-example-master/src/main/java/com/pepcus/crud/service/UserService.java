package com.pepcus.crud.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.crud.entity.Book;
import com.pepcus.crud.entity.Employee;
import com.pepcus.crud.entity.Self;
import com.pepcus.crud.entity.User;
import com.pepcus.crud.exceptionhandler.ResourceNotFoundException;
import com.pepcus.crud.repository.BookRepository;
import com.pepcus.crud.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;

  public User saveUser(User user) {
    user.setRegistrationDate(LocalDateTime.now());
    return userRepository.save(user);
  }

  public String issueBook(int id, List<Book> requestedBookList) {

    Optional<User> existingUserOptional = userRepository.findById(id);
    // handle custom exception if userid not available
    if (!existingUserOptional.isPresent()) {
      throw new ResourceNotFoundException("Not found User with id = " + id);
    } else {
      User user = existingUserOptional.get();

      if (user.getDeactivationDate() == null) {
        // checks book presence in db
        for (Book requestedBook : requestedBookList) {
          List<Book> existingBookList = bookRepository.findAll();
          for (Book existingBook : existingBookList) {
            if (requestedBook.getId().equals(existingBook.getId())) {
              if (user.getBook().contains(existingBook) && (existingBook.getIssueOn() != null && (existingBook.getReturnOn() == null))
                  || (user.getBook().contains(existingBook) && existingBook.getIssueOn() != null && existingBook.getReturnOn() != null
                      && existingBook.getIssueOn().isAfter(existingBook.getReturnOn()))) {

                return "you already issued these books.";
              }

              else if ((existingBook.getIssueOn() == null && (existingBook.getReturnOn() == null))) {

                existingBook.setIssueOn(LocalDateTime.now());
                List<Book> bookList = user.getBook();
                bookList.add(existingBook);

              }

              else if ((!(user.getBook().contains(existingBook))) && (existingBook.getIssueOn() != null && (existingBook.getReturnOn() == null))
                  || (!(user.getBook().contains(existingBook)) && existingBook.getIssueOn() != null && existingBook.getReturnOn() != null
                      && existingBook.getIssueOn().isAfter(existingBook.getReturnOn()))) {

                return "This book is already issued to other user !!!";
              }

            }

          }

        }
        userRepository.save(user);
        return "Book issued succesfully !!!";
      }

      return "Book is not available in Library !!!";

    }
  }

  public String deregisterUserById(int id) {
    Optional<User> existingUser = userRepository.findById(id);
    // handle custom exception if userid not available
    if (!existingUser.isPresent()) {
      throw new ResourceNotFoundException("Not found User with id = " + id);
    }
    User user = existingUser.get();
    if (user.getBook().isEmpty()) {
      user.setDeactivationDate(LocalDateTime.now());
      userRepository.save(user);

      return "User Deactivated !!";

    }

    return "First return books then you can Deactivated !!!";

  }

  public String returnBook(int id, List<Book> requestedBookList) {

    Optional<User> existingUserOptional = userRepository.findById(id);
    // handle custom exception if userid not available
    if (!existingUserOptional.isPresent()) {
      throw new ResourceNotFoundException("Not found User with id = " + id);
    } else {
      User user = existingUserOptional.get();

      if (user.getDeactivationDate() == null) {
        // checks book presence in db
        for (Book requestedBook : requestedBookList) {
          List<Book> existingBookList = bookRepository.findAll();
          for (Book existingBook : existingBookList) {
            if (requestedBook.getId().equals(existingBook.getId())) {
              if (user.getBook().contains(existingBook) && (existingBook.getIssueOn() != null && (existingBook.getReturnOn() == null))
                  || (user.getBook().contains(existingBook) && existingBook.getIssueOn() != null && existingBook.getReturnOn() != null
                      && existingBook.getIssueOn().isAfter(existingBook.getReturnOn()))) {
                existingBook.setReturnOn(LocalDateTime.now());
                List<Book> bookList = user.getBook();
                bookList.remove(existingBook);

              } else if (user.getBook().isEmpty()) {
                return "You dont have these books or You didn't issued book, so you cant return !!!";
              }

              else if ((!(user.getBook().contains(existingBook))) && (existingBook.getIssueOn() != null && (existingBook.getReturnOn() == null))
                  || (!(user.getBook().contains(existingBook)) && existingBook.getIssueOn() != null && existingBook.getReturnOn() != null
                      && existingBook.getIssueOn().isAfter(existingBook.getReturnOn()))) {

                return "You dont have permission to return these books. These books are issued to other user !!!";
              }

            } 

          }
          
           // return "this book doesent belongs to you / library doesnt contain this book!!!";
          
        }
        userRepository.save(user);
        return "Book returned succesfully !!!";
      }

    }
    return "User is deactivated cant perform any action !!!";

  }

}
