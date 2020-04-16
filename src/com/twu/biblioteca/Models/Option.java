package com.twu.biblioteca.Models;

public class Option {

    private int Id;
    private String Name;

    public int getId() {
        return Id;
    }

    public Option(int id, String name) {
        Id = id;
        Name = name;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
