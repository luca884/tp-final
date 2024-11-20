package InterfazGrafica;

import Excepciones.ElementoDuplicadoException;
import Excepciones.ElementoNoEncontradoException;
import Gestores.GestorMantenimiento;
import Varios.Mantenimiento;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class IGMantenimiento {
    private JTable tablaMantenimiento;
    private JButton borrarButton;
    private JButton editarButton;
    private JButton atrasButton;
    private JPanel panel;
    private JButton registrarButton;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private GestorMantenimiento gestorMantenimiento = new GestorMantenimiento();

    public IGMantenimiento(){
        //Cargar datos
        gestorMantenimiento.cargarDesdeArchivo("mantenimiento.json", Mantenimiento.class);
        //Cargar datos al entrar
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

        registrarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MantenimientoRegistrar mantenimientoRegistrar = new MantenimientoRegistrar();
                mantenimientoRegistrar.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(registrarButton);
                frame.dispose();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] filasSeleccionadas = tablaMantenimiento.getSelectedRows();

                if (filasSeleccionadas.length > 0) {
                    int confirmacion = JOptionPane.showConfirmDialog(
                            panel,
                            "¿Estás seguro de que deseas eliminar estos mantenimientos?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                            String dniMantenimiento = (String) modeloTabla.getValueAt(filasSeleccionadas[i], 0);
                            borrarMantenimiento(dniMantenimiento);
                            modeloTabla.removeRow(filasSeleccionadas[i]);
                        }
                        cargarDatosEnTabla(); // Actualiza los cambios
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Seleccione al menos un mantenimiento para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaMantenimiento.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener los datos de la fila seleccionada
                    String dni = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                    String descripcion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                    double costo = (double) modeloTabla.getValueAt(filaSeleccionada, 2);
                    String estado = (String) modeloTabla.getValueAt(filaSeleccionada, 3);

                    // Crear un JDialog para editar el mantenimiento
                    JDialog dialog = new JDialog((JFrame) null, "Editar Mantenimiento", true);
                    dialog.setLayout(new BorderLayout());

                    JPanel editPanel = new JPanel(new GridLayout(4, 2));
                    JTextField editDescripcionField = new JTextField(descripcion);
                    JTextField editCostoField = new JTextField(String.valueOf(costo));
                    JTextField editEstadoField = new JTextField(estado);

                    editPanel.add(new JLabel("Descripción:"));
                    editPanel.add(editDescripcionField);
                    editPanel.add(new JLabel("Costo:"));
                    editPanel.add(editCostoField);
                    editPanel.add(new JLabel("Estado:"));
                    editPanel.add(editEstadoField);

                    JButton confirmButton = new JButton("Confirmar");
                    confirmButton.addActionListener(e1 -> {
                        try {
                            // Obtener los datos editados
                            double nuevoCosto = Double.parseDouble(editCostoField.getText());
                            String nuevoEstado = editEstadoField.getText();

                            // Crear el nuevo objeto Mantenimiento
                            Mantenimiento mantenimientoEditado = new Mantenimiento(
                                    dni,  // Mantener el DNI original
                                    editDescripcionField.getText(),
                                    nuevoCosto,
                                    nuevoEstado
                            );

                            // Buscar el mantenimiento por DNI en la lista
                            Mantenimiento mantenimientoSeleccionado = null;
                            for (Mantenimiento mantenimiento : gestorMantenimiento.getLista()) {
                                if (mantenimiento.getDni().equals(dni)) {
                                    mantenimientoSeleccionado = mantenimiento;
                                    break;
                                }
                            }

                            if (mantenimientoSeleccionado != null) {
                                // Eliminar el mantenimiento anterior y agregar el editado
                                gestorMantenimiento.eliminar(mantenimientoSeleccionado);
                                gestorMantenimiento.agregar(mantenimientoEditado);

                                // Guardar los cambios en el archivo JSON
                                gestorMantenimiento.guardarEnArchivo("mantenimiento.json");

                                // Actualizar la tabla
                                modeloTabla.setValueAt(editDescripcionField.getText(), filaSeleccionada, 1);
                                modeloTabla.setValueAt(nuevoCosto, filaSeleccionada, 2);
                                modeloTabla.setValueAt(nuevoEstado, filaSeleccionada, 3);

                                dialog.dispose(); // Cerrar el diálogo
                            } else {
                                JOptionPane.showMessageDialog(null, "Mantenimiento no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error al editar el costo", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (ElementoDuplicadoException | ElementoNoEncontradoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });

                    dialog.add(editPanel, BorderLayout.CENTER);
                    dialog.add(confirmButton, BorderLayout.SOUTH);
                    dialog.setSize(new Dimension(350, 250));  // Ajustado para los nuevos campos
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });





    }


    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Mantenimiento" );
        frame.setContentPane(panel); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }

    private void borrarMantenimiento(String dni) {
        Mantenimiento mantenimientoParaEliminar = null;
        for (Mantenimiento mantenimiento : gestorMantenimiento.getLista()) {
            if (mantenimiento.getDni().equals(dni)) {
                mantenimientoParaEliminar = mantenimiento;
                break;
            }
        }
        if (mantenimientoParaEliminar != null) {
            try {
                gestorMantenimiento.eliminar(mantenimientoParaEliminar);
                gestorMantenimiento.guardarEnArchivo("mantenimiento.json");
            } catch (ElementoNoEncontradoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void cargarDatosEnTabla(){
        //Configurar las columnas
        String[] columnas = {"DNI", "DESCRIPCION", "COSTO", "ESTADO"};
        modeloTabla = new DefaultTableModel(columnas,0);
        HashSet<Mantenimiento> mantenimientoList = gestorMantenimiento.getLista();

        // Agregar filas con los datos
        for (Mantenimiento mantenimiento : mantenimientoList) {
            modeloTabla.addRow(new Object[]{
                    mantenimiento.getDni(),
                    mantenimiento.getDescripcion(),
                    mantenimiento.getCosto(),
                    mantenimiento.getEstado()
            });
        }

        //Asignar el modelo al JTable
        tablaMantenimiento.setModel(modeloTabla);

    }




}
