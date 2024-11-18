package Personas;

import Enumeraciones.NivelPermisos;

public abstract class Persona {

    private String nombre;
    private String apellido;
    private final String dni;
    protected NivelPermisos nivelPermisos;

    protected Persona(String nombre, String apellido, String dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public NivelPermisos getNivelPermisos(){
        return this.nivelPermisos;
    }

    public void setNivelPermisos(NivelPermisos nivelPermisos){
        this.nivelPermisos = nivelPermisos;
    }
}
