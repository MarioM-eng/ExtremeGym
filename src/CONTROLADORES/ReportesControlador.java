/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADORES;

import MODELOS.ReportesBo;
import MODELOS.ReportesPdfVo;
import MODELOS.ReportesVo;
import VISTAS.VistaReportes;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class ReportesControlador {
    
    private VistaReportes vista;
    private ReportesBo modelo;
    private ReportesPdfVo vo;
    private String tipoR="Diario";
    
    public ReportesControlador(VistaReportes vista,ReportesBo modelo){
        this.vista = vista;
        this.modelo = modelo;
        
        fechaHoy();
        
        vista.jBrdiario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                tipoR="Día";
                vista.jPrdiario.setVisible(true);
                vista.jPrmensual.setVisible(false);
                vista.jPranual.setVisible(false);
                if(!vista.jBrdiario.isSelected()){
                    vista.jBrdiario.setBackground(new Color(255, 255, 255));
                    vista.jBrdiario.setForeground(new Color(42, 42, 42));
                    vista.jBrmensual.setBackground(new Color(42, 42, 42));
                    vista.jBrmensual.setForeground(new Color(255, 255, 255));
                    vista.jBranual.setBackground(new Color(42, 42, 42));
                    vista.jBranual.setForeground(new Color(255, 255, 255));
                    vista.jBgenerarpdf.setEnabled(false);

        }
            }
            
        });
        
        
        
        vista.jBrmensual.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                tipoR="Mes";
                vista.jPrdiario.setVisible(false);
                vista.jPrmensual.setVisible(true);
                vista.jPranual.setVisible(false);
                if(!vista.jBrmensual.isSelected()){
                    vista.jBrdiario.setBackground(new Color(42, 42, 42));
                    vista.jBrdiario.setForeground(new Color(255, 255, 255));
                    vista.jBrmensual.setBackground(new Color(255, 255, 255));
                    vista.jBrmensual.setForeground(new Color(42, 42, 42));
                    vista.jBranual.setBackground(new Color(42, 42, 42));
                    vista.jBranual.setForeground(new Color(255, 255, 255));
                    vista.jBgenerarpdfMes.setEnabled(false);
                }
            }
            
        });
        
        vista.jBranual.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                tipoR="Año";
                vista.jPrdiario.setVisible(false);
                vista.jPrmensual.setVisible(false);
                vista.jPranual.setVisible(true);
                if(!vista.jBrmensual.isSelected()){
                    vista.jBrdiario.setBackground(new Color(42, 42, 42));
                    vista.jBrdiario.setForeground(new Color(255, 255, 255));
                    vista.jBrmensual.setBackground(new Color(42, 42, 42));
                    vista.jBrmensual.setForeground(new Color(255, 255, 255));
                    vista.jBranual.setBackground(new Color(255, 255, 255));
                    vista.jBranual.setForeground(new Color(42, 42, 42));
                    vista.jBgenerarpdfAnio.setEnabled(false);

                }
            }
            
        });
        
        vista.jBgenerarrep.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //reporte();
                if(reporte() == false){
                    System.out.println("Vacío");
                }else{
                    vista.jBgenerarpdf.setEnabled(true);
                }
            }
            
        });
        
        vista.jBgenerarrepmes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //reporteMes();
                if(reporteMes() == false){
                    System.out.println("Vacío");
                }else{
                    vista.jBgenerarpdfMes.setEnabled(true);
                }
                
            }
            
        });
        
        vista.jBgenerarrepanio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //reporteAnio();
                if(reporteAnio() == false){
                    System.out.println("Vacío");
                }else{
                    vista.jBgenerarpdfAnio.setEnabled(true);
                }
                
            }
            
        });
        
        vista.jBgenerarpdf.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                generarReporte();
            }
            
        });
        
        vista.jBgenerarpdfMes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                generarReporteAnioMes();
            }
            
        });
        
        vista.jBgenerarpdfAnio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                generarReporteAnioMes();
            }
            
        });
    }
    
    public ReportesControlador(){
        
    }
    
    public void fechaHoy(){
        Calendar cal = Calendar.getInstance();
        this.vista.jDfecha.setDate(cal.getTime());
    }
    
    public Date getFecha(){
        
        Date fecha = null;
        
            try{
                java.util.Date date = this.vista.jDfecha.getDate();//Se obtiene la fecha del jCalendar
                long d = date.getTime();//Se almacena en una variable tipo long para ser utilizada en la libreria Date de sql 
                Date fechaC = new Date(d);//Convertimos la fecha del jCalendar a una fecha compatible con sql
                
                fecha = fechaC;
                
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar la fecha","¡Error!",JOptionPane.ERROR_MESSAGE);
            }
        
        return fecha;
    }
    
    public boolean reporte(){
        
        List<ReportesVo> listaResult;
        Date fecha = getFecha();
        
        listaResult = this.modelo.reporte(fecha);
        
        if(listaResult.isEmpty()){//Sí está vaía retornara el valor false
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
            return false;
        }
        int val = calcularValorTotal(listaResult);
        vista.jLvalTotal.setText("Valor total: "+val);
        String[] columnas = new String[3];
              
                columnas[0] = "Nombre del cliente";
                columnas[1] = "Apellido del cliente";
                columnas[2] = "Valor pagado";
                //columnas[3] = "Admin. registró pago";
                
                if(listaResult.size() > 0){
                    this.modelo.LlenarJtable(listaResult, vista.jTTablereportes, columnas);
                }else{
                     JOptionPane.showMessageDialog(null, "No se encontraron resultados");
                }
                
        vo = new ReportesPdfVo(String.valueOf(fecha),String.valueOf(val),tipoR);
        
        return true;
                
    }
    
    public boolean reporteMes(){
        
        List<ReportesVo> listaResult;
        String anio = String.valueOf(this.vista.jYManio.getYear());//Pasamos la año a String
        Calendar cal = Calendar.getInstance();//Se obtiene fecha actual
        cal.set(Calendar.MONTH, vista.jMmes.getMonth());//Se cambia mes al seleccionado en comboBox
        java.util.Date date = cal.getTime();
        long d = date.getTime();
        Date fecha = new Date(d);
        String valor = String.valueOf(fecha);//Pasamos la fecha a String
        String mes = valor.substring(4, 7);//Tomamos los caracteres que corresponden al mes anteponirnto un -
        String anioMes = anio+mes;
        
        listaResult = this.modelo.reporteAnioMes(anioMes);
        if(listaResult.isEmpty()){//Sí está vaía retornara el valor false
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
            return false;
        }
        int val = calcularValorTotal(listaResult);
        vista.jLvalTotalMes.setText("Valor total: "+val);
        vista.jLasisTotalMes.setText("Asistencia total: "+asistenciasTotales(listaResult));
        String[] columnas = new String[4];
              
                columnas[0] = "Nombre del cliente";
                columnas[1] = "Apellido del cliente";
                columnas[2] = "Asistencias";
                columnas[3] = "Total pagado";
                
                if(listaResult.size() > 0){
                    this.modelo.LlenarJtableAnioMes(listaResult, vista.jTTablereportesmes, columnas);
                }else{
                     JOptionPane.showMessageDialog(null, "No se encontraron resultados");
                }
                
        vo = new ReportesPdfVo(anioMes,String.valueOf(val),tipoR);
        
        return true;
    }
    
    public boolean reporteAnio(){
        
        List<ReportesVo> listaResult;
        String anio = String.valueOf(this.vista.jYanio.getYear());//Pasamos la año a String
        
        listaResult = this.modelo.reporteAnioMes(anio);
        if(listaResult.isEmpty()){//Sí está vaía retornara el valor false
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
            return false;
        }
        int val = calcularValorTotal(listaResult);
        vista.jLvalTotalAnio.setText("Valor total: "+val);
        vista.jLasisTotalAnio.setText("Asistencia total: "+asistenciasTotales(listaResult));
        String[] columnas = new String[4];
              
                columnas[0] = "Nombre del cliente";
                columnas[1] = "Apellido del cliente";
                columnas[2] = "Asistencias";
                columnas[3] = "Total pagado";
                
                if(listaResult.size() > 0){
                    this.modelo.LlenarJtableAnioMes(listaResult, vista.jTTablereportesanio, columnas);
                }else{
                     JOptionPane.showMessageDialog(null, "No se encontraron resultados");
                }
                
        vo = new ReportesPdfVo(anio,String.valueOf(val),tipoR);
        
        return true;
    }
    
    public int calcularValorTotal(List<ReportesVo> listaResult){
        
        
        int valorTotal=0;
        
        if(listaResult.size() > 0){
            for(int i=0;i<listaResult.size();i++){
                int valor = Integer.parseInt(listaResult.get(i).getValor());
                valorTotal=valor+valorTotal;
            }
        }else{
            System.out.println("No se encontraron resultados");
        }
        return valorTotal;
        
    }
    
    public int asistenciasTotales(List<ReportesVo> listaResult){
        
        
        int asisTotal=0;
        
        if(listaResult.size() > 0){
            for(int i=0;i<listaResult.size();i++){
                int valor = Integer.parseInt(listaResult.get(i).getAsistencias());
                asisTotal=valor+asisTotal;
            }
        }else{
            System.out.println("No se encontraron resultados");
        }
        return asisTotal;
        
    }
    
    public void generarReporte(){
        
        this.modelo.generarReporte(vo);
        
        
    }
    
    public void generarReporteAnioMes(){
        
        this.modelo.generarReporteAnioMes(vo);
        
        
    }
    
}
