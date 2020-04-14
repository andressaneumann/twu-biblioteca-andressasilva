package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Repositories.BookRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    ArrayList<String> availableOptions;
    Scanner in = new Scanner(System.in);
    Boolean isAnswerValid = false;

    public void Menu() {

        while (!isAnswerValid) {
            System.out.println("Please choose between the available options and press the respective number: ");
            ArrayList<String> availableOptions = AvailableOptions();

            for (int i = 0; i < availableOptions.size(); i++)
                System.out.println(availableOptions.get(i));

            String userAnswer = in.nextLine();
            isAnswerValid = CheckingUserInput(userAnswer);
        }

        ArrayList<Book> books = GetListOfBooks();
        System.out.println("\n** List of books **");
        for (Book book : books) {
            System.out.println("\nBook " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Publication Year: " + book.getYearReleased());
        }

    }

    public ArrayList<String> AvailableOptions() {

        availableOptions = new ArrayList<String>();
        availableOptions.add("1. List of Books");


        return availableOptions;
    }

    public Boolean CheckingUserInput(String userAnswer) {

        if (userAnswer.equals("1"))
            return true;
        else {
            System.out.println(GettingErrorMessageWhenInvalidOptionChosen());
            return false;
        }
    }

    public String GettingErrorMessageWhenInvalidOptionChosen() {
        String errorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        return errorMessage;
    }

    public ArrayList<Book> GetListOfBooks() {
        BookRepository listOfBooks = new BookRepository();
        ArrayList<Book> books = listOfBooks.GetBooks();

        return books;
    }
}
