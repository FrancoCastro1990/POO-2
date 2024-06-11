package org.duocuc.Main;

import org.duocuc.Book.Book;
import org.duocuc.Exceptions.BookNotAvailableException;
import org.duocuc.Exceptions.BookNotFoundException;
import org.duocuc.Exceptions.UserInputException;
import org.duocuc.Library.Library;
import org.duocuc.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book(101, "Cien años de soledad", "Gabriel García Márquez", true);
        Book book2 = new Book(102, "1984", "George Orwell", false);
        Book book3 = new Book(103, "Don Quijote de la Mancha", "Miguel de Cervantes", true);
        Book book4 = new Book(104, "Matar a un ruiseñor", "Harper Lee", true);
        Book book5 = new Book(105, "El Gran Gatsby", "F. Scott Fitzgerald", false);
        Book book6 = new Book(106, "Crimen y castigo", "Fiódor Dostoyevski", true);
        Book book7 = new Book(107, "Orgullo y prejuicio", "Jane Austen", true);
        Book book8 = new Book(108, "Ulises", "James Joyce", false);
        Book book9 = new Book(109, "El Hobbit", "J.R.R. Tolkien", true);
        Book book10 = new Book(110, "Fahrenheit 451", "Ray Bradbury", true);
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10));

        HashMap<String, Book> hashBooks = new HashMap<>();
        for(Book book: books) {
            System.out.println("nombre: "+book.getName()+", autor: "+book.getAuthor());
            hashBooks.put(book.getName()+"_"+book.getAuthor(), book);
        }

        library.setBooks(hashBooks);

        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users= new ArrayList<>();

        String userName;
        try{
            System.out.println("Ingrese su nombre: ");
            userName=scanner.nextLine();
            if (userName.trim().isEmpty()) {
                throw new UserInputException("Su nombre no puede estar vacio.");
            }
        }  catch (UserInputException e) {
            System.err.println(e.getMessage());
            return;
        }

        User currentUser = new User(userName);
        users.add(currentUser);

        library.setUsers(users);


        Book foundBook = null;

        do{
            try {
                System.out.println("Ingrese el nombre del libro o el autor a buscar para pedir prestado:");
                String searchQuery = scanner.nextLine().toLowerCase();
                if (searchQuery.trim().isEmpty()) {
                    throw new UserInputException("La consulta de búsqueda no puede estar vacía.");
                }
                if(searchQuery.trim().length()<3){
                    throw new UserInputException("Ingrese 3 o mas caracteres.");
                }
                foundBook = library.borrowBook(searchQuery);
                System.out.println("Libro encontrado:");
                System.out.println(foundBook.getName()+" - "+foundBook.getAuthor());
                System.out.println("Ahora el libro no se encuentra disponible para su prestamo.");
            } catch (BookNotFoundException | BookNotAvailableException e) {
                System.err.println(e.getMessage());
            } catch (UserInputException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }while(foundBook == null);
    }
}