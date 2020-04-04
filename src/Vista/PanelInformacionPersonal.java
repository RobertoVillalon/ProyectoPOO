package Vista;

import Controlador.Controlador;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * Clase encargada de la visualizacion de la informacion personal del pacientes
 * @author RobertoVillalon
 */
public class PanelInformacionPersonal extends JPanel{
    Controlador ctrl;
    /**
     * Contiene el nombre del paciente
     */
    public static String nombre = null;
    /**
     * Contiene el rut del paciente
     */
    public static String rut;
    /**
     * Contiene el sistema previsional del paciente
     */
    public static String sistema;
    /**
     * Contiene el nombre del doctor del paciente
     */
    public static String doctor;
    /**
     * Contiene la fecha de nacimiento del paciente
     */
    public static String fecha;
    /**
     * Contiene la edad del paciente
     */
    public static int edad;
    /**
     * MenuItem enargado de la edicion de los datos personales del paciente en la plataforma
     */
    public JMenuItem editarIpersonal = new JMenuItem("Informacion Personal");
    /**
     * Cuadro de texto encargado del nombre ante una edicion
     */
    public static JTextField fieldNombre = new JTextField();
    /**
     * Cuadro de texto encargado del rut ante una edicion
     */
    public static JTextField fieldRut = new JTextField();
    /**
     * Cuadro de texto encargado de recibir la edad ante una edicion
     */
    public static JTextField fieldEdad = new JTextField();
    /**
     * Cuadro de texto encargado de recibir la fecha de nacimiento ante una edicion
     */
    public static JTextField fieldFecha = new JTextField();
    /**
     * Cuadro de texto encargado del sistema previsional del pacinte ante una edicion
     */
    public static JTextField fieldSistema = new JTextField();
    JButton listo = new JButton("Listo"),cancelar = new JButton("Cancelar");
    /**
     * Metodo constructor encargado de la cracion del panel
     * @param nombre contiene el nombre del paciente
     * @param rut contiene el rut del paciente
     * @param fecha contiene la fecha de nacimiento del paciente
     * @param sistema contiene el sistema previsional del paciente
     * @param doctor contiene el nombre del doctor del paciente
     * @param edad contiene la edad del paciente
     */
    public PanelInformacionPersonal(String nombre, String rut,String fecha, String sistema, String doctor, int edad){
        PanelInformacionPersonal.nombre = nombre;
        PanelInformacionPersonal.rut = rut;
        PanelInformacionPersonal.fecha = fecha;
        PanelInformacionPersonal.sistema = sistema;
        PanelInformacionPersonal.doctor = doctor;
        PanelInformacionPersonal.edad = edad;
        
        IniciarComponentes();
    }

    public PanelInformacionPersonal(String nombre, String rut,String fecha, String sistema, String doctor, int edad,int id){
        PanelInformacionPersonal.nombre = nombre;
        PanelInformacionPersonal.rut = rut;
        PanelInformacionPersonal.fecha = fecha;
        PanelInformacionPersonal.sistema = sistema;
        PanelInformacionPersonal.doctor = doctor;
        PanelInformacionPersonal.edad = edad;
        
        ModificarComponentes();
    }
    
    private void IniciarComponentes(){
        IniciarPanel();
        AgregarLabels();
        IniciarMenuItem();
        ctrl = new Controlador();
        ConectaControlador(ctrl);
    }
    
    private void ModificarComponentes(){
        IniciarPanel();
        ModificarInformacionPersonal();
        IniciarBotones();
        ctrl = new Controlador();
        ConectaControlador(ctrl);
        
    }
    
    void IniciarPanel(){
        setLayout(new GridLayout(0, 1, 20, 1));
        setBorder(new TitledBorder(new EtchedBorder(), "Informacion Personal",1,2,new Font("Segoe UI Historic",15,15)));
        setBounds(0,20,400,350);
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        setVisible(true);
    }

    void AgregarLabels(){
        JLabel labelNombre = new JLabel();
        JLabel labelRut = new JLabel();
        JLabel labelFecha = new JLabel();
        JLabel labelSistema = new JLabel();
        JLabel labelEdad = new JLabel();
        
        labelNombre.setText(nombre);labelNombre.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK),"Nombre",2,2));
        labelNombre.setHorizontalAlignment(JLabel.CENTER);
        labelRut.setText(rut);labelRut.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK),"Rut",2,2));
        labelRut.setHorizontalAlignment(JLabel.CENTER);
        labelEdad.setText(""+edad);labelEdad.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK),"Edad",2,2));
        labelEdad.setHorizontalAlignment(JLabel.CENTER);
        labelFecha.setText(fecha);labelFecha.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK),"Fecha De Nacimiento",2,2));
        labelFecha.setHorizontalAlignment(JLabel.CENTER);
        labelSistema.setText(sistema);labelSistema.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK),"Sistema",2,2));
        labelSistema.setHorizontalAlignment(JLabel.CENTER);
        
        add(labelNombre);
        add(labelRut);
        add(labelEdad);
        add(labelFecha);
        add(labelSistema);
    }
    
    void IniciarMenuItem() {
        editarIpersonal.setActionCommand("editarIpersonal");
    }
    
    void ModificarInformacionPersonal(){
        JLabel labelNombre = new JLabel("Nombre: ");
        fieldNombre.setText(nombre);
        JLabel labelRut = new JLabel("Rut: ");
        fieldRut.setText(rut);

        JLabel labelFecha = new JLabel("Fecha de Nacimiento: ");
        fieldFecha.setText(fecha);
        JLabel labelSistema = new JLabel("Sistema Medico: ");
        fieldSistema.setText(sistema);
        JLabel labelEdad = new JLabel("Edad: ");
        fieldEdad.setText(""+edad);

        
        add(labelNombre);
        add(fieldNombre);
        add(labelRut);
        add(fieldRut);
        add(labelEdad);
        add(fieldEdad);
        add(labelFecha);
        add(fieldFecha);
        add(labelSistema);
        add(fieldSistema);
    }
    
    void IniciarBotones(){

        listo.setActionCommand("listoPIP");
        listo.setBounds(20, 260, 80, 20);
        add(listo);
        
        cancelar.setActionCommand("cancelarPIP");
        cancelar.setBounds(120, 260, 100, 20);
        add(cancelar);
    }
    
     /**
     * Metodo que se encarga de la conexion con el controlador para los eventos
     * @param c parametro de tipo controlador que transoporta la informacion de la vista al controlador
     */
    
    void ConectaControlador(Controlador c){
        listo.addActionListener(c);
        cancelar.addActionListener(c);
        editarIpersonal.addActionListener(c);
    }

    
}
