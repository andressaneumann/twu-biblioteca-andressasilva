package com.twu.biblioteca.Models;

import com.twu.biblioteca.Interfaces.Option;

public class ListOfBooksOption implements Option {
    @Override
    public String showOptionName() {
        return "List of books";
    }

    @Override
    public void optionAction() {

    }

    @Override
    public Boolean continueLoop() {
        return null;
    }
}
