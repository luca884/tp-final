package InterfazGrafica;

import Enumeraciones.Carpa;
import Enumeraciones.Estacionamiento;
import Enumeraciones.Servicio;
import Gestores.GestorClientes;
import Personas.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuUsuario {
    private JPanel panel;
    private JButton atrasButton;
    private JButton editarButton;
    private JTable tablaClientes;
    private JLabel subTitulo;
    private JPanel panel1;
GestorClientes gestorClientes = new GestorClientes();

    public MenuUsuario (){



        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IniciarSesion iniciarSesion = new IniciarSesion();
                iniciarSesion.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaClientes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener los datos de la fila seleccionada
                    String dni = (String) tablaClientes.getValueAt(filaSeleccionada, 0);
                    String nombre = (String) tablaClientes.getValueAt(filaSeleccionada, 1);
                    String apellido = (String) tablaClientes.getValueAt(filaSeleccionada, 2);
                    Long telefono = Long.parseLong(tablaClientes.getValueAt(filaSeleccionada, 3).toString());
                    String correo = (String) tablaClientes.getValueAt(filaSeleccionada, 4);
                    Carpa carpa = Carpa.valueOf((String) tablaClientes.getValueAt(filaSeleccionada, 5));
                    Enumeraciones.Estacionamiento estacionamiento = Enumeraciones.Estacionamiento.valueOf((String) tablaClientes.getValueAt(filaSeleccionada, 6));
                    Servicio servicio = Servicio.valueOf((String) tablaClientes.getValueAt(filaSeleccionada, 7));

                    // Crear un diálogo de edición
                    JDialog dialog = new JDialog((JFrame) null, "Editar Cliente", true);
                    dialog.setLayout(new BorderLayout());

                    // Crear panel de edición
                    JPanel editPanel = new JPanel(new GridLayout(12, 2));
                    JTextField editNombreField = new JTextField(nombre);
                    JTextField editApellidoField = new JTextField(apellido);
                    JTextField editTelefonoField = new JTextField(String.valueOf(telefono));
                    JTextField editCorreoField = new JTextField(correo);

                    // Crear botones de selección para Carpa
                    JRadioButton vipCarpaButton = new JRadioButton("VIP");
                    JRadioButton estandarCarpaButton = new JRadioButton("Estandar");
                    ButtonGroup carpaButtonGroup = new ButtonGroup();
                    carpaButtonGroup.add(vipCarpaButton);
                    carpaButtonGroup.add(estandarCarpaButton);

                    if (carpa == Carpa.VIP) vipCarpaButton.setSelected(true);
                    else estandarCarpaButton.setSelected(true);

                    // Crear botones de selección para Estacionamiento
                    JRadioButton vipEstacionamientoButton = new JRadioButton("VIP");
                    JRadioButton estandarEstacionamientoButton = new JRadioButton("Estandar");
                    JRadioButton noIncluyeEstacionamientoButton = new JRadioButton("No Incluye");
                    ButtonGroup estacionamientoButtonGroup = new ButtonGroup();
                    estacionamientoButtonGroup.add(vipEstacionamientoButton);
                    estacionamientoButtonGroup.add(estandarEstacionamientoButton);
                    estacionamientoButtonGroup.add(noIncluyeEstacionamientoButton);

                    if (estacionamiento == Enumeraciones.Estacionamiento.VIP) vipEstacionamientoButton.setSelected(true);
                    else if (estacionamiento == Enumeraciones.Estacionamiento.ESTANDAR) estandarEstacionamientoButton.setSelected(true);
                    else noIncluyeEstacionamientoButton.setSelected(true);

                    // Crear botones de selección para Servicio
                    JCheckBox spaCheckBox = new JCheckBox("Spa");
                    JCheckBox guarderiaCheckBox = new JCheckBox("Guardería");

                    if (servicio == Servicio.SPA) spaCheckBox.setSelected(true);
                    else if (servicio == Servicio.GUARDERIA) guarderiaCheckBox.setSelected(true);
                    else if (servicio == Servicio.GUARDERIA_Y_SPA) {
                        spaCheckBox.setSelected(true);
                        guarderiaCheckBox.setSelected(true);
                    }

                    // Añadir los componentes al panel
                    editPanel.add(new JLabel("Nombre:"));
                    editPanel.add(editNombreField);
                    editPanel.add(new JLabel("Apellido:"));
                    editPanel.add(editApellidoField);
                    editPanel.add(new JLabel("Teléfono:"));
                    editPanel.add(editTelefonoField);
                    editPanel.add(new JLabel("Correo Electrónico:"));
                    editPanel.add(editCorreoField);
                    editPanel.add(new JLabel("Carpa:"));
                    editPanel.add(vipCarpaButton);
                    editPanel.add(new JLabel(""));
                    editPanel.add(estandarCarpaButton);
                    editPanel.add(new JLabel("Estacionamiento:"));
                    editPanel.add(vipEstacionamientoButton);
                    editPanel.add(new JLabel(""));
                    editPanel.add(estandarEstacionamientoButton);
                    editPanel.add(new JLabel(""));
                    editPanel.add(noIncluyeEstacionamientoButton);
                    editPanel.add(new JLabel("Servicio:"));
                    editPanel.add(spaCheckBox);
                    editPanel.add(guarderiaCheckBox);

                    // Crear botón de confirmación
                    JButton confirmButton = new JButton("Confirmar");
                    confirmButton.addActionListener(e1 -> {
                        try {
                            // Obtener los datos editados
                            Carpa nuevaCarpa = vipCarpaButton.isSelected() ? Carpa.VIP : Carpa.ESTANDAR;
                            Estacionamiento nuevoEstacionamiento = vipEstacionamientoButton.isSelected() ? Estacionamiento.VIP
                                    : estandarEstacionamientoButton.isSelected() ? Estacionamiento.ESTANDAR
                                    : Estacionamiento.NO_INCLUYE;

                            Servicio nuevoServicio;
                            if (spaCheckBox.isSelected() && guarderiaCheckBox.isSelected()) {
                                nuevoServicio = Servicio.GUARDERIA_Y_SPA;
                            } else if (spaCheckBox.isSelected()) {
                                nuevoServicio = Servicio.SPA;
                            } else if (guarderiaCheckBox.isSelected()) {
                                nuevoServicio = Servicio.GUARDERIA;
                            } else {
                                throw new IllegalArgumentException("Debe seleccionar al menos un servicio.");
                            }

                            // Actualizar el cliente en el gestor
                            Cliente clienteEditado = new Cliente(
                                    editNombreField.getText(),
                                    editApellidoField.getText(),
                                    dni,
                                    Long.parseLong(editTelefonoField.getText()),
                                    editCorreoField.getText()
                            );
                            clienteEditado.setCarpa(nuevaCarpa);
                            clienteEditado.setEstacionamiento(nuevoEstacionamiento);
                            clienteEditado.setServicio(nuevoServicio);

                            GestorClientes gestorClientes = new GestorClientes();
                            gestorClientes.cargarDesdeArchivo("clientes.json");
                            gestorClientes.eliminar(clienteEditado);
                            gestorClientes.agregar(clienteEditado);
                            gestorClientes.guardarEnArchivo("clientes.json");


                            // Actualizar la tabla
                            tablaClientes.setValueAt(editNombreField.getText(), filaSeleccionada, 1);
                            tablaClientes.setValueAt(editApellidoField.getText(), filaSeleccionada, 2);
                            tablaClientes.setValueAt(editTelefonoField.getText(), filaSeleccionada, 3);
                            tablaClientes.setValueAt(editCorreoField.getText(), filaSeleccionada, 4);
                            tablaClientes.setValueAt(nuevaCarpa.name(), filaSeleccionada, 5);
                            tablaClientes.setValueAt(nuevoEstacionamiento.name(), filaSeleccionada, 6);
                            tablaClientes.setValueAt(nuevoServicio.name(), filaSeleccionada, 7);

                            dialog.dispose();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });

                    dialog.add(editPanel, BorderLayout.CENTER);
                    dialog.add(confirmButton, BorderLayout.SOUTH);
                    dialog.setSize(new Dimension(400, 400));
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


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
        tablaClientes.setModel(modelo);

        // Mostrar en consola cuántos clientes fueron añadidos
        System.out.println("Número de clientes añadidos a la tabla: " + listaClientes.size());
    }



    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Menu Usuario");
        frame.setContentPane(panel1); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }






}
