package Personas;

import Enumeraciones.NivelPermisos;

import java.util.HashMap;
import java.util.Objects;

public abstract class Persona {

    private String nombre;
    private String apellido;
    private String dni;
    protected NivelPermisos nivelPermisos;
    private HashMap<String, Integer> credenciales = new HashMap<>();

    protected Persona(String nombre, String apellido, String dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    public Persona() {
    }


    /* toString */
    @Override
    public String toString() {
        return "dni:%s\nnombre: %s\napellido: %s\nnivel permisos: %s".formatted(dni, nombre, apellido, nivelPermisos);
    }

    /* equals y hashcode  */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Persona persona = (Persona) object;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    /* metodos */
    public Boolean comprobarCredenciales(String nombre, String clave){
        String nombre_usuario = credenciales.keySet().toArray()[0].toString();
        Integer clave_hashcode = credenciales.get(nombre);
        return nombre_usuario.equals(nombre) && clave_hashcode == clave.hashCode();
    }

    /* getters y setters */
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

    public void setDni(String dni){
        this.dni = dni;
    }

    public NivelPermisos getNivelPermisos(){
        return this.nivelPermisos;
    }

    public void setNivelPermisos(NivelPermisos nivelPermisos){
        this.nivelPermisos = nivelPermisos;
    }

    public HashMap<String, Integer> getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(HashMap<String, Integer> credenciales) {
        this.credenciales = credenciales;
    }
}
