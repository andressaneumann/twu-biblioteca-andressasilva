package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Repositories.BookRepository;
import com.twu.biblioteca.Repositories.MovieRepository;

import java.util.ArrayList;

public class MediaController {

    BookRepository bookRepository = new BookRepository();
    ArrayList<Book> books = bookRepository.getBooks();

    MovieRepository movieRepository = new MovieRepository();
    ArrayList<Movie> movies = movieRepository.getMovies();

    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<Book>();

        for (int i = 0; i < books.size(); i++){
            if(books.get(i).getAvailableToBook().equals(true))
                availableBooks.add(books.get(i));
        }

        return availableBooks;
    }

    public ArrayList<Movie> getAvailableMovies() {
        ArrayList<Movie> availableMovies = new ArrayList<Movie>();

        for (int i = 0; i < movies.size(); i++){
            if(movies.get(i).getAvailableToBook().equals(true))
                availableMovies.add(movies.get(i));
        }

        return availableMovies;
    }

    public ArrayList<Book> getCheckedOutBooks(){
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

        for (int i = 0; i < books.size(); i++){
            if(books.get(i).getAvailableToBook().equals(false))
                checkedOutBooks.add(books.get(i));
        }

        return checkedOutBooks;
    }

    public ArrayList<Movie> getCheckedOutMovies(){
        ArrayList<Movie> checkedOutMovies = new ArrayList<Movie>();

        for (int i = 0; i < movies.size(); i++){
            if(movies.get(i).getAvailableToBook().equals(false))
                checkedOutMovies.add(movies.get(i));
        }

        return checkedOutMovies;
    }


    public Boolean checkingOutBook(int bookCodeToCheckout, Integer loggedInUserId) {

        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == bookCodeToCheckout && books.get(i).getAvailableToBook() == true){
                bookRepository.updateBookAvailableStatus(bookCodeToCheckout, false);
                bookRepository.updateUserIdOfBooker(bookCodeToCheckout, loggedInUserId);

                return true;
            }
        }
        return false;
    }

    public void returningBook(int checkedOutBookCode) {
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == checkedOutBookCode)
                bookRepository.updateBookAvailableStatus(checkedOutBookCode, true);
        }
    }

    public Boolean checkingOutMovie(int movieCodeToCheckout) {

        for(int i = 0; i < movies.size(); i++){
            if(movies.get(i).getId() == movieCodeToCheckout && movies.get(i).getAvailableToBook() == true) {
                movieRepository.updateMovieAvailableStatus(movieCodeToCheckout, false);
                return true;
            }
        }

        return false;
    }

}
