
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADORES;

import MODELOS.ClienteBo;
import MODELOS.ClienteVo;
import MODELOS.EstadoClienteVo;
import VISTAS.VistaPago;
import VISTAS.VistaCliente;
import Validar.Validar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author mario
 */
public class ClienteControlador {
    
    private VistaCliente vista;
    private VistaPago vistac;
    private String valor = "";//Variable para almacenar el valor de la busqueda del cliente
    private JTable tabla; //Variable para almacenar la tabla

    private ClienteBo modelo;
    
    public ClienteControlador(VistaCliente vista, ClienteBo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        //Botón para Registrar
        vista.jBregis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aioe) {
                boolean nombre = Validar.validarJText(vista.jTnombre,vista.jLverifiNom,30);
                boolean apellido = Validar.validarJText(vista.jTapellido,vista.jLverifiAp,30);
                boolean celular = Validar.validarCelular(vista.jTcel,vista.jLverifiCel,10);
                if(nombre && celular) registrarCliente();
                
            }
        }); 
        
        //Botones para Buscar
        vista.jBbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aioe) {
                valor = vista.jTbusval.getText();
                tabla = vista.jTTableclientes;
                consultarCliente();
            }
        }); 
        vista.jBelim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aioe) {
                int mensaje = JOptionPane.showConfirmDialog(vista, "¿Estás seguro que deseas eliminar este(os) cliente(s)?","¡Advertencia!",JOptionPane.WARNING_MESSAGE);
                if(mensaje == 0){
                    eliminarCliente();
                }
            }
        }); 
        vista.jBtodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aioe) {
                try {
                    listaCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        
        //Botones para modificar
        vista.jBbuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aioe) {
                buscarUnoCliente();
            }
        }); 
        vista.jBmodifi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aioe) {
                boolean nombre = Validar.validarJText(vista.jTnombreMod,vista.jLverifiNomMod,30);
                boolean apellido = Validar.validarJText(vista.jTapellidoMod,vista.jLverifiApMod,30);
                if(nombre && apellido){
                    int mensaje = JOptionPane.showConfirmDialog(vista, "¿Estás seguro que deseas modificar los datos?","¡Advertencia!",JOptionPane.WARNING_MESSAGE);
                    if(mensaje == 0){
                        modificarCliente();
                    }
                }
            }
        }); 
        
        //AQUÏ COMIENZAN LAS VALIDACIONES
        //Validaciones con Listener
        Validar.validarConListener(vista.jTbusval,null,30);
        Validar.validarConListener(vista.jTnombre,vista.jLverifiNom,30);
        Validar.validarConListener(vista.jTapellido,vista.jLverifiAp,30);
        Validar.validarConListener(vista.jTcel,vista.jLverifiCel,10);
        Validar.validarConListener(vista.jTnombreMod,vista.jLverifiNomMod,30);
        Validar.validarConListener(vista.jTapellidoMod,vista.jLverifiApMod,30);
        
        //FIN DE LAS VALIDACIONES
        
    }
    
    public ClienteControlador(VistaPago vista, ClienteBo modelo){
        this.vistac = vista;
        this.modelo = modelo;
        
        vista.jBbuscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                valor = vista.jTbusval.getText();
                tabla = vista.jTTableclientes;
                consultarCliente();
            }
            
        });
        
    }
    
    public Date fechaActual(){
        Calendar cal = Calendar.getInstance();
        java.util.Date date = cal.getTime();//Se obtiene la fecha actual
        long d = date.getTime();//Se almacena en una variable tipo long para ser utilizada en la libreria Date de sql 
        Date fecha = new Date(d);
        return fecha;
    }
    
    
    public void registrarCliente(){
        
        
        String nombre = vista.jTnombre.getText();//
        String apellido = vista.jTapellido.getText();//
        String celular = vista.jTcel.getText();
        int admin = AdminControlador.id;//Desde el controlador de administrador, con su variable static, obtenermos el id del administrador
        int resultado = 0; //variable para verificar si se actualizó una linea o no
        if(nombre.equals("") || apellido.equals("")){
            JOptionPane.showMessageDialog(vista, "Los caracteres marcados con * con obligatorios","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else {
            if(this.modelo.verificarCel(celular) == 0){//Este metodo devuelve 1 si no hubo errores con la verificacion
                ClienteVo cliente = new ClienteVo("",nombre,apellido,celular,1,fechaActual(),admin);
                resultado = this.modelo.agregar(cliente);//Este metodo devuelve 1 si no hubo errores con la consulta
            }else{
                JOptionPane.showMessageDialog(vista, "El número de celular ingresado ya existe","¡Error!",JOptionPane.ERROR_MESSAGE);
            }
                   
        }
        
        
       
       if(resultado > 0){//Si la variable resultado cambia a 1, es porque se insertó una fila
           JOptionPane.showMessageDialog(vista, "Inserción Exitosa");
       }else{
           JOptionPane.showMessageDialog(vista, "Inserción NO Exitosa","Advertencia",JOptionPane.WARNING_MESSAGE);
       }
        
    }
    
    public boolean consultarCliente(){
        List<ClienteVo> listaResult;
        if(valor.equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar una letra, nombre, apellido o número de celular"); 
            return false;
        }
        listaResult = this.modelo.buscar(valor);
        
        String[] columnas = new String[7];
              
                columnas[0] = "";
                columnas[1] = "Id";
                columnas[2] = "Nombre";
                columnas[3] = "Apellido";
                columnas[4] = "Celular";
                columnas[5] = "Estado";
                columnas[6] = "Fecha de registro";
                
                if(listaResult.size() > 0){
                    this.modelo.LlenarJtable(listaResult, tabla, columnas);
                }else{
                     JOptionPane.showMessageDialog(null, "No se encontraron resultados");
                }
        return true;
    }
    
    public boolean listaCliente() throws SQLException{//Lista todos los clientes 
        List<ClienteVo> listaClientesTodos;
        listaClientesTodos = this.modelo.listarCliente();
        
        String[] columnas = new String[7];
              
                columnas[0] = "";
                columnas[1] = "Id";
                columnas[2] = "Nombre";
                columnas[3] = "Apellido";
                columnas[4] = "Celular";
                columnas[5] = "Estado";
                columnas[6] = "Fecha de registro";
                
                if(listaClientesTodos.size() > 0){
                    this.modelo.LlenarJtable(listaClientesTodos, vista.jTTableclientes, columnas);
                }else{
                     JOptionPane.showMessageDialog(vista, "No se encontraron clientes en la base de datos","Advertencia",JOptionPane.WARNING_MESSAGE);
                }
        return true;
    }
    
    public boolean estaVacio(JTable tabla){//Verifica si el jTable está vacio o no
        //System.out.println(tabla.getValueAt(tabla.getRowCount(), tabla.getRowCount()));
                return tabla.getModel().getRowCount() > 0;
        
    }
    
    public boolean estaSeleccionado(int row, int column, JTable tabla){//Método para saber si una fila está seleccionada con el checkbox
        return (boolean)tabla.getValueAt(row, column) != false;
        
    }
    
    public void eliminarCliente(){//Elimina uno o varios clientes
        boolean resultado = false;
        int verificar=0;
        if(estaVacio(this.vista.jTTableclientes)){
            for(int i=0; i<this.vista.jTTableclientes.getRowCount();i++){
                if(estaSeleccionado(i,0,this.vista.jTTableclientes)){
                    verificar++;
                    System.out.println(estaSeleccionado(i,0,this.vista.jTTableclientes));
                    System.out.println(Integer.parseInt(this.vista.jTTableclientes.getValueAt(i, 1).toString()));
                    resultado = this.modelo.eliminar(Integer.parseInt(this.vista.jTTableclientes.getValueAt(i, 1).toString()));
                }
            }
            if(verificar==0){
                JOptionPane.showMessageDialog(vista, "Seleccione la(s) columna(s) que desea eliminar");
            }
        }else{
            JOptionPane.showMessageDialog(vista, "No hay campos en la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
        if(resultado){
            try {
                JOptionPane.showMessageDialog(vista, "Usuario(s) eliminado con exito");
                listaCliente();//Lista todos los clientes luego de eliminar 1
            } catch (SQLException ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Error al eliminar usuario(s)","¡Error!",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static ClienteVo buscarUnoCliente(int id){//Para obtener el nombre de un cliente desde otro controlador
        ClienteBo mod = null;
        try {
            mod = new ClienteBo();
        } catch (Exception ex) {
            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClienteVo result = mod.buscarUno(id);
        
        if(result==null){
            JOptionPane.showMessageDialog(null, "Este usuario no existe en la base de datos","Advertencia",JOptionPane.WARNING_MESSAGE);
            
        }
        return result;
    }
    
    private ClienteVo Result;//Cliente encontrado
    public boolean buscarUnoCliente(){
        String valor = this.vista.jTbuscar.getText();
        if(valor.equals("")){
            JOptionPane.showMessageDialog(vista, "Debe ingresar id del cliente"); 
            return false;
        }
        Result = this.modelo.buscarUno(Integer.parseInt(valor));
        
        if(Result==null){
            JOptionPane.showMessageDialog(vista, "Este usuario no existe en la base de datos","Advertencia",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        this.vista.jTnombreMod.setText(Result.getNombre());
        this.vista.jTapellidoMod.setText(Result.getApellido());
        this.vista.jTcelMod.setText(Result.getCelular());
        this.vista.jCestadoMod.setSelectedIndex(Result.getEstado()-1);
        
        return true;
    }
    
    public void modificarCliente(){
        
        String id = String.valueOf(Result.getId());
        String nombre = this.vista.jTnombreMod.getText();
        String apellido = this.vista.jTapellidoMod.getText();
        String celular = this.vista.jTcelMod.getText();
        EstadoClienteVo estad = (EstadoClienteVo)vista.jCestadoMod.getSelectedItem();
        int estado = estad.getId();
        int mod = 0;
        
        if(nombre.equals(Result.getNombre()) && 
                apellido.equals(Result.getApellido()) && 
                    celular.equals(Result.getCelular()) && 
                        this.vista.jCestadoMod.getSelectedIndex() == Result.getEstado()-1){
            JOptionPane.showMessageDialog(vista, "No hubo cambios","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            if(nombre.equals("") || apellido.equals("") || celular.equals("")){
                JOptionPane.showMessageDialog(vista, "Los caracteres marcados con * con obligatorios","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else{
                ClienteVo cliente = new ClienteVo(id,nombre,apellido,celular,estado,Result.getRegis(),Result.getAdmin());
                mod = this.modelo.modificar(cliente);
                
                if(mod==1){
                    JOptionPane.showMessageDialog(vista, "Se modificó el usuario con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Error al modificar","¡Error!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }
    
    
    
}
