package Vista;

import Controlador.Controlador;
import com.toedter.calendar.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class PanelSecretaria extends JPanel{
    Controlador ctrl;
    public static JPanel panelHoras = new JPanel(),panelCertificados = new JPanel(),panelSur = new JPanel(),panelNorte = new JPanel(),panelcentroCertificados = new JPanel();
    JTabbedPane menuPaneles = new JTabbedPane();
    public static JDateChooser seleccionHora = new JDateChooser();
    public static JComboBox seleccionDoctor;
    static JScrollPane scroll = new JScrollPane();
    public static JTable tablaDia = null;
    public static JButton cargar = new JButton("Cargar"),guardar = new JButton("Guardar"),imprimir = new JButton("Imprimir Certificado");
    public static JList listaDoctores = new JList(),listaPacientes = new JList();
    public static JLabel labelseleccionaDia = new JLabel("Selecciona Fecha: "),labelseleccionaDoctor = new JLabel("Selecciona Doctor"),labelSeleccion = new JLabel("Selecciona un doctor");
    public static ArrayList<String> auxiliarHoras = new ArrayList<>(),auxiliarNombre = new ArrayList<>(),auxiliarMotivo = new ArrayList<>(),auxiliarDoctor = new ArrayList<>(),auxiliarDireccion = new ArrayList<>();
    public static ArrayList<Integer> auxiliarPago = new ArrayList<>(),auxiliarTelefono = new ArrayList<>();
    
    
    public PanelSecretaria() {
        IniciarComponentes();
    }

    private void IniciarComponentes() {
        IniciarPanel();
        AgregarJTabbedPane();
        IniciarPanelHoras();
        IniciarPanelCertificados();
        ctrl = new Controlador();
        ConectaControlador(ctrl);    
    }

    private void IniciarPanel() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new EtchedBorder(), "Panel Secretaria",1,2,new Font("Segoe UI Historic",15,15)));
        setSize(795,380);
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        setVisible(true);
    }

    private void AgregarJTabbedPane(){
        float hsb[] = new float[3];
        Color.RGBtoHSB(26, 188, 156,hsb);
        panelHoras.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        panelCertificados.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        panelHoras.setLayout(new BorderLayout());
        panelCertificados.setLayout(new BorderLayout());
        menuPaneles.setSize(menuPaneles.getPreferredSize());
        menuPaneles.add("Horas Pacientes", panelHoras);
        menuPaneles.add("Certificados Medicos", panelCertificados);
        menuPaneles.setBackground(Color.WHITE);
        add(menuPaneles,BorderLayout.CENTER);
    }

    private void IniciarPanelHoras(){

        panelNorte.add(labelseleccionaDia);
        panelNorte.add(seleccionHora);
        panelNorte.add(labelseleccionaDoctor);
        seleccionDoctor.setBackground(Color.WHITE);
        panelNorte.add(seleccionDoctor);
        panelHoras.add(panelNorte,BorderLayout.BEFORE_FIRST_LINE);
        cargar.setActionCommand("cargaTabla");
        guardar.setActionCommand("guardaTabla");
        panelSur.add(cargar);
        panelSur.add(guardar);
        panelNorte.setBackground(this.getBackground());
        panelSur.setBackground(this.getBackground());
        panelHoras.add(panelSur,BorderLayout.SOUTH);
    }
    
    public static void IniciarJTable(){
        CrearTabla();
        tablaDia.setGridColor(panelHoras.getBackground());
        tablaDia.setBackground(Color.LIGHT_GRAY);
        scroll.setViewportView(tablaDia);
        panelHoras.add(scroll,BorderLayout.CENTER);
    }

    private void IniciarPanelCertificados(){
        imprimir.setEnabled(false);
        imprimir.setActionCommand("imprimirPIP");
        imprimir.setBounds(20, 260, 150, 20);
        panelcentroCertificados.setLayout(new GridLayout(0, 1, 0, 0));
        panelcentroCertificados.setBackground(panelCertificados.getBackground());
        labelSeleccion.setHorizontalAlignment(JLabel.CENTER);
        panelCertificados.add(labelSeleccion,BorderLayout.NORTH);
        panelCertificados.add(panelcentroCertificados,BorderLayout.CENTER);
        panelCertificados.add(imprimir,BorderLayout.SOUTH);
        
    }

    private static void CrearTabla(){
        int cont = 0,posArrays = 0;
        String encabezados[] = {"Hora","Nombre","Motivo","Pago","Doctor","Telefono","Direccion"};
        String horas[] = {"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00"};
        String arreglo[][] = new String[horas.length][horas.length];
        
        for(int i = 0; i < horas.length; i++){
            for(int j = 0; j < encabezados.length ; j++){
                switch(j){
                    case 0:
                        arreglo[i][j] = horas[cont];
                        cont++;
                    break;
                    case 1:
                        for(posArrays = 0; posArrays < auxiliarHoras.size(); posArrays++){
                            if(horas[cont - 1].equals(auxiliarHoras.get(posArrays))){
                                arreglo[i][j] = auxiliarNombre.get(posArrays);
                            }
                        }
                    break;
                    case 2:
                        for(posArrays = 0; posArrays < auxiliarHoras.size(); posArrays++){
                            if(horas[cont - 1].equals(auxiliarHoras.get(posArrays))){
                                arreglo[i][j] = auxiliarMotivo.get(posArrays);
                            }
                        }
                    break;
                    case 3: 
                        for(posArrays = 0; posArrays < auxiliarHoras.size(); posArrays++){
                            if(horas[cont - 1].equals(auxiliarHoras.get(posArrays))){
                                arreglo[i][j] = String.valueOf(auxiliarPago.get(posArrays));
                            }
                        }
                    break;
                    case 4:
                        for(posArrays = 0; posArrays < auxiliarHoras.size(); posArrays++){
                            if(horas[cont - 1].equals(auxiliarHoras.get(posArrays))){
                                arreglo[i][j] = auxiliarDoctor.get(posArrays);
                            }
                        }
                    break;
                    case 5:
                        for(posArrays = 0; posArrays < auxiliarHoras.size(); posArrays++){
                            if(horas[cont - 1].equals(auxiliarHoras.get(posArrays))){
                                arreglo[i][j] = String.valueOf(auxiliarTelefono.get(posArrays));
                            }
                        }
                    break;
                    case 6: 
                        for(posArrays = 0; posArrays < auxiliarHoras.size(); posArrays++){
                            if(horas[cont - 1].equals(auxiliarHoras.get(posArrays))){
                                arreglo[i][j] = auxiliarDireccion.get(posArrays);
                            }
                        }
                    break;

                }
            }
        }
        
        
        PanelSecretaria.tablaDia = new JTable(arreglo, encabezados);
    }
    
    public static void GuardarTabla(){
        auxiliarDoctor.clear();auxiliarDireccion.clear();auxiliarHoras.clear();auxiliarMotivo.clear();auxiliarNombre.clear();auxiliarPago.clear();auxiliarTelefono.clear();
        
        for(int i = 0; i < tablaDia.getRowCount(); i++){
            if(tablaDia.getValueAt(i, 1) != null && !(tablaDia.getValueAt(i, 1).equals(""))){
                auxiliarHoras.add(tablaDia.getValueAt(i, 0).toString());
                auxiliarNombre.add(tablaDia.getValueAt(i, 1).toString());
                auxiliarMotivo.add(tablaDia.getValueAt(i, 2).toString());
                auxiliarPago.add(Integer.parseInt(tablaDia.getValueAt(i, 3).toString()));
                auxiliarDoctor.add(tablaDia.getValueAt(i, 4).toString());
                auxiliarTelefono.add(Integer.parseInt(tablaDia.getValueAt(i, 5).toString()));
                auxiliarDireccion.add(tablaDia.getValueAt(i, 6).toString());
            }
        }
        
        
    }
    
    public static void CrearJListdoctores(ArrayList doctores){
        listaDoctores.setVisibleRowCount(15);
        listaDoctores.setListData(doctores.toArray());
        panelcentroCertificados.add(new JScrollPane(listaDoctores));
    }
    
    public static void CrearJListpacientes(ArrayList pacientes){
        listaPacientes.setVisibleRowCount(15);
        listaPacientes.setListData(pacientes.toArray());
        panelcentroCertificados.add(new JScrollPane(listaPacientes),BorderLayout.CENTER);
    }
    
    private void ConectaControlador(Controlador ctrl) {
        listaDoctores.addListSelectionListener(ctrl);
        listaPacientes.addListSelectionListener(ctrl);
        imprimir.addActionListener(ctrl);
        cargar.addActionListener(ctrl);
        guardar.addActionListener(ctrl);
    }
    
}



