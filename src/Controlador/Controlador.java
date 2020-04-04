package Controlador;
import Modelo.*;
import java.awt.event.*;
import Vista.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Clase que maneja la transeferencia de datos entre Modelo y la Vista a travez del la interfaz ActionListener,WindowListener y ListSelectionListener
 * @author RobertoVillalon
 */

public class Controlador implements ActionListener,WindowListener,ListSelectionListener{
    public static String doctor = null,secretaria = null;
    public static ArrayList<String> auxiliar = new ArrayList<String>();
    String nombreUsuario,contraseñaUsuario,emailUsuario,especialidadUsuario,tipoUsuario;
    String nombrePaciente;
    /**
     * Metodo principal de la interfaz ActionListener, en el se ejecutan todos los eventos del programa
     * @param ae parametro que contiene la informacion del objeto Swing a modificar
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        String Id = ae.getActionCommand();
        
        switch(Id){
            case "ingresoUsuario":
                nombreUsuario = PanelCargaUsuario.fieldUsuario.getText();
                contraseñaUsuario = String.valueOf(PanelCargaUsuario.fieldContraseña.getPassword());
                if(Modelo.ValidoDoctor(nombreUsuario, contraseñaUsuario)){
                    secretaria = null;
                    doctor = nombreUsuario;
                    Vista.panelPrincipal.removeAll();
                    Vista.panelPrincipal.add(Vista.barra);
                    Vista.AgregarLogo("Completo");
                    System.out.println("Ingreso Exitoso");
                    Vista.panelPrincipal.repaint();
                    Vista.panelPrincipal.revalidate();
                }
                else if(Modelo.ValidoSecretaria(nombreUsuario, contraseñaUsuario)){
                    secretaria = nombreUsuario;
                    doctor = null;
                    PanelSecretaria.seleccionDoctor = new JComboBox();
                    auxiliar = Modelo.ObtenerDoctores();
                    for(int i = 0; i < auxiliar.size(); i++){
                        PanelSecretaria.seleccionDoctor.addItem(auxiliar.get(i));
                    }
                    Vista.panelPrincipal.removeAll();
                    System.out.println("Ingreso Exitoso");
                    PanelSecretaria ps = new PanelSecretaria();
                    PanelSecretaria.CrearJListdoctores(Modelo.ObtenerDoctores());
                    Vista.panelPrincipal.add(ps);
                    Vista.panelPrincipal.repaint();
                    Vista.panelPrincipal.revalidate();
                }
                else{
                    JOptionPane.showMessageDialog(null, "El nombre y/o contraseña son incorrectos", "Error", 2);
                    PanelCargaUsuario.fieldUsuario.setText("");
                    PanelCargaUsuario.fieldContraseña.setText(""); 
                }
            break;
            
            case "registroUsuario":
               Vista.panelPrincipal.removeAll();
               PanelRegistroUsuario pru = new PanelRegistroUsuario();
               Vista.AgregarLogo("Medio izquierda");
               Vista.panelPrincipal.add(pru);
               Vista.panelPrincipal.repaint();
               Vista.panelPrincipal.revalidate();
            break;
            case "radioDoctor":
                tipoUsuario = PanelRegistroUsuario.radioDoctor.getText();
                PanelRegistroUsuario.fieldEspecialidad.setText("");
                PanelRegistroUsuario.fieldEspecialidad.setEnabled(true);
            break;
            case "radioSecretaria":
                tipoUsuario = PanelRegistroUsuario.radioSecretaria.getText();
                PanelRegistroUsuario.fieldEspecialidad.setText("NO DISPONIBLE");
                PanelRegistroUsuario.fieldEspecialidad.setEnabled(false);
            break;
            case "registrarUsuario":
                if(PanelRegistroUsuario.radioDoctor.isSelected()){
                    tipoUsuario = PanelRegistroUsuario.radioDoctor.getText();
                }
                nombreUsuario = PanelRegistroUsuario.fieldUsuario.getText();
                contraseñaUsuario = String.valueOf(PanelRegistroUsuario.fieldContraseña.getPassword());
                emailUsuario = PanelRegistroUsuario.fieldEmail.getText();
                switch (tipoUsuario) {
                    case "Doctor":
                        especialidadUsuario = PanelRegistroUsuario.fieldEspecialidad.getText();
                    break;
                    case "Secretaria":
                        especialidadUsuario = null;
                    break;
                }
                if(nombreUsuario.isEmpty() || contraseñaUsuario.isEmpty() || emailUsuario.isEmpty() || tipoUsuario.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Datos Erroneos, Procure completar todos los espacios","Error",2);
                }
                else if(Modelo.RegistroUsuario(nombreUsuario,emailUsuario, contraseñaUsuario, tipoUsuario, especialidadUsuario)){
                    JOptionPane.showMessageDialog(null,"Usuario Registrado, Redirigiendo a la pantalla de ingreso","Registro Exitoso",2);
                    Vista.panelPrincipal.removeAll();
                    Vista.AgregarLogo("Medio Abajo");
                    PanelCargaUsuario pcp = new PanelCargaUsuario();
                    Vista.panelPrincipal.add(pcp);
                    Vista.panelPrincipal.repaint();
                    Vista.panelPrincipal.revalidate();
                    PanelRegistroUsuario.fieldUsuario.setText("");
                    PanelRegistroUsuario.fieldContraseña.setText("");
                    PanelRegistroUsuario.fieldEmail.setText("");
                    PanelRegistroUsuario.fieldEspecialidad.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos, Intente denuevo mas tarde");
                }
            break;
            
            case "volverRegistro":
                    Vista.panelPrincipal.removeAll();
                    Vista.AgregarLogo("Medio abajo");
                    PanelCargaUsuario pcp1 = new PanelCargaUsuario();
                    Vista.panelPrincipal.add(pcp1);
                    Vista.panelPrincipal.repaint();
                    Vista.panelPrincipal.revalidate();
                    PanelRegistroUsuario.fieldUsuario.setText("");
                    PanelRegistroUsuario.fieldContraseña.setText("");
                    PanelRegistroUsuario.fieldEmail.setText("");
                    PanelRegistroUsuario.fieldEspecialidad.setText("");
            break;
            
            case "MnuevoPaciente":        
               NuevoPaciente NP = new NuevoPaciente(doctor);
            break;
            
            case "McargarPaciente":
                auxiliar = Modelo.CargarNombres(doctor);
                CargarPaciente CP = new CargarPaciente(doctor);
            break;
            
            case "MborrarPaciente":
                auxiliar = Modelo.CargarNombres(doctor);
                BorrarPaciente BP = new BorrarPaciente(doctor);
            break;
            
            case "registrarPaciente":
                if(NuevoPaciente.fieldNombre.getText().isEmpty() || NuevoPaciente.fieldEdad.getText().isEmpty() || NuevoPaciente.fieldRut.getText().isEmpty() || NuevoPaciente.fieldFecha.getText().isEmpty() || NuevoPaciente.fieldSistema.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Falta informacion de registro");
                }
                else{
                    Modelo.RegistrarPaciente(NuevoPaciente.fieldNombre.getText(), NuevoPaciente.fieldRut.getText(), NuevoPaciente.fieldFecha.getText(), NuevoPaciente.fieldSistema.getText(), doctor, Integer.parseInt(NuevoPaciente.fieldEdad.getText()));
                    JOptionPane.showMessageDialog(null,"Paciente Registrado");   
                }
                
            break;
            
            case "cargarPaciente":
                nombrePaciente = CargarPaciente.caja.getSelectedItem().toString();
                if(PanelInformacionPersonal.nombre != null){
                    Vista.panelPrincipal.removeAll();
                    PanelInformacionMedica.razon = null;
                    PanelInformacionMedica.descripcion = null;
                    PanelInformacionMedica.diagnostico = null;
                }
                
                PanelInformacionMedica.razon = Modelo.CargaRazon(nombrePaciente, doctor);
                PanelInformacionMedica.descripcion = Modelo.CargaDescripcion(nombrePaciente, doctor);
                PanelInformacionMedica.diagnostico = Modelo.CargaDiagnostico(nombrePaciente, doctor);
                if(PanelInformacionMedica.razon == null || PanelInformacionMedica.descripcion == null || PanelInformacionMedica.diagnostico == null){
                    if(PanelInformacionMedica.razon == null){
                        PanelInformacionMedica.razon = "No existe informacion";
                    }
                    
                    if(PanelInformacionMedica.descripcion == null){
                        PanelInformacionMedica.descripcion = "No existe informacion";
                    }
                    
                    if(PanelInformacionMedica.diagnostico == null){
                        PanelInformacionMedica.diagnostico = "No existe informacion";
                    }    
                }
                
                PanelInformacionPersonal PIP = new PanelInformacionPersonal(nombrePaciente, Modelo.CargaRut(nombrePaciente, doctor), Modelo.CargaFechaNacimiento(nombrePaciente, doctor), Modelo.CargaSistema(nombrePaciente, doctor), doctor, Modelo.CargaEdad(nombrePaciente, doctor));
                PanelInformacionMedica PIM = new PanelInformacionMedica(nombrePaciente, PanelInformacionMedica.ObtenerRazon(PanelInformacionMedica.razon), PanelInformacionMedica.ObtenerDescripcion(PanelInformacionMedica.descripcion), PanelInformacionMedica.ObtenerDiagnostico(PanelInformacionMedica.diagnostico),doctor);
                Vista.labelLogo.setVisible(false);
                Vista.editar.setVisible(true);
                Vista.editar.setEnabled(true);
                if(Vista.editar.getSubElements().length < 1){
                    Vista.editar.add(PIP.editarIpersonal);
                    Vista.editar.add(PIM.editarImedica);
                }
                
                Vista.barra.add(Vista.editar);
                Vista.panelPrincipal.add(Vista.barra);
                Vista.panelPrincipal.add(PIP);
                Vista.panelPrincipal.add(PIM);
                Vista.panelPrincipal.revalidate();
                Vista.panelPrincipal.repaint();
            break;
            
            case "editarIpersonal":
                
                PanelInformacionPersonal PIP6 = new PanelInformacionPersonal(PanelInformacionPersonal.nombre, PanelInformacionPersonal.rut, PanelInformacionPersonal.fecha, PanelInformacionPersonal.sistema, doctor, PanelInformacionPersonal.edad,0);
                PanelInformacionMedica PIM6 = new PanelInformacionMedica(PanelInformacionPersonal.nombre, PanelInformacionMedica.razon, PanelInformacionMedica.descripcion, PanelInformacionMedica.diagnostico,doctor);
                
                Vista.panelPrincipal.removeAll();
                Vista.editar.setEnabled(true);
                Vista.editar.removeAll();
                Vista.editar.add(PIP6.editarIpersonal);
                Vista.editar.add(PIM6.editarImedica);
                Vista.panelPrincipal.add(Vista.barra);
                Vista.panelPrincipal.add(PIP6);
                Vista.panelPrincipal.add(PIM6);
                Vista.panelPrincipal.revalidate();
                Vista.panelPrincipal.repaint();
            break;
            case "editarImedica":
                PanelInformacionMedica.razon = Modelo.CargaRazon(PanelInformacionPersonal.nombre, doctor);
                PanelInformacionMedica.descripcion = Modelo.CargaDescripcion(PanelInformacionPersonal.nombre, doctor);
                PanelInformacionMedica.diagnostico = Modelo.CargaDiagnostico(PanelInformacionPersonal.nombre, doctor);
                PanelInformacionPersonal PIP7 = new PanelInformacionPersonal(PanelInformacionPersonal.nombre, PanelInformacionPersonal.rut, PanelInformacionPersonal.fecha, PanelInformacionPersonal.sistema, doctor, PanelInformacionPersonal.edad);
                PanelInformacionMedica PIM7 = new PanelInformacionMedica(PanelInformacionPersonal.nombre, PanelInformacionMedica.razon, PanelInformacionMedica.descripcion, PanelInformacionMedica.diagnostico,doctor,0);         
                Vista.panelPrincipal.removeAll();
                Vista.editar.setEnabled(true);
                Vista.editar.removeAll();
                Vista.editar.add(PIP7.editarIpersonal);
                Vista.editar.add(PIM7.editarImedica);
                Vista.panelPrincipal.add(Vista.barra);
                Vista.panelPrincipal.add(PIP7);
                Vista.panelPrincipal.add(PIM7);
                Vista.panelPrincipal.revalidate();
                Vista.panelPrincipal.repaint();
            break;
            case "listoPIP":
                try{
                    String nombreviejo = PanelInformacionPersonal.nombre;
                    Modelo.ActualizaPacientes(PanelInformacionPersonal.fieldNombre.getText(), PanelInformacionPersonal.fieldRut.getText(), PanelInformacionPersonal.fieldFecha.getText(), PanelInformacionPersonal.fieldSistema.getText(), nombreviejo, Integer.parseInt(PanelInformacionPersonal.fieldEdad.getText()));
                    JOptionPane.showMessageDialog(null,"Paciente Modificado");
                    PanelInformacionPersonal PIP2 = new PanelInformacionPersonal(PanelInformacionPersonal.fieldNombre.getText(), PanelInformacionPersonal.fieldRut.getText(), PanelInformacionPersonal.fieldFecha.getText(), PanelInformacionPersonal.fieldSistema.getText(), doctor, Integer.parseInt(PanelInformacionPersonal.fieldEdad.getText()));
                    PanelInformacionMedica PIM2 = new PanelInformacionMedica(PanelInformacionPersonal.nombre, PanelInformacionMedica.razon, PanelInformacionMedica.descripcion, PanelInformacionMedica.diagnostico,doctor);
                    Vista.panelPrincipal.removeAll();
                    Vista.editar.setEnabled(true);
                    Vista.editar.removeAll();
                    Vista.editar.add(PIP2.editarIpersonal);
                    Vista.editar.add(PIM2.editarImedica);
                    Vista.panelPrincipal.add(Vista.barra);
                    Vista.panelPrincipal.add(PIP2);
                    Vista.panelPrincipal.add(PIM2);
                    Vista.panelPrincipal.revalidate();
                    Vista.panelPrincipal.repaint();
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Inserte una edad correcta porfavor","ERROR",1);
                }

            break;
            case "cancelarPIP":
                PanelInformacionPersonal PIP3 = new PanelInformacionPersonal(PanelInformacionPersonal.nombre, PanelInformacionPersonal.rut, PanelInformacionPersonal.fecha, PanelInformacionPersonal.sistema, doctor, PanelInformacionPersonal.edad);
                PanelInformacionMedica PIM3 = new PanelInformacionMedica(PanelInformacionPersonal.nombre, PanelInformacionMedica.razon, PanelInformacionMedica.descripcion, PanelInformacionMedica.diagnostico,doctor);
                Vista.panelPrincipal.removeAll();
                Vista.panelPrincipal.add(Vista.barra);
                Vista.editar.removeAll();
                Vista.editar.add(PIP3.editarIpersonal);
                Vista.editar.add(PIM3.editarImedica);
                Vista.panelPrincipal.add(PIP3);
                Vista.panelPrincipal.add(PIM3);
                Vista.panelPrincipal.revalidate();
                Vista.panelPrincipal.repaint();
            break;
            case "imprimirPIP":
                if(PanelSecretaria.listaDoctores.getSelectedIndices().length > 1 || PanelSecretaria.listaPacientes.getSelectedIndices().length > 1 ){
                    JOptionPane.showMessageDialog(null, "Seleccion Multiple no valida", "ERROR", 3, null);
                }
                else if(PanelSecretaria.listaPacientes.getSelectedIndices().length == 0){
                    JOptionPane.showMessageDialog(null, "Seleccione un paciente porfavor", "ERROR", 3, null);
                }
                else{
                    try{
                        ImpresionCertificados imprime = new ImpresionCertificados(PanelSecretaria.listaPacientes.getSelectedValue().toString(),PanelSecretaria.listaDoctores.getSelectedValue().toString());
                    }catch(NullPointerException e){
                        JOptionPane.showMessageDialog(null,"Seleccion No Valida","Error", 0);
                    }
                }
            break;
            case "listoPIM":
                if(PanelInformacionMedica.fieldRazon.getText().isEmpty() || PanelInformacionMedica.fieldDescripcion.getText().isEmpty() || PanelInformacionMedica.fieldDiagnostico.getText().isEmpty()){
                    if(PanelInformacionMedica.fieldRazon.getText().isEmpty()){
                        PanelInformacionMedica.fieldRazon.setText("No existe Informacion");
                    }
                    
                    if(PanelInformacionMedica.fieldDescripcion.getText().isEmpty()){
                        PanelInformacionMedica.fieldDescripcion.setText("No existe Informacion");
                    }
                    
                    if(PanelInformacionMedica.fieldDiagnostico.getText().isEmpty()){
                        PanelInformacionMedica.fieldDiagnostico.setText("No existe Informacion");
                    }
                }
                
                PanelInformacionMedica.razon = PanelInformacionMedica.fieldRazon.getText();
                PanelInformacionMedica.descripcion = PanelInformacionMedica.fieldDescripcion.getText();
                PanelInformacionMedica.diagnostico = PanelInformacionMedica.fieldDiagnostico.getText();
                Modelo.CaracteristicasMedicasPaciente(PanelInformacionMedica.nombre, PanelInformacionMedica.razon, PanelInformacionMedica.descripcion, PanelInformacionMedica.diagnostico);
                JOptionPane.showMessageDialog(null,"Paciente Modificado");
                PanelInformacionPersonal PIP4 = new PanelInformacionPersonal(PanelInformacionPersonal.nombre, PanelInformacionPersonal.rut, PanelInformacionPersonal.fecha, PanelInformacionPersonal.sistema, doctor, PanelInformacionPersonal.edad);
                PanelInformacionMedica PIM4 = new PanelInformacionMedica(PanelInformacionMedica.nombre,PanelInformacionMedica.ObtenerRazon(PanelInformacionMedica.razon), PanelInformacionMedica.ObtenerDescripcion(PanelInformacionMedica.descripcion),PanelInformacionMedica.ObtenerDiagnostico(PanelInformacionMedica.diagnostico),doctor);
                Vista.panelPrincipal.removeAll();
                Vista.editar.setEnabled(true);
                Vista.editar.removeAll();
                Vista.editar.add(PIP4.editarIpersonal);
                Vista.editar.add(PIM4.editarImedica);
                Vista.panelPrincipal.add(Vista.barra);
                Vista.panelPrincipal.add(PIP4);
                Vista.panelPrincipal.add(PIM4);
                Vista.panelPrincipal.revalidate();
                Vista.panelPrincipal.repaint();
            break;
            case "cancelarPIM":
                PanelInformacionPersonal PIP5 = new PanelInformacionPersonal(PanelInformacionPersonal.nombre, PanelInformacionPersonal.rut, PanelInformacionPersonal.fecha, PanelInformacionPersonal.sistema, doctor, PanelInformacionPersonal.edad);
                PanelInformacionMedica PIM5 = new PanelInformacionMedica(PanelInformacionPersonal.nombre, PanelInformacionMedica.ObtenerRazon(PanelInformacionMedica.razon), PanelInformacionMedica.ObtenerDescripcion(PanelInformacionMedica.descripcion), PanelInformacionMedica.ObtenerDiagnostico(PanelInformacionMedica.diagnostico),doctor);
                Vista.panelPrincipal.removeAll();
                Vista.panelPrincipal.add(Vista.barra);
                Vista.editar.removeAll();
                Vista.editar.add(PIP5.editarIpersonal);
                Vista.editar.add(PIM5.editarImedica);
                Vista.panelPrincipal.add(PIP5);
                Vista.panelPrincipal.add(PIM5);
                Vista.panelPrincipal.revalidate();
                Vista.panelPrincipal.repaint();
            break;
            case "borrarPaciente":
                String nombre2 = BorrarPaciente.caja.getSelectedItem().toString();
                Modelo.BorrarPaciente(nombre2, doctor);
                JOptionPane.showMessageDialog(null,"Paciente Borrado");
            break;
            case "cargaTabla":
                String doctorSeleccionado = PanelSecretaria.seleccionDoctor.getSelectedItem().toString();
                Date fecha = PanelSecretaria.seleccionHora.getDate();           
                if(fecha == null){
                    JOptionPane.showMessageDialog(null, "Seleccione una fecha", "IMPORTANTE!", 2);
                }
                else{
                    if(PanelSecretaria.tablaDia == null){
                        String fecha2 = new java.sql.Date(fecha.getTime()).toString();
                        PanelSecretaria.auxiliarNombre = Modelo.ObtenerNombrePacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarMotivo = Modelo.ObtenerMotivoPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarDireccion = Modelo.ObtenerDireccionPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarHoras = Modelo.ObtenerHoraPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarDoctor = Modelo.ObtenerDoctorPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarPago = Modelo.ObtenerPagoPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarTelefono = Modelo.ObtenerTelefonoPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.IniciarJTable();
                        Vista.panelPrincipal.repaint();
                        Vista.panelPrincipal.revalidate();
                    }
                    else{
                        PanelSecretaria.tablaDia.removeAll();
                        String fecha2 = new java.sql.Date(fecha.getTime()).toString();
                        PanelSecretaria.auxiliarNombre = Modelo.ObtenerNombrePacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarMotivo = Modelo.ObtenerMotivoPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarDireccion = Modelo.ObtenerDireccionPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarHoras = Modelo.ObtenerHoraPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarDoctor = Modelo.ObtenerDoctorPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarPago = Modelo.ObtenerPagoPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.auxiliarTelefono = Modelo.ObtenerTelefonoPacienteSec(fecha2,doctorSeleccionado);
                        PanelSecretaria.IniciarJTable();
                        
                    }
                            

                }
            break;
            case "guardaTabla":
                PanelSecretaria.GuardarTabla();    
                String fecha2 = new java.sql.Date(PanelSecretaria.seleccionHora.getDate().getTime()).toString();
                Modelo.BorrarTabla(fecha2);
                for(int i = 0; i < PanelSecretaria.auxiliarHoras.size(); i++){
                    Modelo.GuardarTabla(PanelSecretaria.auxiliarNombre, PanelSecretaria.auxiliarMotivo, PanelSecretaria.auxiliarDireccion, PanelSecretaria.auxiliarHoras, PanelSecretaria.auxiliarDoctor, PanelSecretaria.auxiliarPago, PanelSecretaria.auxiliarTelefono,fecha2,i);
                }
                
                JOptionPane.showMessageDialog(null, "Tabla Guardada","Procedimiento Exitoso",1);
            break;
        }
    }

    
   /**
    * Metodo perteneciente a la interfaz WindowListener, Esta a la escucha para efectuar acciones en el cierre del programa
    * @param we parametro que contiene la informacion de la accion del cierre del programa
    */
    @Override
    public void windowClosing(WindowEvent we){
        String[] botones = {"Salir","Cerrar Sesion"};
        
        if(doctor == null && secretaria == null){
            System.exit(0);
        }
        
        int valor = JOptionPane.showOptionDialog(null, "Que desea Hacer?", "Eliga una opcion", 0, 0, null, botones,botones[0]);
        
        if(valor == 0){
            System.exit(0);
        }
        else if(valor == 1){
            Vista.panelPrincipal.removeAll();
            PanelCargaUsuario pcp2 = new PanelCargaUsuario();
            Vista.AgregarLogo("Medio Abajo");
            PanelSecretaria.panelNorte.removeAll();
            PanelSecretaria.panelSur.removeAll();
            PanelSecretaria.panelHoras.removeAll();
            PanelSecretaria.panelCertificados.removeAll();
            PanelSecretaria.panelcentroCertificados.removeAll();
            PanelSecretaria.listaDoctores.removeAll();
            PanelSecretaria.listaPacientes.removeAll();
            PanelSecretaria.labelSeleccion.setText("Selecciona un doctor");
            doctor = null;
            secretaria = null;
            Vista.panelPrincipal.add(pcp2);
            Vista.panelPrincipal.repaint();
            Vista.panelPrincipal.revalidate();
        }
    }

    
    /**
    * Metodo perteneciente a la interfaz WindowListener, Esta a la escucha para efectuar acciones en el inicio del programa
    * @param we parametro que contiene la informacion de la accion del inicio del programa
    */
    @Override
    public void windowOpened(WindowEvent we) {
    }

    /**
    * Metodo perteneciente a la interfaz WindowListener, Esta a la escucha para efectuar acciones despues del cierre del programa
    * @param we parametro que contiene la informacion de la accion del cierre del programa
    */
    
    @Override
    public void windowClosed(WindowEvent we) {
    }

     /**
    * Metodo perteneciente a la interfaz WindowListener, Esta a la escucha para efectuar acciones en la minimizacion del programa
    * @param we parametro que contiene la informacion de la accion de la minimizacion del programa
    */
    
    @Override
    public void windowIconified(WindowEvent we) {
    }
    
    /**
    * Metodo perteneciente a la interfaz WindowListener, Esta a la escucha para efectuar acciones en la maximizacion del programa
    * @param we parametro que contiene la informacion de la accion de maximizar del programa
    */
    
    @Override
    public void windowDeiconified(WindowEvent we) {
        if(doctor != null){
            JOptionPane.showMessageDialog(null, "Bienvenido Dr(a). "+doctor,"Hola Otra Vez",1);
        }
        
        if(secretaria != null){
            JOptionPane.showMessageDialog(null, "Hola de nuevo Sr(a) "+secretaria, "Hola Otra Vez",1);
        }
    }
    
     /**
    * Metodo perteneciente a la interfaz WindowListener, Esta a la escucha para efectuar acciones en la activacion del programa
    * @param we parametro que contiene la informacion de la accion de activacion del programa
    */
    
    
    @Override
    public void windowActivated(WindowEvent we) {
    }

    /**
    * Metodo perteneciente a la interfaz WindowListener, Esta a la escucha para efectuar acciones en la desactivacion del programa
    * @param we parametro que contiene la informacion de la accion de la desactivacion del programa
    */
    
    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    /**
    * Metodo perteneciente a la interfaz ListSelectionListener. Esta a la escucha ante cualquier cambio en su JTable asignado
    * @param lse parametro que contiene la informacion de la accion realizada en una tabla
    */
    
    @Override
    public void valueChanged(ListSelectionEvent lse){
        if(PanelSecretaria.listaPacientes.getSelectedValue() != null && PanelSecretaria.listaPacientes.isShowing()){
            PanelSecretaria.labelSeleccion.setText("Click en boton imprimir");
            PanelSecretaria.imprimir.setEnabled(true);
        }
        else if(PanelSecretaria.listaDoctores.isShowing() && !(PanelSecretaria.listaPacientes.isShowing())){
            try{
                PanelSecretaria.labelSeleccion.setText("Selecciona un Paciente");
                auxiliar = Modelo.CargarNombres(PanelSecretaria.listaDoctores.getSelectedValue().toString());
                PanelSecretaria.CrearJListpacientes(auxiliar);
                PanelSecretaria.panelcentroCertificados.repaint();
                PanelSecretaria.panelcentroCertificados.revalidate();
            }catch(NullPointerException e){}
        }
        else if(PanelSecretaria.listaDoctores.isShowing() && PanelSecretaria.listaPacientes.isShowing()){
            if(PanelSecretaria.listaPacientes.getSelectedValue() == null){
                PanelSecretaria.labelSeleccion.setText("Selecciona un paciente");
                PanelSecretaria.panelcentroCertificados.repaint();
                PanelSecretaria.panelcentroCertificados.revalidate();
            }
            
            try{
                auxiliar = Modelo.CargarNombres(PanelSecretaria.listaDoctores.getSelectedValue().toString());
            
                if(auxiliar != null){
                    PanelSecretaria.listaPacientes.setListData(auxiliar.toArray());
                    PanelSecretaria.imprimir.setEnabled(false);
                    PanelSecretaria.listaPacientes.repaint();
                    PanelSecretaria.listaPacientes.revalidate();
                }
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Ningun Doctor Seleccionado","Error", 0);
                PanelSecretaria.labelSeleccion.setText("Selecciona Doctor");
                PanelSecretaria.panelcentroCertificados.removeAll();
                PanelSecretaria.CrearJListdoctores(Modelo.ObtenerDoctores());
                PanelSecretaria.panelcentroCertificados.repaint();
                PanelSecretaria.panelcentroCertificados.revalidate();
            }   
        }
    }
}
