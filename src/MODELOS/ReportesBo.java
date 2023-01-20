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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mario
 */
public class ReportesBo {
    
    private Conexion conexion;

    public ReportesBo() {
        
        try {
            
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("DatosConexion.properties"));            
            this.conexion = new Conexion(HelperENCRYPT.Desencriptar(propiedades.getProperty("server")), HelperENCRYPT.Desencriptar(propiedades.getProperty("basededatos")), HelperENCRYPT.Desencriptar(propiedades.getProperty("user")), HelperENCRYPT.Desencriptar(propiedades.getProperty("password")));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminBo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ReportesBo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<ReportesVo> reporte(Date fecha){
        List<ReportesVo> lista = new ArrayList<ReportesVo>();
        String pago = "CALL ReportePagoDiario(?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(pago);
            stmt.setDate(1, fecha);
            ResultSet resultadoBusqueda = stmt.executeQuery();
            while(resultadoBusqueda.next()){
                ReportesVo reporte = new ReportesVo();
                reporte.setAdminPago(resultadoBusqueda.getString("Admin."));
                reporte.setNombreCliente(resultadoBusqueda.getString("nombreCliente"));
                reporte.setApellidoCliente(resultadoBusqueda.getString("apellidoCliente"));
                reporte.setValor(resultadoBusqueda.getString("valor"));
                lista.add(reporte);
            
            }
            
            conexion.cerrarConexion();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }
    
    public List<ReportesVo> reporteAnioMes(String anioMes){
        
        List<ReportesVo> lista = new ArrayList<ReportesVo>();
        String pago = "CALL reportePagoAnioMes(?)";
        try {
            CallableStatement stmt = conexion.getConexion().prepareCall(pago);
            stmt.setString(1, anioMes);
            ResultSet resultadoBusqueda = stmt.executeQuery();
            while(resultadoBusqueda.next()){
                ReportesVo reporte = new ReportesVo();
                reporte.setNombreCliente(resultadoBusqueda.getString("nombreCliente"));
                reporte.setApellidoCliente(resultadoBusqueda.getString("apellidoCliente"));
                reporte.setAsistencias(resultadoBusqueda.getString("asistencias"));
                reporte.setValor(resultadoBusqueda.getString("totalPago"));
                reporte.setTipo(1);
                lista.add(reporte);
                
            }
            
            conexion.cerrarConexion();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
    }
    
    public void LlenarJtable(List<ReportesVo> listado,JTable tabla, String[] nombreColumnas){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        
        for (int i = 0; i < nombreColumnas.length; i++) {
            modeloTabla.addColumn(nombreColumnas[i]);
        }
        
        for (ReportesVo reporte : listado) {
            
            //String admin = reporte.getIdAdminPago();
            String nombre = reporte.getNombreCliente();
            String apellido = reporte.getApellidoCliente();
            String valor = reporte.getValor();
            
            Object[] fila = {nombre, apellido, valor};
            
            modeloTabla.addRow(fila);
        }
        
        tabla.setModel(modeloTabla);
    }
    
    public void LlenarJtableAnioMes(List<ReportesVo> listado,JTable tabla, String[] nombreColumnas){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        
        for (int i = 0; i < nombreColumnas.length; i++) {
            modeloTabla.addColumn(nombreColumnas[i]);
        }
        
        for (ReportesVo reporte : listado) {
            
            String nombre = reporte.getNombreCliente();
            String apellido = reporte.getApellidoCliente();
            String asistencia = reporte.getAsistencias();
            String valor = reporte.getValor();
            
            Object[] fila = {nombre, apellido,asistencia, valor};
            
            modeloTabla.addRow(fila);
        }
        
        tabla.setModel(modeloTabla);
    }
    
    public void generarReporte(ReportesPdfVo vo){
        
        JasperReport reporte = null;//Variable para crear un reporte
        
        //String path = "src\\VISTAS\\reportes\\report.jasper";//Ruta del archivo del reporte
        
        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/VISTAS/reportes/report.jasper"));//Pasamos la ruta al reporte
            
            Map parametros = new HashMap();//Objeto para pasar variables al reporte
            parametros.put("fec", vo.getFecha());
            parametros.put("valorTotal", "Total de pagos: $"+vo.getValor());
            parametros.put("tipoRep",vo.getTipoRep()+": "+vo.getFecha());
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros,conexion.getConexion());
            
            JasperViewer view = new JasperViewer (jprint,false);//Vista del reporte
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);//MOstramos la vista
            
        } catch (JRException ex) {
            //Logger.getLogger(ReportesBo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        
    }
    
    public void generarReporteAnioMes(ReportesPdfVo vo){
        
        JasperReport reporte = null;
        
        //String path = "src\\VISTAS\\reportes\\reportAnioMes.jasper";
        
        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/VISTAS/reportes/reportAnioMes.jasper"));
            
            Map parametros = new HashMap();
            parametros.put("fec", vo.getFecha());
            parametros.put("valorTotal", "Total de pagos: $"+vo.getValor());
            parametros.put("tipoRep", vo.getTipoRep()+": "+vo.getFecha());
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros,conexion.getConexion());
            
            JasperViewer view = new JasperViewer (jprint,false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            
        } catch (JRException ex) {
            //Logger.getLogger(ReportesBo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        
    }
    
}
