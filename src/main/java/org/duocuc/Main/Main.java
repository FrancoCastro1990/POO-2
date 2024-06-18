package org.duocuc.Main;

import org.duocuc.Book.Book;
import org.duocuc.Exceptions.BookNotAvailableException;
import org.duocuc.Exceptions.BookNotFoundException;
import org.duocuc.Exceptions.UserInputException;
import org.duocuc.Library.Library;
import org.duocuc.Manager.Manager;
import org.duocuc.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        //AGREGAMOS UNOS LIBROS DE PRUEBA
        Book book1 = new Book(101, "Cien años de soledad", "Gabriel García Márquez", true);
        Book book2 = new Book(102, "1984", "George Orwell", true);
        Book book3 = new Book(103, "Don Quijote de la Mancha", "Miguel de Cervantes", true);
        Book book4 = new Book(104, "Matar a un ruiseñor", "Harper Lee", true);
        Book book5 = new Book(105, "El Gran Gatsby", "F. Scott Fitzgerald", true);
        Book book6 = new Book(106, "Crimen y castigo", "Fiódor Dostoyevski", true);
        Book book7 = new Book(107, "Orgullo y prejuicio", "Jane Austen", true);
        Book book8 = new Book(108, "Ulises", "James Joyce", true);
        Book book9 = new Book(109, "El Hobbit", "J.R.R. Tolkien", true);
        Book book10 = new Book(110, "Fahrenheit 451", "Ray Bradbury", true);
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10));


        library.setBooks(books);

        Manager manager = new Manager(library);
        //MOSTRAMOS EL MENU
        manager.showMenu();

    }
}