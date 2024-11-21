package Gestores.Reservas;

import Enumeraciones.Estado;
import Excepciones.ElementoDuplicadoException;
import Gestores.Gestor;
import Personas.Cliente;
import Reservas.Reserva;
import Servicios.Carpa;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;

public class GestorReservasCarpas extends Gestor<Reserva<Carpa>> {
    /* metodos */
    public Reserva<Carpa> buscarPorCliente(Cliente cliente){
        for(Reserva<Carpa> reserva : getLista()){
            if(reserva.getCliente().equals(cliente)) {
                return reserva;
            }
        }
        return null;
    }

    @Override
    public void agregar(Reserva<Carpa> reserva) throws ElementoDuplicadoException {
        Carpa carpa = reserva.getServicio();
        if (carpa.esReservable()) {
            carpa.setEstado(Estado.OCUPADO);
            super.agregar(reserva);
        }
    }

    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        Type type = TypeToken.getParameterized(HashSet.class, TypeToken.getParameterized(Reserva.class, Carpa.class).getType()).getType();
        super.cargarDesdeArchivo(nombre_archivo, type);
    }

    // TODO: averiguar qué carajo pasa con el tercer argumento
    /*
    public void agregarAlArchivo(Reserva<Carpa> carpa, String nombre_archivo) {
        //super.agregarAlArchivo(carpa, nombre_archivo, Reserva<Carpa>.class);
    }
    */

}
