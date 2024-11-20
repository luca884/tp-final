package InterfazGrafica;

import Enumeraciones.Puesto;
import Enumeraciones.Servicio;
import Gestores.GestorClientes;
import Gestores.GestorEmpleados;
import Personas.Cliente;
import Personas.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Spa {
    private JTable tablaClientes;
    private JTable tablaEmpleados;
    private JButton atrasButton;
    private JPanel panel;
    private DefaultTableModel modeloTablaEmpleado;
    private DefaultTableModel modeloTablaClientes;
    private GestorEmpleados gestorEmpleados = new GestorEmpleados();
    private GestorClientes gestorClientes = new GestorClientes();

    public Spa (){
        // Cargar empleados desde el archivo
        gestorEmpleados.cargarDesdeArchivo("empleados.json", Empleado.class);
        // Cargar los datos en la tabla al crear la ventana
        cargarDatosTablaEmpleados();
        cargarDatosTablaClientes();


        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGerente menuGerente = new MenuGerente();
                menuGerente.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });
    }

    void cargarDatosTablaEmpleados() {
        // Configurar las columnas de la tabla
        String[] columnas = {"DNI", "Nombre", "Apellido", "Puesto", "Salario", "Horario"};
        modeloTablaEmpleado = new DefaultTableModel(columnas, 0);

        // Obtener el HashSet de empleados desde el gestor
        HashSet<Empleado> conjuntoEmpleados = gestorEmpleados.getLista();

        // Agregar filas con los datos de los empleados
        for (Empleado empleado : conjuntoEmpleados) {
            if (empleado.getPuesto() == Puesto.SPA) {
                modeloTablaEmpleado.addRow(new Object[]{
                        empleado.getDni(),
                        empleado.getNombre(),
                        empleado.getApellido(),
                        empleado.getPuesto(),
                        empleado.getSalario(),
                        empleado.getHorario(),
                });
            }
        }

        // Asignar el modelo al JTable
        tablaEmpleados.setModel(modeloTablaEmpleado);
    }


    void cargarDatosTablaClientes() {
        // Corregir la clase de deserialización: debe ser Cliente.class, no Empleado.class
        gestorClientes.cargarDesdeArchivo("clientes.json", Cliente.class);

        // Configurar las columnas de la tabla
        String[] columnas = {"DNI", "Nombre", "Apellido", "Teléfono", "Correo Electrónico", "Carpa", "Servicio"};
        modeloTablaClientes = new DefaultTableModel(columnas, 0);

        // Imprimir los datos cargados
        System.out.println("Datos cargados desde el archivo:");
        gestorClientes.getLista().forEach(System.out::println);

        // Obtener la lista de clientes desde el gestor
        List<Cliente> listaClientes = new ArrayList<>(gestorClientes.getLista());

        // Agregar filas con los datos de los clientes
        for (Cliente cliente : listaClientes) {
            if (cliente.getServicio() == Servicio.SPA || cliente.getServicio() == Servicio.GUARDERIA_Y_SPA) {
                modeloTablaClientes.addRow(new Object[]{
                        cliente.getDni(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getTelefono(),
                        cliente.getCorreo(),
                        cliente.getCarpa(),
                        cliente.getServicio(),
                });
            }
        }

        // Asignar el modelo al JTable
        tablaClientes.setModel(modeloTablaClientes);
    }


    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Registrar Empleado");
        frame.setContentPane(panel); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }


}
