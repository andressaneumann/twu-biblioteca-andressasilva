package com.twu.biblioteca.Controllers;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    Scanner in = new Scanner(System.in);
    Boolean isAnswerValid = false;

    public void Menu(){

        while(!isAnswerValid){
            System.out.println("Please choose between the available options and press the respective number: ");
            ArrayList<String> availableOptions = AvailableOptions();

            for(int i = 0; i < availableOptions.size(); i++)
                System.out.println(availableOptions.get(i));

            String userAnswer = in.nextLine();
            isAnswerValid = CheckingUserInput(userAnswer);
        }
    }

    public ArrayList<String> AvailableOptions(){

        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("1. List of Books");

        return availableOptions;
    }

    public Boolean CheckingUserInput(String userAnswer){

        if(userAnswer.equals("1"))
            return true;
        else{
            System.out.println(GettingErrorMessageWhenInvalidOptionChosen());
            return false;
        }
    }

    public String GettingErrorMessageWhenInvalidOptionChosen(){
        String errorMessage = "\n-- You need to choose between the available options, please try again! --\n";

        return errorMessage;
    }
}
