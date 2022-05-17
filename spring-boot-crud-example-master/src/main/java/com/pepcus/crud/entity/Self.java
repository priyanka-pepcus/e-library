package com.pepcus.crud.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "self")
public class Self {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "self_id")
//  @NotNull
  private int selfId;

  @NotNull
  @Column(name = "self_name")
  private String selfName;

  @OneToMany(cascade = CascadeType.ALL)
 
 private List<Book> book;

  public int getSelfId() {
    return selfId;
  }

  public void setSelfId(int selfId) {
    this.selfId = selfId;
  }

  public String getSelfName() {
    return selfName;
  }

  public void setSelfName(String selfName) {
    this.selfName = selfName;
  }

  public List<Book> getBook() {
    return book;
  }

  public void setBook(List<Book> book) {
    this.book = book;
  }
  
  

}
