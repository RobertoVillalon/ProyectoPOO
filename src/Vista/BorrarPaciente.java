package Vista;

import Controlador.Controlador;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Clase encargada de los componentes visuales para borrar un paciente
 * @author RobertoVillalon 
 */
public class BorrarPaciente extends JFrame{
    String doctor;
    Controlador ctrl;
    JPanel panelborrar = new JPanel();
    JLabel labelPacientes = new JLabel();
    JButton seleccionar = new JButton(),volver = new JButton();
    /** 
     * Combobox el cual contendra el nombre de los pacientes del doctor
     */
    public static JComboBox caja = new JComboBox();
    
    public BorrarPaciente(String doctor) {
        this.doctor = doctor;
        setTitle("Borrar Paciente");
        setSize(300,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        ctrl = new Controlador();
        IniciarComponentes();
    }
    
    /**
     * Metodo que inicia todos los componentes de la clase
     */
    
    private void IniciarComponentes(){
        IniciarPanel();
        IniciarLabel();
        IniciarComboBox();
        IniciarBotones();
    }
    
    /**
     * Metodo que inicia el panel del frame
     */
    
    void IniciarPanel(){
        panelborrar.setBounds(0,0,400,300);
        panelborrar.setLayout(new GridLayout(0, 1, 10, 1));
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        panelborrar.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        getContentPane().add(panelborrar);
    }
    
    /**
     * Metodo que inicia los labels del frame
     */
    
    void IniciarLabel(){
        
        labelPacientes.setText("Nombre");
        labelPacientes.setFont(new Font("Comic Sans MS",3,13));
        labelPacientes.setHorizontalAlignment(JLabel.CENTER);
        labelPacientes.setBounds(10,20,80,20);
        panelborrar.add(labelPacientes);
        
    }
    
    /**
     * Metodo que iniciar el cuadro de seleccion de pacientes del frame
     */
    
    void IniciarComboBox(){
       int i;
       
       caja.setBounds(80, 20, 200, 20);
       caja.setVisible(true);
       ((JLabel)caja.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
       panelborrar.add(caja);
       
       if(caja.getItemCount() > 0){
           caja.removeAllItems();
       }
       
       for(i = 0 ; i < Controlador.auxiliar.size(); i++){
            caja.addItem(Controlador.auxiliar.get(i));
       }
       
       if(Controlador.auxiliar.isEmpty()){
           JOptionPane.showMessageDialog(null, "No hay Controlador.auxiliar para borrar");
           dispose();
       }
       
       Controlador.auxiliar.clear();
       
    }

    /**
     * Metodo que inicia los botones del frame
     */
    
    void IniciarBotones(){
        seleccionar.setText("Borrar");
        seleccionar.setBounds(10, 80, 80, 20);
        seleccionar.setActionCommand("borrarPaciente");
        panelborrar.add(seleccionar);
        
        volver.setText("Volver");
        volver.setBounds(10, 110, 80, 20);
        panelborrar.add(volver);
        
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

    
    
    

