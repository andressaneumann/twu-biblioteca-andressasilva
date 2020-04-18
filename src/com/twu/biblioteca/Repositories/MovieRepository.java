package com.twu.biblioteca.Repositories;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;

import java.util.ArrayList;

public class MovieRepository {

    ArrayList<Movie> movies;

    public MovieRepository(){
        getMovies();
    }

    public ArrayList<Movie> getMovies(){

        movies = new ArrayList<Movie>(5);

        Movie fightClub = new Movie(0, "Fight Club", "David Fincher", 10, true);
        Movie inception = new Movie(1, "Inception", "Christopher Nolan", 10, true);
        Movie pulpFiction = new Movie(2, "Pulp Fiction", "Quentin Tarantino", 10, true);
        Movie avatar = new Movie(3, "Avatar", "James Cameron", 7, true);
        Movie toyStory = new Movie(4, "Toy Story", "John Lasseter", 5, false);

        movies.add(fightClub);
        movies.add(inception);
        movies.add(pulpFiction);
        movies.add(avatar);
        movies.add(toyStory);

        return movies;
    }

    public void updateMovieAvailableStatus(int id, Boolean status) {

        for (Movie movie : movies) {
            if (movie.getId() == id)
                movie.setAvailable(status);
        }
    }

}
