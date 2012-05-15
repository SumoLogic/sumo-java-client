package com.sumologic.client;

/**
 * Created by IntelliJ IDEA.
 * User: daphne
 * Date: 5/11/12
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class Credential {
  private String email;
  private String password;

  public void Credential(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public void setEmail(String email) {
    setEmailAndPassword(email, this.password);
  }

  public void setPassword(String password) {
    setEmailAndPassword(this.email, password);
  }

  public void setEmailAndPassword(String email, String password) {
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
