package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.LoginController;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginControllerTests {

    LoginController loginController;

    @Before
    public void setUp(){
        loginController = new LoginController();
    }

    @Test
    public void validatingUserInput_SendingAValidInput_ReturnsTrue(){
        String input = "123-4561";
        Boolean isInputValid = loginController.validatingUserInput(input);

        assertThat(isInputValid, is(equalTo(true)));
    }

    @Test
    public void validatingUserInput_SendingAnInvalidInput_ReturnsFalse(){
        String input = "123";
        Boolean isInputValid = loginController.validatingUserInput(input);

        assertThat(isInputValid, is(equalTo(false)));
    }

    @Test
    public void doesLibraryCodeExist_SendingAValidLibraryCode_ReturnsTrue(){
        String libraryCode = "000-0001";
        Boolean isInputValid = loginController.doesLibraryCodeExist(libraryCode);

        assertThat(isInputValid, is(equalTo(true)));
    }

    @Test
    public void doesLibraryCodeExist_SendingAnInvalidLibraryCode_ReturnsFalse(){
        String libraryCode = "000-0012";
        Boolean isInputValid = loginController.doesLibraryCodeExist(libraryCode);

        assertThat(isInputValid, is(equalTo(false)));
    }

    @Test
    public void checkingUserPassword_SendingAValidLibraryCodeAndPassword_ReturnsTrue(){
        String libraryCode = "000-0001";
        String password = "123#";

        Boolean isInputValid = loginController.checkingUserPassword(libraryCode, password);

        assertThat(isInputValid, is(equalTo(true)));
    }

    @Test
    public void checkingUserPassword_SendingAnInvalidLibraryCodeAndPassword_ReturnsFalse(){
        String libraryCode = "000-0001";
        String password = "123235";

        Boolean isInputValid = loginController.checkingUserPassword(libraryCode, password);

        assertThat(isInputValid, is(equalTo(false)));
    }

}
