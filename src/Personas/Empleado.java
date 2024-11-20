package Personas;

import Enumeraciones.NivelPermisos;
import Enumeraciones.Puesto;

public class Empleado extends Persona {
    /* atributos */
    private Double salario;

    private String horario;
    private Puesto puesto;


    /* constructor */
    public Empleado(String nombre, String apellido, String dni, String horario, Puesto puesto, double salario) {
        super(nombre, apellido, dni);
        this.salario = salario;
        this.horario = horario;
        this.puesto = puesto;
        this.nivelPermisos = NivelPermisos.EMPLEADO;
    }

    public Empleado() {

    }

    /* metodos */

   /* public Double calcularSalario(){
        return valor_hora * cantidad_horas;
    }

    */

    /* toString */
    @Override
    public String toString() {
        return super.toString() + "\nSalario: %s\nPuesto: %s".formatted(salario, puesto);
    }

    /* getters y setters*/
    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }


    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
