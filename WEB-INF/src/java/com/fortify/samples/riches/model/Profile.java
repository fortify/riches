package com.fortify.samples.riches.model;

public class Profile
{
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String ssno;


    public Profile() {
    }

    public Profile(String email, String firstname, Long id, String lastname, String password, String ssno, String username) {
        this.email = email;
        this.firstname = firstname;
        this.id = id;
        this.lastname = lastname;
        this.password = password;
        this.ssno = ssno;
        this.username = username;
    }

    public Profile(String username, String password, String firstname, String lastname, String email, String ssno)
    {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.ssno = ssno;   
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getFirstname() {
      return firstname;
    }

    public void setFirstname(String firstname) {
      this.firstname = firstname;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getLastname() {
      return lastname;
    }

    public void setLastname(String lastname) {
      this.lastname = lastname;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getSsno() {
      return ssno;
    }

    public void setSsno(String ssno) {
      this.ssno = ssno;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

}
