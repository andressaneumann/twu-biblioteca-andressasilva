package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MenuController;
import com.twu.biblioteca.Models.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

public class MenuControllerTests {

    MenuController menuController;

    @Before
    public void SetUp() throws Exception{
        menuController = new MenuController();
    }

    @Test
    public void AvailableOptions_FirstOption_ListOfBooks(){
        ArrayList<String> availableOptions  = menuController.AvailableOptions();
        String optionOne = "1. List of Books";

        assertThat(availableOptions.get(0), is(equalTo(optionOne)) );
    }

    @Test
    public void CheckingUserInput_WhenCorrectAnswer_ReturnsTrue(){
        Boolean response = menuController.CheckingUserInput("1");

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void CheckingUserInput_WhenWrongAnswer_ReturnsFalse(){
        Boolean response = menuController.CheckingUserInput("2");

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void GettingErrorMessageWhenInvalidOptionChosen_WhenCalled_ReturnsMessage(){

        String errorMessage = menuController.GettingErrorMessageWhenInvalidOptionChosen();
        String actualErrorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        assertThat(errorMessage, is(equalTo(actualErrorMessage)));
    }

    @Test
    public void GetListOfBooks_WhenCalled_ReturnsAValidList(){
        ArrayList<Book> listOfBooks = menuController.GetListOfBooks();

        assertThat(listOfBooks.size(), is(equalTo(5)));
    }

}


