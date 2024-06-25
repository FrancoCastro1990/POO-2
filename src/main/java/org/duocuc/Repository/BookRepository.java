package org.duocuc.Repository;

import org.duocuc.Book.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements Repository<Book> {
    private File file;
    public BookRepository() {
        this.file = new File("libros.csv");
    }
    @Override
    public void add(Book newBook) {
        try(FileWriter writer = new FileWriter(this.file, true)){
            writer.append(newBook.toString());
            writer.append("\n");
            writer.flush();
        }catch(IOException e) {
            System.out.println("error al agregar un libro");
        }
    }

    @Override
    public List<Book> getAll() {
        ArrayList<Book> books = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                books.add(new Book( Long.parseLong(datos[0]) ,datos[1],datos[2],Boolean.parseBoolean(datos[3])));
            }
        }catch(IOException e) {
            System.out.println("Error al obtener todos los libros");
        }
        return books;
    }

    public void updateAll(List<Book> books) {
        try(FileWriter writer = new FileWriter(this.file )){
            for(Book book:books) {
                writer.write(book.toString()+"\n");
            }
            writer.flush();
            System.out.println("archivo Actualizado correctamente");
        }catch(IOException e) {
            System.out.println("Error al actualizar lista de libros");
        }
    }

}
