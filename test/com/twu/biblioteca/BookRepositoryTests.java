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
    public void GetBooks_ComparingToAnEmptyList_AssertsNotEquals(){

        ArrayList<Book> emptyBookList = new ArrayList<Book>();
        ArrayList<Book> allBooks = bookRepository.GetBooks();

        assertNotEquals(emptyBookList, allBooks);
    }

}

