package InterfazGrafica;

import Enumeraciones.Puesto;
import Gestores.GestorClientes;
import Gestores.GestorEmpleados;
import Personas.Cliente;
import Personas.Empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesion {
    private JPanel panel1;
    private JButton iniciarSesionButton;
    private JButton nuevoClienteButton;
    private JTextField textUsuario;
    private JPasswordField textContraseña;
    private JButton cerrarButton;


    public IniciarSesion (){
        //Cerrar el programa
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón Salir presionado");
                System.exit(0);

                // Cerrar la aplicación
            }
        });

        nuevoClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarCliente registrarCliente = new RegistrarCliente();
                registrarCliente.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(nuevoClienteButton);
                frame.dispose();
            }
        });

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // guarda datos ingresados en variables
                String usuario_ingresado = textUsuario.getText();
                String contraseña_ingresada = new String(textContraseña.getPassword());

                // crea los gestores y carga los datos desde sus archivos
                GestorClientes gestor_clientes = new GestorClientes();
                GestorEmpleados gestor_empleados = new GestorEmpleados();
                gestor_empleados.cargarDesdeArchivo("empleados.json");
                gestor_clientes.cargarDesdeArchivo("clientes.json");

                // busca cliente con nombre de usuario igual al ingresado
                Cliente cliente = gestor_clientes.buscarPorUsuario(usuario_ingresado);

                // si encuentra al cliente
                if (cliente != null) {
                    // comprueba si las credenciales ingresadas son correctas
                    if (cliente.comprobarCredenciales(usuario_ingresado, contraseña_ingresada)) {
                        MenuUsuario menuUsuario = new MenuUsuario(cliente);
                        menuUsuario.setVisible(true);
                        JFrame frame = (JFrame) SwingUtilities.getRoot(iniciarSesionButton);
                        frame.dispose();

                        System.out.println("ENCONTRO CLIENTE");
                    }
                }else {
                    System.out.println("NO ENCONTRO CLIENTE");
                }


                // si no encontro cliente buscar empleado
                if (cliente == null) {
                    // busca empleado con nombre de usuario igual al ingresado
                    Empleado empleado = gestor_empleados.buscarPorUsuario(usuario_ingresado);


                    // si encuentra al empleado
                    if (empleado != null) {
                        // comprueba si las credenciales ingresadas son correctas
                        if (empleado.comprobarCredenciales(usuario_ingresado, contraseña_ingresada)) {
                            // empleado ingresado con exito
                            if (empleado.getPuesto() == Puesto.ADMINISTRADOR) {
                                // menu administrador
                                MenuGerente menuGerente = new MenuGerente();
                                menuGerente.setVisible(true);
                                JFrame frame = (JFrame) SwingUtilities.getRoot(iniciarSesionButton);
                                frame.dispose();
                                System.out.println("ENCONTRO ADMINISTRADOR");
                            } else {
                                // menu demas empleados
                                MenuEmpleado menuEmpleado = new MenuEmpleado(empleado);
                                menuEmpleado.setVisible(true);
                                JFrame frame = (JFrame) SwingUtilities.getRoot(iniciarSesionButton);
                                frame.dispose();
                                System.out.println("ENCONTRO EMPLEADO");
                            }
                        }
                    }
                }
            }
        });

    }


    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Iniciar Sesion");
        frame.setContentPane(panel1); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }


    public static void main(String[] args) {
        IniciarSesion iniciarSesion = new IniciarSesion();
        iniciarSesion.setVisible(true);
    }



}
