package InterfazGrafica;

import Excepciones.ElementoDuplicadoException;
import Gestores.GestorClientes;
import Personas.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;

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

        checkBox_CarpaVIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
            }
        });

        checkBox_CarpaStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
            }
        });

        checkBox_EstacionamientoTechado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
            }
        });

        checkBoxEstacionamientoStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
            }
        });

        checkBox_SINestacionamiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
            }
        });

        checkBox_Spa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();

            }
        });

        checkBox_Guarderia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecioTotal();
            }

        });


    }


    private void actualizarPrecioTotal() {
        precioTotal = 0;  // Reinicia el precio total antes de recalcularlo

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
        textPrecioTotal.setText(String.format(Locale.US, "%.2f", precioTotal));
        // textPrecioTotal.setText(String.format(Locale.forLanguageTag("en-US"), "%.2f", precioTotal));
        // textPrecioTotal.setText(String.format("%.2f", precioTotal));
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
