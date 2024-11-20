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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clientes {
    private JTable TablaClientes;
    private JButton atrasButton;
    private JButton borrarButton;
    private JPanel panel;

    public Clientes(){

        // Llamada inicial para cargar los datos cuando la ventana de clientes se cree
        cargarDatosEnTabla();
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGerente menuGerente = new MenuGerente();
                menuGerente.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });



        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = TablaClientes.getSelectedRow();  // Obtiene la fila seleccionada
                if (filaSeleccionada != -1) {
                    // Obtener el DNI del cliente seleccionado en la tabla
                    String dni = (String) TablaClientes.getValueAt(filaSeleccionada, 0);  // La primera columna tiene el DNI

                    // Mostrar cuadro de confirmación
                    int confirmacion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de que desea eliminar al cliente con DNI: " + dni + "?",
                            "Confirmación de eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Crear el gestor de clientes
                        GestorClientes gestorClientes = new GestorClientes();
                        gestorClientes.cargarDesdeArchivo("clientes.json");  // Cargar los datos del archivo

                        // Buscar el cliente con el DNI seleccionado
                        Cliente clienteAEliminar = null;
                        for (Cliente cliente : gestorClientes.getLista()) {
                            if (cliente.getDni().equals(dni)) {
                                clienteAEliminar = cliente;
                                break;  // Salir del bucle si se encuentra el cliente
                            }
                        }

                        if (clienteAEliminar != null) {
                            // Eliminar el cliente de la lista
                            try {
                                gestorClientes.eliminar(clienteAEliminar);  // Llama a eliminar() desde GestorClientes
                                // Guardar los cambios en el archivo JSON
                                gestorClientes.guardarEnArchivo("clientes.json");
                                // Actualizar la tabla después de eliminar el cliente
                                cargarDatosEnTabla();  // Recargar los datos actualizados en la tabla
                                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Operación cancelada. El cliente no fue eliminado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



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
        // Inicia con las columnas básicas
        List<String> columnas = new ArrayList<>(Arrays.asList("DNI", "Nombre", "Apellido", "Teléfono", "Correo Electrónico"));

        // Verificar si agregar columnas adicionales basadas en los atributos del cliente
        for (Cliente cliente : listaClientes) {
            if (cliente.getCarpa() != null) {
                if (!columnas.contains("Carpa")) {
                    columnas.add("Carpa");
                }
            }
            if (cliente.getEstacionamiento() != null) {
                if (!columnas.contains("Estacionamiento")) {
                    columnas.add("Estacionamiento");
                }
            }
            if (cliente.getServicio() != null) {
                if (!columnas.contains("Servicio")) {
                    columnas.add("Servicio");
                }
            }
        }

        // Convertir la lista de columnas en un arreglo para el DefaultTableModel
        String[] columnasArray = columnas.toArray(new String[0]);
        DefaultTableModel modelo = new DefaultTableModel(columnasArray, 0);

        // Asegurarse de que la tabla no tenga filas previas
        modelo.setRowCount(0);  // Vacía las filas de la tabla

        // Agregar filas con los datos de los clientes
        for (Cliente cliente : listaClientes) {
            List<Object> row = new ArrayList<>();
            row.add(cliente.getDni());
            row.add(cliente.getNombre());
            row.add(cliente.getApellido());
            row.add(cliente.getTelefono());
            row.add(cliente.getCorreo());

            // Agregar datos de Carpa, Estacionamiento y Servicio si corresponden
            if (columnas.contains("Carpa")) {
                row.add(cliente.getCarpa());
            }
            if (columnas.contains("Estacionamiento")) {
                row.add(cliente.getEstacionamiento() != null ? cliente.getEstacionamiento() : "No tiene");
            }
            if (columnas.contains("Servicio")) {
                row.add(cliente.getServicio() != null ? cliente.getServicio() : "No tiene");
            }

            modelo.addRow(row.toArray());
        }

        // Asignar el modelo al JTable
        TablaClientes.setModel(modelo);

        // Mostrar en consola cuántos clientes fueron añadidos
        System.out.println("Número de clientes añadidos a la tabla: " + listaClientes.size());
    }





}