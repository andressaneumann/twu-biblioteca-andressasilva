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
    public void availableOptionsTest(){
        ArrayList<String> availableOptions  = menuController.availableOptions();

        assertThat(availableOptions.isEmpty(), is(equalTo(false)));
    }

    @Test
    public void checkingUserInput_WhenCorrectAnswer_ReturnsTrue(){
        Boolean response = menuController.checkingUserInput("1");

        assertThat(response, is(equalTo(true)));
    }

    @Test
    public void checkingUserInput_WhenWrongAnswer_ReturnsFalse(){
        Boolean response = menuController.checkingUserInput("2");

        assertThat(response, is(equalTo(false)));
    }

    @Test
    public void GettingErrorMessageWhenInvalidOptionChosen_WhenCalled_ReturnsMessage(){

        String errorMessage = menuController.GettingErrorMessageWhenInvalidOptionChosen();
        String actualErrorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        assertThat(errorMessage, is(equalTo(actualErrorMessage)));
    }

    @Test
    public void parseUserInput_SendingString_ReturnsInt(){
        String stringTest = "1";
        int result = menuController.parseUserInput(stringTest);

        assertThat(result, is(equalTo(1)));
    }

}


