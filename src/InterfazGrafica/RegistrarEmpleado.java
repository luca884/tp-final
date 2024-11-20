package InterfazGrafica;
import Enumeraciones.Puesto;
import Excepciones.ElementoDuplicadoException;
import Gestores.GestorEmpleados;
import Personas.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegistrarEmpleado {
    private JPanel panel1;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textDni;
    private JTextField textHorario;
    private JTextField textSalario;
    private JTextField textPuesto;
    private JButton confirmarButton;
    private JButton atrasButton;
    private JCheckBox checkBoxAdmin;
    private JCheckBox checkBoxMantenimiento;
    private JCheckBox checkBoxServicio;

    private DefaultTableModel modeloTabla;

    public RegistrarEmpleado() {

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String nombre, apellido, dni, horario, salario;
                    Puesto puesto = null;



                    nombre = textNombre.getText();
                    apellido = textApellido.getText();
                    dni = textDni.getText();
                    horario = textHorario.getText();
                    salario =textSalario.getText();

                    if (checkBoxAdmin.isSelected()){
                        puesto = Puesto.ADMINISTRADOR;
                    }
                    if (checkBoxMantenimiento.isSelected()){
                        puesto = Puesto.MANTENIMIENTO;
                    }
                    if (checkBoxServicio.isSelected()){
                        puesto = Puesto.SERVICIO_AL_CLIENTE;
                    }

                if (puesto == null) {
                    JOptionPane.showMessageDialog(panel1, "Debe seleccionar un puesto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Detener la ejecución si no se seleccionó un puesto
                }



                    if (!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !horario.isEmpty() && !salario.isEmpty()){

                        Empleado empleado = new Empleado();
                        empleado.setNombre(nombre);
                        empleado.setApellido(apellido);
                        empleado.setDni(dni);
                        empleado.setHorario(horario);
                        empleado.setSalario(Double.valueOf(salario));
                        empleado.setPuesto(puesto);

                        // crea gestor
                        GestorEmpleados gestorEmpleados = new GestorEmpleados();

                        // carga clientes guardados en archivo, si existe el archivo
                        File archivo_clientes = new File("empleados.json");
                        if(archivo_clientes.exists()){
                            gestorEmpleados.cargarDesdeArchivo("empleados.json");
                        }

                        try {
                            // agrega cliente al gestor
                            gestorEmpleados.agregar(empleado);
                            // actualiza archivo
                            gestorEmpleados.guardarEnArchivo("empleados.json");

                        } catch (ElementoDuplicadoException ex) {
                            System.err.println(ex.getMessage());
                        }

                        limpiar();
                        JOptionPane.showMessageDialog(panel1, "Empleado registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                        Empleados empleados = new Empleados();
                        empleados.setVisible(true);
                        JFrame frame = (JFrame) SwingUtilities.getRoot(confirmarButton);
                        frame.dispose();



                    }else {// Si algun Text esta vacio
                        JOptionPane.showMessageDialog(panel1, "Error al registrar el empleado. Campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);

                    }



            }
        });


        //Menu boton
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

        public void limpiar(){
            textNombre.setText("");
            textApellido.setText("");
            textDni.setText("");
            textHorario.setText("");
            textSalario.setText("");
        }


        public void setVisible(boolean visible){
            JFrame frame = new JFrame("Registrar Empleado");
            frame.setContentPane(panel1); //Asigna el contenido a la ventana
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
            frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
            frame.requestFocusInWindow(); //Hace foco a la ventana
            frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
            frame.setVisible(visible); //Muestra la ventana si "visible" es true
        }
    }



