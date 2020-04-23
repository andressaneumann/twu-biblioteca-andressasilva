package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.*;
import com.twu.biblioteca.Repositories.UserRepository;

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

    //Booleans
    Boolean isUserLoggedIn = false;
    Boolean programIsRunning = true;
    Boolean isAnswerValid = false;
    Scanner in = new Scanner(System.in);

    //ints
    int loggedInUserId;

    //Strings
    String processOutput = "";

    public ArrayList<Option> instantiateOptions(Boolean isUserLoggedIn){
        Option login = new Option(1, "Login", false);
        Option seeMyProfile = new Option(2, "See my profile", true);
        Option booksList = new Option(3, "List of Books", false);
        Option checkoutBook = new Option(4, "Checkout a Book", true);
        Option returnABook = new Option(5, "Return a Book", true);
        Option movieList = new Option(6, "List of Movies", false);
        Option checkoutAMovie = new Option(7,"Checkout a Movie", false);
        Option checkWhoCheckedOutEachBook = new Option(8, "Who checked out each book", true);
        Option exit = new Option(9, "Exit program", false);

        if(isUserLoggedIn){
            availableOptions.clear();

            availableOptions.add(seeMyProfile);
            availableOptions.add(booksList);
            availableOptions.add(checkoutBook);
            availableOptions.add(returnABook);
            availableOptions.add(movieList);
            availableOptions.add(checkoutAMovie);
            availableOptions.add(checkWhoCheckedOutEachBook);
            availableOptions.add(exit);

            return availableOptions;
        }

        availableOptions.add(login);
        availableOptions.add(booksList);
        availableOptions.add(movieList);
        availableOptions.add(checkoutAMovie);
        availableOptions.add(exit);

        return availableOptions;
    }

    public MenuController() {
        availableOptions = instantiateOptions(isUserLoggedIn);
    }

    public void main() {

        while (programIsRunning) {
            updatingLists();
            System.out.println("\nPlease choose between the available options and press the respective number: ");

            printMenuOptions(availableOptions);
            int userAnswer = readingIntegerOutput();
            isAnswerValid = checkingIfMenuInputIsValid(userAnswer);

            if(isAnswerValid)
                userAction(userAnswer);
        }
    }

    public void printMenuOptions(ArrayList<Option> availableOptions) {

        for (int i = 0; i < availableOptions.size(); i++)
            System.out.println(i+1 + ". " + availableOptions.get(i).getName());
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

        for(int i = 1; i <= availableOptions.size(); i++) {
            if (i == userAnswer)
                optionFound = true;
        }

        return optionFound;
    }

    public Boolean checkingIfMediaCodeIsValid(int mediaCode, Boolean isAvailableToBook, Class<?> tClass){

        String mediaType = returnMediaType(tClass);

        switch (mediaType){
            case "Book":
                if(isAvailableToBook){
                    for(Book book : booksToCheckout){
                        if(book.getId() == mediaCode)
                            return true;
                    }
                }

                for(Book book : checkedOutBooks){
                    if(book.getId() == mediaCode)
                        return true;
                }
                break;

            case "Movie":

                if(isAvailableToBook){
                    for(Movie movie : moviesToCheckout){
                        if(movie.getId() == mediaCode)
                            return true;
                    }
                }

                for(Movie movie : checkedOutMovies){
                    if(movie.getId() == mediaCode)
                        return true;
                }
                break;
        }

        return false;
    }

    public String returnMediaType(Class<?> tClass) {
        if(tClass.equals(Book.class))
            return "Book";

        return "Movie";
    }

    public Option currentOptionSelected(int answer){

        for(int i = 1; i <= availableOptions.size(); i++){
            if(i == answer)
                return availableOptions.get(i-1);
        }
        return null;
    }

    public void userAction(int answer){
        Option currentOptionSelected = currentOptionSelected(answer);

        switch (currentOptionSelected.getName()){
            case "Login":
                Boolean loginProcess = loginProcess();

                if(loginProcess){
                    System.out.println("User logged in!");
                    break;
                }

                System.out.println("Login failed.");
                break;
            case "See my profile":
                printProfileInformation();
                break;
            case "List of Books":
                processOutput = generatingStringListOfMedia(booksToCheckout, Book.class, true, "books:");
                System.out.println(processOutput);
                break;
            case "Checkout a Book":
                processOutput = generatingStringListOfMedia(booksToCheckout, Book.class, true, "books to checkout:");
                System.out.println(processOutput);

                if(booksToCheckout.size() > 0){
                    System.out.println("Please select the book code correspondent to the book you want to checkout:");
                    int bookCodeToCheckout = readingIntegerOutput();

                    String checkoutBooks = checkoutProcess(bookCodeToCheckout, Book.class);
                    System.out.println(checkoutBooks);
                }
                break;
            case "Return a Book":
                processOutput = generatingStringListOfMedia(checkedOutBooks, Book.class, false, "checked out books:");
                System.out.println(processOutput);

                if(checkedOutBooks.size() > 0){
                    System.out.println("\nPlease inform the code of the book you want to return: ");
                    int bookCodeToReturn = readingIntegerOutput();

                    String returningBookOutput = returningBookProcess(bookCodeToReturn);
                    System.out.println(returningBookOutput);
                }
                break;

            case "List of Movies":
                processOutput = generatingStringListOfMedia(moviesToCheckout, Movie.class,true, "movies:");
                System.out.println(processOutput);
                break;

            case "Checkout a Movie":
                processOutput = generatingStringListOfMedia(moviesToCheckout, Movie.class,true, "movies to checkout:");
                System.out.println(processOutput);

                if(checkedOutMovies.size()>0){
                    System.out.println("Please select the movie code correspondent to the movie you want to checkout:");
                    int movieCodeToCheckout = readingIntegerOutput();

                    processOutput = checkoutProcess(movieCodeToCheckout, Movie.class);
                    System.out.println(processOutput);
                }
                break;

            case "Who checked out each book":
                printBooksAndUsersThatBookedEachOne();
                break;

            case "Exit program":
                programIsRunning = false;
                System.out.println("Exiting the program...");
                System.exit(0);
                break;

            default:
                System.out.println(gettingErrorMessageWhenInvalidOptionChosen());
        }
    }

    public Boolean loginProcess(){

        loggedInUserId = loginController.main();

        if(loggedInUserId >= 0){
            isUserLoggedIn = true;
            instantiateOptions(isUserLoggedIn);

            return true;
        }

        return false;
    }

    public void printProfileInformation(){
        User currentUser = gettingUserInformationFromId(loggedInUserId);

        System.out.println("My profile: \n");
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Phone number: " + currentUser.getPhoneNumber());
    }

    public String generatingStringListOfMedia(ArrayList<?> mediaList, Class<?> tClass, Boolean isAvailableToBook, String messageAction){
        if(!mediaList.isEmpty()){
            String message = "List of " + messageAction + "\n";

            return message + checkingListTypeAndReturningRespectiveData(isAvailableToBook, tClass);
        }

        return "Nothing to show, list is empty!";
    }

    public User gettingUserInformationFromId(int id){
        UserRepository userRepository = new UserRepository();
        ArrayList<User> users = userRepository.getUsers();

        for(User user : users){
            if(user.getId() == id)
                return user;
        }

        return null;
    }

    public void printBooksAndUsersThatBookedEachOne(){
        System.out.println("Books booked and the person who has each one:");

        if(checkedOutBooks.size()>0){
            for(Book book : checkedOutBooks){
                System.out.println("Book code: " + book.getId() + " | Book title: " + book.getTitle() + " | Booker: " + gettingUserInformationFromId(book.getUserIdOfBooker()).getName());
            }
        } else{
            System.out.println("No books currently booked.");
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

    public void updatingLists(){
        booksToCheckout = mediaController.getAvailableBooks();
        checkedOutBooks = mediaController.getCheckedOutBooks();
        moviesToCheckout = mediaController.getAvailableMovies();
        checkedOutMovies = mediaController.getCheckedOutMovies();
    }

    public String checkoutProcess(int mediaCodeToCheckout, Class<?> tClass){
        Boolean isMediaCheckedOut = false;
        Boolean isMediaAvailable = checkingIfMediaCodeIsValid(mediaCodeToCheckout, true, tClass);

        String mediaType = returnMediaType(tClass);

        switch (mediaType){
            case "Book":
                if(isMediaAvailable)
                    isMediaCheckedOut = mediaController.checkingOutBook(mediaCodeToCheckout, loggedInUserId);

                if(isMediaCheckedOut)
                    return "Thank you! Enjoy the book!";

                return "Sorry, that book is not available!";

            case "Movie":
                if(isMediaAvailable)
                    isMediaCheckedOut = mediaController.checkingOutMovie(mediaCodeToCheckout);

                if(isMediaCheckedOut)
                    return "Thank you! Enjoy the movie!";

                return "Sorry, that movie is not available!";
        }

        return "";
    }

    public String returningBookProcess(int bookCodeToReturn){
        Boolean isBookAvailable = checkingIfMediaCodeIsValid(bookCodeToReturn, false, Book.class);

        if(isBookAvailable){
            mediaController.returningBook(bookCodeToReturn);
            return "Thank you for returning the book!";
        }

        return "That is not a valid book to return.";
    }

    public String checkingListTypeAndReturningRespectiveData(Boolean isAvailableToBook, Class<?> tClass){
        String listOfMedia = "";

        String mediaType = returnMediaType(tClass);

        switch (mediaType){
            case "Book":

                if(isAvailableToBook){
                    for (Book book : booksToCheckout) {
                        listOfMedia += ("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: " + book.getAuthor()
                                + " | Publication Year: " + book.getYearReleased() + "\n");
                    }
                    break;
                }

                for (Book book : checkedOutBooks) {
                    listOfMedia += ("Book Code: " + book.getId() + " | " + "Title: " + book.getTitle() + " | Author: "
                            + book.getAuthor() + " | Publication Year: " + book.getYearReleased() + "\n");
                }
            break;

            case "Movie":

                if(isAvailableToBook){
                    for(Movie movie : moviesToCheckout)
                        listOfMedia += ("Movie Code: " + movie.getId() + " | Name: " + movie.getName() + " | Director: "
                                + movie.getDirector() + " | Rating: " + checkMovieRating(movie.getRating()) + "\n");
                    break;
                }

                for(Movie movie : checkedOutMovies)
                    listOfMedia += ("Movie Code: " + movie.getId() + " | Name: " + movie.getName() + " | Director: "
                            + movie.getDirector() + " | Rating: " + checkMovieRating(movie.getRating()) + "\n");

            break;
        }

        return listOfMedia;

    }

    public String checkMovieRating(Integer rating){
        if(rating == null)
            return "Unrated";

        return rating.toString();
    }

    public String gettingErrorMessageWhenInvalidOptionChosen() {
        return "\n-- You need to choose between the available options, please try again! --\n";
    }
}
