package Modelo;

import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 * Clase que se encarga de imprimir los certificados de los pacientes
 * @author RobertoVillalon
 */
public class ImpresionCertificados implements Printable{
    String nombre,doctor,diagnostico;
    PrinterJob printerJob;
    static Date date = new Date();
    
    /**
     * Metodo Constructor de la clase
     * @param nombre Contiene el nombre del paciente al que pertenece el certificado
     * @param doctor Contiene el nombre del doctor encargado de entregar el certificado
     */
    
    public ImpresionCertificados(String nombre, String doctor){
        this.nombre = nombre;
        this.doctor = doctor;
        
        ObtenerFecha();
        
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);
        
        try{
            pj.print();
            pj.setJobName("Certificado Medico "+this.nombre);
        } 
        catch(PrinterException e){
           JOptionPane.showMessageDialog(null,"Documento no impreso","Error",2);
        }
    }
    
    /**
     * Metodo Principal de la interfaz Printable
     * @param g Variable encargada de la escritura en el archivo de impresion
     * @param f Variable encargada de las caracteristicas fisicas que poseera la impresion
     * @param nPagina Variable que contiene el numero de pagina actual que se esta modificando
     * @return Se retorna si la impresion se puede o no llevar a cabo mediante las variables PAGE_EXISTS o NO_SUCH_PAGE
     */
    @Override
    public int print(Graphics g, PageFormat f, int nPagina){
        if(nPagina == 0){
            f.setPaper(new Paper());
            f.setOrientation(1);
            g.drawString("El profesional certifica que don (a) "+this.nombre+" se sometio a una revision ",100,100);
            g.drawString("medica con fecha "+ObtenerFecha()+ " y lo excusa de la falta a su deber",100,120);
            g.drawString("Sugerencias: ", 100, 140);
            g.drawString("\n\nFirma: "+doctor,100,250);
            System.out.println(PAGE_EXISTS);
            return PAGE_EXISTS;
        }
        else
            return NO_SUCH_PAGE;
   }
/**
 * Metodo encargado de la obtencion de la fecha de emision del certificado
 * @return La fecha del dia de emision del certificado
 */
    public static String ObtenerFecha() {
        
        String Fecha = "dd MMMMM YYYY HH:mm";
        SimpleDateFormat fSimple = new SimpleDateFormat(Fecha);
        Fecha = fSimple.format(date);
        
        return Fecha;
    }
}