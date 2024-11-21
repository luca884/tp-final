package InterfazGrafica;

import Enumeraciones.Puesto;
import Excepciones.ElementoDuplicadoException;
import Excepciones.ElementoNoEncontradoException;
import Gestores.GestorEmpleados;
import Personas.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class MenuEmpleado {
    private JPanel panel1;
    private JButton atrasButton;
    private JButton editarButton;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private GestorEmpleados gestorEmpleados = new GestorEmpleados();


    public MenuEmpleado(Empleado empleado){
        // Cargar datos en la tabla con el empleado recibido
        cargarDatosEnTabla(empleado);

        // Acciones del botón "Atras"
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IniciarSesion iniciarSesion = new IniciarSesion();
                iniciarSesion.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });

        // Acción del botón "Editar"
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Usar directamente el empleado recibido en lugar de obtenerlo de la tabla
                String dni = empleado.getDni();
                String nombre = empleado.getNombre();
                String apellido = empleado.getApellido();
                Puesto puesto = empleado.getPuesto();
                double salario = empleado.getSalario();
                String horario = empleado.getHorario();

                // Crear un JDialog para editar el empleado
                JDialog dialog = new JDialog((JFrame) null, "Editar Empleado", true);
                dialog.setLayout(new BorderLayout());

                JPanel editPanel = new JPanel(new GridLayout(8, 2));  // Modificar a 8 filas para los puestos
                JTextField editNombreField = new JTextField(nombre);
                JTextField editApellidoField = new JTextField(apellido);
                //Etiquetas para campos no editables
                JLabel salarioLabel = new JLabel(String.valueOf(salario));
                JLabel puestoLabel = new JLabel(puesto.name());
                JLabel horarioLabel = new JLabel(horario);

                // Añadir los componentes al panel
                editPanel.add(new JLabel("Nombre:"));
                editPanel.add(editNombreField);
                editPanel.add(new JLabel("Apellido:"));
                editPanel.add(editApellidoField);
                editPanel.add(new JLabel("Salario:"));
                editPanel.add(salarioLabel);
                editPanel.add(new JLabel("Horario:"));
                editPanel.add(horarioLabel);
                editPanel.add(new JLabel("Puesto:"));
                editPanel.add(puestoLabel);

                JButton confirmButton = new JButton("Confirmar");
                confirmButton.addActionListener(e1 -> {
                    try {
                        // Obtener los datos editados
                        String nuevoNombre = editNombreField.getText();
                        String nuevoApellido = editApellidoField.getText();

                        // Crear el nuevo objeto Empleado con los datos editados
                        Empleado empleadoEditado = new Empleado(
                                nuevoNombre,
                                nuevoApellido,
                                dni, // Mantener el DNI original
                                horario,
                                puesto,
                                salario
                        );

                        // Actualizar el empleado en la lista
                        gestorEmpleados.agregar(empleadoEditado);

                        gestorEmpleados.eliminar(empleado);
                        cargarDatosEnTabla(empleadoEditado);

                        // Guardar los cambios en el archivo JSON
                        gestorEmpleados.guardarEnArchivo("empleados.json");


                        // Actualizar la tabla
                        modeloTabla.setValueAt(nuevoNombre, 0, 1);
                        modeloTabla.setValueAt(nuevoApellido, 0, 2);

                        dialog.dispose(); // Cerrar el diálogo
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error al editar el salario", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ElementoDuplicadoException | ElementoNoEncontradoException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                dialog.add(editPanel, BorderLayout.CENTER);
                dialog.add(confirmButton, BorderLayout.SOUTH);
                dialog.setSize(new Dimension(350, 350));  // Ajustado para los nuevos campos
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }

    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Menu Empleado");
        frame.setContentPane(panel1); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }

    void cargarDatosEnTabla(Empleado empleado) {
        // Configurar las columnas de la tabla
        String[] columnas = {"DNI", "Nombre", "Apellido", "Puesto", "Salario", "Horario"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        // Agregar filas con los datos de los empleados

            modeloTabla.addRow(new Object[]{
                    empleado.getDni(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getPuesto(),
                    empleado.getSalario(),
                    empleado.getHorario(),
            });


        // Asignar el modelo al JTable
        tablaEmpleados.setModel(modeloTabla);
    }



}
