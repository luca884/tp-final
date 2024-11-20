package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IGMantenimiento {
    private JTable tablaMantenimiento;
    private JButton borrarButton;
    private JButton editarButton;
    private JButton atrasButton;
    private JPanel panel;
    private JButton registrarButton;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;

    public IGMantenimiento() {


        // Acción para el botón de volver atrás
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGerente menuGerente = new MenuGerente();
                menuGerente.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });

        // Acción para registrar nuevo mantenimiento
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MantenimientoRegistrar mantenimientoRegistrar = new MantenimientoRegistrar();
                mantenimientoRegistrar.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(registrarButton);
                frame.dispose();
            }
        });

        // Acción para editar mantenimiento
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaMantenimiento.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener los datos de la fila seleccionada
                    String id = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                    String descripcion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                    String fecha = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
                    String estado = (String) modeloTabla.getValueAt(filaSeleccionada, 3);

                    // Crear un JDialog para editar el mantenimiento
                    JDialog dialog = new JDialog((JFrame) null, "Editar Mantenimiento", true);
                    dialog.setLayout(new BorderLayout());

                    JPanel editPanel = new JPanel(new GridLayout(4, 2));
                    JTextField editDescripcionField = new JTextField(descripcion);
                    JTextField editFechaField = new JTextField(fecha);
                    JTextField editEstadoField = new JTextField(estado);

                    editPanel.add(new JLabel("Descripción:"));
                    editPanel.add(editDescripcionField);
                    editPanel.add(new JLabel("Fecha de Mantenimiento:"));
                    editPanel.add(editFechaField);
                    editPanel.add(new JLabel("Estado:"));
                    editPanel.add(editEstadoField);

                    JButton confirmButton = new JButton("Confirmar");
                    confirmButton.addActionListener(e1 -> {
                        // Actualizar los datos en la tabla
                        modeloTabla.setValueAt(editDescripcionField.getText(), filaSeleccionada, 1);
                        modeloTabla.setValueAt(editFechaField.getText(), filaSeleccionada, 2);
                        modeloTabla.setValueAt(editEstadoField.getText(), filaSeleccionada, 3);

                        // Cerrar el diálogo
                        dialog.dispose();
                    });

                    dialog.add(editPanel, BorderLayout.CENTER);
                    dialog.add(confirmButton, BorderLayout.SOUTH);
                    dialog.setSize(new Dimension(350, 200));
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para borrar mantenimiento
        borrarButton.addActionListener(e -> {
            int[] filasSeleccionadas = tablaMantenimiento.getSelectedRows();

            if (filasSeleccionadas.length > 0) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        panel,
                        "¿Estás seguro de que deseas eliminar estos mantenimientos?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Eliminar las filas seleccionadas
                    for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                        modeloTabla.removeRow(filasSeleccionadas[i]);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Seleccione al menos un mantenimiento para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public void setVisible(boolean visible) {
        // Cuando se hace visible la ventana, recarga los datos en la tabla
        if (visible) {
            cargarDatosEnTabla(); // Asegura que la tabla se actualice al abrir la ventana
        }

        JFrame frame = new JFrame("Mantenimiento");
        frame.setContentPane(panel); // Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la ventana, pero no para el programa
        frame.pack(); // Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Muestra la ventana en pantalla completa
        frame.setLocationRelativeTo(null); // Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); // Muestra la ventana si "visible" es true
    }

    private void cargarDatosEnTabla() {
        // Inicializar la tabla y el modelo de datos
        String[] columnas = {"ID", "Descripción", "Fecha de Mantenimiento", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        // Aquí puedes cargar los datos desde una fuente (por ejemplo, archivo o base de datos)
        // Ejemplo de datos estáticos para la tabla
        String[][] datos = {
                {"1", "Mantenimiento de aire acondicionado", "2024-11-20", "Pendiente"},
                {"2", "Reemplazo de bombillos", "2024-11-19", "Completado"}
        };

        // Agregar filas con los datos
        for (String[] fila : datos) {
            modeloTabla.addRow(fila);
        }

        // Asignar el modelo al JTable
        tablaMantenimiento.setModel(modeloTabla);
    }
}
