package Vista;

import Controlador.Controlador;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
/**
 * Clase encargada de la visualizacion de la carga de doctores
 * @author RobertoVillalon
 */
public class PanelCargaUsuario extends JPanel{
    Controlador ctrl;
    /**
     * Cuadro de texto que contendra la informacion del nombre de usuario
     */
    public static JTextField fieldUsuario = new JTextField();
    /**
     * Cuadro de texto de tipo Password que contendra la informacion de la contraseña del usuario
     */
    public static JPasswordField fieldContraseña = new JPasswordField();
    JButton ingresar = new JButton();
    JButton registrarse = new JButton();
    /**
     * Metodo constructor encargado de las funciones de la creacion del panel
     */
    public PanelCargaUsuario(){
        setVisible(true);
        ctrl = new Controlador();
        IniciarComponentes();
    }

    private void IniciarComponentes(){
        IniciarPanel();
        IniciarLabelsyFields();
        IniciarBotones();
    }

    void IniciarPanel(){
        setBounds(0,230,795,140);
        setLayout(new GridLayout(0, 2, 0, 0));
        setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK, 5),"Panel de Inicio", 2, 2));
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
    }
    
    void IniciarLabelsyFields(){
        JLabel labelNombre = new JLabel("Nombre");labelNombre.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelContraseña = new JLabel("Contraseña");labelContraseña.setHorizontalAlignment(JLabel.CENTER);
        
        fieldUsuario.setHorizontalAlignment(JTextField.CENTER);
        fieldContraseña.setHorizontalAlignment(JTextField.CENTER);
        
        fieldUsuario.setText("Claudia Villanueva");
        fieldContraseña.setText("123");
        
        add(labelNombre);
        add(labelContraseña);
        add(fieldUsuario);
        add(fieldContraseña);
    }

    void IniciarBotones(){
        ingresar.setText("Ingresar");
        ingresar.setBounds(10, 80, 80, 20);
        ingresar.setActionCommand("ingresoUsuario");
        add(ingresar);
        
        registrarse.setText("Registrarse");
        registrarse.setBounds(10, 110, 80, 20);
        registrarse.setActionCommand("registroUsuario");
        add(registrarse);
        
        ConectaControlador(ctrl);
    }
    
     /**
     * Metodo que se encarga de la conexion con el controlador para los eventos
     * @param c parametro de tipo controlador que transoporta la informacion de la vista al controlador
     */
    
    void ConectaControlador(Controlador c){
        ingresar.addActionListener(c);
        registrarse.addActionListener(c);
    }
}
