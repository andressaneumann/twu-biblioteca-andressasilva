package com.twu.biblioteca.Models;

public class Movie {

    private int id;
    private String name;
    private String director;
    private int rating;
    private Boolean isAvailable;

    public Movie(int id, String name, String director, int rating, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.rating = rating;
        this.isAvailable = isAvailable;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
