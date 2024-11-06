import Gestores.Reservas.GestorReservasEstacionamiento;
import Personas.Cliente;
import Servicios.Estacionamiento;

public class Main {
    public static void main(String[] args) {
        // GestorReservasEstacionamiento gestorReservasEstacionamiento = new GestorReservasEstacionamiento();

        /* codigo de muestra: hacer reserva estacionamiento
        Estacionamiento estacionamiento = new Estacionamiento(500);
        Cliente cliente = new Cliente("Juan", "alberdi", "43.112.777");

        // agregar reserva al gestor
        gestorReservasEstacionamiento.agregar(estacionamiento.reservar(cliente));

        // guardar reservas en archivo
        gestorReservasEstacionamiento.guardarEnArchivo("reservas_estacionamiento.json");

        // mostrar reservas
        gestorReservasEstacionamiento.mostrar();
        */

        /*
        // cargar reservas desde archivo (json)
        gestorReservasEstacionamiento.cargarDesdeArchivo("reservas_estacionamiento.json");

        // mostrar reservas
        gestorReservasEstacionamiento.mostrar();
        */

        /*
        System.out.println(Estacionamiento.getLugares_disponibles());
        System.out.println(Estacionamiento.getCapacidad());
        */

        /* codigo de muestra: empleados */
        // GestorEmpleados gestor_empleados = new Gestores.GestorEmpleados();
        /*
        HashSet<Empleado> empleados = new HashSet<>(List.of(
                new Empleado("Pepe", "Sanchez", "12", 12.0, 8, Puesto.ADMINISTRADOR),
                new Empleado("Maria", "Gomez", "34", 15.5, 9, Puesto.MANTENIMIENTO),
                new Empleado("Luis", "Torres", "56", 10.0, 6, Puesto.SERVICIO_AL_CLIENTE),
                new Empleado("Ana", "Martinez", "78", 20.0, 7, Puesto.ADMINISTRADOR)
        ));

        gestor_empleados.setLista(empleados);
        gestor_empleados.guardarEnArchivo("empleados.json");
        */

        /*
        gestor_empleados.cargarDesdeArchivo("empleados.json");
        gestor_empleados.mostrar();
        */
    }
}
