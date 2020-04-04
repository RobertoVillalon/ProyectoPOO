package Vista;

import Controlador.Controlador;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicBorders;
/**
 * Clase encargada de la visualizacion de la informacion medica del paciente
 * @author RobertoVillalon
 */
public class PanelInformacionMedica extends JPanel{
    Controlador ctrl;
    /**
     * Contiene el nombre del paciente que se esta tratando
     */
    public static String nombre;
    /**
     * Contiene la razon medica del paciente que se etsa tratando
     */
    public static String razon;
    /**
     * Contiene la descripcion medica del paciente que se esta tratando
     */
    public static String descripcion;
    /**
     * Contiene el diagnostico medico del paciente que se esta tratando
     */
    public static String diagnostico;
    /**
     * Contiene el nombre del doctor encargado del paciente que se esta tratando
     */
    public static String doctor;
    JButton carga = new JButton();
    /**
     * Cuadro de texto que contendra la razon medica por la que el paciente tiene cita
     */
    public static JTextArea fieldRazon = new JTextArea();
    /**
    * Cuadro de texto que contendra la descripcion medica que da el paciente acerca de lo que siente comno malestar
    */
    public static JTextArea fieldDescripcion = new JTextArea();
    /**
     * Cuadro de texto que contendra el diagnostico final del medico. En este se puede dar una descripcion profesional como anotar medicamentos
     */
    public static JTextArea fieldDiagnostico = new JTextArea();
    /**
     * MenuItem que se encarga de la modificacion del panel para la edicion de datos
     */
    public JMenuItem editarImedica = new JMenuItem("Informacion Medica");    
    JButton listo = new JButton("Listo"),cancelar = new JButton("Cancelar");
    /**
     * Metodo costructor de la clase encargado de la construccion correcta del panels
     * @param nombre contiene el nombre del paciente
     * @param razon contiene la razon medica del paciente
     * @param descripcion contiene la descripcion medica del paciente
     * @param diagnostico contiene el diagnostico final del paciente
     * @param doctor contiene el nombre del doctor encargado del paciente
     */
    public PanelInformacionMedica(String nombre, String razon, String descripcion, String diagnostico,String doctor) {
        PanelInformacionMedica.nombre = nombre;
        PanelInformacionMedica.razon = razon;
        PanelInformacionMedica.descripcion = descripcion;
        PanelInformacionMedica.diagnostico = diagnostico;
        PanelInformacionMedica.doctor = doctor;
        
        IniciarComponentes();

    }
    
    public PanelInformacionMedica(String nombre, String razon, String descripcion, String diagnostico,String doctor,int id){
        PanelInformacionMedica.nombre = nombre;
        PanelInformacionMedica.razon = razon;
        PanelInformacionMedica.descripcion = descripcion;
        PanelInformacionMedica.diagnostico = diagnostico;
        
        ModificarComponentes();
    }

    private void IniciarComponentes(){
        if(razon == null || descripcion == null || diagnostico == null){
            razon = descripcion = diagnostico = "No existe informacion";
        }
        
        IniciarPanel();
        AgregarLabels();
        IniciarMenuItem(ctrl);
        ctrl = new Controlador();
        ConectaControlador(ctrl);
    }
    
    private void ModificarComponentes(){
        
        if(razon == null || descripcion == null || diagnostico == null){
            razon = descripcion = diagnostico = "No existe informacion";
        }
        
        IniciarPanel();
        ModificarInformacionMedica();
        IniciarBotones();
        ctrl = new Controlador();
        ConectaControlador(ctrl);
    }
    
    void IniciarPanel(){
        setLayout(new GridLayout(0,1));
        setBounds(400,20,400,350);
        setBorder(new TitledBorder(new EtchedBorder(), "Informacion Medica",1,2,new Font("Segoe UI Historic",15,15)));
        float hsb[] = new float[3];
        hsb = Color.RGBtoHSB(26, 188, 156,hsb);
        setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        setVisible(true);
    }

    void AgregarLabels(){
        JLabel razonMedica = new JLabel(razon);razonMedica.setHorizontalAlignment(JLabel.CENTER);
        JLabel descripcionMedica = new JLabel(descripcion);descripcionMedica.setHorizontalAlignment(JLabel.CENTER);
        JLabel diagnosticoMedico = new JLabel(diagnostico);diagnosticoMedico.setHorizontalAlignment(JLabel.CENTER);
        
        razonMedica.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK), "Razon Medica",2,2));
        descripcionMedica.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK), "Descripcion Medica",2,2));
        diagnosticoMedico.setBorder(new TitledBorder(new WindowsBorders.DashedBorder(Color.BLACK), "Diagnostico",2,2));
        add(razonMedica);
        add(descripcionMedica);
        add(diagnosticoMedico);
    }

    void IniciarMenuItem(Controlador c) {
        
        editarImedica.setActionCommand("editarImedica");
        ConectaControlador(c);
    }
    
    void ModificarInformacionMedica(){
        JLabel razonMedica = new JLabel("Razon Medica: ");
        JLabel descripcionMedica = new JLabel("Descripcion Medica: ");
        JLabel diagnosticoMedico = new JLabel("Diagnostico: ");
        
        add(razonMedica);
        fieldRazon.setBorder(new BasicBorders.FieldBorder(Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
        fieldRazon.setText(razon);
        fieldRazon.setLineWrap(true);
        JScrollPane barra1 = new JScrollPane(fieldRazon,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(barra1);
        
        add(descripcionMedica);
        fieldDescripcion.setBorder(new BasicBorders.FieldBorder(Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
        fieldDescripcion.setText(descripcion);
        fieldDescripcion.setLineWrap(true);
        JScrollPane barra2 = new JScrollPane(fieldDescripcion,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(barra2);
        
        add(diagnosticoMedico);
        fieldDiagnostico.setBorder(new BasicBorders.FieldBorder(Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
        fieldDiagnostico.setText(diagnostico);
        fieldDiagnostico.setLineWrap(true);
        JScrollPane barra3 = new JScrollPane(fieldDiagnostico,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(barra3);
            
    }

    void IniciarBotones(){
        listo.setBounds(20, 320, 80, 20);
        listo.setActionCommand("listoPIM");
        add(listo);
        
        cancelar.setBounds(120, 320, 100, 20);
        cancelar.setActionCommand("cancelarPIM");
        add(cancelar);
      
    }
    
     /**
     * Metodo que se encarga de la conexion con el controlador para los eventos
     * @param c parametro de tipo controlador que transoporta la informacion de la vista al controlador
     */
    
    void ConectaControlador(Controlador c){
        
        editarImedica.addActionListener(c);
        listo.addActionListener(c);
        cancelar.addActionListener(c);
        
    }
    
    /**
     * Metodo encargado de ajustar la variable razon para su correcta visualizacion mediante codigo HTML
     * @param aux parametro que contiene la version original de la variable razon
     * @return retorna la variable razon concatenada con instrucciones HTML para efectuar saltos de linea en los JTextArea
     */
    
    public static String ObtenerRazon(String aux){
        String inicio = "<html><body>",finalizar = "</body></html>",salto = "<br>";
        int i = 0,maximo = 67,linea = 67,lineas = 0,actual = 0;
        ArrayList arreglo = new ArrayList();
        
        if(aux.length() <= linea){
            return aux;
        }
        
        if(razon != null || descripcion != null || diagnostico != null){
            while(maximo < aux.length()){
                if(i == maximo){
                    lineas++;
                    arreglo.add(aux.substring(actual, maximo));
                    actual = maximo;
                    maximo+=linea;
                    if(maximo > aux.length()){
                        arreglo.add(aux.substring(actual, aux.length()));
                    }
                }
                i++;
            }

            aux = "";
            for (i = 0; i < arreglo.size(); i++){
                aux = aux.concat(arreglo.get(i).toString());
                aux = aux.concat(salto);
            }
        
            aux = inicio.concat(aux);
            aux = aux.concat(finalizar);
        }
        
        return aux;
    }
    /**
     * Metodo encargado de ajustar la variable descripcion para su correcta visualizacion mediante codigo HTML
     * @param aux parametro que contiene la version original de la variable razon
     * @return retorna la variable descripcion concatenada con instrucciones HTML para efectuar saltos de linea en los JTextArea
     */
    public static String ObtenerDescripcion(String aux){
        String inicio = "<html><body>",finalizar = "</body></html>",salto = "<br>";
        int i = 0,maximo = 67,linea = 67,actual = 0;
        ArrayList arreglo = new ArrayList();
                       
        if(aux.length() <= linea){
            return aux;
        }
        
        if(razon != null || descripcion != null || diagnostico != null){
            while(maximo <= aux.length()){
                if(i == maximo){
                    arreglo.add(aux.substring(actual, maximo));
                    actual = maximo;
                    maximo+=linea;
                    if(maximo > aux.length()){
                        arreglo.add(aux.substring(actual, aux.length()));
                    }
                }
                i++;
            }

            aux = "";
            for (i = 0; i < arreglo.size(); i++){
                aux = aux.concat(arreglo.get(i).toString());
                aux = aux.concat(salto);
            }
        
            aux = inicio.concat(aux);
            aux = aux.concat(finalizar);
        }
        
        return aux;  
    }
    
      /**
     * Metodo encargado de ajustar la variable diagnostico para su correcta visualizacion mediante codigo HTML
     * @param aux parametro que contiene la version original de la variable razon
     * @return retorna la variable diagnostico concatenada con instrucciones HTML para efectuar saltos de linea en los JTextArea
     */
    
    public static String ObtenerDiagnostico(String aux){
        String inicio = "<html><body>",finalizar = "</body></html>",salto = "<br>";
        int i = 0,maximo = 67,linea = 67,actual = 0;
        ArrayList arreglo = new ArrayList();
        
        if(aux.length() <= linea){
            return aux;
        }
        
        if(razon != null || descripcion != null || diagnostico != null){
            while(maximo < aux.length()){
                if(i == maximo){
                    arreglo.add(aux.substring(actual, maximo));
                    actual = maximo;
                    maximo+=linea;
                    if(maximo > aux.length()){
                        arreglo.add(aux.substring(actual, aux.length()));
                    }
                }
                i++;
            }

            aux = "";
            for (i = 0; i < arreglo.size(); i++){
                aux = aux.concat(arreglo.get(i).toString());
                aux = aux.concat(salto);
            }
        
            aux = inicio.concat(aux);
            aux = aux.concat(finalizar);
        }
        
        return aux;
    }
}
