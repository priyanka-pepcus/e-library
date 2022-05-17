package com.pepcus.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.crud.entity.Book;
import com.pepcus.crud.entity.Self;
import com.pepcus.crud.service.SelfService;
@RestController
@RequestMapping("/shelves")
public class SelfController {
  
  @Autowired
  private SelfService selfService;
  
  @PostMapping("/addSelf")
   public Self saveSelf(@RequestBody Self self) {
   return selfService.saveSelf(self);
   }
  @PatchMapping("/assignBook/{id}")
  public Self saveBook(@PathVariable int id,@RequestBody List<Book> book) {
    return selfService.saveBook(id, book);
    
  }
  
  
  
}
