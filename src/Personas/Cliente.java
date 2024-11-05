package Personas;

import Enumeraciones.NivelPermisos;

public class Cliente extends Persona{

    // Deberíamos ser capaces de agregar atributos exclusivos para
    // los clientes, pero no se me ocurre nada a mí
    //                                                      -Eze

    public Cliente(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
        this.nivelPermisos = NivelPermisos.CLIENTE;
    }

    /* toString */
    // todo: modificar este metodo al agregar atributos exclusivos, si no borrar
    @Override
    public String toString() {
        return super.toString();
    }

}
