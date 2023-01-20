/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOS;

import CONEXION.Conexion;
import CONTROLADORES.AdminControlador;
import encrypt.HelperENCRYPT;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class AdminBo {//Clase donde se declararan los métodos de la entidad
    
    private Conexion conexion;

    public AdminBo() {
        
        try {
            
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("DatosConexion.properties"));            
            try {
                this.conexion = new Conexion(HelperENCRYPT.Desencriptar(propiedades.getProperty("server")), HelperENCRYPT.Desencriptar(propiedades.getProperty("basededatos")), HelperENCRYPT.Desencriptar(propiedades.getProperty("user")), HelperENCRYPT.Desencriptar(propiedades.getProperty("password")));
            } catch (Exception ex) {
                Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AdminVo listarAdmin(){
        AdminVo adminVo=null;
        
        try{
        
        String listarAdmin = "call listarAdmin()";
        CallableStatement callable = conexion.getConexion().prepareCall(listarAdmin);
        
        ResultSet resultadoBusqueda = callable.executeQuery();
        
        if(resultadoBusqueda.first()){
                              adminVo = new AdminVo();
                              adminVo.setId(resultadoBusqueda.getInt("idadministrador"));
                              adminVo.setNombre(resultadoBusqueda.getString("nombre"));
                              adminVo.setApellido(resultadoBusqueda.getString("apellido"));
                              adminVo.setIdentificador(resultadoBusqueda.getString("identificador"));
                              adminVo.setClave(resultadoBusqueda.getString("clave"));                              
                          }
                          
                          conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return adminVo;
    }
    
    public int actializarAdminClave(String clave){
        
        int resultado = 0;
        String sql = "Call actualizarAdminClave(?,?)";
        try {
            CallableStatement callable = conexion.getConexion().prepareCall(sql);
            callable.setInt(1, AdminControlador.id);
            callable.setString(2, clave);
            resultado = callable.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
    
    public int actializarAdmin(String nom,String ape,String user){
        
        int resultado = 0;
        String sql = "Call actualizarAdmin(?,?,?,?)";
        try {
            CallableStatement callable = conexion.getConexion().prepareCall(sql);
            callable.setInt(1, AdminControlador.id);
            callable.setString(2, nom);
            callable.setString(3, ape);
            callable.setString(4, user);
            resultado = callable.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
    
    public int ultimaVezAdmin(Date date, int id){
        int i=0;
        String verificar = "CALL ultimaVezAdmin(?,?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(verificar);
            stmt.setDate(1, date);
            stmt.setInt(2, id);
            i = stmt.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    
    
}
