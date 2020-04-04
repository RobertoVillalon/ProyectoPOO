package Vista;

import Controlador.Controlador;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders;
/**
 * Clase encargada de la visualizacion de la carga de doctores
 * @author RobertoVillalon
 */
public class PanelRegistroUsuario extends JPanel{
    Controlador ctrl;
    /**
     * Cuadro de texto en el cual se ingresara el nombre del nuevo usuario
     */
    public static JTextField fieldUsuario = new JTextField();
    /**
     * Cuadro de texto en el cual se ingresara el email del nuevo usuario
     */
    public static JTextField fieldEmail = new JTextField();
    /**
     * Cuadro de texto encargado de la especialidad del nuevo usuario (Solo disponible para usuarios del tipo doctor)
     */
    public static JTextField fieldEspecialidad = new JTextField();
    /**
     * Boton de seleccion perteneciente al tipo de usuario Doctor
     */
    public static JRadioButton radioDoctor = new JRadioButton("Doctor");
    /**
     * Boton de seleccion perteneciente al tipo de usuario Secretaria(o)
     */
    public static JRadioButton radioSecretaria = new JRadioButton("Secretaria");
    /**
     * Grupo el cual abarca los dos botones anteriores. sirve para eliminar la seleccion multiple de tipo de usuario
     */
    public static ButtonGroup grupo = new ButtonGroup();
    /**
     * Cuadro de recto de tipo Password en el cual se ingresara la contraseña del nuevo usuario
     */
    public static JPasswordField fieldContraseña = new JPasswordField();
    JButton volver = new JButton();
    JButton registrarse = new JButton();
    /**
     * Metodo constructor que se encarga de la creacion del panel de registro
     */
    public PanelRegistroUsuario(){
        setSize(400,350);
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
        setBounds(400,0,397,370);
        setLayout(new GridLayout(0, 1, 0, 0));
        //setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK, 5),"Panel de Registro", 2, 2));
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
    }
    
    void IniciarLabelsyFields(){
        JLabel labelNombre = new JLabel("Nombre: ");labelNombre.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelContraseña = new JLabel("Contraseña: ");labelContraseña.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelEmail = new JLabel("Email: ");labelEmail.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelEspecialidad = new JLabel("Especialidad: ");labelEspecialidad.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelTipo = new JLabel("Tipo: ");labelTipo.setHorizontalAlignment(JLabel.CENTER);
        JPanel panelradioBoton = new JPanel();
        
        fieldUsuario.setHorizontalAlignment(JTextField.CENTER);
        fieldContraseña.setHorizontalAlignment(JTextField.CENTER);
        fieldEmail.setHorizontalAlignment(JTextField.CENTER);
        fieldEspecialidad.setHorizontalAlignment(JTextField.CENTER);
        
        add(labelNombre);
        add(fieldUsuario);
        add(labelContraseña);
        add(fieldContraseña);
        add(labelEmail);
        add(fieldEmail);
        add(labelTipo);
        grupo.add(radioDoctor);
        grupo.add(radioSecretaria);
        radioDoctor.setActionCommand("radioDoctor");
        radioSecretaria.setActionCommand("radioSecretaria");
        radioDoctor.setSelected(true);
        radioDoctor.setBackground(Color.WHITE);
        radioSecretaria.setBackground(Color.WHITE);
        radioDoctor.setBorder(new MetalBorders.TextFieldBorder());
        radioSecretaria.setBorder(new MetalBorders.TextFieldBorder());
        panelradioBoton.add(radioDoctor);
        panelradioBoton.add(radioSecretaria);
        panelradioBoton.setBackground(Color.WHITE);
        panelradioBoton.setBorder(new MetalBorders.TextFieldBorder());
        add(panelradioBoton);
        add(labelEspecialidad);
        add(fieldEspecialidad);
    }

    void IniciarBotones(){  
        registrarse.setText("Registrarse");
        registrarse.setActionCommand("registrarUsuario");
        add(registrarse);
        
        volver.setText("Volver");
        volver.setActionCommand("volverRegistro");
        add(volver);
        
        ConectaControlador(ctrl);
    }
    
     /**
     * Metodo que se encarga de la conexion con el controlador para los eventos
     * @param c parametro de tipo controlador que transoporta la informacion de la vista al controlador
     */
    
    void ConectaControlador(Controlador c){
        registrarse.addActionListener(c);
        volver.addActionListener(c);
        radioDoctor.addActionListener(c);
        radioSecretaria.addActionListener(c);
    }
}
