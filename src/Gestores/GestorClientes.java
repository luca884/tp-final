package Gestores;

import Personas.Cliente;
import Personas.Persona;

public class GestorClientes extends Gestor<Cliente> {

    /* metodos */
    public Cliente buscarPorUsuario(String usuario){
        for(Cliente cliente: getLista()){
            if(cliente.getCredenciales().keySet().toArray()[0].toString().equals(usuario)){
                return cliente;
            }
        }
        return null;
    }

    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Cliente.class);
    }

    public void agregarAlArchivo(Cliente cliente, String nombre_archivo) {
        super.agregarAlArchivo(cliente, nombre_archivo, Cliente.class);
    }
}
