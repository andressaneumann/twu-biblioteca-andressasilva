package com.twu.biblioteca.Repositories;

import com.twu.biblioteca.Models.Book;

import java.util.ArrayList;

public class BookRepository {

    ArrayList<Book> books;

    public BookRepository() {
        getBooks();
    }

    public ArrayList<Book> getBooks() {

        books = new ArrayList<Book>(5);

        Book hamlet = new Book(0,"Hamlet", "William Shakespeare", 1603, true);
        Book firstHarryPotter = new Book (1, "Harry Potter and the Order of the Phoenix", "J. K. Rowling", 2003, true);
        Book theSilentPatient = new Book (2, "The Silent Patient", "Alex Michaelides", 2019, true);
        Book theAlchemist = new Book(3, "The Alchemist", "Paulo Coelho", 1988, true);
        Book theGreatGatsby = new Book(4, "The Great Gatsby", "Scott Fitzgerald", 1925, false);


        books.add(hamlet);
        books.add(firstHarryPotter);
        books.add(theSilentPatient);
        books.add(theAlchemist);
        books.add(theGreatGatsby);

        return books;
    }

    public void updateBookAvailableStatus(int id, Boolean status){

        for(Book book : books){
            if(book.getId() == id)
                book.setAvailable(status);
        }
    }
}
