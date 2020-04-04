package Vista;

import Controlador.Controlador;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Clase encargada de la visualizacion del registro de los nuevos pacientes
 * @author RobertoVillalon
 */
public class NuevoPaciente extends JFrame{
    String nombre,rut,fechanacimiento,sistema,doctor;
    int edad;
    Controlador ctrl;
    JPanel panelregistro = new JPanel();
    /**
     * Boton encargado del registro de un nuevo paciente
     */
    public JButton registrar = new JButton();
    /**
     * Boton encargado de la cancelacion del registro de un nuevo paciente
     */
    public JButton cancelar = new JButton();
    /**
     * Cuadro de texto que contedra el nombre del nuevo paciente
     */
    public static JTextField fieldNombre = new JTextField();
    /**
     * Cuadro de texto que contedra la edad del nuevo paciente
     */
    public static JTextField fieldEdad = new JTextField();
    /**
     * Cuadro de texto que contedra el rut del nuevo paciente
     */
    public static JTextField fieldRut = new JTextField();
    /**
     * Cuadro de texto que contedra la fecha de nacimiento del nuevo paciente
     */
    public static JTextField fieldFecha = new JTextField();
    /**
     * Cuadro de texto que contedra el sistema previsional del nuevo paciente
     */
    public static JTextField fieldSistema = new JTextField();
    /**
     * Metodo construtor de la clase, encargado de las funciones para la creacion del frame
     * @param doctor parametro que contiene el nombre del doctor
     */
    public NuevoPaciente(String doctor) {
        this.doctor = doctor;
        ctrl = new Controlador();
        setTitle("Registro");
        setSize(400,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        IniciarComponentes();
    }
    
    private void IniciarComponentes(){
        CrearPanel();
        CreaLabelsyTextFields();
        CrearBotones();
    }
    
    void CrearPanel(){
        panelregistro.setBounds(0,0,400,300);
        panelregistro.setLayout(new GridLayout(0, 1, 10, 1));
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        panelregistro.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        getContentPane().add(panelregistro);
    }

    void CreaLabelsyTextFields(){
        JLabel labelNombre = new JLabel();
        JLabel labelEdad = new JLabel();
        JLabel labelRut = new JLabel();
        JLabel labelFecha = new JLabel();
        JLabel labelSistema = new JLabel();
        JLabel labelDoctor = new JLabel();
        Font fuente = new Font("Franklin Gothic Medium",3,13);
        
        labelNombre.setText("Nombre");
        labelNombre.setFont(fuente);
        labelNombre.setHorizontalAlignment(JLabel.CENTER);
        labelNombre.setBounds(20,20,60,20);
        panelregistro.add(labelNombre);
        
        fieldNombre.setBounds(20,40,200,20);
        panelregistro.add(fieldNombre);
        
        labelEdad.setText("Edad");
        labelEdad.setFont(fuente);
        labelEdad.setHorizontalAlignment(JLabel.CENTER);
        labelEdad.setBounds(20,70,60,20);
        panelregistro.add(labelEdad);
        
        fieldEdad.setBounds(20,90,200,20);
        panelregistro.add(fieldEdad);
        
        labelRut.setText("Rut");
        labelRut.setFont(fuente);
        labelRut.setHorizontalAlignment(JLabel.CENTER);
        labelRut.setBounds(20,120,60,20);
        panelregistro.add(labelRut);
        
        fieldRut.setBounds(20,140,200,20);
        panelregistro.add(fieldRut);
        
        labelFecha.setText("Fecha de Nacimiento");
        labelFecha.setFont(fuente);
        labelFecha.setHorizontalAlignment(JLabel.CENTER);
        labelFecha.setBounds(20,170,200,20);
        panelregistro.add(labelFecha);
        
        fieldFecha.setBounds(20,190,200,20);
        panelregistro.add(fieldFecha);
        
        labelSistema.setText("Sistema");
        labelSistema.setFont(fuente);
        labelSistema.setHorizontalAlignment(JLabel.CENTER);
        labelSistema.setBounds(20,220,100,20);
        panelregistro.add(labelSistema);
        
        fieldSistema.setBounds(20,240,200,20);
        panelregistro.add(fieldSistema);

        labelDoctor.setText("Doctor: "+doctor);
        labelDoctor.setFont(fuente);
        labelDoctor.setBounds(20,270,200,20);
        panelregistro.add(labelDoctor);
    }
    
    void CrearBotones(){
        String opc = null;
        
        registrar.setText("Registrar");
        registrar.setBounds(20, 300, 120, 20);
        registrar.setActionCommand("registrarPaciente");
        panelregistro.add(registrar);
        
        cancelar.setText("Cancelar");
        cancelar.setBounds(160, 300, 120, 20);
        cancelar.setActionCommand("cancelarRegistropaciente");
        panelregistro.add(cancelar);
        ConectaControlador(ctrl);
    }
    
    /**
     * Metodo que se encarga de la conexion con el controlador para los eventos
     * @param c parametro de tipo controlador que transoporta la informacion de la vista al controlador
     */
    
    void ConectaControlador(Controlador c){
        
        registrar.addActionListener(c);
        
        ActionListener aCancelar = (ActionEvent ae) -> {
            dispose();
        };cancelar.addActionListener(aCancelar);
    }
}
