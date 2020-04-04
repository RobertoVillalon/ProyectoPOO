package Modelo;

import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de la conexion con la base de datos;
 * @author RobertoVillalon
 */
public class Modelo{
    private Connection conexionPrueba;
    private Statement sPrueba;
    static String nombre,rut,fecha,sistema,doctor,nombreviejo;
    static String razon,descripcion,diagnostico;
    static int edad;
    static Connection conexion;
    static ResultSet resultadoConsulta = null;
    static Statement s = null;
    
    public Modelo() {
        Modelo.Conectarse();
        
        try {
            s = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(""+e.getLocalizedMessage());
        }
    }
    
    
    /**
     * Metodo encargado de crear una conexion con la base de datos
     * @return Una variable de tipo conexion
     */
    public static boolean Conectarse(){
        
        if(conexion != null){
            System.out.println("Ya existe una conexion");
            return true;
        }
       
        try{
            Class.forName("org.postgresql.Driver");
            
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","a");

        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Problemas de conexion= "+e.getLocalizedMessage());
            return false;
        }
        
        
        return true;
    }
   
    
    public static boolean RegistroUsuario(String nombre, String email, String contraseña, String tipo, String especialidad){
        
        try{
            
            int resultado = s.executeUpdate("INSERT INTO trabajador (nombre,email,contraseña,tipo,especialidad) VALUES ('"+nombre+"','"+email+"','"+contraseña+"','"+tipo+"','"+especialidad+"')");
            
            if(resultado == 0){
                JOptionPane.showMessageDialog(null,"Error al registrar al paciente","Error",0);
                return false;
            }
            
        }
        catch(SQLException e){
            System.out.println("Error de conexion= "+e.getLocalizedMessage());
        }
        
        return true;
    }
    
    public static boolean ValidoDoctor(String nombre, String contraseña){
       
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM trabajador WHERE tipo = 'Doctor'");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
                if(nombre.equals(resultadoConsulta.getString("nombre")) && contraseña.equals(resultadoConsulta.getString("contraseña"))){
                    return true;
                }
            }
            
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return false;
    }
    
    public static boolean ValidoSecretaria(String nombre, String contraseña){
       
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM trabajador WHERE tipo = 'Secretaria'");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
                if(nombre.equals(resultadoConsulta.getString("nombre")) && contraseña.equals(resultadoConsulta.getString("contraseña"))){
                    return true;
                }
            }
            
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return false;
    }
    
    /**
     * Metodo encargado del registro del paciente en la base de datos
     * @param nombre Nombre del paciente a registrar
     * @param rut rut del paciente a registrar
     * @param fecha fecha de nacimiento del paciente a registrar
     * @param sistema sistema de salud del paciente a registrar
     * @param doctor doctor encargado del paciente a registrar
     * @param edad edad del paciente a registrar
     */
    public static void RegistrarPaciente(String nombre, String rut,String fecha, String sistema, String doctor, int edad){
        
        try{
            
            int resultado = s.executeUpdate("INSERT INTO pacientes (nombrepaciente,edadpaciente,rutpaciente,sistemasalud,doctor,fechanacimiento) VALUES ('"+nombre+"','"+edad+"','"+rut+"','"+sistema+"','"+doctor+"','"+fecha+"')");
            
            if(resultado == 0){
                JOptionPane.showMessageDialog(null,"Error al registrar al paciente","Error",0);
            }
            
        }
        catch(SQLException e){
            System.out.println("Error de conexion= "+e.getLocalizedMessage());
        }
        

        
    }
    
    /**
     * Metodo encargado de la actualizacion de pacientes en la base de datos
     * @param nombre nuevo nombre del paciente a actualizar datos
     * @param rut nuevo rut del paciente a actualizar datos
     * @param fecha nueva fecha de nacimiento del paciente a actualizar datos
     * @param sistema nuevo sistema medico del paciente a actualizar datos
     * @param nombreviejo antiguo nombre del paciente a actualizar datos
     * @param edad  nueva edad del paciente a actualizar datos
     */
    
    public static void ActualizaPacientes(String nombre,String rut, String fecha, String sistema,String nombreviejo, int edad){
                 
        try{
            
            int resultado = s.executeUpdate("UPDATE pacientes SET (nombrepaciente,rutpaciente,fechanacimiento,sistemasalud,edadpaciente) = ('"+nombre+"','"+rut+"','"+fecha+"','"+sistema+"','"+edad+"') where nombrepaciente = '"+nombreviejo+"'");
            
            if(resultado == 0){
                JOptionPane.showMessageDialog(null,"Error al actualizar informacion medica del paciente","Error",0);
            }
            
        }
        catch(SQLException e){
            System.out.println("Error de conexion= "+e.getLocalizedMessage());
        }
    }
    
    /**
     * Metodo encargado de la actualizacion de los datos medicos del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param razon razon medica por la que el paciente se encuentra en una revision
     * @param descripcion descripcion detallada de la razon medica
     * @param diagnostico diagnostico con el que el paciente se va al terminar la consulta
     */
    
    public static void CaracteristicasMedicasPaciente(String nombre, String razon, String descripcion, String diagnostico){
         
        try{
            
            int resultado = s.executeUpdate("UPDATE pacientes SET (razonmedica,descripcionmedica,diagnostico) = ('"+razon+"','"+descripcion+"','"+diagnostico+"') where nombrepaciente = '"+nombre+"'");
            
            if(resultado == 0){
                JOptionPane.showMessageDialog(null,"Error al actualizar informacion medica del paciente","Error",0);
            }
            
        }
        catch(SQLException e){
            System.out.println("Error de conexion= "+e.getLocalizedMessage());
        }
    }
    
    /**
     * Metodo encargado de la carga de nombres de los pacientes en la base de datos
     * @param doctor doctor a cargo de los pacientes a los que se quiere acceder
     * @return retorna una lista en la que se encuentran todos los pacientes del doctor
     */
    
    public static ArrayList<String> CargarNombres(String doctor){
        ArrayList<String> nombres = new ArrayList<>();
        
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
               nombre = resultadoConsulta.getString("nombrepaciente");
               nombres.add(nombre);
            }
            
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        Collections.sort(nombres);
        
        return nombres;
      }
    
    /**
     * Metodo encargado de la obtencion de la edad del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param doctor nombre del doctor encargado
     * @return retorna la edad del paciente encontrada en la base de datos
     */
    
    public static int CargaEdad(String nombre, String doctor){
          
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
               edad = Integer.parseInt(resultadoConsulta.getString("edadpaciente"));
            }
        }catch(NumberFormatException | SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        
        return edad;
      }
      
     /**
     * Metodo encargado de la obtencion de la fecha de nacimiento del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param doctor nombre del doctor encargado
     * @return retorna la fecha de nacimiento del paciente encontrada en la base de datos
     */
    
    public static String CargaFechaNacimiento(String nombre, String doctor){
        
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
               fecha = resultadoConsulta.getString("fechanacimiento");
            }
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return fecha;
      }
    
     /**
     * Metodo encargado de la obtencion del rut del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param doctor nombre del doctor encargado
     * @return retorna el rut del paciente encontrado en la base de datos
     */
    
    public static String CargaRut(String nombre, String doctor){
          
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
               rut = resultadoConsulta.getString("rutpaciente");
            }
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return rut;
      }
      
     /**
     * Metodo encargado de la obtencion del sistema de salud del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param doctor nombre del doctor encargado
     * @return retorna el sistema de salud del paciente encontrado en la base de datos
     */
    
    public static String CargaSistema(String nombre, String doctor){
          
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
               sistema = resultadoConsulta.getString("sistemasalud");
            }
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return sistema;
      }
    
     /**
     * Metodo encargado de la obtencion de la razon medica del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param doctor nombre del doctor encargado
     * @return retorna la razon medica del paciente encontrada en la base de datos
     */
    
    public static String CargaRazon(String nombre, String doctor){
        
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
               razon = resultadoConsulta.getString("razonmedica");
            }
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return razon;
    }
    
         /**
     * Metodo encargado de la obtencion de la descripcion medica del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param doctor nombre del doctor encargado
     * @return retorna la descripcion medica del paciente encontrada en la base de datos
     */
    
    public static String CargaDescripcion(String nombre, String doctor){
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos= "+e.getLocalizedMessage());
        }
        
        try {
            while(resultadoConsulta.next()){
               descripcion = resultadoConsulta.getString("descripcionmedica");
            }
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return descripcion;
    }
    
     /**
     * Metodo encargado de la obtencion del diagnostico del paciente en la base de datos
     * @param nombre nombre del paciente
     * @param doctor nombre del doctor encargado
     * @return retorna el ultimo diagnostico del paciente encontrado en la base de datos
     */
    
    public static String CargaDiagnostico(String nombre, String doctor){
        try{
            resultadoConsulta = s.executeQuery("SELECT * FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"' ");
        }catch(SQLException e){
            System.out.println("Problemas buscando la base de datos");
        }
        
        try {
            while(resultadoConsulta.next()){
               diagnostico = resultadoConsulta.getString("diagnostico");
            }
        }catch(SQLException e){
            System.out.println("Error al obtener datos= "+e.getLocalizedMessage());
        }
        
        return diagnostico;
    }
    
    /**
     * Metodo encargado de la eliminacion de pacientes en la base de datos
     * @param nombre nombre del paciente a eliminar
     * @param doctor nombre del doctor del paciente a eliminar
     */
    
    public static void BorrarPaciente(String nombre, String doctor){
           
        try{
            
            int resultado = s.executeUpdate("DELETE FROM pacientes WHERE nombrepaciente= '"+nombre+"' and doctor= '"+doctor+"'");
            
        }
        catch(SQLException e){
            System.out.println("Error al borrar datos= "+e.getLocalizedMessage());
        }
      }
    
    /**
     * Metodo encargado de la obtencion de nombres de los pacientes con hora al doctor para la secretaria
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @param doctor contiene el nombre del doctor encargado de los pacientes requeridos por la secretaria
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<String> ObtenerNombrePacienteSec(String fecha, String doctor){
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM horaspacientes WHERE fecha= '"+fecha+"' and doctor = '"+doctor+"'");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getString("nombrepaciente"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
     /**
     * Metodo encargado de la obtencion del motivo medico de los pacientes con hora al doctor para la secretaria
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @param doctor contiene el nombre del doctor encargado de los pacientes requeridos por la secretaria
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<String> ObtenerMotivoPacienteSec(String fecha, String doctor){
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM horaspacientes WHERE fecha= '"+fecha+"' and doctor = '"+doctor+"'");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getString("motivopaciente"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
      /**
     * Metodo encargado de la obtencion de pagos de los pacientes con hora al doctor para la secretaria
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @param doctor contiene el nombre del doctor encargado de los pacientes requeridos por la secretaria
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<Integer> ObtenerPagoPacienteSec(String fecha, String doctor){
        ArrayList<Integer> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM horaspacientes WHERE fecha= '"+fecha+"' and doctor = '"+doctor+"'");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getInt("Pago"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
     /**
     * Metodo encargado de la obtencion de nombres de los doctores con hora al doctor para la secretaria, normalmente es igual al parametro doctor, se utiliza para la insercion correcta en la tabla de horas
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @param doctor contiene el nombre del doctor encargado de los pacientes requeridos por la secretaria
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<String> ObtenerDoctorPacienteSec(String fecha, String doctor){
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM horaspacientes WHERE fecha= '"+fecha+"' and doctor = '"+doctor+"'");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getString("doctor"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
     /**
     * Metodo encargado de la obtencion del telefono de los pacientes con hora al doctor para la secretaria
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @param doctor contiene el nombre del doctor encargado de los pacientes requeridos por la secretaria
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<Integer> ObtenerTelefonoPacienteSec(String fecha, String doctor){
        ArrayList<Integer> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM horaspacientes WHERE fecha= '"+fecha+"' and doctor = '"+doctor+"'");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getInt("Telefono"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
     /**
     * Metodo encargado de la obtencion de la direccion de los pacientes con hora al doctor para la secretaria
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @param doctor contiene el nombre del doctor encargado de los pacientes requeridos por la secretaria
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<String> ObtenerDireccionPacienteSec(String fecha, String doctor){
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM horaspacientes WHERE fecha= '"+fecha+"' and doctor = '"+doctor+"'");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getString("direccion"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
     /**
     * Metodo encargado de la obtencion de la hora en las que los pacientes se presentaran a su cita medica
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @param doctor contiene el nombre del doctor encargado de los pacientes requeridos por la secretaria
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<String> ObtenerHoraPacienteSec(String fecha, String doctor){
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM horaspacientes WHERE fecha= '"+fecha+"' and doctor = '"+doctor+"'");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getString("hora"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
     /**
     * Metodo encargado de la obtencion de nombres de los doctores para la posterior obtencion de pacientes de cada doctor
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static ArrayList<String> ObtenerDoctores(){
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            resultadoConsulta = s.executeQuery("SELECT * FROM trabajador WHERE tipo = 'Doctor' ");
            
            while(resultadoConsulta.next()){
                lista.add(resultadoConsulta.getString("nombre"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener Tabla");
        }
        
        return lista;
    }
    
      /**
     * Metodo encargado de la eliminacion de tablas en la base de datos. Se utiliza para eliminar tablas obsoletas
     * @param fecha contiene la fecha de la hora en que el paciente tiene la revision con el doctor
     * @return retorna una lista de los pacientes que cumplen con la fecha y doctor buscados
     */
    
    public static boolean BorrarTabla(String fecha){
         
        try {
            s.executeQuery("DELETE FROM horaspacientes WHERE fecha = '"+fecha+"'");
            return true;
        } catch (SQLException ex) {
            
        }
        
        return false;
    }
    
    
     /**
     * Metodo encargado del guardado de la tabla actualizada en la base de datos
     * @param auxNombre arreglo que contiene los nombres de los pacientes que se encuentran en la lista a guardar
     * @param auxMotivo arreglo que contiene el motivo medico de los pacientes que se encuentran en la lista a guardar
     * @param auxDireccion arreglo que contiene la direccion de los pacientes que se encuentran en la lista a guardar
     * @param auxHoras arreglo que contiene las horas medicas de los pacientes que se encuentran en la lista a guardar
     * @param auxDoctor arreglo que contiene los nombres de los doctores de los pacientes que se encuentran en la lista a guardar
     * @param auxPago arreglo que contiene los pagos los pacientes que se encuentran en la lista a guardar
     * @param auxTelefono arreglo que contiene los telefonos de los pacientes que se encuentran en la lista a guardar
     * @param fecha arreglo que contiene las fechas de citas con el medico de los pacientes que se encuentran en la lista a guardar
     * @param i paarameto que contiene la posicion de los pacientes en los arreglos antes mencionados
     * @return true por cada paciente insertado correctamente en la base de datos
     */



    public static boolean GuardarTabla(ArrayList<String> auxNombre, ArrayList<String> auxMotivo, ArrayList<String> auxDireccion, ArrayList<String> auxHoras, ArrayList<String> auxDoctor, ArrayList<Integer> auxPago, ArrayList<Integer> auxTelefono, String fecha,int i){
        
        try{
            
            s.executeQuery("INSERT INTO horaspacientes VALUES ('"+auxNombre.get(i)+"','"+auxMotivo.get(i)+"','"+auxPago.get(i)+"','"+auxTelefono.get(i)+"','"+auxDireccion.get(i)+"','"+fecha+"','"+auxHoras.get(i)+"','"+auxDoctor.get(i)+"')");
        
        } catch(SQLException ex){
            return false;
        }
        
        
        
        return true; 
    }
    
    /**
     * Metodo encargado de cerrar la conexion existente entre el programa y la base de datos
     * @return retorna conexion culminada con la base de datos 
     */
    
    public static Connection CerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e){
            System.out.println("Error al cerrar conexion= "+e.getLocalizedMessage());
    }
        
        return conexion;
    }
    /**
     * Metodo encargado de crear una conexion diferente de la conexion original para efectuar pruebas
     */
    public void ObtenerConexionPrueba(){
        try {
            Class.forName("org.postgresql.Driver");
            
            try {
                conexionPrueba = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","a");
            } catch (SQLException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
    /**
     * Metodo encargado de probar la conexion con la conexion diferente creada anteriormente
     * @param consulta Contiene la consulta de prueba que se quier efecutuar en la base de datos
     * @return un parametro de tipo Statement parra usarlo en las pruebas
     */
    public int PruebaConexion(String consulta){
        try {
            sPrueba = conexionPrueba.createStatement();
            return sPrueba.executeUpdate(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }  
    }
}
