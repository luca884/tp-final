package Gestores;

import Excepciones.ElementoDuplicadoException;
import Excepciones.ElementoNoEncontradoException;
import Json.LocalDateAdapter;
import Personas.Empleado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashSet;

public abstract class Gestor<T> {
    /* atributos */
    protected HashSet<T> lista = new HashSet<>();
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();


    /* constructores */
    public Gestor() {
    }

    public Gestor(HashSet<T> lista) {
        this.lista = lista;
    }


    /* metodos */
    public void agregar(T item) throws ElementoDuplicadoException {
        if (lista.contains(item)) {
            throw new ElementoDuplicadoException("El elemento ya se encuentra en la lista");
        }
        lista.add(item);
    }

    public void eliminar(T item) throws ElementoNoEncontradoException {
        if (!lista.contains(item)) {
            throw new ElementoNoEncontradoException("El elemento no se encontró en la lista");
        }
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
            file.write(gson.toJson(getLista()));
            System.out.println("Archivo guardado con éxito.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarDesdeArchivo(String nombre_archivo, Class<T> clazz) {
        System.out.println("Carga de archivo: " + nombre_archivo);
        Type type = TypeToken.getParameterized(HashSet.class, clazz).getType();
        cargarDesdeArchivo(nombre_archivo, type);
    }

    public void cargarDesdeArchivo(String nombre_archivo, Type type) {
        try (FileReader reader = new FileReader(nombre_archivo)) {
            setLista(gson.fromJson(reader, type));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void agregarAlArchivo(T item, String nombre_archivo, Class<T> clazz) {
        Type type = TypeToken.getParameterized(HashSet.class, clazz).getType();
        agregarAlArchivo(item, nombre_archivo, type);
    }

    public void agregarAlArchivo(T item, String nombre_archivo, Type type) {
        // carga clientes guardados en archivo, si existe el archivo
        File archivo = new File(nombre_archivo);
        if (archivo.exists()) {
            cargarDesdeArchivo(nombre_archivo, type);
            // cargarDesdeArchivo(nombre_archivo, clazz);
        }

        try {
            // agrega cliente al gestor
            agregar(item);

            // actualiza archivo
            guardarEnArchivo(nombre_archivo);
        } catch (ElementoDuplicadoException ex) {
            System.err.println(ex.getMessage());
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
