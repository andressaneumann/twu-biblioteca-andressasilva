package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Interfaces.Option;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.ListOfBooksOption;
import com.twu.biblioteca.Repositories.BookRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    ArrayList<Option> availableOptions;
    Scanner in = new Scanner(System.in);
    Boolean isAnswerValid = false;
    ListOfBooksOption booksOption = new ListOfBooksOption();

    public void Main() {

        while (!isAnswerValid) {
            System.out.println("Please choose between the available options and press the respective number: ");
            ArrayList<Option> availableOptions = AvailableOptions();

            for (int i = 0; i < availableOptions.size(); i++)
                System.out.println(i+1 + " - " + availableOptions.get(i).showOptionName());

            String userAnswer = in.nextLine();
            isAnswerValid = CheckingUserInput(userAnswer);
        }

        ArrayList<Book> books = booksOption.getBooks();
        System.out.println("\n** List of books **");
        for (Book book : books) {
            System.out.println("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased());
        }

    }

    public ArrayList<Option> AvailableOptions() {

        availableOptions = new ArrayList<Option>();
        availableOptions.add(booksOption);

        return availableOptions;
    }

    public Boolean CheckingUserInput(String userAnswer) {

        int answer = 0;
        try{
            answer = Integer.parseInt(userAnswer);
        }
        catch (Exception e){
            System.out.println(e);
        }

        switch (answer){
            case 1:
                return true;
            case 2:
            case 3:
                return false;
            default:
                System.out.println(GettingErrorMessageWhenInvalidOptionChosen());
                return false;
        }

    }

    public String GettingErrorMessageWhenInvalidOptionChosen() {
        String errorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        return errorMessage;
    }

}
