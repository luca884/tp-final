package InterfazGrafica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class Empleados {
    private JTable TablaEmpleados;
    private JButton menuButton;
    private JButton editarButton;
    private JButton borrarButton;
    private JButton registrarButton;
    private JPanel PanelEmpleados;

    public Empleados() {
    //Menu
        menuButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MenuGerente menuGerente = new MenuGerente();
                menuGerente.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(menuButton);
                frame.dispose();

            }
        });

    //Registrar
        registrarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarEmpleado registrarEmpleado = new RegistrarEmpleado();
                registrarEmpleado.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(registrarButton);
                frame.dispose();
            }
        });



    }
    public void setVisible (boolean visible){
        JFrame frame = new JFrame("Empleados");
        frame.setContentPane(PanelEmpleados); //Asigna el contenido a la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra la ventana, pero no para el programa
        frame.pack(); //Ajusta el tamaño del JFrame para que encaje con el contenido
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Muestra la ventana en pantalla completa
        frame.setLocationRelativeTo(null); //Coloca el JFrame en el centro de la pantalla
        frame.setVisible(visible); //Muestra la ventana si "visible" es true
}



}
