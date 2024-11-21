package Gestores;

import Excepciones.ElementoDuplicadoException;
import Excepciones.ElementoNoEncontradoException;
import Varios.Mantenimiento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;

public class GestorMantenimiento extends Gestor<Mantenimiento> {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Constructor
    public GestorMantenimiento() {
        super();
    }

    // Método para guardar en archivo
    @Override
    public void guardarEnArchivo(String nombre_archivo) {
        try (FileWriter file = new FileWriter(nombre_archivo)) {
            file.write(gson.toJson(getLista()));
            System.out.println("Archivo guardado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Método para cargar desde archivo
    @Override
    public void cargarDesdeArchivo(String nombre_archivo, Type type) {
        try (FileReader reader = new FileReader(nombre_archivo)) {
            setLista(gson.fromJson(reader, type));
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    // Método para agregar un mantenimiento
    @Override
    public void agregar(Mantenimiento mantenimiento) throws ElementoDuplicadoException {
        if (lista.contains(mantenimiento)) {
            throw new ElementoDuplicadoException("El mantenimiento con ese DNI ya está registrado.");
        }
        lista.add(mantenimiento);
    }

    // Método para eliminar un mantenimiento
    @Override
    public void eliminar(Mantenimiento mantenimiento) throws ElementoNoEncontradoException {
        if (!lista.contains(mantenimiento)) {
            throw new ElementoNoEncontradoException("El mantenimiento no se encuentra en la lista.");
        }
        lista.remove(mantenimiento);
    }
}