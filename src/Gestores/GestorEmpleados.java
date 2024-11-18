package Gestores;

import Personas.Empleado;

public class GestorEmpleados extends Gestor<Empleado> {
    /* archivos json */
    public void cargarDesdeArchivo(String nombre_archivo) {
        super.cargarDesdeArchivo(nombre_archivo, Empleado.class);
    }
}
