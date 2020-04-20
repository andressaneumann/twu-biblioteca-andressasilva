package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MediaController;
import com.twu.biblioteca.Controllers.MenuController;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Repositories.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Random;

public class MenuControllerTests {

    MenuController menuController;
    MediaController mediaController;
    Random random;

    @Before
    public void SetUp(){
        menuController = new MenuController();
        mediaController = new MediaController();
        random = new Random();
    }

    @Test
    public void checkingIfMenuInputIsValid_WhenCorrectAnswer_ReturnsTrue(){
        Boolean response = menuController.checkingIfMenuInputIsValid(1);

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void checkingIfMenuInputIsValid_WhenWrongAnswer_ReturnsFalse(){
        Boolean response = menuController.checkingIfMenuInputIsValid(8);

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAValidBookToCheckoutIsSend_ReturnsTrue(){
        int bookCode = 1;
        Boolean isAvailable = true;

        Boolean response = menuController.checkingIfMediaCodeIsValid(bookCode, isAvailable, Book.class);

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAValidMovieToCheckoutIsSend_ReturnsTrue(){
        int movieCode = 1;
        Boolean isAvailable = true;

        Boolean response = menuController.checkingIfMediaCodeIsValid(movieCode, isAvailable, Movie.class);

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAInvalidBookToCheckoutIsSend_ReturnsFalse(){
        int bookCode = 10;
        Boolean isAvailable = true;

        Boolean response = menuController.checkingIfMediaCodeIsValid(bookCode, isAvailable, Book.class);

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAInvalidMovieToCheckoutIsSend_ReturnsFalse(){
        int movieCode = 10;
        Boolean isAvailable = true;

        Boolean response = menuController.checkingIfMediaCodeIsValid(movieCode, isAvailable, Movie.class);

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAValidBookToReturnIsSend_ReturnsTrue(){
        int bookCode = 4;
        Boolean isAvailable = false;

        Boolean response = menuController.checkingIfMediaCodeIsValid(bookCode, isAvailable, Book.class);

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAValidMovieToReturnIsSend_ReturnsTrue(){
        int movieCode = 4;
        Boolean isAvailable = false;

        Boolean response = menuController.checkingIfMediaCodeIsValid(movieCode, isAvailable, Movie.class);

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAInvalidBookToReturnIsSend_ReturnsFalse(){
        int bookCode = 15;
        Boolean isAvailable = false;

        Boolean response = menuController.checkingIfMediaCodeIsValid(bookCode, isAvailable, Book.class);

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void checkingIfMediaCodeIsValid_WhenAInvalidMovieToReturnIsSend_ReturnsFalse(){
        int movieCode = 15;
        Boolean isAvailable = false;

        Boolean response = menuController.checkingIfMediaCodeIsValid(movieCode, isAvailable, Movie.class);

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void listMessage_WhenAnEmptyBookListIsSend_ReturnsNothingToShow(){
        ArrayList<Book> emptyList = new ArrayList<Book>();
        String messageAction = "books:";
        String expectedOutput = "List of books:\nNothing to show. List empty!";

        String realOutput = menuController.listMessage(emptyList, Book.class, messageAction, true);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void listMessage_WhenAnEmptyMovieListIsSend_ReturnsNothingToShow(){
        ArrayList<Movie> emptyList = new ArrayList<Movie>();
        String messageAction = "movies:";
        String expectedOutput = "List of movies:\nNothing to show. List empty!";

        String realOutput = menuController.listMessage(emptyList, Movie.class, messageAction, true);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void listMessage_WhenAValidAvailableBookListIsSend_ReturnsBooksAvailableToCheckout(){
        ArrayList<Book> availableBooks = mediaController.getAvailableBooks();
        String messageAction = "books:";
        String expectedOutput = "List of books:\nBook Code: 0 | Title: Hamlet | Author: William Shakespeare | Publication Year: 1603\n" +
                "Book Code: 1 | Title: Harry Potter and the Order of the Phoenix | Author: J. K. Rowling | Publication Year: 2003\n" +
                "Book Code: 2 | Title: The Silent Patient | Author: Alex Michaelides | Publication Year: 2019\n" +
                "Book Code: 3 | Title: The Alchemist | Author: Paulo Coelho | Publication Year: 1988\n";

        String realOutput = menuController.listMessage(availableBooks,Book.class, messageAction, true);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void listMessage_WhenAValidAvailableMovieListIsSend_ReturnsMoviesAvailableToCheckout(){
        ArrayList<Movie> availableMovies = mediaController.getAvailableMovies();
        String messageAction = "movies:";
        String expectedOutput = "List of movies:\n" +
                "Movie Code: 0 | Name: Fight Club | Director: David Fincher | Rating: 10\n" +
                "Movie Code: 1 | Name: Inception | Director: Christopher Nolan | Rating: 10\n" +
                "Movie Code: 2 | Name: Pulp Fiction | Director: Quentin Tarantino | Rating: 10\n" +
                "Movie Code: 3 | Name: Avatar | Director: James Cameron | Rating: 7\n";

        String realOutput = menuController.listMessage(availableMovies,Movie.class, messageAction, true);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void listMessage_WhenAValidAvailableCheckoutBookListIsSend_ReturnsBooksToReturn(){
        ArrayList<Book> checkedOutBooks = mediaController.getCheckedOutBooks();
        String messageAction = "current checked out books: ";
        String expectedOutput = "List of current checked out books: \n" +
                "Book Code: 4 | Title: The Great Gatsby | Author: Scott Fitzgerald | Publication Year: 1925\n";

        String realOutput = menuController.listMessage(checkedOutBooks,Book.class, messageAction, false);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void checkoutProcess_WhenAValidBookCodeIsSend_ChecksOutTheBook(){
        int bookCodeToCheckOut = 0;
        String expectedOutput = "Thank you! Enjoy the book!";

        String realOutput = menuController.checkoutProcess(bookCodeToCheckOut, Book.class);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void checkoutProcess_WhenAnInvalidBookCodeIsSend_CheckoutFails(){
        int bookCodeToCheckOut = 10;
        String expectedOutput = "Sorry, that book is not available!";

        String realOutput = menuController.checkoutProcess(bookCodeToCheckOut, Book.class);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void returningBookProcess_WhenAValidBookCodeIsSend_ReturnsTheBook(){
        int bookCodeToReturn = 4;
        String expectedOutput = "Thank you for returning the book!";

        String realOutput = menuController.returningBookProcess(bookCodeToReturn);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void returningBookProcess_WhenAInvalidBookCodeIsSend_ReturnFails(){
        int bookCodeToReturn = 15;
        String expectedOutput = "That is not a valid book to return.";

        String realOutput = menuController.returningBookProcess(bookCodeToReturn);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void checkingIfListIsEmpty_SendingAEmptyList_ReturnsTrue(){
        ArrayList<Book> emptyList = new ArrayList<Book>();

        Boolean output = menuController.checkingIfListIsEmpty(emptyList);

        assertThat(output, is(equalTo(true)));
    }

    @Test
    public void checkingIfListIsEmpty_SendingAPopulatedList_ReturnsFalse(){
        ArrayList<Book> populatedList = mediaController.getAvailableBooks();

        Boolean output = menuController.checkingIfListIsEmpty(populatedList);

        assertThat(output, is(equalTo(false)));
    }

    @Test
    public void returnStringOfMenuOptions_IfUserIsLoggedIn_ReturnsAllOptions(){
        Boolean isUseLoggedIn = true;

        String menuOptions = menuController.returnStringOfMenuOptions(isUseLoggedIn);
        String expectedMenuOptions = "1. Login\n2. List of Books\n3. Checkout a Book\n" +
                "4. Return a Book\n5. List of Movies\n6. Checkout a Movie\n7. Exit program\n";

        assertThat(menuOptions, is(equalTo(expectedMenuOptions)));
    }

    @Test
    public void returnStringOfMenuOptions_IfUserIsLoggedOut_ReturnsFewerOptions(){
        Boolean isUseLoggedIn = false;

        String menuOptions = menuController.returnStringOfMenuOptions(isUseLoggedIn);
        String expectedMenuOptions = "\nIn order to checkout and return books you need to be logged in!\n1. Login\n2. List of Books\n5. List of Movies\n6. Checkout a Movie\n7. Exit program\n";

        assertThat(menuOptions, is(equalTo(expectedMenuOptions)));
    }

    @Test
    public void returnListOfBooks_RequestingForAnAvailableBooksList_ReturnsAStringWithAllAvailableBooks(){
        Boolean isAvailable = true;
        String expectedOutput = "Book Code: 0 | Title: Hamlet | Author: William Shakespeare | Publication Year: 1603\n" +
                "Book Code: 1 | Title: Harry Potter and the Order of the Phoenix | Author: J. K. Rowling | Publication Year: 2003\n" +
                "Book Code: 2 | Title: The Silent Patient | Author: Alex Michaelides | Publication Year: 2019\n" +
                "Book Code: 3 | Title: The Alchemist | Author: Paulo Coelho | Publication Year: 1988\n";

        String realOutput = menuController.returnListOfMedia(isAvailable, Book.class);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void returnListOfBooks_RequestingForCheckedOutBooks_ReturnsAStringWithAllCheckedOutBooks(){
        Boolean isAvailable = false;
        String expectedOutput = "Book Code: 4 | Title: The Great Gatsby | Author: Scott Fitzgerald | Publication Year: 1925\n";

        String realOutput = menuController.returnListOfMedia(isAvailable, Book.class);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void GettingErrorMessageWhenInvalidOptionChosen_WhenCalled_ReturnsMessage(){
        String errorMessage = menuController.GettingErrorMessageWhenInvalidOptionChosen();
        String actualErrorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        assertThat(errorMessage, is(equalTo(actualErrorMessage)));
    }

}


