package Gestores;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;

public abstract class Gestor<T> {
    /* atributos */
    public HashSet<T> lista = new HashSet<>();

    /* constructores */
    public Gestor() {
    }

    public Gestor(HashSet<T> lista) {
        this.lista = lista;
    }

    /* metodos */
    public void agregar(T item) {
        lista.add(item);
    }

    public void eliminar(T item) {
        lista.remove(item);
    }

    public void mostrar(T item) {
        /* todo: implementar los toString para que funcione bien */
        System.out.println(item);
    }

    public void mostrar() {
        for (T item : lista) {
            mostrar(item);
            System.out.println("-".repeat(24));
        }
    }

    public T buscar(T item) {
        /*
            este método no tiene mucho sentido
            sobreescribir/sobrecargar en cada clase derivada
            para buscar por el campo identificador o el que sea conveniente
         */
        for (T _item : lista) {
            if (_item.equals(item)) {
                return _item;
            }
        }
        return null;
    }

    /* archivos json */
    public void guardarEnArchivo(String nombre_archivo) {
        try (FileWriter file = new FileWriter(nombre_archivo)) {
            Gson gson = new Gson();
            file.write(gson.toJson(getLista()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarDesdeArchivo(String nombre_archivo, Class<T> clazz) {
        try (FileReader reader = new FileReader(nombre_archivo)) {
            Gson gson = new Gson();
            //Type type = new TypeToken<HashSet<T>>() {}.getType();
            Type type = TypeToken.getParameterized(HashSet.class, clazz).getType();
            setLista(gson.fromJson(reader, type));
            //setLista(gson.fromJson(reader, getLista().getClass()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /* getters y setters */
    public HashSet<T> getLista() {
        return lista;
    }

    public void setLista(HashSet<T> lista) {
        this.lista = lista;
    }
}
