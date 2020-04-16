package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Repositories.BookRepository;

import java.util.ArrayList;

public class MediaController {

    BookRepository bookRepository = new BookRepository();
    ArrayList<Book> books = bookRepository.getBooks();

    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<Book> availableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<Book>();

        for (int i = 0; i < books.size(); i++){
            if(books.get(i).getAvailable().equals(true))
                availableBooks.add(books.get(i));
        }

        return availableBooks;
    }


    public void checkoutBook(int bookCodeToCheckout) {

        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == bookCodeToCheckout)
                bookRepository.updateBookAvailableStatus(bookCodeToCheckout, false);
        }
    }
}
