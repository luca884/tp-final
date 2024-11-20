package InterfazGrafica;

import Varios.Mantenimiento;
import Gestores.GestorMantenimiento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String dni = textFieldDni.getText();
                String descripcion = textFieldDescripcion.getText();
                double costo = Double.parseDouble(textFieldCosto.getText());
                String estado = textFieldEstado.getText();

                Mantenimiento mantenimiento = new Mantenimiento(dni, descripcion, costo, estado);

                try {
                    GestorMantenimiento gestorMantenimiento = new GestorMantenimiento();
                    gestorMantenimiento.cargarDesdeArchivo("mantenimiento.json", Mantenimiento.class);
                    gestorMantenimiento.agregar(mantenimiento);
                    gestorMantenimiento.guardarEnArchivo("mantenimiento.json");

                    JOptionPane.showMessageDialog(null, "Mantenimiento registrado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el mantenimiento: " + ex.getMessage());
                }
            }
        });
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
