package com.pepcus.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.crud.entity.Book;
import com.pepcus.crud.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
  private BookService bookService;
  
//  @PostMapping("/addBook")
//  public Book saveBook(@RequestBody Book book) {
//    return bookService.saveBook(book);
//    
//  }
//  @PostMapping("/addAllBook")
//  public List<Book> saveAll(@RequestBody List<Book> bookList){
//    return bookService.saveAll(bookList);
//    
//  }
//  
  @PatchMapping("/issueBook/{userId}")
  private String saveIssueBook(@PathVariable int userId, @RequestBody List<Book> book) {
    System.out.println("controllerrrrrr   "+userId);
    System.out.println("controllerrrrrr   "+book.get(0).getBookName());
     System.out.println("controllerrrrrr   "+book.get(0).getBookName());
    return bookService.saveIssueBook(userId, book);
  }

}
