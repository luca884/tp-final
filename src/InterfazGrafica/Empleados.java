package InterfazGrafica;

import Enumeraciones.Puesto;
import Excepciones.ElementoDuplicadoException;
import Excepciones.ElementoNoEncontradoException;
import Gestores.Gestor;
import Gestores.GestorEmpleados;
import Personas.Empleado;
import Personas.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;

public class Empleados {

    private JButton menuButton;
    private JButton editarButton;
    private JButton borrarButton;
    private JButton registrarButton;
    private JPanel PanelEmpleados;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private GestorEmpleados gestorEmpleados = new GestorEmpleados();


    public Empleados() {
        // Cargar empleados desde el archivo
        gestorEmpleados.cargarDesdeArchivo("empleados.json", Empleado.class);
        // Cargar los datos en la tabla al crear la ventana
        cargarDatosEnTabla();

        // Acción para el botón de menú
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGerente menuGerente = new MenuGerente();
                menuGerente.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(menuButton);
                frame.dispose();
            }
        });

        // Acción para el botón de registrar un nuevo empleado
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarEmpleado registrarEmpleado = new RegistrarEmpleado();
                registrarEmpleado.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(registrarButton);
                frame.dispose();
            }
        });

        // Acción para el botón de editar
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabla.getSelectedRow();
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
                    JTextField editSalarioField = new JTextField(String.valueOf(salario));
                    JTextField editHorarioField = new JTextField(horario);  // Campo para editar horario

                    // Crear los JRadioButton para los puestos
                    JRadioButton mantenimientoRadioButton = new JRadioButton(Puesto.MANTENIMIENTO.name());
                    JRadioButton administradorRadioButton = new JRadioButton(Puesto.ADMINISTRADOR.name());
                    JRadioButton servicioRadioButton = new JRadioButton(Puesto.SERVICIO_AL_CLIENTE.name());
                    JRadioButton spaRadioButton = new JRadioButton(Puesto.SPA.name());
                    JRadioButton guarderiaRadioButton = new JRadioButton(Puesto.GUARDERIA.name());

                    // Crear un ButtonGroup para los RadioButton
                    ButtonGroup puestoButtonGroup = new ButtonGroup();
                    puestoButtonGroup.add(mantenimientoRadioButton);
                    puestoButtonGroup.add(administradorRadioButton);
                    puestoButtonGroup.add(servicioRadioButton);
                    puestoButtonGroup.add(spaRadioButton);
                    puestoButtonGroup.add(guarderiaRadioButton);

                    // Marcar el RadioButton que corresponde al puesto actual
                    if (puesto == Puesto.MANTENIMIENTO) {
                        mantenimientoRadioButton.setSelected(true);
                    } else if (puesto == Puesto.ADMINISTRADOR) {
                        administradorRadioButton.setSelected(true);
                    } else if (puesto == Puesto.SERVICIO_AL_CLIENTE) {
                        servicioRadioButton.setSelected(true);
                    } else if (puesto == Puesto.SPA) {
                        spaRadioButton.setSelected(true);
                    } else if (puesto == Puesto.GUARDERIA) {
                        guarderiaRadioButton.setSelected(true);
                    }

                    // Añadir los componentes al panel
                    editPanel.add(new JLabel("Nombre:"));
                    editPanel.add(editNombreField);
                    editPanel.add(new JLabel("Apellido:"));
                    editPanel.add(editApellidoField);
                    editPanel.add(new JLabel("Salario:"));
                    editPanel.add(editSalarioField);
                    editPanel.add(new JLabel("Horario:"));  // Nueva etiqueta para el horario
                    editPanel.add(editHorarioField);  // Nuevo campo de texto para el horario
                    editPanel.add(new JLabel("Puesto:"));
                    editPanel.add(mantenimientoRadioButton);
                    editPanel.add(new JLabel(""));
                    editPanel.add(administradorRadioButton);
                    editPanel.add(new JLabel(""));
                    editPanel.add(servicioRadioButton);
                    editPanel.add(new JLabel(""));
                    editPanel.add(spaRadioButton);
                    editPanel.add(new JLabel(""));
                    editPanel.add(guarderiaRadioButton);

                    JButton confirmButton = new JButton("Confirmar");
                    confirmButton.addActionListener(e1 -> {
                        try {
                            // Obtener los datos editados
                            double nuevoSalario = Double.parseDouble(editSalarioField.getText());
                            String nuevoHorario = editHorarioField.getText();

                            // Determinar el puesto seleccionado
                            Puesto puestoSeleccionado = null;
                            if (mantenimientoRadioButton.isSelected()) {
                                puestoSeleccionado = Puesto.MANTENIMIENTO;
                            } else if (administradorRadioButton.isSelected()) {
                                puestoSeleccionado = Puesto.ADMINISTRADOR;
                            } else if (servicioRadioButton.isSelected()) {
                                puestoSeleccionado = Puesto.SERVICIO_AL_CLIENTE;
                            } else if (spaRadioButton.isSelected()) {
                                puestoSeleccionado = Puesto.SPA;
                            } else if (guarderiaRadioButton.isSelected()) {
                                puestoSeleccionado = Puesto.GUARDERIA;
                            }

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
                                        nuevoHorario,
                                        puestoSeleccionado,
                                        nuevoSalario
                                );

                                // Eliminar el empleado anterior y agregar el editado
                                gestorEmpleados.eliminar(empleadoSeleccionado);
                                gestorEmpleados.agregar(empleadoEditado);

                                // Guardar los cambios en el archivo JSON
                                gestorEmpleados.guardarEnArchivo("empleados.json");

                                // Actualizar la tabla
                                modeloTabla.setValueAt(editNombreField.getText(), filaSeleccionada, 1);
                                modeloTabla.setValueAt(editApellidoField.getText(), filaSeleccionada, 2);
                                modeloTabla.setValueAt(puestoSeleccionado.name(), filaSeleccionada, 3);
                                modeloTabla.setValueAt(nuevoSalario, filaSeleccionada, 4);
                                modeloTabla.setValueAt(nuevoHorario, filaSeleccionada, 5);

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






        // Acción para el botón de borrar
        borrarButton.addActionListener(e -> {
            int[] filasSeleccionadas = tabla.getSelectedRows();

            if (filasSeleccionadas.length > 0) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        PanelEmpleados,
                        "¿Estás seguro de que deseas eliminar estos empleados?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                        String dniEmpleado = (String) modeloTabla.getValueAt(filasSeleccionadas[i], 0);
                        borrarEmpleado(dniEmpleado);
                        modeloTabla.removeRow(filasSeleccionadas[i]);
                    }
                    cargarDatosEnTabla(); //actualiza los cambios
                }
            } else {
                JOptionPane.showMessageDialog(PanelEmpleados, "Seleccione al menos un empleado para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
    }




    public void setVisible(boolean visible) {
        // Cuando se hace visible la ventana, recarga los datos en la tabla
        if (visible) {
            cargarDatosEnTabla(); // Asegura que la tabla se actualice al abrir la ventana
        }

        JFrame frame = new JFrame("Empleados");
        frame.setContentPane(PanelEmpleados); // Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la ventana, pero no para el programa
        frame.pack(); // Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Muestra la ventana en pantalla completa
        frame.setLocationRelativeTo(null); // Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); // Muestra la ventana si "visible" es true
    }

    private void borrarEmpleado(String dniEmpleado) {
        boolean eliminado = gestorEmpleados.getLista().removeIf(emp -> emp.getDni().equals(dniEmpleado));

        if (eliminado) {
            gestorEmpleados.guardarEnArchivo("empleados.json");
            gestorEmpleados.cargarDesdeArchivo("empleados.json", Empleado.class); // Recarga los datos
            System.out.println("Empleado eliminado y archivo actualizado.");
        } else {
            System.err.println("Empleado no encontrado.");
        }
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
        tabla.setModel(modeloTabla);
    }


}
