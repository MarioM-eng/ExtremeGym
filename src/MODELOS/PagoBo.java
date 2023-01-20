/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOS;

import CONEXION.Conexion;
import CONTROLADORES.ClienteControlador;
import encrypt.HelperENCRYPT;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author mario
 */
public class PagoBo {
    
    private Conexion conexion;
    
    public PagoBo() {
        
        try {
            
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("DatosConexion.properties"));            
            this.conexion = new Conexion(HelperENCRYPT.Desencriptar(propiedades.getProperty("server")), HelperENCRYPT.Desencriptar(propiedades.getProperty("basededatos")), HelperENCRYPT.Desencriptar(propiedades.getProperty("user")), HelperENCRYPT.Desencriptar(propiedades.getProperty("password")));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PagoBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Método para buscar los clientes que ya han hecho pagos el día actual o que aun tengan una mensualidad
    public boolean validarCliente(int id, Date fecha){
        
        int cliente;
        try{
        
        String listarCliente = "call listarClientesNoPagar(?)";
        CallableStatement callable = conexion.getConexion().prepareCall(listarCliente);
        callable.setDate(1, fecha);
        ResultSet resultadoBusqueda = callable.executeQuery();
        
        while(resultadoBusqueda.next()){
            cliente = resultadoBusqueda.getInt("cliente_id");
            if(cliente == id){
                return false;
            }
            
        }
                          
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean registrarPago(int idad,int idcli,int val,Date fechai, Date fechaf, int tpago){
        int i;
        String pago = "CALL agregarPago(?,?,?,?,?,?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(pago);
            stmt.setInt(1, idad);
            stmt.setInt(2, idcli);
            stmt.setInt(3, val);
            stmt.setDate(4, fechai);
            stmt.setDate(5, fechaf);
            stmt.setInt(6, tpago);
            i = stmt.executeUpdate();
            
            conexion.cerrarConexion();
            
            return i != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    //Método para buscar todos los clientes
    public List<PagoVo> listarPagoEnProceso(){
        List<PagoVo> listaCliente = new ArrayList<PagoVo>();
        
        try{
        
        String listarCliente = "call listarPagoEnProceso()";
        CallableStatement callable = conexion.getConexion().prepareCall(listarCliente);
        
        ResultSet resultadoBusqueda = callable.executeQuery();
        
        while(resultadoBusqueda.next()){
            PagoVo pago = new PagoVo();
            pago.setId(resultadoBusqueda.getInt("idpago"));
            pago.setIdAdministrador(resultadoBusqueda.getInt("administrador_idadministrador"));
            pago.setIdCliente(resultadoBusqueda.getInt("cliente_id"));
            pago.setValor(resultadoBusqueda.getString("valor"));
            pago.setFechaInicial(resultadoBusqueda.getDate("fechainicial"));
            pago.setFechaFinal(resultadoBusqueda.getDate("fechafinal"));
            pago.setTipoPago(resultadoBusqueda.getInt("tipodepago_idtipodepago"));
            pago.setEstado(resultadoBusqueda.getInt("estado"));
            
            listaCliente.add(pago);
            
        }
                          
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaCliente;
    }
    
    public void LlenarJtable(List<PagoVo> listado,JTable tabla, String[] nombreColumnas){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        
        for (int i = 0; i < nombreColumnas.length; i++) {
            modeloTabla.addColumn(nombreColumnas[i]);
        }
        
        for (PagoVo pago : listado) {
            boolean seleccionar = false;
            int id = pago.getId();
            int id_Clinte = pago.getIdCliente();
            ClienteVo esta = ClienteControlador.buscarUnoCliente(pago.getIdCliente());//ejecutamos el metodo que nos trae el cliente por medio del id del cliente que tiene registrado el pago
            String nombre = esta.getNombre();
            String apellido = esta.getApellido();
            java.sql.Date fechainicial = pago.getFechaInicial();
            java.sql.Date fechafinal = pago.getFechaFinal();
            
            Object[] fila = {seleccionar,id, id_Clinte, nombre, apellido, fechainicial,fechafinal};
            
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
    
    //Método para buscar todos los pagos vencidos
    public List<PagoVo> pagosVencidos(){
        List<PagoVo> listaCliente = new ArrayList<PagoVo>();
        
        try{
        
        String pagosV = "call pagosVencidos()";
        CallableStatement callable = conexion.getConexion().prepareCall(pagosV);
        ResultSet resultadoBusqueda = callable.executeQuery();
        
        while(resultadoBusqueda.next()){
            PagoVo pago = new PagoVo();
            pago.setId(resultadoBusqueda.getInt("idpago"));
            pago.setIdAdministrador(resultadoBusqueda.getInt("administrador_idadministrador"));
            pago.setIdCliente(resultadoBusqueda.getInt("cliente_id"));
            pago.setValor(resultadoBusqueda.getString("valor"));
            pago.setFechaInicial(resultadoBusqueda.getDate("fechainicial"));
            pago.setFechaFinal(resultadoBusqueda.getDate("fechafinal"));
            pago.setTipoPago(resultadoBusqueda.getInt("tipodepago_idtipodepago"));
            pago.setEstado(resultadoBusqueda.getInt("estado"));
            
            listaCliente.add(pago);
            
        }
                          
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaCliente;
    }
    
    public boolean marcarVisto(int id){
        int i;
        String estadoPago = "CALL marcarPagoVencidoComoVisto(?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(estadoPago);
            stmt.setInt(1, id);
            i = stmt.executeUpdate();
            
            conexion.cerrarConexion();
            
            return i != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    public boolean estadoPago(Date fecha){
        int i;
        String estadoPago = "CALL estadoPago(?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(estadoPago);
            stmt.setDate(1, fecha);
            i = stmt.executeUpdate();
            
            conexion.cerrarConexion();
            
            return i != 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    
    
}
