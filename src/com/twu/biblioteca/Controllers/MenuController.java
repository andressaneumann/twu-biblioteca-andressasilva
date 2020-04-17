package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Option;
import com.twu.biblioteca.Repositories.BookRepository;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    MediaController mediaController = new MediaController();

    ArrayList<Book> books = mediaController.getBooks();
    ArrayList<Book> booksToCheckout = mediaController.getAvailableBooks();
    ArrayList<Book> checkedOutBooks = mediaController.getCheckedOutBooks();
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
        Option returnABook = new Option(3, "3. Return a Book");
        Option exit = new Option(5, "5. Exit program");

        availableOptions.add(booksList);
        availableOptions.add(checkoutBook);
        availableOptions.add(returnABook);
        availableOptions.add(exit);
    }

    public void main() {

        while (programIsRunning) {
            updateBookLists();
            System.out.println("\nPlease choose between the available options and press the respective number: ");

            printMenuOptions();
            int userAnswer = readingIntegerOutput();
            isAnswerValid = checkingIfMenuInputIsValid(userAnswer);

            if(isAnswerValid)
                userAction(userAnswer);
        }
    }

    public void printMenuOptions(){
        for (int i = 0; i < availableOptions.size(); i++)
            System.out.println(availableOptions.get(i).getName());
    }

    public int readingIntegerOutput(){
        while(!in.hasNextInt()){
            System.out.println("\nYou need to insert a number! Please try again.");
            in.next();
        }
        int userAnswer = in.nextInt();

        return userAnswer;
    }

    public Boolean checkingIfMenuInputIsValid(int userAnswer) {

        Boolean optionFound = false;

        for(Option option : availableOptions){
            if(option.getId() == userAnswer)
               optionFound = true;
        }

        return optionFound;
    }

    public Boolean checkingIfBookCodeIsValid(int bookCode, Boolean isAvailable) {
        Boolean codeFound = false;

        if(isAvailable){
            for(Book book : booksToCheckout){
                if(book.getId() == bookCode)
                    codeFound = true;
            }
            return codeFound;
        }

        for(Book book : checkedOutBooks){
            if(book.getId() == bookCode)
                codeFound = true;
        }
        return codeFound;
    }

    public void userAction(int answer){

        String messageAction = "";
        String messageToPrint = "";
        String processOutput = "";

        switch (answer){
            case 1:
                messageAction = "books:";
                messageToPrint = listMessage(booksToCheckout, messageAction, true);

                System.out.println(messageToPrint);
                break;
            case 2:
                messageAction = "books to checkout: ";
                messageToPrint = listMessage(booksToCheckout, messageAction, true);
                System.out.println(messageToPrint);

                System.out.println("Please select the book code correspondent to the book you want to checkout:");
                int bookCodeToCheckout = readingIntegerOutput();

                processOutput = checkoutProcess(bookCodeToCheckout);
                System.out.println(processOutput);
                break;
            case 3:
                messageAction = "current checked out books: ";
                messageToPrint = listMessage(booksToCheckout, messageAction, false);
                System.out.println(messageToPrint);

                System.out.println("\nPlease inform the code of the book you want to return: ");
                int bookCodeToReturn = readingIntegerOutput();

                processOutput = returningBookProcess(bookCodeToReturn);
                System.out.println(processOutput);
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

    public String listMessage(ArrayList<Book> list, String messageAction, Boolean isAvailable){
        String message = "List of " + messageAction + "\n";
        Boolean isListEmpty = checkingIfListIsEmpty(list);

        if(!isListEmpty)
            return message + returnListOfBooks(isAvailable);

        return message + "Nothing to show. List empty!";

    }

    public void updateBookLists(){
        booksToCheckout = mediaController.getAvailableBooks();
        checkedOutBooks = mediaController.getCheckedOutBooks();
    }

    public String checkoutProcess(int bookCodeToCheckout){
        Boolean isBookAvailable = checkingIfBookCodeIsValid(bookCodeToCheckout, true);

        if(isBookAvailable){
            mediaController.checkingOutBook(bookCodeToCheckout);
            return "Thank you! Enjoy the book!";
        }

        return "Sorry, that book is not available!";
    }

    public String returningBookProcess(int bookCodeToReturn){
        Boolean isBookAvailable = checkingIfBookCodeIsValid(bookCodeToReturn, false);

        if(isBookAvailable){
            mediaController.returningBook(bookCodeToReturn);
            return "Thank you for returning the book!";
        }
        return "That is not a valid book to return.";
    }

    public Boolean checkingIfListIsEmpty(ArrayList<Book> list){
        return list.isEmpty();
    }

    public String returnListOfBooks(Boolean isAvailable){
        String listOfBooksToCheckOut = "";
        String listOfBookCheckedOut = "";

        if(isAvailable){
            for (Book book : booksToCheckout) {
                listOfBooksToCheckOut += ("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased() + "\n");
            }
            return listOfBooksToCheckOut;
        }

        for(Book book : checkedOutBooks){
            listOfBookCheckedOut += ("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased() + "\n");
        }
        return listOfBookCheckedOut;
    }

    public String GettingErrorMessageWhenInvalidOptionChosen() {
        return "\n-- You need to choose between the available options, please try again! --\n";
    }
}
