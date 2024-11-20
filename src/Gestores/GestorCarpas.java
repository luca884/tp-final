package Gestores;

import Servicios.Carpa;

public class GestorCarpas extends Gestor<Carpa> {
    /* metodos */
    public Carpa buscarReservablePorTipo(Enumeraciones.Carpa tipo_de_carpa) {
        for (Carpa carpa : getLista()) {
            if (carpa.esReservable() && carpa.getTipo_de_carpa() == tipo_de_carpa) {
                return carpa;
            }
        }
        return null;
    }

    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Carpa.class);
    }
}