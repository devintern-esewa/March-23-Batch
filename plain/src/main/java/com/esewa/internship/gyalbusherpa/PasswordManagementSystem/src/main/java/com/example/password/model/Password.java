package com.esewa.internship.gyalbusherpa.PasswordManagementSystem.src.main.java.com.example.password.model;

public class Password {

    private int id;
    private String accountName;
    private String userName;
    private String email;
    private String password;

    public Password() {
        super();
    }

    public Password(int id, String accountName, String userName, String email, String password) {
        super();
        this.id = id;
        this.accountName = accountName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Password(String accountName, String userName, String email, String password) {
        super();
        this.accountName = accountName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
