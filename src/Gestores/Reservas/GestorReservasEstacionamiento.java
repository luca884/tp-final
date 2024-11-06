package Gestores.Reservas;

import Gestores.Gestor;
import Reservas.Reserva;
import Servicios.Estacionamiento;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;

public class GestorReservasEstacionamiento extends Gestor<Reserva<Estacionamiento>> {
    @Override
    public void agregar(Reserva reserva) {
        Estacionamiento estacionamiento = (Estacionamiento) reserva.getServicio();
        if (estacionamiento.esReservable()) {
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
}
