package Gestores;

import Servicios.Carpa;
import Servicios.Estacionamiento;

public class GestorEstacionamientos extends Gestor<Estacionamiento> {
    /* metodos */
    public Estacionamiento buscarReservablePorTipo(Enumeraciones.Estacionamiento tipo_de_estacionamiento) {
        for (Estacionamiento estacionamiento : getLista()) {
            if (estacionamiento.esReservable() && estacionamiento.getTipo_de_estacionamiento() == tipo_de_estacionamiento) {
                return estacionamiento;
            }
        }
        return null;
    }

    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Carpa.class);
    }
}