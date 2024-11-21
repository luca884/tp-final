package Servicios;

import Enumeraciones.Estado;

public class Estacionamiento  extends Servicio {
    /* atributos */
    static private Integer capacidad = 70;
    static private Integer lugares_disponibles = capacidad;
    private Enumeraciones.Estacionamiento tipo_de_estacionamiento ;

    /* constructores */
    public Estacionamiento(Enumeraciones.Estacionamiento tipo_de_estacionamiento) {
        super();
        this.tipo_de_estacionamiento = tipo_de_estacionamiento;
        this.setPrecio(calcularPrecio());
    }

    /* metodos */
    public Boolean esReservable(){
        return Estacionamiento.lugares_disponibles > 0 && getEstado() == Estado.DISPONIBLE;
    }

    public Double calcularPrecio(){
        return switch (tipo_de_estacionamiento) {
            case Enumeraciones.Estacionamiento.VIP -> 500000.0;
            case Enumeraciones.Estacionamiento.ESTANDAR -> 750000.0;
            case Enumeraciones.Estacionamiento.NO_INCLUYE -> null;
        };
    }

    public static void cambiarLugaresDispoibles(Integer numero){
        lugares_disponibles += numero;
    }

    /* getters y setters */
    public static Integer getCapacidad() {
        return capacidad;
    }

    public static void setCapacidad(Integer capacidad) {
        Estacionamiento.capacidad = capacidad;
    }

    public static Integer getLugares_disponibles() {
        return lugares_disponibles;
    }

    public static void setLugares_disponibles(Integer lugares_disponibles) {
        Estacionamiento.lugares_disponibles = lugares_disponibles;
    }

    public Enumeraciones.Estacionamiento getTipo_de_estacionamiento() {
        return tipo_de_estacionamiento;
    }

    public void setTipo_de_estacionamiento(Enumeraciones.Estacionamiento tipo_de_estacionamiento) {
        this.tipo_de_estacionamiento = tipo_de_estacionamiento;
    }
}