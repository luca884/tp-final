package InterfazGrafica;

import Gestores.GestorClientes;
import Personas.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Estacionamiento {
    private JTable tablaEstacionamiento;
    private JButton atrasButton;
    private JPanel panel;

    public Estacionamiento (){
        cargarDatosTablaEstacionamiento();
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



    public void cargarDatosTablaEstacionamiento() {
        // Gestor para manejar los clientes
        GestorClientes gestorClientes = new GestorClientes();
        gestorClientes.cargarDesdeArchivo("clientes.json");

        // Imprimir los datos cargados
        System.out.println("Datos cargados desde el archivo:");
        gestorClientes.getLista().forEach(System.out::println);

        // Obtener la lista de clientes desde el gestor
        java.util.List<Cliente> listaClientes = gestorClientes.getLista().stream().toList();

        // Configurar las columnas de la tabla
        // Inicia con las columnas básicas
        List<String> columnas = new ArrayList<>(Arrays.asList("Estacionamiento", "DNI" , "Nombre", "Apellido", "Teléfono", "Correo Electrónico"));

        // Convertir la lista de columnas en un arreglo para el DefaultTableModel
        String[] columnasArray = columnas.toArray(new String[0]);
        DefaultTableModel modelo = new DefaultTableModel(columnasArray, 0);

        // Asegurarse de que la tabla no tenga filas previas
        modelo.setRowCount(0);  // Vacía las filas de la tabla

        // Agregar filas con los datos de los clientes
        for (Cliente cliente : listaClientes) {
            List<Object> row = new ArrayList<>();
            row.add(cliente.getEstacionamiento());
            row.add(cliente.getDni());
            row.add(cliente.getNombre());
            row.add(cliente.getApellido());
            row.add(cliente.getTelefono());
            row.add(cliente.getCorreo());
            modelo.addRow(row.toArray());
        }

        tablaEstacionamiento.setModel(modelo);

    }


    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Estacionamiento");
        frame.setContentPane(panel); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }

}
