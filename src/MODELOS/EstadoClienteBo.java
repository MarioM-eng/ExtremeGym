/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOS;

import CONEXION.Conexion;
import encrypt.HelperENCRYPT;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class EstadoClienteBo {
    
    private Conexion conexion;
    
    public EstadoClienteBo(){
        
        
            
        try {
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("DatosConexion.properties"));            
            this.conexion = new Conexion(HelperENCRYPT.Desencriptar(propiedades.getProperty("server")), HelperENCRYPT.Desencriptar(propiedades.getProperty("basededatos")), HelperENCRYPT.Desencriptar(propiedades.getProperty("user")), HelperENCRYPT.Desencriptar(propiedades.getProperty("password")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EstadoClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EstadoClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EstadoClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    
    }
    
    
    
    public List<EstadoClienteVo> listarEstadoCliente(){//
        List<EstadoClienteVo> lista = new ArrayList<EstadoClienteVo>();
        
        try{
        
        String listarTipo = "CALL listarEstadoCliente()";
        CallableStatement callable = conexion.getConexion().prepareCall(listarTipo);
        
        ResultSet resultadoBusqueda = callable.executeQuery();
        while(resultadoBusqueda.next()){
            EstadoClienteVo estado = new EstadoClienteVo();
            estado.setId(resultadoBusqueda.getInt("id"));
            estado.setEstado(resultadoBusqueda.getString("estado")); 
            lista.add(estado);
            
        }
        
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public EstadoClienteVo listarEstadoClientePorId(int id){//
        EstadoClienteVo estado = null;
        try{
        
        String listarTipo = "CALL listarEstadoClientePorId(?)";
        CallableStatement callable = conexion.getConexion().prepareCall(listarTipo);
        callable.setInt(1, id);
        ResultSet resultadoBusqueda = callable.executeQuery();
        if(resultadoBusqueda.first()){
            estado = new EstadoClienteVo();
            estado.setId(resultadoBusqueda.getInt("id"));
            estado.setEstado(resultadoBusqueda.getString("estado")); 
        }
        
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    
}
