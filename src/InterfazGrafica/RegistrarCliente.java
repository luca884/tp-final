package InterfazGrafica;

import Enumeraciones.Carpa;
import Enumeraciones.Servicio;
import Excepciones.ElementoDuplicadoException;
import Gestores.GestorClientes;
import Personas.Cliente;
import Enumeraciones.Estacionamiento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.HashMap;

public class RegistrarCliente {
    private JButton atrasButton;
    private JTextField textNombre;
    private JTextField textPrecioTotal;
    private JTextField textApellido;
    private JTextField textDni;
    private JTextField textTelefono;
    private JTextField textCorreoelectronico;
    private JCheckBox checkBox_CarpaVIP;
    private JCheckBox checkBox_CarpaStandard;
    private JCheckBox checkBox_EstacionamientoTechado;
    private JCheckBox checkBoxEstacionamientoStandard;
    private JCheckBox checkBox_SINestacionamiento;
    private JCheckBox checkBox_Spa;
    private JCheckBox checkBox_Guarderia;
    private JButton registrarButton;
    private JPanel panel;
private double precioTotal;
private Cliente cliente = new Cliente();

    public RegistrarCliente(){
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IniciarSesion iniciarSesion = new IniciarSesion();
                iniciarSesion.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });




        // Crear ButtonGroups para las opciones mutuamente excluyentes
        ButtonGroup carpasGroup = new ButtonGroup();
        ButtonGroup estacionamientoGroup = new ButtonGroup();

// Asignar los JCheckBox de Carpas al ButtonGroup
        carpasGroup.add(checkBox_CarpaVIP);
        carpasGroup.add(checkBox_CarpaStandard);

// Asignar los JCheckBox de Estacionamiento al ButtonGroup
        estacionamientoGroup.add(checkBox_EstacionamientoTechado);
        estacionamientoGroup.add(checkBoxEstacionamientoStandard);
        estacionamientoGroup.add(checkBox_SINestacionamiento);

        checkBox_Spa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
                // Lógica para definir el servicio cuando se selecciona o deselecciona el Spa
                if (checkBox_Spa.isSelected()) {
                    // Si Guardería también está seleccionada, asignar el servicio "GUARDERIA_Y_SPA"
                    if (checkBox_Guarderia.isSelected()) {
                        cliente.setServicio(Servicio.GUARDERIA_Y_SPA);
                    } else {
                        cliente.setServicio(Servicio.SPA);
                    }
                } else {
                    // Si Guardería está seleccionada pero Spa no, asignar Guardería
                    if (checkBox_Guarderia.isSelected()) {
                        cliente.setServicio(Servicio.GUARDERIA);
                    } else {
                        cliente.setServicio(null); // Si ninguno está seleccionado, servicio es null
                    }
                }
            }
        });

        checkBox_Guarderia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
                // Lógica para definir el servicio cuando se selecciona o deselecciona la Guardería
                if (checkBox_Guarderia.isSelected()) {
                    // Si Spa también está seleccionado, asignar el servicio "GUARDERIA_Y_SPA"
                    if (checkBox_Spa.isSelected()) {
                        cliente.setServicio(Servicio.GUARDERIA_Y_SPA);
                    } else {
                        cliente.setServicio(Servicio.GUARDERIA);
                    }
                } else {
                    // Si Spa está seleccionado pero Guardería no, asignar SPA
                    if (checkBox_Spa.isSelected()) {
                        cliente.setServicio(Servicio.SPA);
                    } else {
                        cliente.setServicio(null); // Si ninguno está seleccionado, servicio es null
                    }
                }
            }
        });

// Lógica para Carpas
        checkBox_CarpaVIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox_CarpaVIP.isSelected()) {
                    checkBox_CarpaStandard.setSelected(false); // Desmarcar la otra opción si se selecciona esta
                }
                actualizarPrecioTotal();
                cliente.setCarpa(checkBox_CarpaVIP.isSelected() ? Carpa.VIP : null);
            }
        });

        checkBox_CarpaStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox_CarpaStandard.isSelected()) {
                    checkBox_CarpaVIP.setSelected(false); // Desmarcar la otra opción si se selecciona esta
                }
                actualizarPrecioTotal();
                cliente.setCarpa(checkBox_CarpaStandard.isSelected() ? Carpa.ESTANDAR : null);
            }
        });

// Lógica para Estacionamientos
        checkBox_EstacionamientoTechado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox_EstacionamientoTechado.isSelected()) {
                    checkBoxEstacionamientoStandard.setSelected(false); // Desmarcar la otra opción si se selecciona esta
                    checkBox_SINestacionamiento.setSelected(false); // Desmarcar la opción sin estacionamiento
                }
                actualizarPrecioTotal();
                cliente.setEstacionamiento(checkBox_EstacionamientoTechado.isSelected() ? Estacionamiento.VIP : Estacionamiento.NO_INCLUYE);
            }
        });

        checkBoxEstacionamientoStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxEstacionamientoStandard.isSelected()) {
                    checkBox_EstacionamientoTechado.setSelected(false); // Desmarcar la otra opción si se selecciona esta
                    checkBox_SINestacionamiento.setSelected(false); // Desmarcar la opción sin estacionamiento
                }
                actualizarPrecioTotal();
                cliente.setEstacionamiento(checkBoxEstacionamientoStandard.isSelected() ? Estacionamiento.ESTANDAR : Estacionamiento.NO_INCLUYE);
            }
        });

        checkBox_SINestacionamiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox_SINestacionamiento.isSelected()) {
                    checkBox_EstacionamientoTechado.setSelected(false); // Desmarcar la opción con techo si se selecciona esta
                    checkBoxEstacionamientoStandard.setSelected(false); // Desmarcar la opción estándar si se selecciona esta
                }
                actualizarPrecioTotal();
                cliente.setEstacionamiento(checkBox_SINestacionamiento.isSelected() ? Estacionamiento.NO_INCLUYE : Estacionamiento.NO_INCLUYE);
            }
        });




        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            String nombre, apellido, dni, telefono, correoelectronico, precioTotal;

            nombre = textNombre.getText();
            apellido = textApellido.getText();
            dni = textDni.getText();
            telefono = textTelefono.getText();
            correoelectronico = textCorreoelectronico.getText();
            precioTotal = textPrecioTotal.getText();

            if(!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !telefono.isEmpty() && !correoelectronico.isEmpty() && !precioTotal.isEmpty()){

                // crea cliente con los datos

                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setDni(dni);
                cliente.setTelefono(Long.valueOf(telefono));
                cliente.setCorreo(correoelectronico);
                cliente.setValor_Total(Double.parseDouble(textPrecioTotal.getText()));



                // crea gestor
                GestorClientes gestor_clientes = new GestorClientes();

                // carga clientes guardados en archivo, si existe el archivo
                File archivo_clientes = new File("clientes.json");
                if(archivo_clientes.exists()){
                    gestor_clientes.cargarDesdeArchivo("clientes.json");
                }

                try {
                    // agrega cliente al gestor
                    gestor_clientes.agregar(cliente);

                    // actualiza archivo
                    gestor_clientes.guardarEnArchivo("clientes.json");

                } catch (ElementoDuplicadoException ex) {
                    System.err.println(ex.getMessage());
                }
                // Variables para usuario y contraseña
                final String[] usuario = new String[1];   // Variable para el usuario
                final String[] contrasena = new String[1]; // Variable para la contraseña

                // Crear el diálogo
                JDialog dialogo = new JDialog((JFrame) null, "Usuario y contraseña", true);
                dialogo.setLayout(new BorderLayout());
                JPanel editPanel = new JPanel(new GridLayout(6, 4));

                    // Campo para el usuario
                JTextField editUsuarioField = new JTextField();
                editPanel.add(new JLabel("Usuario:"));
                editPanel.add(editUsuarioField);

                    // Campo para la contraseña
                JPasswordField editContrasenaField = new JPasswordField();
                editPanel.add(new JLabel("Contraseña:"));
                editPanel.add(editContrasenaField);

                // Botón para confirmar
                JButton confirmButton = new JButton("Confirmar");

                // Acción al presionar el botón
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Captura los valores ingresados
                        String usuarioIngresado = editUsuarioField.getText().trim();
                        String contrasenaIngresada = new String(editContrasenaField.getPassword());

                        // Verificar que ambos campos estén completos
                        if (!usuarioIngresado.isEmpty() && !contrasenaIngresada.isEmpty()) {
                            // Verificar que el usuario y la contraseña sean distintos
                            if (!usuarioIngresado.equals(contrasenaIngresada)) {
                                usuario[0] = usuarioIngresado;
                                contrasena[0] = contrasenaIngresada;

                                // crea cliente con los datos ingresados
                                Cliente cliente = new Cliente();
                                cliente.setNombre(nombre);
                                cliente.setApellido(apellido);
                                cliente.setDni(dni);
                                cliente.setTelefono(Long.valueOf(telefono));
                                cliente.setCorreo(correoelectronico);
                                cliente.setValor_Total(Double.parseDouble(textPrecioTotal.getText()));

                                HashMap<String, Integer> credenciales = new HashMap<>();
                                credenciales.put(usuario[0], contrasena[0].hashCode());
                                cliente.setCredenciales(credenciales);

                                // crea gestor
                                GestorClientes gestor_clientes = new GestorClientes();

                                // agrega cliente a archivo
                                gestor_clientes.agregarAlArchivo(cliente, "clientes.json", Cliente.class);

                                // Mensaje de éxito
                                JOptionPane.showMessageDialog(dialogo, "Usuario y contraseña guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                dialogo.dispose(); // Cierra el diálogo
                            } else {
                                // Mensaje de error si son iguales
                                JOptionPane.showMessageDialog(dialogo, "El usuario y la contraseña deben ser distintos.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Mensaje de error si faltan campos
                            JOptionPane.showMessageDialog(dialogo, "Debe completar ambos campos.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // Agregar componentes al diálogo
                dialogo.add(editPanel, BorderLayout.CENTER);
                dialogo.add(confirmButton, BorderLayout.SOUTH);
                dialogo.setSize(new Dimension(300, 220));
                dialogo.setLocationRelativeTo(null);
                dialogo.setVisible(true);

                limpiarCampos();
                JOptionPane.showMessageDialog(panel, "Cliente registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            /*   RegistrarUsuarioContra registrarUsuarioContra = new RegistrarUsuarioContra();
               registrarUsuarioContra.setVisible(true);*/
                JFrame frame = (JFrame) SwingUtilities.getRoot(registrarButton);
                frame.dispose();

            }else {
                JOptionPane.showMessageDialog(panel, "Error al registrar sus datos. Campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);

            }



            }
        });




    }


    private void actualizarPrecioTotal() {
        precioTotal = 0;  // Reinicia el precio total antes de recalcularlo

        // Verifica que solo una opción de carpa esté seleccionada
        if (checkBox_CarpaVIP.isSelected() && checkBox_CarpaStandard.isSelected()) {
            JOptionPane.showMessageDialog(panel, "Solo puede seleccionar una carpa", "Error", JOptionPane.ERROR_MESSAGE);
            checkBox_CarpaVIP.setSelected(false);
            checkBox_CarpaStandard.setSelected(false);
            return; // Sale de la función si hay un error
        }

        // Verifica que solo una opción de estacionamiento esté seleccionada
        if ((checkBox_EstacionamientoTechado.isSelected() && checkBoxEstacionamientoStandard.isSelected()) ||
                (checkBox_EstacionamientoTechado.isSelected() && checkBox_SINestacionamiento.isSelected()) ||
                (checkBoxEstacionamientoStandard.isSelected() && checkBox_SINestacionamiento.isSelected())) {
            JOptionPane.showMessageDialog(panel, "Solo puede seleccionar una opción de estacionamiento", "Error", JOptionPane.ERROR_MESSAGE);
            checkBox_EstacionamientoTechado.setSelected(false);
            checkBoxEstacionamientoStandard.setSelected(false);
            checkBox_SINestacionamiento.setSelected(false);
            return; // Sale de la función si hay un error
        }

        // Lógica para el cálculo del precio
        if (checkBox_CarpaVIP.isSelected()) {
            precioTotal += 1000000;
        }

        if (checkBox_CarpaStandard.isSelected()) {
            precioTotal += 750000;
        }

        if (checkBox_EstacionamientoTechado.isSelected()) {
            precioTotal += 500000;
        }

        if (checkBoxEstacionamientoStandard.isSelected()) {
            precioTotal += 250000;
        }

        if (checkBox_SINestacionamiento.isSelected()) {
            precioTotal += 0;
        }

        if (checkBox_Spa.isSelected()) {
            precioTotal += 300000;
        }

        if (checkBox_Guarderia.isSelected()) {
            precioTotal += 400000;
        }


        // Actualiza el JTextField con el precio total
        textPrecioTotal.setText(String.format(Locale.US, "%.0f", precioTotal));
    }


    private void limpiarCampos(){
        textNombre.setText("");
        textApellido.setText("");
        textDni.setText("");
        textTelefono.setText("");
        textCorreoelectronico.setText("");
        textPrecioTotal.setText("");
    }


    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Registrar Clientes");
        frame.setContentPane(panel); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }

}
