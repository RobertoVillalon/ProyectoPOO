package ClasePrincipal;

import Vista.*;
import Controlador.Controlador;
import Modelo.Modelo;

/**
 * Clase Principal del programa
 * @author RobertoVillalon
 */

public class Main{
    /**
     * Metodo Principal del Programa
     * @param args parametro por defecto del metodo Main
     */
    public static void main(String [] args){
       Hilo hilo1 = new Hilo();
       
       javax.swing.SwingUtilities.invokeLater(hilo1);
   }
}

class Hilo implements Runnable{
    /**
     * Clase que implenta la interfaz Runnable para ejecutar el metodo invokeLater
     */
        @Override
        public void run() {
            Vista vista = new Vista();
            Controlador c = new Controlador();
            Modelo m = new Modelo();
    }
}
