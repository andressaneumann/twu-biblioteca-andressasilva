package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MediaController;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Repositories.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaControllerTests {

    BookRepository bookRepository;
    MediaController mediaController;
    Random random;

    @Before
    public void setUp(){

        mediaController = new MediaController();
        random = new Random();
    }

    @Test
    public void getBooks_WhenCalled_ReturnBooksAvailable(){
        ArrayList<Book> books = mediaController.getBooks();

        assertThat(books.isEmpty(), is(false));

    }

    @Test
    public void availableBooks_WhenCalled_ReturnAvailableBooks(){
        ArrayList<Book> availableBooks = mediaController.getAvailableBooks();

        assertThat(availableBooks.get(0).getAvailable(), is(equalTo(true)));
    }

    @Test
    public void checkoutBook_WhenCalled_ChangeBookAvailableStatusToFalse(){
        ArrayList<Book> availableBooks = mediaController.getAvailableBooks();
        int loggedInUser = 0;
        int listSize = availableBooks.size();
        int randomBookCodeToCheckout = random.nextInt(listSize);

        Book currentBook = availableBooks.get(randomBookCodeToCheckout);

        mediaController.checkingOutBook(currentBook.getId(), loggedInUser);

        assertThat(availableBooks.get(randomBookCodeToCheckout).getAvailable(), is(equalTo(false)));
    }

    @Test
    public void returningBook_WhenCalled_TurnsBookAvailableAgain(){
        ArrayList<Book> checkedOutBooks = mediaController.getCheckedOutBooks();
        int listSize = checkedOutBooks.size();
        int randomCheckedOutBookCode = random.nextInt(listSize);

        Book currentBook = checkedOutBooks.get(randomCheckedOutBookCode);

        mediaController.returningBook(currentBook.getId());

        assertThat(checkedOutBooks.get(randomCheckedOutBookCode).getAvailable(), is(equalTo(true)));
    }

}
