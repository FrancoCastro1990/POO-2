package org.duocuc.Library;

import org.duocuc.Book.Book;
import org.duocuc.Exceptions.BookNotAvailableException;
import org.duocuc.Exceptions.BookNotFoundException;
import org.duocuc.User.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private ArrayList<User> users;
    private HashMap<String,Book> books;

    public Library(){}

    public Library(ArrayList<User> users, HashMap<String, Book> books) {
        this.users = users;
        this.books = books;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public  HashMap<String, Book> getBooks() {
        return books;
    }

    public void setBooks( HashMap<String, Book> books) {
        this.books = books;
    }

    public Book borrowBook(String query) throws BookNotFoundException, BookNotAvailableException {
        for (String bookKey : books.keySet()) {
            if (bookKey.toLowerCase().contains(query)) {
                Book foundBook = books.get(bookKey);

                if (!foundBook.isAvailable()) {
                    throw new BookNotAvailableException("El libro '" + foundBook.getName() + "' no está disponible.");
                }

                foundBook.setAvailable(false);

                books.put(bookKey, foundBook);
                return foundBook;
            }
        }
        throw new BookNotFoundException();
    }
}
