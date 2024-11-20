package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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






    }


    public void setVisible(boolean visible){
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
