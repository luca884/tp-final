package InterfazGrafica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegistrarEmpleado {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton confirmarButton;
    private JButton atrasButton;


    public RegistrarEmpleado() {
    //Menu boton
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleados empleados = new Empleados();
                empleados.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(atrasButton);
                frame.dispose();
            }
        });

    }



        public void setVisible(boolean visible){
            JFrame frame = new JFrame("Registrar Empleado");
            frame.setContentPane(panel1); //Asigna el contenido a la ventana
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
            frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
            frame.requestFocusInWindow(); //Hace foco a la ventana
            frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
            frame.setVisible(visible); //Muestra la ventana si "visible" es true
        }
    }



