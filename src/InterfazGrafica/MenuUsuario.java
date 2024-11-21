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
        private JButton atrasButton;
        private JButton editarButton;
        private JTable tablaClientes;
        private JPanel panel1;
        private JTextField textPrecioTotal; // Añadimos un JTextField para mostrar el precio total
        GestorClientes gestorClientes = new GestorClientes();
    double finalPrecioTotal = 0;


    public MenuUsuario(Cliente cliente) {
            cargarDatosEnTabla(cliente);

            // Acción del botón Atras
            atrasButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    IniciarSesion iniciarSesion = new IniciarSesion();
                    iniciarSesion.setVisible(true);
                    JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                    frame.dispose();
                }
            });

            // Acción del botón Editar
            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Crear un diálogo de edición
                    JDialog dialog = new JDialog((JFrame) null, "Editar Cliente", true);
                    dialog.setLayout(new BorderLayout());

                    // Crear panel de edición
                    JPanel editPanel = new JPanel(new GridLayout(14, 2)); // Se aumenta el número de filas
                    JTextField editNombreField = new JTextField(cliente.getNombre());
                    JTextField editApellidoField = new JTextField(cliente.getApellido());
                    JTextField editTelefonoField = new JTextField(String.valueOf(cliente.getTelefono()));
                    JTextField editCorreoField = new JTextField(cliente.getCorreo());

                    // Crear botones de selección para Carpa
                    JRadioButton vipCarpaButton = new JRadioButton("VIP");
                    JRadioButton estandarCarpaButton = new JRadioButton("Estandar");
                    ButtonGroup carpaButtonGroup = new ButtonGroup();
                    carpaButtonGroup.add(vipCarpaButton);
                    carpaButtonGroup.add(estandarCarpaButton);

                    if (cliente.getCarpa() == Carpa.VIP) vipCarpaButton.setSelected(true);
                    else estandarCarpaButton.setSelected(true);

                    // Crear botones de selección para Estacionamiento
                    JRadioButton vipEstacionamientoButton = new JRadioButton("VIP");
                    JRadioButton estandarEstacionamientoButton = new JRadioButton("Estandar");
                    JRadioButton noIncluyeEstacionamientoButton = new JRadioButton("No Incluye");
                    ButtonGroup estacionamientoButtonGroup = new ButtonGroup();
                    estacionamientoButtonGroup.add(vipEstacionamientoButton);
                    estacionamientoButtonGroup.add(estandarEstacionamientoButton);
                    estacionamientoButtonGroup.add(noIncluyeEstacionamientoButton);

                    if (cliente.getEstacionamiento() == Estacionamiento.VIP) vipEstacionamientoButton.setSelected(true);
                    else if (cliente.getEstacionamiento() == Estacionamiento.ESTANDAR) estandarEstacionamientoButton.setSelected(true);
                    else noIncluyeEstacionamientoButton.setSelected(true);

                    // Crear checkboxes para Servicio
                    JCheckBox spaCheckBox = new JCheckBox("Spa");
                    JCheckBox guarderiaCheckBox = new JCheckBox("Guardería");

                    if (cliente.getServicio() == Servicio.SPA) spaCheckBox.setSelected(true);
                    else if (cliente.getServicio() == Servicio.GUARDERIA) guarderiaCheckBox.setSelected(true);
                    else if (cliente.getServicio() == Servicio.GUARDERIA_Y_SPA) {
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

                    // Crear y añadir JTextField para mostrar el valor total
                    editPanel.add(new JLabel("Valor Total:"));
                    textPrecioTotal = new JTextField();
                    textPrecioTotal.setEditable(false); // Hacer el campo solo lectura
                    editPanel.add(textPrecioTotal);

                    // Crear botón de confirmación
                    JButton confirmButton = new JButton("Confirmar");
                    confirmButton.addActionListener(e1 -> {
                        try {
                            // Obtener los datos editados
                            Carpa nuevaCarpa = null;
                            if (vipCarpaButton.isSelected()){
                                nuevaCarpa = Carpa.VIP;
                                finalPrecioTotal += 1000000;
                            }else if (estandarCarpaButton.isSelected()){
                                nuevaCarpa = Carpa.ESTANDAR;
                                finalPrecioTotal += 750000;
                            }

// Establecer el estacionamiento
                            Estacionamiento nuevoEstacionamiento;
                            if (vipEstacionamientoButton.isSelected()) {
                                nuevoEstacionamiento = Estacionamiento.VIP;
                                finalPrecioTotal += 500000;
                            } else if (estandarEstacionamientoButton.isSelected()) {
                                nuevoEstacionamiento = Estacionamiento.ESTANDAR;
                                finalPrecioTotal += 250000;
                            } else {
                                nuevoEstacionamiento = Estacionamiento.NO_INCLUYE;
                                finalPrecioTotal += 0;
                            }

                            Servicio nuevoServicio = null;
                            if (spaCheckBox.isSelected() && guarderiaCheckBox.isSelected()) {
                                nuevoServicio = Servicio.GUARDERIA_Y_SPA;
                                finalPrecioTotal += 700000;

                            } else if (spaCheckBox.isSelected() && !guarderiaCheckBox.isSelected()) {
                                nuevoServicio = Servicio.SPA;
                                finalPrecioTotal += 300000;
                            } else if (guarderiaCheckBox.isSelected() && !spaCheckBox.isSelected()) {
                                nuevoServicio = Servicio.GUARDERIA;
                                finalPrecioTotal += 400000;
                            }

                            // Actualizar los datos del cliente
                            cliente.setNombre(editNombreField.getText());
                            cliente.setApellido(editApellidoField.getText());
                            cliente.setTelefono(Long.parseLong(editTelefonoField.getText()));
                            cliente.setCorreo(editCorreoField.getText());
                            cliente.setCarpa(nuevaCarpa);
                            cliente.setEstacionamiento(nuevoEstacionamiento);
                            cliente.setServicio(nuevoServicio);
                            cliente.setValor_Total(finalPrecioTotal);

                            // Guardar cambios en el gestor
                            gestorClientes.cargarDesdeArchivo("clientes.json");
                            gestorClientes.eliminar(cliente);
                            gestorClientes.agregar(cliente);
                            gestorClientes.guardarEnArchivo("clientes.json");

                            // Recargar los datos en la tabla
                            cargarDatosEnTabla(cliente);

                            dialog.dispose();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });

                    dialog.add(editPanel, BorderLayout.CENTER);
                    dialog.add(confirmButton, BorderLayout.SOUTH);
                    dialog.setSize(new Dimension(400, 450)); // Ajustamos el tamaño para incluir el campo de valor total
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
            });
        }



    private void cargarDatosEnTabla(Cliente cliente) {
        // Configurar columnas dinámicas
        List<String> columnas = new ArrayList<>(Arrays.asList(
                "DNI", "Nombre", "Apellido", "Teléfono", "Correo Electrónico", "Carpa", "Estacionamiento", "Servicio","Precio Total"
        ));


        // Crear el modelo de tabla con las columnas dinámicas
        String[] columnasArray = columnas.toArray(new String[0]);
        DefaultTableModel modelo = new DefaultTableModel(columnasArray, 0);

        // Crear fila de datos del cliente
        List<Object> row = new ArrayList<>();
        row.add(cliente.getDni());
        row.add(cliente.getNombre());
        row.add(cliente.getApellido());
        row.add(cliente.getTelefono());
        row.add(cliente.getCorreo());
        row.add(cliente.getCarpa());
        row.add(cliente.getEstacionamiento());
        row.add(cliente.getServicio());
        row.add(cliente.getValor_Total());


        // Añadir fila al modelo de la tabla
        modelo.addRow(row.toArray());
        tablaClientes.setModel(modelo);

        // Debug: Imprimir el estado final del cliente y la fila
        System.out.println("Cliente cargado: " + cliente);
        System.out.println("Fila cargada: " + row);
    }


    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Menu Usuario");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}


  /*  private void cargarDatosEnTabla(Cliente cliente) {
        // Configurar columnas dinámicas
        List<String> columnas = new ArrayList<>(Arrays.asList(
                "DNI", "Nombre", "Apellido", "Teléfono", "Correo Electrónico"
        ));

        if (cliente.getCarpa() != null) columnas.add("Carpa");
        if (cliente.getEstacionamiento() != null) columnas.add("Estacionamiento");
        if (cliente.getServicio() != null) columnas.add("Servicio");

        String[] columnasArray = columnas.toArray(new String[0]);
        DefaultTableModel modelo = new DefaultTableModel(columnasArray, 0);

        List<Object> row = new ArrayList<>();
        row.add(cliente.getDni());
        row.add(cliente.getNombre());
        row.add(cliente.getApellido());
        row.add(cliente.getTelefono());
        row.add(cliente.getCorreo());
        if (cliente.getCarpa() != null) row.add(cliente.getCarpa());
        if (cliente.getEstacionamiento() != null) row.add(cliente.getEstacionamiento());
        if (cliente.getServicio() != null) row.add(cliente.getServicio());

        modelo.addRow(row.toArray());
        tablaClientes.setModel(modelo);
    }*/



