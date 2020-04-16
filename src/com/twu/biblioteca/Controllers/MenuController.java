package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Option;
import com.twu.biblioteca.Repositories.BookRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    Boolean programIsRunning = true;
    ArrayList<Option> availableOptions = new ArrayList<Option>();
    Scanner in = new Scanner(System.in);
    Boolean isAnswerValid = false;

    public MenuController() {
        instantiateOptions();
    }

    public void instantiateOptions(){
        Option booksList = new Option(1, "1. List of Books");
        Option checkoutBook = new Option(2, "2. Checkout a Book");
        Option exit = new Option(5, "5. Exit program");

        availableOptions.add(booksList);
        availableOptions.add(checkoutBook);
        availableOptions.add(exit);
    }

    public void main() {

        while (programIsRunning) {
            System.out.println("\nPlease choose between the available options and press the respective number: ");

            for (int i = 0; i < availableOptions.size(); i++)
                System.out.println(availableOptions.get(i).getName());

            String userAnswer = in.nextLine();
            isAnswerValid = checkingUserInput(userAnswer);

            if(isAnswerValid){
                userAction(userAnswer);
            }
        }

    }

    public Boolean checkingUserInput(String userAnswer) {

        int finalAnswer = parseUserInput(userAnswer);
        Boolean optionFound = false;

        for(Option option : availableOptions){
            int id = option.getId();
            if(id == finalAnswer)
               optionFound = true;
        }

        return optionFound;
    }

    public int parseUserInput(String userAnswer){

        int answer = 0;

        try{
            answer = Integer.parseInt(userAnswer);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return answer;
    }

    public void userAction(String answer){

        int convertedAnswer = parseUserInput(answer);

        switch (convertedAnswer){
            case 1:
                MediaController mediaController = new MediaController();

                ArrayList<Book> books = mediaController.getBooks();
                System.out.println("\n** List of books **");
                for (Book book : books) {
                    System.out.println("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased());
                }
                break;
            case 4:
                programIsRunning = false;
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println(GettingErrorMessageWhenInvalidOptionChosen());
        }
    }

    public String GettingErrorMessageWhenInvalidOptionChosen() {
        String errorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        return errorMessage;
    }

}
