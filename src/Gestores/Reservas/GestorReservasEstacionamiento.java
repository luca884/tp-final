package Gestores.Reservas;

import Enumeraciones.Estado;
import Excepciones.ElementoDuplicadoException;
import Gestores.Gestor;
import Reservas.Reserva;
import Servicios.Carpa;
import Servicios.Estacionamiento;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashSet;

public class GestorReservasEstacionamiento extends Gestor<Reserva<Estacionamiento>> {

    @Override
    public void agregar(Reserva<Estacionamiento> reserva) throws ElementoDuplicadoException {
        Estacionamiento estacionamiento = reserva.getServicio();
        if (estacionamiento.esReservable()) {
            estacionamiento.setEstado(Estado.OCUPADO);
            super.agregar(reserva);
            Estacionamiento.cambiarLugaresDispoibles(-1);
        }
    }

    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        Type type = TypeToken.getParameterized(HashSet.class, TypeToken.getParameterized(Reserva.class, Estacionamiento.class).getType()).getType();
        super.cargarDesdeArchivo(nombre_archivo, type);
        Estacionamiento.cambiarLugaresDispoibles(-getLista().size());
    }

    public void agregarAlArchivo(Reserva<Estacionamiento> estacionamiento, String nombre_archivo) {
        Type type = TypeToken.getParameterized(HashSet.class, TypeToken.getParameterized(Reserva.class, Estacionamiento.class).getType()).getType();
        super.agregarAlArchivo(estacionamiento, nombre_archivo, type);
    }

}
