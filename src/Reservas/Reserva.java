package Reservas;

import Personas.Cliente;
import Servicios.Servicio;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Reserva <T extends Servicio> {
    /* atributos */
    static private Integer contador_id = 0;
    private Integer id;

    private T servicio;
    private Cliente cliente;
    private LocalDate fecha;

    /* constructores */
    public Reserva(T servicio, Cliente cliente, LocalDate fecha) {
        this.servicio = servicio;
        this.cliente = cliente;
        this.fecha = fecha;
        contador_id++;
        this.id = contador_id;
    }

    /* metodos */

    /* toString */
    @Override
    public String toString() {
        return "id: %s\ncliente: %s\nServicio: %s".formatted(id, cliente.getDni(), servicio.getIdServicio());
    }

    /* getters y setters */
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(T servicio) {
        this.servicio = servicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }
}
