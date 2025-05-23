package com.example.java_project;

public class User {
    private int id;
    private String userName;
    private String passWord;


    @Override
    public String toString(){
        return "Id: " + id + ", user name: " + userName + ", password: " + passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
