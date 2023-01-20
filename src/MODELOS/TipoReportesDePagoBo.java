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
public class TipoReportesDePagoBo {
    
    private Conexion conexion;
    
    public TipoReportesDePagoBo(){
        
        try {
            
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("DatosConexion.properties"));            
            this.conexion = new Conexion(HelperENCRYPT.Desencriptar(propiedades.getProperty("server")), HelperENCRYPT.Desencriptar(propiedades.getProperty("basededatos")), HelperENCRYPT.Desencriptar(propiedades.getProperty("user")), HelperENCRYPT.Desencriptar(propiedades.getProperty("password")));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoReportesDePagoBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<TipoReportesDePagoVo> listarTipoReportesDePago(){
        List<TipoReportesDePagoVo> lista = new ArrayList<TipoReportesDePagoVo>();
        try{
        
        String listarTipoReportesDePago = "CALL listarTipoReportesDePago()";
        CallableStatement callable = conexion.getConexion().prepareCall(listarTipoReportesDePago);
        
        ResultSet resultadoBusqueda = callable.executeQuery();
        while(resultadoBusqueda.next()){
            TipoReportesDePagoVo report = new TipoReportesDePagoVo();
            report.setId(resultadoBusqueda.getInt("id"));
            report.setNombre(resultadoBusqueda.getString("nombre")); 
            lista.add(report);
        }
        
        conexion.cerrarConexion();
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
