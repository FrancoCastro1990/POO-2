package org.duocuc.Exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message) {
        super(message);
    }
    public BookNotFoundException(){
        super("No se encontró ningún libro que coincida con la búsqueda.");

    }
}
