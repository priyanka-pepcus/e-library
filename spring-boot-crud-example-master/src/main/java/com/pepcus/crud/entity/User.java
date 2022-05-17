package com.pepcus.crud.entity;

import java.sql.Date;
import java.time.LocalDateTime;
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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  
  private Integer userId;

  @Column(name = "user_name")
  @NotNull
  private String name;

  @Column(name = "registration_Date")
  @NotNull
  private LocalDateTime registrationDate;

  @Column(name = "deactivation_date")
  @JsonIgnore
  private LocalDateTime deactivationDate;
  
//  @Column(name = "status", columnDefinition = "tinyint(1) default true")
//  @NotNull
//  private boolean active = true;
 
  @OneToMany(cascade = CascadeType.ALL)
  //@JoinColumn(name = "user_id")
 // @JsonIgnore
  private List<Book> book;
  
 

//  @Column(name = "issued_on")
//  @JsonIgnore
//  private LocalDateTime issuedOn;
  
 
//
//  public LocalDateTime getIssuedOn() {
//    return issuedOn;
//  }
//
//  public void setIssuedOn(LocalDateTime issuedOn) {
//    this.issuedOn = issuedOn;
//  }



//  public boolean isActive() {
//    return active;
//  }
//
//  public void setActive(boolean active) {
//    this.active = active;
//  }

  public List<Book> getBook() {
    return book;
  }

  public void setBook(List<Book> book) {
    this.book = book;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



  public LocalDateTime getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDateTime registrationDate) {
    this.registrationDate = registrationDate;
  }

  public LocalDateTime getDeactivationDate() {
    return deactivationDate;
  }

  public void setDeactivationDate(LocalDateTime deactivationDate) {
    this.deactivationDate = deactivationDate;
  }



}
