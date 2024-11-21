package Gestores;

import Servicios.Estacionamiento;

public class GestorEstacionamientos extends Gestor<Estacionamiento> {
    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Estacionamiento.class);
    }

    /* métodos */
    public Estacionamiento buscarReservablePorTipo(Enumeraciones.Estacionamiento tipoEstacionamiento) {
        for(Estacionamiento elemento : getLista()){
            if(elemento.esReservable() && elemento.getTipoEstacionamiento() == tipoEstacionamiento){
                return elemento;
            }
        }
        return null;
    }
}
