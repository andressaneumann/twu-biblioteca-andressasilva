package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MediaController;
import com.twu.biblioteca.Controllers.MenuController;
import com.twu.biblioteca.Models.Book;
import com.twu.biblioteca.Repositories.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Random;

public class MenuControllerTests {

    MenuController menuController;
    MediaController mediaController;
    Random random;

    @Before
    public void SetUp(){
        menuController = new MenuController();
        mediaController = new MediaController();
        random = new Random();
    }

    @Test
    public void checkingIfMenuInputIsValid_WhenCorrectAnswer_ReturnsTrue(){
        Boolean response = menuController.checkingIfMenuInputIsValid(1);

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void checkingIfMenuInputIsValid_WhenWrongAnswer_ReturnsFalse(){
        Boolean response = menuController.checkingIfMenuInputIsValid(8);

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void checkingIfBookCodeIsValid_WhenCorrectAnswer_ReturnsTrue(){
        ArrayList<Book> availableBooks = mediaController.getAvailableBooks();
        int listSize = availableBooks.size();
        int randomBookCode = random.nextInt(listSize);

        Boolean isBookAvailable = menuController.checkingIfBookCodeIsValid(randomBookCode);

        assertThat(isBookAvailable, is(equalTo(true)));
    }

    @Test
    public void checkingIfBookCodeIsValid_WhenWrongAnswer_ReturnsFalse(){
        ArrayList<Book> availableBooks = mediaController.getAvailableBooks();
        int listSize = availableBooks.size();
        int randomBookCode = listSize + 1;

        Boolean isBookAvailable = menuController.checkingIfBookCodeIsValid(randomBookCode);

        assertThat(isBookAvailable, is(equalTo(false)));
    }

    @Test
    public void GettingErrorMessageWhenInvalidOptionChosen_WhenCalled_ReturnsMessage(){
        String errorMessage = menuController.GettingErrorMessageWhenInvalidOptionChosen();
        String actualErrorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        assertThat(errorMessage, is(equalTo(actualErrorMessage)));
    }

}


