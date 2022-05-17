package com.pepcus.crud.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")

  private Integer id;

  @Column(name = "book_name")
  
  private String bookName;
  @NotNull
  @Column(name = "added_on")
  @NotNull
  private LocalDateTime addedOn = LocalDateTime.now();

  @Column(name = "modified_on")
  private Date modifiedOn;
  
  @Column(name = "issue_on") 
 private LocalDateTime issueOn;
  
  @Column(name = "return_on") 
  private LocalDateTime returnOn;
  
//  @ManyToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "book_id")
//  private User user;
  
  
//
//
//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(User user) {
//    this.user = user;
//  }

  public LocalDateTime getIssueOn() {
    return issueOn;
  }

  public void setIssueOn(LocalDateTime localDateTime) {
    this.issueOn = localDateTime;
  }

  public LocalDateTime getReturnOn() {
    return returnOn;
  }

  public void setReturnOn(LocalDateTime localDateTime) {
    this.returnOn = localDateTime;
  }



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public LocalDateTime getAddedOn() {
    return addedOn;
  }

  public void setAddedOn(LocalDateTime addedOn) {
    this.addedOn = addedOn;
  }

  public Date getModifiedOn() {
    return modifiedOn;
  }

  public void setModifiedOn(Date modifiedOn) {
    this.modifiedOn = modifiedOn;
  }

}
