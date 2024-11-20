package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGerente {
    private JButton mantenimientoButton;
    private JButton carpasButton;
    private JButton empleadosButton;
    private JButton spaButton;
    private JButton estacionamientoButton;
    private JButton utileriaButton;
    private JButton clientesButton;
    private JButton guarderiaButton;
    private JButton salirButton;
    private JPanel panelJFrame;

    public MenuGerente(){
    //Registro
        empleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la nueva ventana
                Empleados empleados = new Empleados();
                empleados.setVisible(true);

                // Obtener el JFrame que contiene al botón
                JFrame frame = (JFrame) SwingUtilities.getRoot(empleadosButton);

                // Cerrar la ventana actual
                frame.dispose();
                System.out.println("Botón Empleado presionado");
            }
        });

        clientesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes clientes = new Clientes();
                clientes.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(clientesButton);
                frame.dispose();
                System.out.println("Botón Cliente presionado");
            }
        });

    //Varios
        mantenimientoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IGMantenimiento IGMantenimiento = new IGMantenimiento();
                IGMantenimiento.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(mantenimientoButton);
                frame.dispose();
                System.out.println("Botón Mantenimiento presionado");
            }
        });

        utileriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utileria utileria = new Utileria();
                utileria.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(utileriaButton);
                frame.dispose();
                System.out.println("Botón Utilería presionado");
            }
        });

    //Alquileres
        carpasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Carpas carpas = new Carpas();
                carpas.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(carpasButton);
                frame.dispose();
                System.out.println("Botón Carpas presionado");
            }
        });

        estacionamientoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estacionamiento estacionamiento = new Estacionamiento();
                estacionamiento.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(estacionamientoButton);
                frame.dispose();
                System.out.println("Botón Estacionamiento presionado");
            }
        });

    //Servicios
        spaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spa spa = new Spa();
                spa.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(spaButton);
                frame.dispose();
                System.out.println("Botón Spa presionado");
            }
        });

        guarderiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Guarderia guarderia = new Guarderia();
                guarderia.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(mantenimientoButton);
                frame.dispose();
                System.out.println("Botón Guardería presionado");
            }
        });


    //Cerrar el programa
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Botón Salir presionado");
                System.exit(0); // Cerrar la aplicación
            }
        });



    }

    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Menu Gerente");
        frame.setContentPane(panelJFrame); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }


    public static void main(String[] args) {
        MenuGerente menuGerente = new MenuGerente();
        JFrame frame = new JFrame("Menu Gerente");
        frame.setContentPane(menuGerente.panelJFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);


    }








}
