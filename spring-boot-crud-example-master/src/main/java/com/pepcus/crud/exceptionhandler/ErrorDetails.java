package com.pepcus.crud.exceptionhandler;

import java.util.Date;
import java.util.Map;

public class ErrorDetails {
  private int statusCode;
  private Date timestamp;
  private String message;
  private Map<String, String> details;
  private String description;

  public ErrorDetails(Date date, String message, Map<String, String> errorMap) {
    super();
    this.timestamp = date;
    this.message = message;
    this.details = errorMap;
  }

  public ErrorDetails(int statusCode, Date timestamp, String message, String description) {
    super();
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ErrorDetails(Date date, String message2, String description) {
    // TODO Auto-generated constructor stub
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, String> getDetails() {
    return details;
  }

  public void setDetails(Map<String, String> details) {
    this.details = details;
  }

}
