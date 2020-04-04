package Vista;

import Controlador.Controlador;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
/**
 * Clase que contendra todas las otras clases visuales del programa
 * @author RobertoVillalon
 */
public final class Vista extends JFrame{
    public static JLabel labelLogo = new JLabel();
    JComboBox doctores = new JComboBox();
    static URL direccion = Vista.class.getResource("res/Logo.png");
    static ImageIcon logo = new ImageIcon(direccion);
    public static JPanel panelPrincipal = new JPanel();
    public static JMenuBar barra = new JMenuBar();
    public static JMenu paciente = new JMenu(),mDoctor = new JMenu(),editar = new JMenu();
    public static JMenu[] barraInvisibles = new JMenu[5];
    public static JMenuItem cargarDoctor = new JMenuItem();
    public static JMenuItem nuevo = new JMenuItem(),cargar = new JMenuItem(),borrar = new JMenuItem();
    Controlador ctrl;
    String doctor;
     
    public Vista(){
       IniciarComponentes();
    }
    
    void IniciarComponentes() {
        setTitle("Plataforma Medica");
        setSize(800,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        ctrl = new Controlador();
        CrearPanelPrincipal();
        CrearBarraMenu();
        AgregarLogo("Medio abajo");
        AgregarPanel();
        ConectaControlador(ctrl);
    }
    
    void CrearPanelPrincipal(){
        panelPrincipal.setLayout(null);
        
        panelPrincipal.setBounds(0,0,800,400);
        
        getContentPane().add(panelPrincipal);
        
    }

    void CrearBarraMenu(){
        
        cargarDoctor.setText("Cargar Doctor");
        cargarDoctor.setActionCommand("McargarDoctor");
        mDoctor.add(cargarDoctor);
        
        nuevo.setText("Nuevo Paciente");
        nuevo.setActionCommand("MnuevoPaciente");
        paciente.add(nuevo);
        
        cargar.setText("Cargar Paciente");
        cargar.setActionCommand("McargarPaciente");
        paciente.add(cargar);
        
        borrar.setText("Borrar Paciente");
        borrar.setActionCommand("MborrarPaciente");
        paciente.add(borrar);
        
        paciente.setText("Paciente");
        paciente.setVisible(true);
        
        editar.setText("Editar");
        
        barra.add(paciente);
        barra.setVisible(true);
        barra.setBounds(0, 0, 800, 20);
    }

    public static void AgregarLogo(String rango){   
        
        if(rango.toLowerCase().equals("medio abajo")){
            labelLogo.setIcon(new ImageIcon(logo.getImage().getScaledInstance(800,230,Image.SCALE_SMOOTH)));
            labelLogo.setSize(800,230);
            labelLogo.setVisible(true);
            panelPrincipal.add(labelLogo);
        }
        else if(rango.toLowerCase().equals("medio izquierda")){
            labelLogo.setIcon(new ImageIcon(logo.getImage().getScaledInstance(400,380,Image.SCALE_SMOOTH)));
            labelLogo.setSize(400,380);
            labelLogo.setVisible(true);
            panelPrincipal.add(labelLogo);
        }
        else if(rango.toLowerCase().equals("completo")){
            labelLogo.setIcon(new ImageIcon(logo.getImage().getScaledInstance(800,380,Image.SCALE_SMOOTH)));
            labelLogo.setSize(800,380);
            labelLogo.setVisible(true);
            panelPrincipal.add(labelLogo);
        }

    }
    
    void AgregarPanel(){
        PanelCargaUsuario pcp = new PanelCargaUsuario();
        
        panelPrincipal.add(pcp);
    }
    
    public void ConectaControlador(Controlador c){
        cargarDoctor.addActionListener(c);
        nuevo.addActionListener(c);
        cargar.addActionListener(c);
        borrar.addActionListener(c);
        addWindowListener(c);
    }

 
}