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
    private JPanel panel;
    private JButton atrasButton;
    private JButton editarButton;
    private JTable tablaEmpleados;
    private JLabel subTitulo;
    private DefaultTableModel modeloTabla;
    private GestorEmpleados gestorEmpleados = new GestorEmpleados();


    public MenuEmpleado(){

        cargarDatosEnTabla();

        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaEmpleados.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener los datos de la fila seleccionada
                    String dni = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                    String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                    String apellido = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
                    Puesto puesto = (Puesto)  modeloTabla.getValueAt(filaSeleccionada, 3); // Convertir el String Enum
                    double salario = (double) modeloTabla.getValueAt(filaSeleccionada, 4);
                    String horario = (String) modeloTabla.getValueAt(filaSeleccionada, 5);  // Obtener horario

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

                            // Obtener el DNI de la fila seleccionada
                            String dniSeleccionado = (String) modeloTabla.getValueAt(filaSeleccionada, 0);  // Asumiendo que la columna 0 contiene el DNI

                            // Buscar el empleado por DNI en la lista
                            Empleado empleadoSeleccionado = null;
                            for (Empleado empleado : gestorEmpleados.getLista()) {
                                if (empleado.getDni().equals(dniSeleccionado)) {
                                    empleadoSeleccionado = empleado;
                                    break;
                                }
                            }

                            if (empleadoSeleccionado != null) {
                                // Crear el nuevo objeto Empleado
                                Empleado empleadoEditado = new Empleado(
                                        editNombreField.getText(),
                                        editApellidoField.getText(),
                                        dniSeleccionado, // Mantener el DNI original
                                        horario,
                                        puesto,
                                        salario
                                );

                                // Eliminar el empleado anterior y agregar el editado
                                gestorEmpleados.eliminar(empleadoSeleccionado);
                                gestorEmpleados.agregar(empleadoEditado);

                                // Guardar los cambios en el archivo JSON
                                gestorEmpleados.guardarEnArchivo("empleados.json");

                                // Actualizar la tabla
                                modeloTabla.setValueAt(nuevoNombre, filaSeleccionada, 1);
                                modeloTabla.setValueAt(nuevoApellido, filaSeleccionada, 2);

                                dialog.dispose(); // Cerrar el diálogo
                            } else {
                                JOptionPane.showMessageDialog(null, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                            }
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
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
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

    void cargarDatosEnTabla() {
        // Configurar las columnas de la tabla
        String[] columnas = {"DNI", "Nombre", "Apellido", "Puesto", "Salario", "Horario"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        // Obtener el HashSet de empleados desde el gestor
        HashSet<Empleado> conjuntoEmpleados = gestorEmpleados.getLista();

        // Agregar filas con los datos de los empleados
        for (Empleado empleado : conjuntoEmpleados) {
            modeloTabla.addRow(new Object[]{
                    empleado.getDni(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getPuesto(),
                    empleado.getSalario(),
                    empleado.getHorario(),
            });
        }

        // Asignar el modelo al JTable
        tablaEmpleados.setModel(modeloTabla);
    }



}
