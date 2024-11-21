package Gestores;

import Personas.Cliente;
import Personas.Empleado;

public class GestorEmpleados extends Gestor<Empleado> {

    /* metodos */
    public Empleado buscarPorUsuario(String usuario){
        for(Empleado empleado: getLista()){
            if(empleado.getCredenciales().keySet().toArray()[0].toString().equals(usuario)){
                return empleado;
            }
        }
        return null;
    }

    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Empleado.class);
    }
}
