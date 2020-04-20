package com.twu.biblioteca.Controllers;

import com.twu.biblioteca.Models.User;
import com.twu.biblioteca.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginController {

    UserRepository userRepository = new UserRepository();
    ArrayList<User> users = userRepository.getUsers();
    Scanner in = new Scanner(System.in);

    public int main(){
        String libraryCode = "";
        Boolean doesCodeExist = false;
        int userId = -1;

        while (!doesCodeExist){
            System.out.println("Please inform your library code:");
            libraryCode = readingUserOutput();
            doesCodeExist = doesLibraryCodeExist(libraryCode);

            if(!doesCodeExist) System.out.println("This user doesn't exist. Try again!");
        }

        System.out.println("Please inform your password:");
        String password = in.next();

        Boolean isPasswordCorrect = checkingUserPassword(libraryCode, password);

        if(isPasswordCorrect){
            userId = gettingUserId(libraryCode);
        }
        else{
            Boolean newAttempt = threeMorePasswordAttempts(libraryCode);

            if(newAttempt)
                userId = gettingUserId(libraryCode);
        }
        return userId;
    }

    public int gettingUserId(String libraryCode){
        int userId = -1;

        for(User user : users){
            if(user.getLibraryNumber().equals(libraryCode))
                userId = user.getId();
        }

        return userId;
    }

    public String readingUserOutput(){
        String input = "";
        Boolean flag;

        do{
            input = in.next();
            flag = validatingUserInput(input);
            if(!flag) System.out.println("The library code format is xxx-xxxx! Please try again:");
        } while(!flag);

        return input;
    }

    public Boolean threeMorePasswordAttempts(String libraryCode){
        Boolean newAttempt = false;

        for(int i = 0; i < 3; i ++){
            System.out.println("Password incorrect. Try again:");
            String password = in.next();
            newAttempt = checkingUserPassword(libraryCode, password);

            if(newAttempt)
                return true;
        }

        return false;
    }

    public Boolean validatingUserInput(String input){
        String inputPattern = "\\d{3}-\\d{4}";

        return input.matches(inputPattern);
    }

    public Boolean doesLibraryCodeExist(String libraryCode){
        for(User user: users){
            if(user.getLibraryNumber().equals(libraryCode))
                return true;
        }
        return false;
    }

    public Boolean checkingUserPassword(String libraryCode, String password){
        for(User user: users){
            if(user.getLibraryNumber().equals(libraryCode)){
                if(user.getPassword().equals(password))
                    return true;
            }
        }
        return false;
    }

}
