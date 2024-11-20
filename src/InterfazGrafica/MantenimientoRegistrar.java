package InterfazGrafica;

import Varios.Mantenimiento;
import Gestores.GestorMantenimiento;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashSet;

public class MantenimientoRegistrar {
    private JTextField textFieldDni;
    private JTextField textFieldDescripcion;
    private JTextField textFieldCosto;
    private JTextField textFieldEstado;
    private JButton ingresarButton;
    private JButton atrasButton;
    private JPanel panel;

    public MantenimientoRegistrar() {
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IGMantenimiento mantenimiento = new IGMantenimiento();
                mantenimiento.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni, descripcion, costo, estado;
                dni = textFieldDni.getText();
                descripcion = textFieldDescripcion.getText();
                costo = textFieldCosto.getText();
                estado = textFieldEstado.getText();

                // Validar que todos los campos estén completos
                if (!dni.isEmpty() && !descripcion.isEmpty() && !costo.isEmpty() && !estado.isEmpty()) {
                    // Crear el objeto de mantenimiento
                    Mantenimiento mantenimiento = new Mantenimiento();
                    mantenimiento.setDni(dni);
                    mantenimiento.setDescripcion(descripcion);
                    mantenimiento.setCosto(Double.valueOf(costo));
                    mantenimiento.setEstado(estado);

                    // Crear gestor de mantenimiento
                    GestorMantenimiento gestorMantenimiento = new GestorMantenimiento();

                    // Cargar mantenimiento guardado en archivo, si existe el archivo
                    File archivoMantenimiento = new File("mantenimiento.json");
                    if (archivoMantenimiento.exists()) {
                        Type type = new TypeToken<HashSet<Mantenimiento>>() {}.getType();
                        gestorMantenimiento.cargarDesdeArchivo("mantenimiento.json", type);
                    }

                    try {
                        // Agregar el mantenimiento al gestor
                        gestorMantenimiento.agregar(mantenimiento);
                        // Actualizar el archivo
                        gestorMantenimiento.guardarEnArchivo("mantenimiento.json");

                        // Limpiar los campos de entrada
                        limpiarCampos();

                        JOptionPane.showMessageDialog(panel, "Mantenimiento registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                        // Volver a la vista anterior
                        IGMantenimiento mantenimientoVentana = new IGMantenimiento();
                        mantenimientoVentana.setVisible(true);
                        JFrame frame = (JFrame) SwingUtilities.getRoot(ingresarButton);
                        frame.dispose();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Error al registrar el mantenimiento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Si algún campo está vacío
                    JOptionPane.showMessageDialog(panel, "Error al registrar el mantenimiento. Campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void limpiarCampos() {
        textFieldDni.setText("");
        textFieldDescripcion.setText("");
        textFieldCosto.setText("");
        textFieldEstado.setText("");
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Registrar Mantenimiento");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}
