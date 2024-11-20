package InterfazGrafica;
import Enumeraciones.Puesto;
import Excepciones.ElementoDuplicadoException;
import Gestores.GestorEmpleados;
import InterfazGrafica.Empleados;
import Personas.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RegistrarEmpleado {
    private JPanel panel1;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textDni;
    private JTextField textHorario;
    private JTextField textSalario;
    private JButton confirmarButton;
    private JButton atrasButton;
    private JCheckBox checkBoxAdmin;
    private JCheckBox checkBoxMantenimiento;
    private JCheckBox checkBoxServicio;
    private JCheckBox checkBoxSpa;
    private JCheckBox checkBoxGuarderia;
    Empleado empleado = new Empleado();
    Empleados empleados = new Empleados();

    private DefaultTableModel modeloTabla;

    public RegistrarEmpleado() {

        // Crear un ButtonGroup para los JCheckBox
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(checkBoxAdmin);
        buttonGroup.add(checkBoxMantenimiento);
        buttonGroup.add(checkBoxServicio);
        buttonGroup.add(checkBoxSpa);
        buttonGroup.add(checkBoxGuarderia);


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre, apellido, dni, horario, salario;
                Puesto puesto = null;

                nombre = textNombre.getText();
                apellido = textApellido.getText();
                dni = textDni.getText();
                horario = textHorario.getText();
                salario = textSalario.getText();

                // Verificar cuál JCheckBox está seleccionado
                if (checkBoxAdmin.isSelected()) {
                    puesto = Puesto.ADMINISTRADOR;
                }
                if (checkBoxMantenimiento.isSelected()) {
                    puesto = Puesto.MANTENIMIENTO;
                }
                if (checkBoxServicio.isSelected()) {
                    puesto = Puesto.SERVICIO_AL_CLIENTE;
                }
                if (checkBoxSpa.isSelected()) {
                    puesto = Puesto.SPA;
                }
                if (checkBoxGuarderia.isSelected()) {
                    puesto = Puesto.GUARDERIA;
                }

                if (puesto == null) {
                    JOptionPane.showMessageDialog(panel1, "Debe seleccionar un puesto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Detener la ejecución si no se seleccionó un puesto
                }

                if (!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !horario.isEmpty() && !salario.isEmpty()) {
                    Empleado empleado = new Empleado();
                    empleado.setNombre(nombre);
                    empleado.setApellido(apellido);
                    empleado.setDni(dni);
                    empleado.setHorario(horario);
                    empleado.setSalario(Double.valueOf(salario));
                    empleado.setPuesto(puesto);

                    // Crea gestor
                    GestorEmpleados gestorEmpleados = new GestorEmpleados();

                    // Carga empleados guardados en archivo, si existe el archivo
                    File archivo_empleados = new File("empleados.json");
                    if (archivo_empleados.exists()) {
                        gestorEmpleados.cargarDesdeArchivo("empleados.json");
                    }

                    try {
                        // Agrega empleado al gestor
                        gestorEmpleados.agregar(empleado);
                        // Actualiza archivo
                        gestorEmpleados.guardarEnArchivo("empleados.json");

                    } catch (ElementoDuplicadoException ex) {
                        System.err.println(ex.getMessage());
                    }

                    limpiar();
                    JOptionPane.showMessageDialog(panel1, "Empleado registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    Empleados empleados = new Empleados();
                    empleados.cargarDatosEnTabla();

                    empleados.setVisible(true);
                    JFrame frame = (JFrame) SwingUtilities.getRoot(confirmarButton);
                    frame.dispose();

                } else { // Si algún campo está vacío
                    JOptionPane.showMessageDialog(panel1, "Error al registrar el empleado. Campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botón Atras
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleados empleados = new Empleados();
                empleados.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });
    }

    public void limpiar() {
        textNombre.setText("");
        textApellido.setText("");
        textDni.setText("");
        textHorario.setText("");
        textSalario.setText("");
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Registrar Empleado");
        frame.setContentPane(panel1); // Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la ventana, pero no para el programa
        frame.pack(); // Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); // Hace foco a la ventana
        frame.setLocationRelativeTo(null); // Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); // Muestra la ventana si "visible" es true
    }
}
