package Vista;

import Controlador.Controlador;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Clase encargada de la visualizacion de la carga de pacientes desde la base de datos al programa
 * @author RobertoVillalon
 */
public class CargarPaciente extends JFrame{
    Controlador ctrl;
    /**
     * contiene el nombre del doctor que inicio sesion en la plataforma
     */
    public static String doctor;
    JPanel panelcarga = new JPanel();
    /**
     * Combobox el cual contendra el nombre de los pacientes del doctor
     */
    public static JComboBox caja = new JComboBox();
    JLabel labelPacientes = new JLabel();
    JButton seleccionar = new JButton();
    JButton volver = new JButton();
    int edad;
    
    /**
     * Constructor de la clase cargar paciente. En el se dan las ordenes principales para la correta ejecucion de la clase
     * @param doctor contiene el nombre del doctor cuyos pacientes se quieren cargar
     */
    public CargarPaciente(String doctor) {
        CargarPaciente.doctor = doctor;
        setTitle("Cargar Paciente");
        setSize(300,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        ctrl = new Controlador();
        IniciarComponentes();
    }

    private void IniciarComponentes(){
        IniciarPanel();
        IniciarLabel();
        IniciarComboBox();
        IniciarBotones();
    }

    void IniciarPanel(){
        panelcarga.setBounds(0,0,400,300);
        panelcarga.setLayout(new GridLayout(0, 1, 10, 1));
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        panelcarga.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        getContentPane().add(panelcarga);
    }
    
    void IniciarLabel(){
        
        labelPacientes.setText("Nombre");
        labelPacientes.setHorizontalAlignment(JLabel.CENTER);
        labelPacientes.setFont(new Font("Comic Sans MS",3,13));
        labelPacientes.setBounds(10,20,80,20);
        panelcarga.add(labelPacientes);
        
    }
    
    void IniciarComboBox(){
       int i;
       
       caja.setBounds(80, 20, 200, 20);
       ((JLabel)caja.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
       caja.setVisible(true);
       panelcarga.add(caja);
       
       if(caja.getItemCount() > 0){
           caja.removeAllItems();
       }
       
       for(i = 0 ; i < Controlador.auxiliar.size(); i++){
            caja.addItem(Controlador.auxiliar.get(i));
       }
       
       if(Controlador.auxiliar.isEmpty()){
           JOptionPane.showMessageDialog(null, "No hay pacientes registrados");
           dispose();
       }
       
       Controlador.auxiliar.clear();
       
    }

    void IniciarBotones(){
        seleccionar.setText("Cargar");
        seleccionar.setBounds(10, 80, 80, 20);
        seleccionar.setActionCommand("cargarPaciente");
        panelcarga.add(seleccionar);
        
        volver.setText("Volver");
        volver.setBounds(10, 110, 80, 20);
        volver.setActionCommand("cancelarCargarpaciente");
        panelcarga.add(volver);
        
        ActionListener aVolver = (ActionEvent ae) -> {
            dispose();
        };volver.addActionListener(aVolver);seleccionar.addActionListener(aVolver);
        
        ConectaControlador(ctrl);
    }
    
     /**
     * Metodo que se encarga de la conexion con el controlador para los eventos
     * @param c parametro de tipo controlador que transoporta la informacion de la vista al controlador
     */
    
    void ConectaControlador(Controlador c){
        seleccionar.addActionListener(c);
    }
}
