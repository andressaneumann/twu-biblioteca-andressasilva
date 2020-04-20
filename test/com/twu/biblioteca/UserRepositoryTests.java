package com.twu.biblioteca;

import com.twu.biblioteca.Models.Movie;
import com.twu.biblioteca.Models.User;
import com.twu.biblioteca.Repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class UserRepositoryTests {

    UserRepository userRepository;

    @Before
    public void setUp(){
        userRepository = new UserRepository();
    }

    @Test
    public void getUsers_ComparingToAnEmptyList_AssertsNotEquals(){

        ArrayList<User> emptyUserList = new ArrayList<User>();
        ArrayList<User> actualUserList = userRepository.getUsers();

        assertNotEquals(emptyUserList, actualUserList);
    }
}
