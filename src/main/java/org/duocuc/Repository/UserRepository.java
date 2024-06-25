package org.duocuc.Repository;

import org.duocuc.User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository implements Repository<User>{
    private File file;
    public UserRepository() {
        this.file = new File("usuarios.txt");
    }

    @Override
    public void add(User newUser) {
        try{
            FileWriter writer = new FileWriter(this.file, true);
            writer.write(newUser.getName()+","+newUser.getRut()+"\n");
            writer.flush();
        }catch(IOException e) {
            System.out.println("error al agregar un usuario");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try( BufferedReader  reader = new BufferedReader(new FileReader(this.file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                System.out.println("Nombre: " + datos[0] + " , DNI: " + datos[1]);
                users.add(new User(datos[0],datos[1]));
            }

        }catch(IOException e) {
            System.out.println("Error al obtener todos los usuarios");
        }
        return users;
    }

    public void updateAll(List<User> users) {
        try(FileWriter writer = new FileWriter(this.file)){
            for(User user:users) {
                writer.write(user.getName()+","+user.getRut()+"\n");
            }
            writer.flush();
            System.out.println("archivo Actualizado correctamente");
        }catch(IOException e) {
            System.out.println("Error al actualizar lista de usuarios");
        }
    }

    public HashMap<String, User> getAllUser() {
        List<User> usersData=new ArrayList<>(this.getAll());
        HashMap<String, User> users= new HashMap<>();
        for(User user:usersData) {
            users.put(user.getRut(), user);
        }
        return users;
    }

}
