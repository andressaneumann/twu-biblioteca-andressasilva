package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Interfaces.Option;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.ListOfBooksOption;
import com.twu.biblioteca.Repositories.BookRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    Boolean programIsRunning = true;
    ArrayList<Option> availableOptions;
    Scanner in = new Scanner(System.in);
    Boolean isAnswerValid = false;
    ListOfBooksOption booksOption = new ListOfBooksOption();

    public void Main() {

        while (programIsRunning) {
            System.out.println("\nPlease choose between the available options and press the respective number: ");
            ArrayList<Option> availableOptions = availableOptions();

            for (int i = 0; i < availableOptions.size(); i++)
                System.out.println(i+1 + " - " + availableOptions.get(i).showOptionName());

            String userAnswer = in.nextLine();
            isAnswerValid = checkingUserInput(userAnswer);

            if(isAnswerValid){
                int finalAnswer = parseUserInput(userAnswer);
                userAction(finalAnswer);
            }
        }

    }

    public ArrayList<Option> availableOptions() {

        availableOptions = new ArrayList<Option>();
        availableOptions.add(booksOption);

        return availableOptions;
    }

    public Boolean checkingUserInput(String userAnswer) {

        ArrayList<String> optionsCodes = new ArrayList<String>(){
            {
                add("1");
            } };

        if(optionsCodes.contains(userAnswer))
            return true;
        else{
            GettingErrorMessageWhenInvalidOptionChosen();
            return false;
        }
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

    public void userAction(int answer){

        switch (answer){
            case 1:
                MediaController mediaController = new MediaController();

                ArrayList<Book> books = mediaController.getBooks();
                System.out.println("\n** List of books **");
                for (Book book : books) {
                    System.out.println("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased());
                }
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
