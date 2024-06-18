package org.duocuc.Manager;

import org.duocuc.Book.Book;
import org.duocuc.Exceptions.BookNotAvailableException;
import org.duocuc.Exceptions.BookNotFoundException;
import org.duocuc.Exceptions.UserInputException;
import org.duocuc.Exceptions.UserNotFoundException;
import org.duocuc.Library.Library;
import org.duocuc.User.User;

import java.util.Scanner;

public class Manager {
    private Library library;

    public Manager(Library library) {
        this.library = library;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            option = 0;
            System.out.println("----------------");
            System.out.println("[1] agregar usuario");
            System.out.println("[2] Buscar usuario");
            System.out.println("[3] Eliminar usuario");

            System.out.println("[4] agregar libro");
            System.out.println("[5] Buscar libro");
            System.out.println("[6] Eliminar libro");

            System.out.println("[7] Ver libros en orden alfabetico");

            System.out.println("[8] Prestar un libro");
            System.out.println("[9] devolver un libro");
            System.out.println("[10] mostrar libros prestados");
            System.out.println("[11] Salir");
            System.out.println("ingresa una opcion: ");
            try{
                option = scanner.nextInt();
            }catch (Exception e) {
                scanner.nextLine();
                System.out.println("Ingrese un numero.");
            }

            if(option<1 || option>11) {
                System.out.println("Ingrese una opcion valida.");
            } else {
                switch (option) {
                    //registriar usuario
                    case 1: registerUser();
                        break;
                        //buscar usuario por rut
                    case 2: findUser();
                        break;
                        //eliminar usuario por rut
                    case 3:deleteUser();
                        break;
                    //agregar libro
                    case 4:
                        registerBook();
                        break;
                        //buscar libro por nombre
                    case 5: findBook();
                       break;
                       //eliminar libro por nombre
                    case 6: deleteBook();
                    break;
                    //obtiene la lista de libros por orden
                    case 7: getBooksInOrder();
                    break;
                    //prestamos un libro
                    case 8: borrowBook();
                    break;
                    //devolvemos un libro
                    case 9: returnBook();
                    break;
                    //obtenemos la lista de libros prestados
                    case 10: getBorrowBooks();
                    break;
                    case 11: return;
                }
            }

        } while (option!=11);
    }

    private void registerUser() {
        Scanner scanner = new Scanner(System.in);

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

        String rut;
        try{
            System.out.println("Ingrese su rut: ");
            rut=scanner.nextLine();
            if (rut.trim().isEmpty()) {
                throw new UserInputException("Su rut no puede estar vacio.");
            }
        }  catch (UserInputException e) {
            System.err.println(e.getMessage());
            return;
        }

        User currentUser = new User(userName, rut);
        library.addUser(currentUser);
    }

    private void findUser() {
        Scanner scanner = new Scanner(System.in);
        String rut;

        try {
            System.out.println("Ingrese el rut a buscar: ");
            rut=scanner.nextLine();
            User user = library.findUser(rut);
            System.out.println("Usuario encontrado!");
            System.out.println(user.getName()+" "+user.getRut());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        String rut;

        try {
            System.out.println("Ingrese el rut a buscar: ");
            rut=scanner.nextLine();
            library.deleteUser(rut);
            System.out.println("Usuario Eliminado!");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }

    private void registerBook() {
        Scanner scanner = new Scanner(System.in);

        String name;
        try{
            System.out.println("Ingrese el nombre del libro: ");
            name=scanner.nextLine();
            if (name.trim().isEmpty()) {
                throw new UserInputException("Su nombre no puede estar vacio.");
            }
        }  catch (UserInputException e) {
            System.err.println(e.getMessage());
            return;
        }

        String author;
        try{
            System.out.println("Ingrese el autor del libro: ");
            author=scanner.nextLine();
            if (author.trim().isEmpty()) {
                throw new UserInputException("Su nombre no puede estar vacio.");
            }
        }  catch (UserInputException e) {
            System.err.println(e.getMessage());
            return;
        }


        Book book = new Book(generateRandomId(),name, author,true);
        library.addBook(book);
    }

    private void findBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese el nombre del libro a buscar:");
            String searchQuery = scanner.nextLine().toLowerCase();
            if (searchQuery.trim().isEmpty()) {
                throw new UserInputException("La consulta de búsqueda no puede estar vacía.");
            }
            if(searchQuery.trim().length()<3){
                throw new UserInputException("Ingrese 3 o mas caracteres.");
            }
            Book foundBook = library.findBook(searchQuery);
            System.out.println("Libro encontrado:");
            System.out.println(foundBook.getName()+" - "+foundBook.getAuthor());
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (UserInputException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese el nombre del libro a eliminar:");
            String searchQuery = scanner.nextLine().toLowerCase();
            if (searchQuery.trim().isEmpty()) {
                throw new UserInputException("La consulta de búsqueda no puede estar vacía.");
            }
            if(searchQuery.trim().length()<3){
                throw new UserInputException("Ingrese 3 o mas caracteres.");
            }
            Book book=library.deleteBook(searchQuery);
            System.out.println("El libro: "+book.getName()+" Se elimino correctamente.");
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (UserInputException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void getBooksInOrder() {
        System.out.println("Libros en orden alfabético:");
        for (Book book : library.getBooksInOrder()) {
            System.out.println(book.getName());
        }
    }

    private void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese el nombre del libro a pedir prestado:");
            String searchQuery = scanner.nextLine().toLowerCase();
            if (searchQuery.trim().isEmpty()) {
                throw new UserInputException("La consulta de búsqueda no puede estar vacía.");
            }
            if(searchQuery.trim().length()<3){
                throw new UserInputException("Ingrese 3 o mas caracteres.");
            }
            Book foundBook= library.borrowBook(searchQuery);
            System.out.println("El libro:");
            System.out.println(foundBook.getName()+" ahora no se encuentra disponible");
        } catch (BookNotFoundException | BookNotAvailableException e) {
            System.err.println(e.getMessage());
        } catch (UserInputException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void returnBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese el nombre del libro a devolver:");
            String searchQuery = scanner.nextLine().toLowerCase();
            if (searchQuery.trim().isEmpty()) {
                throw new UserInputException("La consulta de búsqueda no puede estar vacía.");
            }
            if(searchQuery.trim().length()<3){
                throw new UserInputException("Ingrese 3 o mas caracteres.");
            }
            Book foundBook= library.returnBook(searchQuery);
            System.out.println("El libro:");
            System.out.println(foundBook.getName()+" ahora se encuentra disponible");
        } catch (BookNotFoundException | BookNotAvailableException e) {
            System.err.println(e.getMessage());
        } catch (UserInputException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void getBorrowBooks() {
        if(library.getBorrowedBooks().isEmpty()) {
            System.out.println("No hay libros prestados");
        } else {
            System.out.println("Libros prestados:");
            for (Book book : library.getBorrowedBooks()) {
                System.out.println(book.getName());
            }
        }
    }

    private long generateRandomId() {
        long randomNumber = (long) (Math.random() * 1_000_000_000L);

        while (randomNumber < 100_000_000L) {
            randomNumber = (long) (Math.random() * 1_000_000_000L);
        }
        return randomNumber;
    }
}
