package com.twu.biblioteca;

import com.twu.biblioteca.Models.Welcome;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WelcomeTests {

    Welcome welcomeObj;

    @Before
    public void setUp(){
        welcomeObj = new Welcome();
    }

    @Test
    public void WelcomeMessage(){
        String welcomeMessage = "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!";
        assertThat(welcomeObj.message(), is(equalTo(welcomeMessage)));
    }

}
