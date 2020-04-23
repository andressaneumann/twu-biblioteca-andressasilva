package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MediaController;
import com.twu.biblioteca.Controllers.MenuController;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Models.Option;
import com.twu.biblioteca.Models.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotEquals;
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
        Boolean response = menuController.checkingIfMenuInputIsValid(12);

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
    public void gettingUserInformationFromId_SendingAValidUserId_ReturnsUser(){
        int userId = 0;
        User emptyUser = new User();
        User user = menuController.gettingUserInformationFromId(userId);

        assertNotEquals(user, emptyUser);
    }

    @Test
    public void returnMediaType_SendingBookClass_ReturnsStringBook(){
        String output = menuController.returnMediaType(Book.class);

        assertThat(output, is(equalTo("Book")));
    }

    @Test
    public void currentOptionSelected_SendingFirstOption_ReturnsFirstOption(){
        Option currentOption = menuController.currentOptionSelected(1);

        assertThat(currentOption.getId(), is(equalTo(1)));
    }

    @Test
    public void returnMediaType_SendingMovieClass_ReturnsStringMovie(){
        String output = menuController.returnMediaType(Movie.class);

        assertThat(output, is(equalTo("Movie")));
    }

    @Test
    public void gettingUserInformationFromId_SendingAnInvalidUserId_ReturnsEmptyUser(){
        int userId = 89;
        User user = menuController.gettingUserInformationFromId(userId);

        assertThat(user, is(equalTo(null)));
    }

    @Test
    public void generatingStringListOfMedia_SendingAValidAvailableToCheckoutBookList_ReturnsAvailableBooksToCheckout(){
        ArrayList<Book> availableBooks = mediaController.getAvailableBooks();
        String realOutput = menuController.generatingStringListOfMedia(availableBooks, Book.class, true, "books to checkout:");

        String expectedOutput = "List of books to checkout:\nBook Code: 0 | Title: Hamlet | Author: William Shakespeare | Publication Year: 1603\n" +
                "Book Code: 1 | Title: Harry Potter and the Order of the Phoenix | Author: J. K. Rowling | Publication Year: 2003\n" +
                "Book Code: 2 | Title: The Silent Patient | Author: Alex Michaelides | Publication Year: 2019\n" +
                "Book Code: 3 | Title: The Alchemist | Author: Paulo Coelho | Publication Year: 1988\n";

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void generatingStringListOfMedia_SendingAEmptyList_ReturnsNothingToShow(){
        ArrayList<Book> emptyList = new ArrayList<Book>();
        String realOutput = menuController.generatingStringListOfMedia(emptyList, Book.class, true, "books to checkout:");

        String expectedOutput = "Nothing to show, list is empty!";

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
    public void returnListOfMedia_RequestingForAnAvailableBooksList_ReturnsAStringWithAllAvailableBooks(){
        Boolean isAvailable = true;
        String expectedOutput = "Book Code: 0 | Title: Hamlet | Author: William Shakespeare | Publication Year: 1603\n" +
                "Book Code: 1 | Title: Harry Potter and the Order of the Phoenix | Author: J. K. Rowling | Publication Year: 2003\n" +
                "Book Code: 2 | Title: The Silent Patient | Author: Alex Michaelides | Publication Year: 2019\n" +
                "Book Code: 3 | Title: The Alchemist | Author: Paulo Coelho | Publication Year: 1988\n";

        String realOutput = menuController.checkingListTypeAndReturningRespectiveData(isAvailable, Book.class);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void returnListOfMedia_RequestingForCheckedOutBooks_ReturnsAStringWithAllCheckedOutBooks(){
        Boolean isAvailable = false;
        String expectedOutput = "Book Code: 4 | Title: The Great Gatsby | Author: Scott Fitzgerald | Publication Year: 1925\n";

        String realOutput = menuController.checkingListTypeAndReturningRespectiveData(isAvailable, Book.class);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void returnListOfMedia_RequestingForAnAvailableMovieList_ReturnsAStringWithAllAvailableMovies(){
        Boolean isAvailable = true;
        String expectedOutput = "Movie Code: 0 | Name: Fight Club | Director: David Fincher | Rating: 10\n" +
                "Movie Code: 1 | Name: Inception | Director: Christopher Nolan | Rating: 10\n" +
                "Movie Code: 2 | Name: Pulp Fiction | Director: Quentin Tarantino | Rating: 10\n" +
                "Movie Code: 3 | Name: Avatar | Director: James Cameron | Rating: Unrated\n";

        String realOutput = menuController.checkingListTypeAndReturningRespectiveData(isAvailable, Movie.class);

        assertThat(expectedOutput, is(equalTo(realOutput)));
    }

    @Test
    public void checkMovieRating_SendingANullEntry_ReturnsUnrated(){
        Integer rating = null;
        String output = menuController.checkMovieRating(rating);

        assertThat(output, is(equalTo("Unrated")));
    }

    @Test
    public void checkMovieRating_SendingAValidEntry_ReturnsEntry(){
        Integer rating = 7;
        String output = menuController.checkMovieRating(rating);

        assertThat(output, is(equalTo("7")));
    }

    @Test
    public void GettingErrorMessageWhenInvalidOptionChosen_WhenCalled_ReturnsMessage(){
        String errorMessage = menuController.gettingErrorMessageWhenInvalidOptionChosen();
        String actualErrorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        assertThat(errorMessage, is(equalTo(actualErrorMessage)));
    }

}


