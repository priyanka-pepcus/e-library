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

  public String issueBook(int id, List<Book> requestedBook) {

    Optional<User> existingUserOptional = userRepository.findById(id);
    // handle custom exception if userid not available
    if (!existingUserOptional.isPresent()) {
      throw new ResourceNotFoundException("Not found User with id = " + id);
    } else {
      User user = existingUserOptional.get();

      if (user.getDeactivationDate() == null) {
        // checks book presence in db
        for (Book one : requestedBook) {
          List<Book> existingBookList = bookRepository.findAll();
          for (Book two : existingBookList) {
            if (one.getId().equals(two.getId())) {
              if (user.getBook().contains(two)) {

                return "you already issued these books.";
              } else if (two.getIssueOn() == null & two.getReturnOn() == null) {
                two.setIssueOn(LocalDateTime.now());
                List<Book> bookList = user.getBook();
                bookList.add(two);
                userRepository.save(user);
                return "Book issued succesfully !!!";

              } else if ((two.getIssueOn() != null & two.getReturnOn() == null)
                  || ((two.getIssueOn() != null & two.getReturnOn() != null)
                      & two.getIssueOn().isAfter(two.getReturnOn()))) {
                return "This book is already issued to other user !!!";
              }

            }
          }

        }

      }

    }
    return "Something went wrong !!!";

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

  public String returnBook(int userId, List<Book> returnBook) {
    Optional<User> existingUserOptional = userRepository.findById(userId);
    // handle custom exception if userid not available
    if (!existingUserOptional.isPresent()) {
      throw new ResourceNotFoundException("Not found User with id = " + userId);
    } else {
      User user = existingUserOptional.get();

      if (user.getDeactivationDate() == null & (!user.getBook().isEmpty())) {
        List<Book> existingBookList = user.getBook();
        for (Book one : existingBookList) {
          for (Book two : returnBook) {
            if (one.getId().equals(two.getId())) {
              if (user.getBook().contains(two)) {
                two.setReturnOn(LocalDateTime.now());
                List<Book> bookList = user.getBook();
                bookList.remove(two);
                userRepository.save(user);

              }

            }
          }

        }
        return "Book Returned succesfully !!!";
      }
    }
    return "you dont have these books.";

  }

}
