package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MenuController;
import com.twu.biblioteca.Models.Welcome;

public class BibliotecaApp {


    public static void main(String[] args) {

        Welcome welcome = new Welcome();
        System.out.println(welcome.message());

        MenuController bibliotecaMenu = new MenuController();

        bibliotecaMenu.main();
    }
}
