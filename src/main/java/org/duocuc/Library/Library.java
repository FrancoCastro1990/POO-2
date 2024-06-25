package org.duocuc.Library;

import org.duocuc.Book.Book;
import org.duocuc.Exceptions.BookNotAvailableException;
import org.duocuc.Exceptions.BookNotFoundException;
import org.duocuc.Exceptions.UserNotFoundException;
import org.duocuc.Repository.Repository;
import org.duocuc.User.User;

import java.util.*;

public class Library {

    private HashMap<String, User> users;
    private ArrayList<Book> books;

    private HashSet<Book> borrowedBooks;

    private Repository<Book> bookRepository;
    private Repository<User> userRepository;

    public Library() {
        this.users = new HashMap<>();
        this.books = new ArrayList<>();
        this.borrowedBooks = new HashSet<>();
    }
    public Library(HashMap<String, User> users, ArrayList<Book> books) {
        this.users = users;
        this.books = books;
    }

    public Library(Repository<Book> bookRepository, Repository<User> userRepository) {
        this.users = new HashMap<>();
        this.books = new ArrayList<>();
        this.borrowedBooks = new HashSet<>();
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        createBorrowedBooks(books);
    }


    public void addUser(User user) {

        this.users.put(user.getRut(), user);
        this.userRepository.add(user);
    }

    //metodo para buscar un usuario
    public User findUser(String rut) throws UserNotFoundException{
        User user = users.get(rut);
        if (user == null) {
            throw new UserNotFoundException("Usuario con RUT '" + rut + "' no encontrado.");
        }
        return user;
    }

    //metodo para eliminar un usuario
    public void deleteUser(String rut) throws UserNotFoundException{
        User removedUser = users.remove(rut);
        if (removedUser == null) {
            throw new UserNotFoundException("Usuario con RUT '" + rut + "' no encontrado.");
        } else {
            this.userRepository.updateAll(new ArrayList<>(users.values()));
        }
    }

    public void addBook(Book book) {
        books.add(book);
        this.bookRepository.add(book);
    }

    //metodo para buscar un libro, de no existir retorna un error
    public Book findBook(String query) throws BookNotFoundException, BookNotAvailableException {
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(query.toLowerCase())) {
                return book;
            }
        }
        throw new BookNotFoundException();
    }

    //metodo para eliminar un libro, de no existir retorna un error
    public Book deleteBook(String name) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                books.remove(book);
                bookRepository.updateAll(books);
                return book;
            }
        }
        throw new BookNotFoundException("El libro con el nombre '" + name + "' no fue encontrado.");
    }

    public Set<Book> getBooksInOrder() {
        return new TreeSet<>(books);
    }

    //metodo para prestar un libro, si el libro ya esta prestado retorna un error
    public Book borrowBook(String bookName) throws BookNotFoundException, BookNotAvailableException {
        Book book = findBook(bookName);
        if (!borrowedBooks.add(book)) {
            throw new BookNotAvailableException("El libro '" + bookName + "' ya está prestado.");
        }
        for(Book currentBook:books){
            if(currentBook.equals(book)) {
                currentBook.setAvailable(false);
            }
        }
        bookRepository.updateAll(books);
        return book;
    }

    //metodo para devolver un libro, si el libro no esta registrado como prestado devuelve un error
    public Book returnBook(String bookName) throws BookNotFoundException, BookNotAvailableException {
        Book book = findBook(bookName);
        if (!borrowedBooks.remove(book)) {
            throw new BookNotFoundException("El libro '" + bookName + "' no está registrado como prestado.");
        }
        for(Book currentBook:books){
            if(currentBook.equals(book)) {
                currentBook.setAvailable(true);
            }
        }
        bookRepository.updateAll(books);
        return book;
    }

    public HashSet<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void createBorrowedBooks(ArrayList<Book> books) {
        for(Book book:books){
            if(book.isAvailable() == false){
                borrowedBooks.add(book);
            }
        }
    }
}
