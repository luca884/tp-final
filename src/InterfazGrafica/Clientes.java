package InterfazGrafica;

import Gestores.GestorClientes;
import Personas.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Clientes {
    private JTable TablaClientes;
    private JButton atrasButton;
    private JButton borrarButton;
    private JPanel panel;

    public Clientes(){
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGerente menuGerente = new MenuGerente();
                menuGerente.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });

        // Llamada inicial para cargar los datos cuando la ventana de clientes se cree
        cargarDatosEnTabla();


    }



    public void setVisible(boolean visible){
        // Cuando se hace visible la ventana, recarga los datos en la tabla
        if (visible) {
            cargarDatosEnTabla(); // Asegura que la tabla se actualice al abrir la ventana
        }

        JFrame frame = new JFrame("Clientes");
        frame.setContentPane(panel); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }

    private void cargarDatosEnTabla() {
        // Gestor para manejar los clientes
        GestorClientes gestorClientes = new GestorClientes();
        gestorClientes.cargarDesdeArchivo("clientes.json");

        // Imprimir los datos cargados
        System.out.println("Datos cargados desde el archivo:");
        gestorClientes.getLista().forEach(System.out::println);

        // Obtener la lista de clientes desde el gestor
        List<Cliente> listaClientes = gestorClientes.getLista().stream().toList();

        // Configurar las columnas de la tabla
        String[] columnas = {"DNI", "Nombre", "Apellido", "Teléfono", "Correo Electrónico"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Asegurarse de que la tabla no tenga filas previas
        modelo.setRowCount(0);  // Vacía las filas de la tabla

        // Agregar filas con los datos de los clientes
        int contador = 0;
        for (Cliente cliente : listaClientes) {
            modelo.addRow(new Object[]{
                    cliente.getDni(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getTelefono(),
                    cliente.getCorreo()
            });
            contador++;
        }

        // Muestra cuántos clientes fueron añadidos
        System.out.println("Número de clientes añadidos a la tabla: " + contador);
        // Asignar el modelo al JTable
        TablaClientes.setModel(modelo);
}




}