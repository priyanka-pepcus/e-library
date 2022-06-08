package com.pepcus.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int addressId;

  @Size(min = 2, message = "City should have atleast 2 characters")
  private String city;

  @Size(min = 2, message = "State should have atleast 2 characters")
  private String state;

  private String pincode;

  public int getId() {
    return addressId;
  }

  public void setId(int id) {
    this.addressId = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPincode() {
    return pincode;
  }

  public void setPincode(String pincode) {
    this.pincode = pincode;
  }

}
