package org.duocuc.Book;

public class Book implements Comparable<Book>{

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    private long id;
    private String name;
    private String author;
    private boolean isAvailable;


    public Book(long id, String name, String author, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    @Override
    public int compareTo(Book other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return name != null ? name.equalsIgnoreCase(book.name) : book.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.toLowerCase().hashCode() : 0;
    }
}
