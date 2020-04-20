package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Models.Option;
import com.twu.biblioteca.Repositories.BookRepository;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    MediaController mediaController = new MediaController();
    LoginController loginController = new LoginController();

    ArrayList<Book> booksToCheckout = mediaController.getAvailableBooks();
    ArrayList<Book> checkedOutBooks = mediaController.getCheckedOutBooks();
    ArrayList<Option> availableOptions = new ArrayList<Option>();

    ArrayList<Movie> moviesToCheckout = mediaController.getAvailableMovies();
    ArrayList<Movie> checkedOutMovies = mediaController.getCheckedOutMovies();

    Boolean isUserLoggedIn = false;
    Boolean programIsRunning = true;
    Boolean isAnswerValid = false;
    Scanner in = new Scanner(System.in);

    public MenuController() {
        instantiateOptions();
    }

    public void instantiateOptions(){
        Option login = new Option(1, "1. Login", false);
        Option booksList = new Option(2, "2. List of Books", false);
        Option checkoutBook = new Option(3, "3. Checkout a Book", true);
        Option returnABook = new Option(4, "4. Return a Book", true);
        Option movieList = new Option(5, "5. List of Movies", false);
        Option checkoutAMovie = new Option(6,"6. Checkout a Movie", false);
        Option exit = new Option(7, "7. Exit program", false);

        availableOptions.add(login);
        availableOptions.add(booksList);
        availableOptions.add(checkoutBook);
        availableOptions.add(returnABook);
        availableOptions.add(movieList);
        availableOptions.add(checkoutAMovie);
        availableOptions.add(exit);
    }

    public void main() {

        while (programIsRunning) {
            updatingLists();
            System.out.println("\nPlease choose between the available options and press the respective number: ");

            String menuOptions = returnStringOfMenuOptions(isUserLoggedIn);
            System.out.println(menuOptions);
            int userAnswer = readingIntegerOutput();
            isAnswerValid = checkingIfMenuInputIsValid(userAnswer);

            if(isAnswerValid)
                userAction(userAnswer);
        }
    }

    public String returnStringOfMenuOptions(Boolean isUserLoggedIn){
        String menuOptions = "";

        if(isUserLoggedIn){
            for (int i = 0; i < availableOptions.size(); i++)
                menuOptions += availableOptions.get(i).getName() + "\n";

            return menuOptions;
        }

        menuOptions += "\nIn order to checkout and return books you need to be logged in!\n";

        for(int i = 0; i<availableOptions.size(); i++){
            if(availableOptions.get(i).getUserNeedsToBeLoggedInToAccessOption().equals(false))
                menuOptions += availableOptions.get(i).getName() + "\n";
        }

        return menuOptions;
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

    public Boolean checkingIfMediaCodeIsValid(int mediaCode, Boolean isAvailable, Class<?> tClass) {
        Boolean codeFound = false;

        int mediaTypeFlag = flaggingMediaType(tClass);

        switch (mediaTypeFlag){
            case 0:
                if(isAvailable){
                    for(Book book : booksToCheckout){
                        if(book.getId() == mediaCode)
                            codeFound = true;
                    }
                    return codeFound;
                }

                for(Book book : checkedOutBooks){
                    if(book.getId() == mediaCode)
                        codeFound = true;
                }
                break;

            case 1:
                if(isAvailable){
                    for(Movie movie : moviesToCheckout){
                        if(movie.getId() == mediaCode)
                            codeFound = true;
                    }
                }

                for(Movie movie : checkedOutMovies){
                    if(movie.getId() == mediaCode)
                        codeFound = true;
                }
                break;
        }

        return codeFound;
    }

    public void userAction(int answer){
        Boolean isListEmpty;

        String messageAction = "";
        String messageToPrint = "";
        String processOutput = "";

        switch (answer){
            case 1:
                isUserLoggedIn = loginController.main();

                if(isUserLoggedIn){
                    System.out.println("User logged in!");
                    break;
                }

                System.out.println("Login failed.");
                break;
            case 2:
                isListEmpty = checkingIfListIsEmpty(booksToCheckout);

                if(!isListEmpty){
                    messageAction = "books:";
                    messageToPrint = listMessage(booksToCheckout, Book.class, messageAction, true);

                    System.out.println(messageToPrint);
                    break;
                }
                System.out.println("Nothing to show, list is empty!");
                break;
            case 3:
                isListEmpty = checkingIfListIsEmpty(booksToCheckout);

                if(!isListEmpty){
                    messageAction = "books to checkout: ";
                    messageToPrint = listMessage(booksToCheckout, Book.class, messageAction, true);
                    System.out.println(messageToPrint);

                    System.out.println("Please select the book code correspondent to the book you want to checkout:");
                    int bookCodeToCheckout = readingIntegerOutput();

                    processOutput = checkoutProcess(bookCodeToCheckout, Book.class);
                    System.out.println(processOutput);

                    break;
                }
                System.out.println("Nothing to show, list is empty!");
                break;
            case 4:
                isListEmpty = checkingIfListIsEmpty(checkedOutBooks);

                if(!isListEmpty){
                    messageAction = "current checked out books: ";
                    messageToPrint = listMessage(checkedOutBooks, Book.class, messageAction, false);
                    System.out.println(messageToPrint);

                    System.out.println("\nPlease inform the code of the book you want to return: ");
                    int bookCodeToReturn = readingIntegerOutput();

                    processOutput = returningBookProcess(bookCodeToReturn);
                    System.out.println(processOutput);
                    break;
                }
                System.out.println("Nothing to show, list is empty!");
                break;

            case 5:
                isListEmpty = checkingIfListIsEmpty(moviesToCheckout);

                if(!isListEmpty){
                    messageAction = "movies:";
                    messageToPrint = listMessage(moviesToCheckout, Movie.class, messageAction, true);

                    System.out.println(messageToPrint);
                    break;
                }
                System.out.println("Nothing to show, list is empty!");
                break;

            case 6:
                isListEmpty = checkingIfListIsEmpty(moviesToCheckout);

                if(!isListEmpty){
                    messageAction = "movies to checkout: ";
                    messageToPrint = listMessage(moviesToCheckout, Movie.class, messageAction, true);
                    System.out.println(messageToPrint);

                    System.out.println("Please select the movie code correspondent to the movie you want to checkout:");
                    int movieCodeToCheckout = readingIntegerOutput();

                    processOutput = checkoutProcess(movieCodeToCheckout, Movie.class);
                    System.out.println(processOutput);
                    break;
                }
                System.out.println("Nothing to show, list is empty!");
                break;
            case 7:
                programIsRunning = false;
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println(GettingErrorMessageWhenInvalidOptionChosen());
        }
    }

    public ArrayList<?> castingListType(ArrayList<?> list, Class<?> tClass){
        if(tClass == Movie.class){
            list = (ArrayList<Book>) list;
            return list;
        }

        list = (ArrayList<Movie>) list;
        return list;
    }

    public int flaggingMediaType(Class<?> tClass){
        if(tClass.equals(Book.class))
            return 0;

        return 1;
    }

    public String listMessage(ArrayList<?> list, Class<?> tClass, String messageAction, Boolean isAvailable){
        list = castingListType(list, tClass);
        String message = "List of " + messageAction + "\n";

        return message + returnListOfMedia(isAvailable, tClass);
    }

    public void updatingLists(){
        booksToCheckout = mediaController.getAvailableBooks();
        checkedOutBooks = mediaController.getCheckedOutBooks();
        moviesToCheckout = mediaController.getAvailableMovies();
        checkedOutMovies = mediaController.getCheckedOutMovies();
    }

    public String checkoutProcess(int mediaCodeToCheckout, Class<?> tClass){
        String output = "";
        Boolean isMediaAvailable = checkingIfMediaCodeIsValid(mediaCodeToCheckout, true, tClass);

        int mediaTypeFlag = flaggingMediaType(tClass);

        switch (mediaTypeFlag){
            case 0:
                if(isMediaAvailable){
                    mediaController.checkingOutBook(mediaCodeToCheckout);
                    output = "Thank you! Enjoy the book!";
                    break;
                }
                output =  "Sorry, that book is not available!";
                break;

            case 1:
                if(isMediaAvailable){
                    mediaController.checkingOutMovie(mediaCodeToCheckout);
                    output = "Thank you! Enjoy the movie!";
                    break;
                }
                output =  "Sorry, that movie is not available!";
                break;
        }

        return output;
    }

    public String returningBookProcess(int bookCodeToReturn){
        Boolean isBookAvailable = checkingIfMediaCodeIsValid(bookCodeToReturn, false, Book.class);

        if(isBookAvailable){
            mediaController.returningBook(bookCodeToReturn);
            return "Thank you for returning the book!";
        }
        return "That is not a valid book to return.";
    }

    public Boolean checkingIfListIsEmpty(ArrayList<?> list){
        return list.isEmpty();
    }

    public String returnListOfMedia(Boolean isAvailable, Class<?> tClass){
        String listOfMedia = "";

        int mediaTypeFlag = flaggingMediaType(tClass);

        switch (mediaTypeFlag){
            case 0:

                if(isAvailable){
                    for (Book book : booksToCheckout) {
                        listOfMedia += ("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased() + "\n");
                    }
                    break;
                }

                for (Book book : checkedOutBooks) {
                    listOfMedia += ("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Publication Year: " + book.getYearReleased() + "\n");
                }
            break;

            case 1:

                if(isAvailable){
                    for(Movie movie : moviesToCheckout)
                        listOfMedia += ("Movie Code: " + movie.getId() + " | Name: " + movie.getName() + " | Director: " + movie.getDirector() + " | Rating: " + movie.getRating() + "\n");
                    break;
                }

                for(Movie movie : checkedOutMovies)
                    listOfMedia += ("Movie Code: " + movie.getId() + " | Name: " + movie.getName() + " | Director: " + movie.getDirector() + " | Rating: " + movie.getRating() + "\n");

            break;
        }

        return listOfMedia;

    }

    public String GettingErrorMessageWhenInvalidOptionChosen() {
        return "\n-- You need to choose between the available options, please try again! --\n";
    }
}
