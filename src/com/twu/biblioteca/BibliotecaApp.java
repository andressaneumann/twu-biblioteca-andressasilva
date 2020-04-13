package com.twu.biblioteca;

import com.twu.biblioteca.Controllers.MenuController;

public class BibliotecaApp {

    public static void main(String[] args) {

        System.out.println("Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!");
        MenuController bibliotecaMenu = new MenuController();

        bibliotecaMenu.Menu();
    }
}
