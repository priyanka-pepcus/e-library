package com.pepcus.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.crud.entity.Book;
import com.pepcus.crud.entity.Self;
import com.pepcus.crud.exceptionhandler.ResourceNotFoundException;
import com.pepcus.crud.repository.SelfRepository;

@Service
public class SelfService {
  @Autowired
  private SelfRepository selfRepository;

  public Self saveSelf(Self self) {
    return selfRepository.save(self);
  }

  public Self saveBook(int id, List<Book> book) {
    Optional<Self> existingSelfOptional = selfRepository.findById(id);
    if (!existingSelfOptional.isPresent()) {
      throw new ResourceNotFoundException("Not found Self with id = " + id);
    }
    Self existingSelf = existingSelfOptional.get();
    List<Book> bookList = existingSelf.getBook();
    book.forEach(newBook -> bookList.add(newBook));
    existingSelf.setBook(bookList);
    return selfRepository.save(existingSelf);
  }

}
