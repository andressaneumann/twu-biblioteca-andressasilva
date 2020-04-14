package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MenuController;

public class BibliotecaApp {


    public static void main(String[] args) {

        Welcome welcome = new Welcome();
        System.out.println(welcome.message());

        MenuController bibliotecaMenu = new MenuController();

        bibliotecaMenu.Menu();
    }
}
