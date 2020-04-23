package com.twu.biblioteca;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Repositories.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class BookRepositoryTests {

    BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository = new BookRepository();
    }

    @Test
    public void getBooks_ComparingToAnEmptyList_AssertsNotEquals(){

        ArrayList<Book> emptyBookList = new ArrayList<Book>();
        ArrayList<Book> allBooks = bookRepository.getBooks();

        assertNotEquals(emptyBookList, allBooks);
    }

    @Test
    public void updateBookAvailableStatus_FromTrueToFalse_ReturnsFalse(){
        int bookCodeToBeUpdated = 0;
        int loggedInUser = 0;
        Boolean newStatus = false;

        ArrayList<Book> books = bookRepository.getBooks();
        bookRepository.updateBookAvailableStatus(bookCodeToBeUpdated, newStatus);

        assertThat(books.get(bookCodeToBeUpdated).getAvailableToBook(), is(equalTo(false)));
    }

    @Test
    public void updateBookAvailableStatus_FromFalseToTrue_ReturnsTrue(){
        int bookCodeToBeUpdated = 4;
        Boolean newStatus = true;

        ArrayList<Book> books = bookRepository.getBooks();
        bookRepository.updateBookAvailableStatus(bookCodeToBeUpdated, newStatus);

        assertThat(books.get(bookCodeToBeUpdated).getAvailableToBook(), is(equalTo(true)));
    }

}

