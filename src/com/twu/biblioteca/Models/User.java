package com.twu.biblioteca.Models;

public class User {

    private int id;
    private String libraryNumber;
    private String name;
    private String password;

    public User(int id, String libraryNumber, String name, String password) {
        this.id = id;
        this.libraryNumber = libraryNumber;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
