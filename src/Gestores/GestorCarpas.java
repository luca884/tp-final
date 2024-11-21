package Gestores;

import Personas.Cliente;
import Servicios.Carpa;

public class GestorCarpas extends Gestor<Carpa> {
    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Carpa.class);
    }


    /* metodos */
    public Carpa buscarReservablePorTipo(Enumeraciones.Carpa carpa) {
        for(Carpa elemento : getLista()){
            if(elemento.esReservable() && elemento.getTipo_de_carpa() == carpa){
                return elemento;
            }
        }
        return null;
    }

}