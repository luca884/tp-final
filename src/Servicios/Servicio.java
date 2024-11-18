package Servicios;

import Enumeraciones.Estado;
import Personas.Cliente;
import Reservas.Reserva;

import java.time.LocalDate;

public abstract class Servicio {
    private static int contIdServicio = 0;
    private final int idServicio;
    private double precio;
    private Estado estado;

    /* constuctores */
    public Servicio() {
        contIdServicio++;
        this.idServicio = contIdServicio;
        this.estado = Estado.DISPONIBLE;
    }
    public Servicio(double precio){
        this();
        this.precio = precio;
    }

    /* metodos */
    public Reserva reservar(Cliente cliente, LocalDate fecha){
        return new Reserva(this, cliente, fecha);
    }
    public Reserva reservar(Cliente cliente){
        return this.reservar(cliente, LocalDate.now());
    }

    /* toString */
    @Override
    public String toString() {
        return "id: %s\nprecio: %s\nestado: %s".formatted(idServicio, precio, estado);
    }

    /* getters y setters */
    public int getIdServicio() {
        return idServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
