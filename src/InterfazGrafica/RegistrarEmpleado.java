package InterfazGrafica;
import Enumeraciones.Puesto;
import Excepciones.ElementoDuplicadoException;
import Gestores.GestorEmpleados;
import InterfazGrafica.Empleados;
import Personas.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

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
                    // Crear diálogo para usuario y contraseña
                    final String[] usuario = new String[1];
                    final String[] contrasena = new String[1];

                    JDialog dialogo = new JDialog((JFrame) null, "Usuario y contraseña", true);
                    dialogo.setLayout(new BorderLayout());
                    JPanel editPanel = new JPanel(new GridLayout(6, 4));

                    JTextField editUsuarioField = new JTextField();
                    editPanel.add(new JLabel("Usuario:"));
                    editPanel.add(editUsuarioField);

                    JPasswordField editContrasenaField = new JPasswordField();
                    editPanel.add(new JLabel("Contraseña:"));
                    editPanel.add(editContrasenaField);

                    JButton confirmButton = new JButton("Confirmar");
                    Puesto finalPuesto = puesto;
                    confirmButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String usuarioIngresado = editUsuarioField.getText().trim();
                            String contrasenaIngresada = new String(editContrasenaField.getPassword());

                            if (!usuarioIngresado.isEmpty() && !contrasenaIngresada.isEmpty()) {
                                if (!usuarioIngresado.equals(contrasenaIngresada)) {
                                    usuario[0] = usuarioIngresado;
                                    contrasena[0] = contrasenaIngresada;

                                    // Crear empleado y asignar valores
                                    Empleado empleado = new Empleado();
                                    empleado.setNombre(nombre);
                                    empleado.setApellido(apellido);
                                    empleado.setDni(dni);
                                    empleado.setHorario(horario);
                                    empleado.setSalario(Double.valueOf(salario));
                                    empleado.setPuesto(finalPuesto);

                                    // Asignar credenciales
                                    HashMap<String, Integer> credenciales = new HashMap<>();
                                    credenciales.put(usuario[0], contrasena[0].hashCode());
                                    empleado.setCredenciales(credenciales);

                                    // Crear gestor
                                    GestorEmpleados gestorEmpleados = new GestorEmpleados();

                                    File archivo_empleados = new File("empleados.json");
                                    if (archivo_empleados.exists()) {
                                        gestorEmpleados.cargarDesdeArchivo("empleados.json");
                                    }

                                    try {
                                        gestorEmpleados.agregar(empleado);
                                        gestorEmpleados.guardarEnArchivo("empleados.json");

                                        // Mensaje de éxito
                                        JOptionPane.showMessageDialog(dialogo, "Empleado registrado correctamente con usuario y contraseña.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                        dialogo.dispose(); // Cierra el diálogo
                                        limpiar(); // Limpiar campos
                                        // Cerrar ventana principal y abrir Empleados
                                        Window mainFrame = SwingUtilities.getWindowAncestor(panel1);
                                        if (mainFrame != null) {
                                            mainFrame.dispose();
                                        }
                                        Empleados empleados = new Empleados();
                                        empleados.setVisible(true);


                                    } catch (ElementoDuplicadoException ex) {
                                        JOptionPane.showMessageDialog(panel1, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(dialogo, "El usuario y la contraseña deben ser distintos.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(dialogo, "Debe completar ambos campos.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    dialogo.add(editPanel, BorderLayout.CENTER);
                    dialogo.add(confirmButton, BorderLayout.SOUTH);
                    dialogo.setSize(new Dimension(300, 220));
                    dialogo.setLocationRelativeTo(null);
                    dialogo.setVisible(true);


                } else {
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
