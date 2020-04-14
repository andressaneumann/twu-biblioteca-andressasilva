package com.twu.biblioteca;

public class Welcome implements com.twu.biblioteca.Interfaces.Welcome {
    @Override
    public String message() {
        return "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!";
    }
}
