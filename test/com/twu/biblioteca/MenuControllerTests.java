package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MenuController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        Assert.assertEquals(availableOptions.get(0), "1. List of Books");
    }

    @Test
    public void CheckingUserInput_WhenCorrectAnswer_ReturnsTrue(){
        Boolean response = menuController.CheckingUserInput("1");

        Assert.assertEquals(response, true);
    }

    @Test
    public void CheckingUserInput_WhenWrongAnswer_ReturnsFalse(){
        Boolean response = menuController.CheckingUserInput("2");

        Assert.assertEquals(response, false);
    }

    @Test
    public void GettingErrorMessageWhenInvalidOptionChosen_WhenCalled_ReturnsMessage(){

        String errorMessage = menuController.GettingErrorMessageWhenInvalidOptionChosen();

        Assert.assertEquals(errorMessage, "\n-- You need to choose between the available options, please try again! --");
    }

}


