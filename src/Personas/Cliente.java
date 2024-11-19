package Personas;

import Enumeraciones.Carpa;
import Enumeraciones.Estacionamiento;
import Enumeraciones.NivelPermisos;
import Enumeraciones.Servicio;

public class Cliente extends Persona{
    private int telefono;
    private String correo;
    private Carpa carpa;
    private Estacionamiento estacionamiento;
    private Servicio servicio;
    private double valor_Total;

    public Cliente(String nombre, String apellido, String dni, int telefono, String correo) {
        super(nombre, apellido, dni);
        this.nivelPermisos = NivelPermisos.CLIENTE;
        this.telefono = telefono;
        this.correo = correo;
        this.carpa = getCarpa();
        this.estacionamiento = getEstacionamiento();
        this.servicio = getServicio();
        this.valor_Total = 0;
    }
    public Cliente() {
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Carpa getCarpa() {
        return carpa;
    }

    public void setCarpa(Carpa carpa) {
        this.carpa = carpa;
    }

    public double getValor_Total() {
        return valor_Total;
    }

    public void setValor_Total(double valor_Total) {
        this.valor_Total = valor_Total;
    }
}
