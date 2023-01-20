/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADORES;

import MODELOS.AdminBo;
import VISTAS.VistaAdmin;
import VISTAS.VistaInicio;
import VISTAS.VistaLogin;
import Validar.Validar;
import encrypt.HelperENCRYPT;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class AdminControlador {//Clase donde se une la parte visible con la lógca del programa.
    
    private VistaLogin VistaL;//Variable de tipo VistaLogin.
    private VistaAdmin vista;//Variable de tipo VistaAdmin.
    private AdminBo modeloBo;//VAriable de tipo ModeloBo--Contiene las funciones declaradas en ella una vez instanciada
    public static int id;
    public static Date ultVez;

    public AdminControlador(VistaLogin VistaL, AdminBo modeloBo) {//Constructor- Se le pasan la vista y el modelo como parametro
        this.VistaL = VistaL;
        this.modeloBo = modeloBo;
        //Listener cuando los campos estás llenos satisfactoriamente
        ActionListener fieldValid = new ActionListener() {//ActionListener es una Interface, por lo que no se puede instanciar un objeto de ello
            //Implementamos los metodos, abstractos, de la interface ActionListener.
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean user = Validar.validarJTextAdmin(VistaL.jTuser,VistaL.jLverificarUser);
                boolean clave = Validar.validarJPassword(VistaL.jPass,VistaL.jLverificarClave,16);
                if(user && clave){
                    try {
                        ingresar();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
         };
        //Botón Ingresar
        VistaL.jBing.addActionListener(fieldValid);//Agregamos un oyente a el botón de ingresar de VistaLogin.Este método recibe un objeto de tipo ActionListener
        
        //Validaciones con listener
        Validar.validarConListener(this.VistaL.jTuser, this.VistaL.jLverificarUser, 16);
        Validar.validarConListener(this.VistaL.jPass, this.VistaL.jLverificarClave, 16);
    }

    public AdminControlador(VistaAdmin vista, AdminBo modeloBo) {
        this.vista = vista;
        this.modeloBo = modeloBo;
        datos();
        this.vista.jBpersonales.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                datos();
            }
            
        });
        
        vista.jBactClave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean clave = Validar.validarJPassword(vista.jPassclave,vista.jLverifiClave,16);
                if(clave){
                    
                    int mensaje = JOptionPane.showConfirmDialog(vista, "¿Estás seguro que deseas cambiar tu contraseña?","¡Advertencia!",JOptionPane.WARNING_MESSAGE);
                    if(mensaje == 0){
                        actualizarClave();
                    }
                    
                }
            }
            
        });
        Validar.validarConListener(vista.jPassclave, vista.jLverifiClave, 12);
        
        vista.jBact.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean nombre = Validar.validarJText(vista.jTnombre,vista.jLverifiNom,16);
                boolean apellido = Validar.validarJText(vista.jTapellido,vista.jLverifiAp,16);
                boolean user = Validar.validarJTextAdmin(vista.jTuser,vista.jLverifiUsu);
                if(nombre && apellido && user){
                    
                    int mensaje = JOptionPane.showConfirmDialog(vista, "¿Estás seguro que deseas cambiar tus datos?","¡Advertencia!",JOptionPane.WARNING_MESSAGE);
                    if(mensaje == 0){
                        actualizar();
                    }
                    
                }
            }
            
        });
        Validar.validarConListener(vista.jTnombre, vista.jLverifiNom, 16);
        Validar.validarConListener(vista.jTapellido, vista.jLverifiAp, 16);
        Validar.validarConListener(vista.jTuser, vista.jLverifiUsu, 16);
    }
    
    
    public AdminControlador(){
        
    }
    
    public void ingresar() throws SQLException{
        //this.VistaL.jTuser.setText("admin");
        //this.VistaL.jPass.setText("admin");
        
        if(this.VistaL.jTuser.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese su usuario");
        }else if(this.VistaL.jPass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese contraseña");
        }else{
            if(this.modeloBo.listarAdmin().getIdentificador().equals(this.VistaL.jTuser.getText())){//Si el identificador del admin es igual a la cadena ingresada en la vista
                if(this.modeloBo.listarAdmin().getClave().equals(HelperENCRYPT.Encriptar(this.VistaL.jPass.getText()))){//Si la clave es igual a la ingresada en la vista
                    id=modeloBo.listarAdmin().getId();
                    ultVez = modeloBo.listarAdmin().getUltimaVez();
                    VistaInicio jFrame = new VistaInicio();//Instanciamos un objeto de la clase VistaInicio.
                    jFrame.setVisible(true);//La mostramos en pantalla
                    jFrame.setLocationRelativeTo(null);//Centramos en pantala
                    ultimaVez();
                    PagoControlador.estado();//Ejecutamos método static que actualizará el estado de los pagos
                    PagoControlador.notificaciónPagosVencidos();
                    this.VistaL.setVisible(false);//Ocultamos la vista en la que estabamos(VistaLogin).
                }else{
                    JOptionPane.showMessageDialog(null, "Contrasena no coincide con usuario");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Nombre de usuario no encontrado ");
            }
        }
        
        
        
        
        
    }
    
   public void ultimaVez(){
       int resultado=0;
       Calendar ca = Calendar.getInstance();
        java.util.Date date = ca.getTime();
        long d = date.getTime();
        Date fecha = new Date(d);
        resultado=this.modeloBo.ultimaVezAdmin(fecha,id);
        
        if(resultado==1){
            System.out.println("Ultima vez del administrador "+this.modeloBo.listarAdmin().getNombre()+": "+fecha);
        }else{
            JOptionPane.showMessageDialog(vista, "Error al actualizar ultima vez del administrador","¡Error!",JOptionPane.ERROR_MESSAGE);
        }
        
   }
    
    public void datos(){//Llena los campos de la vistaAdmin con los datos de la bd
        this.vista.jTnombre.setText(this.modeloBo.listarAdmin().getNombre());
        this.vista.jTapellido.setText(this.modeloBo.listarAdmin().getApellido());
        this.vista.jTuser.setText(this.modeloBo.listarAdmin().getIdentificador());
    }
    
    public void actualizarClave(){
        
        int resul=0;
        if(this.vista.jPassclave.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese nueva contraseña");
        }else{
            String clave = HelperENCRYPT.Encriptar(vista.jPassclave.getText());
            resul = this.modeloBo.actializarAdminClave(clave);
        }
        
        if(resul==1){
            JOptionPane.showMessageDialog(vista, "Actualización de contraseña exitosa");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al actualizar contraseña","¡Error!",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void actualizar(){
        
        int resul=0;
        String nombre = this.vista.jTnombre.getText();
        String apellido = this.vista.jTapellido.getText();
        String usuario = this.vista.jTuser.getText();
        
        if(nombre.equals("") || apellido.equals("") || usuario.equals("")){
            JOptionPane.showMessageDialog(null, "Campo vacío");
        }else{
            if(nombre.equals(this.modeloBo.listarAdmin().getNombre()) &&
                    apellido.equals(this.modeloBo.listarAdmin().getApellido()) &&
                    usuario.equals(this.modeloBo.listarAdmin().getIdentificador())){
                JOptionPane.showMessageDialog(vista, "No se detectaron cambios cambios","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else{
                resul = this.modeloBo.actializarAdmin(nombre,apellido,usuario);
            }
        }
        
        if(resul==1){
            JOptionPane.showMessageDialog(vista, "Actualización exitosa");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al actualizar","¡Error!",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    
}
