package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MediaController;
import com.twu.biblioteca.Models.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaControllerTests {

    MediaController mediaController;

    @Before
    public void setUp(){
        mediaController = new MediaController();
    }

    @Test
    public void getBooks_WhenCalled_ReturnBooksAvailable(){
        ArrayList<Book> books = mediaController.getBooks();

        assertThat(books.isEmpty(), is(false));

    }

    @Test
    public void getAvailableBooks_WhenCalled_ReturnAvailableBooks(){
        ArrayList<Book> availableBooks = mediaController.availableBooks();

        assertThat(availableBooks.get(0).getAvailable(), is(equalTo(true)));
    }

}
