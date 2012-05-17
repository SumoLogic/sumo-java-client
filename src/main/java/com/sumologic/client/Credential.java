package com.sumologic.client;

public class Credential {
  private String email;
  private String password;

  public Credential(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

}
