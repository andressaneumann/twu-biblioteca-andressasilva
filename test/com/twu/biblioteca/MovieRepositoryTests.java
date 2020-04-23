package com.twu.biblioteca;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Repositories.BookRepository;
import com.twu.biblioteca.Repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class MovieRepositoryTests {

    MovieRepository movieRepository;

    @Before
    public void setUp() {
        movieRepository = new MovieRepository();
    }

    @Test
    public void GetMovies_ComparingToAnEmptyList_AssertsNotEquals(){

        ArrayList<Movie> emptyMovieList = new ArrayList<Movie>();
        ArrayList<Movie> allMovies = movieRepository.getMovies();

        assertNotEquals(emptyMovieList, allMovies);
    }

    @Test
    public void updateMovieAvailableStatus_FromTrueToFalse_ReturnsFalse(){
        int movieCodeToBeUpdated = 0;
        Boolean newStatus = false;

        ArrayList<Movie> movies = movieRepository.getMovies();
        movieRepository.updateMovieAvailableStatus(movieCodeToBeUpdated, newStatus);

        assertThat(movies.get(movieCodeToBeUpdated).getAvailableToBook(), is(equalTo(false)));
    }

    @Test
    public void updateMovieAvailableStatus_FromFalseToTrue_ReturnsTrue(){
        int movieCodeToBeUpdated = 4;
        Boolean newStatus = true;

        ArrayList<Movie> movies = movieRepository.getMovies();
        movieRepository.updateMovieAvailableStatus(movieCodeToBeUpdated, newStatus);

        assertThat(movies.get(movieCodeToBeUpdated).getAvailableToBook(), is(equalTo(true)));
    }
}
