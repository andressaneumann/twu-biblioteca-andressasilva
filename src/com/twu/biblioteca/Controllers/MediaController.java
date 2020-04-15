package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Repositories.BookRepository;

import java.util.ArrayList;

public class MediaController {

    public ArrayList<Book> getBooks(){

        BookRepository listOfBooks = new BookRepository();
        ArrayList<Book> books = listOfBooks.GetBooks();

        return books;
    }






}
