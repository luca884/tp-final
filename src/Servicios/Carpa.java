package Servicios;

import Enumeraciones.Estado;
import Interfaces.Reservable;

public class Carpa extends Servicio implements Reservable {
    /* atributos */
    private Enumeraciones.Carpa tipo_de_carpa;

    /* constructores */
    public Carpa(Enumeraciones.Carpa tipo_de_carpa) {
        super();
        this.tipo_de_carpa = tipo_de_carpa;
        this.setPrecio(calcularCosto());
    }
    public Carpa() {
        super();
    }

    /* metodos */
    public Double calcularCosto(){
        return switch (tipo_de_carpa) {
            case Enumeraciones.Carpa.VIP -> 1000000.0;
            case Enumeraciones.Carpa.ESTANDAR -> 750000.0;
        };
    }
    public boolean esReservable(){
        return this.getEstado() == Estado.DISPONIBLE;
    }

    /* getters y setters */
    public Enumeraciones.Carpa getTipo_de_carpa() {
        return tipo_de_carpa;
    }
    public void setTipo_de_carpa(Enumeraciones.Carpa tipo_de_carpa) {
        this.tipo_de_carpa = tipo_de_carpa;
    }
}
