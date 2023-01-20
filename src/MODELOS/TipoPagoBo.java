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
public class TipoPagoBo {
    
    private Conexion conexion;

    public TipoPagoBo() {
        
        try {
            
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("DatosConexion.properties"));            
            this.conexion = new Conexion(HelperENCRYPT.Desencriptar(propiedades.getProperty("server")), HelperENCRYPT.Desencriptar(propiedades.getProperty("basededatos")), HelperENCRYPT.Desencriptar(propiedades.getProperty("user")), HelperENCRYPT.Desencriptar(propiedades.getProperty("password")));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoPagoBo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TipoPagoVo> listarTipoPago(){
        List<TipoPagoVo> lista = new ArrayList<TipoPagoVo>();
        try{
        
        String listarTipo = "CALL listarTipoPago()";
        CallableStatement callable = conexion.getConexion().prepareCall(listarTipo);
        
        ResultSet resultadoBusqueda = callable.executeQuery();
        while(resultadoBusqueda.next()){
            TipoPagoVo pago = new TipoPagoVo();
            pago.setId(resultadoBusqueda.getInt("idtipodepago"));
            pago.setNombre(resultadoBusqueda.getString("nombre")); 
            pago.setValor(resultadoBusqueda.getInt("valor"));
            lista.add(pago);
        }
        
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public int setValor(TipoPagoVo p){
        
        int resultado = 0;
        
        String cambiarValor = "CALL modificarValorTpago(?,?)";
        
        try {
            
            CallableStatement stmt = conexion.getConexion().prepareCall(cambiarValor);
            stmt.setInt(1, p.getId());
            stmt.setInt(2, p.getValor());
            
            
            resultado = stmt.executeUpdate();
            
            conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            Logger.getLogger(TipoPagoBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
        
    }
    
}
