package org.duocuc.Main;
import org.duocuc.Library.Library;
import org.duocuc.Manager.Manager;
import org.duocuc.Repository.BookRepository;
import org.duocuc.Repository.UserRepository;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        //CARGAR LIBROS Y USUARIOS
        BookRepository bookRepository = new BookRepository();
        UserRepository userRepository = new UserRepository();

        Library library = new Library(bookRepository,userRepository);

        library.setBooks(new ArrayList<>(bookRepository.getAll()));
        library.setUsers(userRepository.getAllUser());

        Manager manager = new Manager(library);

        manager.showMenu();
    }
}