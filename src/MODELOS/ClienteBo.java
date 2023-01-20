/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOS;

import CONEXION.Conexion;
import CONTROLADORES.EstadoClienteControlador;
import encrypt.HelperENCRYPT;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author mario
 */
public class ClienteBo {

    private Conexion conexion;

    public ClienteBo() throws Exception {
        
        try {
            
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("DatosConexion.properties"));            
            this.conexion = new Conexion(HelperENCRYPT.Desencriptar(propiedades.getProperty("server")), HelperENCRYPT.Desencriptar(propiedades.getProperty("basededatos")), HelperENCRYPT.Desencriptar(propiedades.getProperty("user")), HelperENCRYPT.Desencriptar(propiedades.getProperty("password")));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int verificarCel(String cel){
        int i=0;
        String verificar = "CALL verificarCel(?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(verificar);
            stmt.setString(1, cel);
            i = stmt.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    public int agregar(ClienteVo cliente){
        int resul = 0;
        
        try {
            String insertarCliente = "CALL agregarCliente(?,?,?,?,?,?)";
            PreparedStatement sentenciaPreparada = conexion.getConexion().prepareStatement(insertarCliente);
            
                              sentenciaPreparada.setString(1, cliente.getNombre());
                              sentenciaPreparada.setString(2, cliente.getApellido());
                              sentenciaPreparada.setString(3, cliente.getCelular());
                              sentenciaPreparada.setInt(4, cliente.getEstado());
                              sentenciaPreparada.setDate(5, cliente.getRegis());
                              sentenciaPreparada.setInt(6, cliente.getAdmin());
                              
                       resul = sentenciaPreparada.executeUpdate();//devuelve un 1 si la ejecucion fue exitosa
                       
                       conexion.cerrarConexion();
               

        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resul;
    }
    
    public List<ClienteVo> buscar(String valor){//Método para buscar cliente según el nombre, apellido o número de celular
        List<ClienteVo> lista = new ArrayList<ClienteVo>();
        String buscarCliente = "CALL buscar(?)";
        for(int i = 0; i < valor.length(); i++){
            if(valor.charAt(i) == KeyEvent.VK_SPACE){
                buscarCliente = "call buscarPorNombreYapellido(?)";
            }
        }
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(buscarCliente);
            stmt.setString(1, valor);
            
            ResultSet resultadoBusqueda = stmt.executeQuery();
        while(resultadoBusqueda.next()){
            ClienteVo cliente = new ClienteVo();
            cliente.setId(resultadoBusqueda.getString("id"));
            cliente.setNombre(resultadoBusqueda.getString("nombre"));
            cliente.setApellido(resultadoBusqueda.getString("apellido"));
            cliente.setCelular(resultadoBusqueda.getString("celular"));
            cliente.setEstado(resultadoBusqueda.getInt("estado"));
            cliente.setRegis(resultadoBusqueda.getDate("registrado_en"));
            cliente.setAdmin(resultadoBusqueda.getInt("administrador_idadministrador"));
            lista.add(cliente);
            
        }
        
        conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    //Método para buscar todos los clientes
    public List<ClienteVo> listarCliente() throws SQLException{
        List<ClienteVo> listaCliente = new ArrayList<ClienteVo>();
        
        try{
        
        String listarCliente = "call listarCliente()";
        CallableStatement callable = conexion.getConexion().prepareCall(listarCliente);
        
        ResultSet resultadoBusqueda = callable.executeQuery();
        
        while(resultadoBusqueda.next()){
            ClienteVo cliente = new ClienteVo();
            cliente.setId(resultadoBusqueda.getString("id"));
            cliente.setNombre(resultadoBusqueda.getString("nombre"));
            cliente.setApellido(resultadoBusqueda.getString("apellido"));
            cliente.setCelular(resultadoBusqueda.getString("celular"));
            cliente.setEstado(resultadoBusqueda.getInt("estado"));
            cliente.setRegis(resultadoBusqueda.getDate("registrado_en"));
            cliente.setAdmin(resultadoBusqueda.getInt("administrador_idadministrador"));
            listaCliente.add(cliente);
            
        }
                          
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaCliente;
    }
    
    public void LlenarJtable(List<ClienteVo> listado,JTable tabla, String[] nombreColumnas){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        
        for (int i = 0; i < nombreColumnas.length; i++) {
            modeloTabla.addColumn(nombreColumnas[i]);
        }
        
        for (ClienteVo ClienteVo : listado) {
            boolean eliminar = false;
            String id = ClienteVo.getId();
            String nombre = ClienteVo.getNombre();
            String apellido = ClienteVo.getApellido();
            String celular = ClienteVo.getCelular();
            EstadoClienteVo esta = EstadoClienteControlador.estado(ClienteVo.getEstado());//ejecutamos el metodo que nos trae el estado por medio del id de estado que tiene registrado el cliente
            String estado = esta.getEstado();
            java.sql.Date regis = ClienteVo.getRegis();
            
            Object[] fila = {eliminar,id, nombre, apellido, celular,estado,regis};
            
            modeloTabla.addRow(fila);
        }
        
        tabla.setModel(modeloTabla);
        addCheckBox(0,tabla);//Agregamos el checkbox
    }
    
    public void addCheckBox(int column, JTable table){//Método para agregar checkBox a la tabla
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setWidth(20);
        tc.setMaxWidth(20);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }
    
    public boolean eliminar(int id){
        int i;
        String eliminarCliente = "CALL eliminarCliente(?,?)";//Recibe el id y el estado del cliente(Inactivo)
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(eliminarCliente);
            stmt.setInt(1, id);
            stmt.setInt(2, 2);
            i = stmt.executeUpdate();
            
            conexion.cerrarConexion();
            
            return i != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    public ClienteVo buscarUno(int valor){
        ClienteVo cliente = null;
        String buscarCliente = "CALL buscarunoCliente(?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(buscarCliente);
            stmt.setInt(1, valor);
            
            ResultSet resultadoBusqueda = stmt.executeQuery();
            if(resultadoBusqueda.first()){
                cliente = new ClienteVo();
                cliente.setId(resultadoBusqueda.getString("id"));
                cliente.setNombre(resultadoBusqueda.getString("nombre"));
                cliente.setApellido(resultadoBusqueda.getString("apellido"));
                cliente.setCelular(resultadoBusqueda.getString("celular"));
                cliente.setEstado(resultadoBusqueda.getInt("estado"));
                cliente.setRegis(resultadoBusqueda.getDate("registrado_en"));
                cliente.setAdmin(resultadoBusqueda.getInt("administrador_idadministrador"));
            }
        
        conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public int modificar(ClienteVo cliente){
        
        int resul = 0;
        
        try {
            String modificarCliente = "CALL modificarCliente(?,?,?,?,?)";
            CallableStatement stmt = conexion.getConexion().prepareCall(modificarCliente);
            
                              stmt.setString(1, cliente.getId());
                              stmt.setString(2, cliente.getNombre());
                              stmt.setString(3, cliente.getApellido());
                              stmt.setString(4, cliente.getCelular());
                              stmt.setInt(5, cliente.getEstado());
                              
                       resul = stmt.executeUpdate();//devuelve un 1 si la ejecucion fue exitosa
                       
                       conexion.cerrarConexion();
               

        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resul;
        
    }
    
    
    
    
    
}
