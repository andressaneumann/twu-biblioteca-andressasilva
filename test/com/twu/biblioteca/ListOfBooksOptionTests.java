package com.twu.biblioteca;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.ListOfBooksOption;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListOfBooksOptionTests {

    ListOfBooksOption listOfBooksOption;

    @Before
    public void setUp(){
        listOfBooksOption = new ListOfBooksOption();
    }

    @Test
    public void showOptionNameTest(){
        String optionName = listOfBooksOption.showOptionName();
        String expectedOptionName = "List of books";

        assertThat(optionName, is(equalTo(expectedOptionName)));
    }

    @Test
    public void continueLoopTest(){
        Boolean continueLoopResponse = listOfBooksOption.continueLoop();

        assertThat(continueLoopResponse, is(equalTo(true)));
    }

    @Test
    public void getBooksTest(){
        ArrayList<Book> books = listOfBooksOption.getBooks();

        assertThat(books.isEmpty(), is(false));

    }





}
