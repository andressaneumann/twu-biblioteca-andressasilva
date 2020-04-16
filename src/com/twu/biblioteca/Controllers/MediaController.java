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

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<Book>();

        for (int i = 0; i < books.size(); i++){
            if(books.get(i).getAvailable().equals(true))
                availableBooks.add(books.get(i));
        }

        return availableBooks;
    }

    public ArrayList<Book> getCheckedOutBooks(){
        ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

        for (int i = 0; i < books.size(); i++){
            if(books.get(i).getAvailable().equals(false))
                checkedOutBooks.add(books.get(i));
        }

        return checkedOutBooks;
    }


    public void checkingOutBook(int bookCodeToCheckout) {

        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == bookCodeToCheckout)
                bookRepository.updateBookAvailableStatus(bookCodeToCheckout, false);
        }
    }

    public void returningBook(int checkedOutBookCode) {
        for(int i = 0; i < books.size(); i++){
            if(books.get(i).getId() == checkedOutBookCode)
                bookRepository.updateBookAvailableStatus(checkedOutBookCode, true);
        }
    }

}
