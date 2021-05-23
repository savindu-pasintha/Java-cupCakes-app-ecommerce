package com.example.lola;

public class userModel {
    private int id;
    private String name;
    private String pass;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }
}
