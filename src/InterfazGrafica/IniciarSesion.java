package InterfazGrafica;

import Enumeraciones.Puesto;
import Gestores.GestorClientes;
import Gestores.GestorEmpleados;
import Personas.Cliente;
import Personas.Empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class IniciarSesion {
    private JPanel panel1;
    private JButton iniciarSesionButton;
    private JButton nuevoClienteButton;
    private JTextField textUsuario;
    private JPasswordField textContraseña;


    public IniciarSesion (){
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
                        // cliente ingresado con exito
                        // aca iria el menu clientes
                    }
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
                            } else {
                                // menu demas empleados
                            }
                        }
                    }
                }

                /*
                if(cliente == null && empleado == null){
                    // no existe el usuario
                }
                */
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
