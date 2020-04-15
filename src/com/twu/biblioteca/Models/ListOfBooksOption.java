package com.twu.biblioteca.Models;

import com.twu.biblioteca.Interfaces.Option;
import com.twu.biblioteca.Repositories.BookRepository;

import java.util.ArrayList;

public class ListOfBooksOption implements Option {
    @Override
    public String showOptionName() {
        return "List of books";
    }

    @Override
    public void optionAction() {
        //getBooks();
    }

    @Override
    public Boolean continueLoop() {
        return true;
    }
}
