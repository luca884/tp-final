package Servicios;

public class Estacionamiento  extends Servicio {
    /* atributos */
    static private Integer capacidad = 70;
    static private Integer lugares_disponibles = 70;

    /* constructores */
    public Estacionamiento() {
        super();
    }
    public Estacionamiento(double precio) {
        super(precio);
    }

    /* metodos */
    public Boolean esReservable(){
        return Estacionamiento.lugares_disponibles > 0;
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
}
