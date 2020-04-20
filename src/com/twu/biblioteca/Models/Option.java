package com.twu.biblioteca.Models;

public class Option {

    private int id;
    private String name;
    private Boolean userNeedsToBeLoggedInToAccessOption;

    public Option(int id, String name, Boolean userNeedsToBeLoggedInToAccessOption) {
        this.id = id;
        this.name = name;
        this.userNeedsToBeLoggedInToAccessOption = userNeedsToBeLoggedInToAccessOption;
    }

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

    public Boolean getUserNeedsToBeLoggedInToAccessOption() {
        return userNeedsToBeLoggedInToAccessOption;
    }

    public void setUserNeedsToBeLoggedInToAccessOption(Boolean userNeedsToBeLoggedInToAccessOption) {
        this.userNeedsToBeLoggedInToAccessOption = userNeedsToBeLoggedInToAccessOption;
    }
}
