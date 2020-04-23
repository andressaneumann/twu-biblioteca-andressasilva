package com.twu.biblioteca.Models;

public class Movie {

    private int id;
    private String name;
    private String director;
    private Integer rating;
    private Boolean isAvailableToBook;

    public Movie(int id, String name, String director, Integer rating, Boolean isAvailableToBook) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.rating = rating;
        this.isAvailableToBook = isAvailableToBook;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getAvailableToBook() {
        return isAvailableToBook;
    }

    public void setAvailableToBook(Boolean availableToBook) {
        isAvailableToBook = availableToBook;
    }
}
