package Personas;

import Enumeraciones.Carpa;
import Enumeraciones.Estacionamiento;
import Enumeraciones.NivelPermisos;
import Enumeraciones.Servicio;
import Gestores.Reservas.GestorReservasCarpas;
import Gestores.Reservas.GestorReservasEstacionamiento;

public class Cliente extends Persona{
    private Long telefono;
    private String correo;
    private Carpa carpa;
    private Estacionamiento estacionamiento;
    private Servicio servicio;
    private double valor_Total;
    /*
    private int numCarpa;
    private int numEstacionamiento;
    */

    public Cliente(String nombre, String apellido, String dni, Long telefono, String correo) {
        super(nombre, apellido, dni);
        this.nivelPermisos = NivelPermisos.CLIENTE;
        this.telefono = telefono;
        this.correo = correo;
        this.carpa = getCarpa();
        this.estacionamiento = getEstacionamiento();
        this.servicio = getServicio();
        this.valor_Total = 0;
        /*
        this.numCarpa = 0;
        this.numEstacionamiento = 0;
         */
    }
    public Cliente() {
    }

    public Long getTelefono() {
        return telefono;
    }

    /* metodos */

    public Integer conseguirNumeroCarpa(){
        GestorReservasCarpas gestor_reservas_carpas = new GestorReservasCarpas();
        gestor_reservas_carpas.cargarDesdeArchivo("reservas-carpas.json");

        return gestor_reservas_carpas.buscarPorCliente(this).getId();
    }

    public Integer conseguirNumeroEstacionamiento(){
        GestorReservasEstacionamiento gestor_reservas_estacionamiento = new GestorReservasEstacionamiento();
        gestor_reservas_estacionamiento.cargarDesdeArchivo("reservas-estacionamiento.json");

        return gestor_reservas_estacionamiento.buscarPorCliente(this).getId();
    }

    /* getters y setters */
    public void setTelefono(Long telefono) {
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

    /*
    public int getNumCarpa() {
        return numCarpa;
    }

    public void setNumCarpa(int numCarpa) {
        this.numCarpa = numCarpa;
    }

    public int getNumEstacionamiento() {
        return numEstacionamiento;
    }

    public void setNumEstacionamiento(int numEstacionamiento) {
        this.numEstacionamiento = numEstacionamiento;
    }
     */
}
