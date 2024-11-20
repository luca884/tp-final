package Gestores;

import Servicios.Carpa;

public class GestorCarpas extends Gestor<Carpa> {
    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Carpa.class);
    }
}