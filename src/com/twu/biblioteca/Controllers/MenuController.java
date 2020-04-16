package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Option;
import com.twu.biblioteca.Repositories.BookRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    MediaController mediaController = new MediaController();

    ArrayList<Book> books = mediaController.getBooks();
    ArrayList<Book> booksToCheckout = mediaController.availableBooks();
    ArrayList<Option> availableOptions = new ArrayList<Option>();

    Boolean programIsRunning = true;
    Boolean isAnswerValid = false;
    Scanner in = new Scanner(System.in);

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

            int userAnswer = readingIntegerOutput();

            isAnswerValid = checkingMenuInputSelected(userAnswer);

            if(isAnswerValid){
                userAction(userAnswer);
            }
        }

    }

    public int readingIntegerOutput(){
        while(!in.hasNextInt()){
            System.out.println("\nYou need to insert a number! Please try again.");
            in.next();
        }
        int userAnswer = in.nextInt();

        return userAnswer;
    }

    public Boolean checkingMenuInputSelected(int userAnswer) {

        Boolean optionFound = false;

        for(Option option : availableOptions){
            if(option.getId() == userAnswer)
               optionFound = true;
        }

        return optionFound;
    }

    public Boolean checkingBookCodeInserted(int bookCode) {
        Boolean codeFound = false;

        for(Book book : booksToCheckout){
            if(book.getId() == bookCode)
                codeFound = true;
        }
        return codeFound;
    }

    public void userAction(int answer){

/*        int convertedAnswer = parseUserInput(answer);*/

        switch (answer){
            case 1:
                System.out.println("\n** List of books **");
                for (Book book : booksToCheckout) {
                    System.out.println("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased());
                }
                break;
            case 2:
                System.out.println("List of books available to checkout: ");

                for(Book book : booksToCheckout)
                    System.out.println("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased());
                System.out.println("Please select the book code correspondent to the book you want to checkout:");
                int bookCodeToCheckout = readingIntegerOutput();

                Boolean isBookAvailable = checkingBookCodeInserted(bookCodeToCheckout);
                if(isBookAvailable){
                    try {
                        mediaController.checkoutBook(bookCodeToCheckout);
                        updateBooksAvailableList();
                        System.out.println("Thank you! Enjoy the book!");
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
                else
                    System.out.println("Sorry, that book is not available!");

            break;
            case 5:
                programIsRunning = false;
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println(GettingErrorMessageWhenInvalidOptionChosen());
        }
    }

    public void updateBooksAvailableList(){
        booksToCheckout = mediaController.availableBooks();
    }

    public String GettingErrorMessageWhenInvalidOptionChosen() {
        String errorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        return errorMessage;
    }


}
