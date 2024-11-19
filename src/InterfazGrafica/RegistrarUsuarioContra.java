package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarUsuarioContra {
    private JTextField textUsuario;
    private JTextField textContraseña;
    private JButton finalizarButton;
    private JButton atrasButton;
    private JPanel panel;

    public RegistrarUsuarioContra (){
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });

        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Guardar el usuario y contraseña


                IniciarSesion iniciarSesion = new IniciarSesion();
                iniciarSesion.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(finalizarButton);
            }
        });




    }


    public void setVisible(boolean visible){
        JFrame frame = new JFrame("Finalizar Registro");
        frame.setContentPane(panel); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.requestFocusInWindow(); //Hace foco a la ventana
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
    }

}
