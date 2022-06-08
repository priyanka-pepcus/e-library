package com.pepcus.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserCollection {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private String aadharNum;
	private String phoneNum;
	private String gender;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAadharNum() {
		return aadharNum;
	}
	public void setAadharNum(String aadharNum) {
		this.aadharNum = aadharNum;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public UserCollection(int id, String name, int age, String aadharNum, String phoneNum, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.aadharNum = aadharNum;
		this.phoneNum = phoneNum;
		this.gender = gender;
	}
	public UserCollection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
