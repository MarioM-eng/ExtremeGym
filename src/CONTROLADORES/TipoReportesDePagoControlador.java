/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADORES;

import MODELOS.TipoReportesDePagoBo;
import MODELOS.TipoReportesDePagoVo;
import VISTAS.VistaReportes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mario
 */
public class TipoReportesDePagoControlador {
    
    private VistaReportes vista;
    private TipoReportesDePagoBo modelo;
    
    public TipoReportesDePagoControlador(VistaReportes vista,TipoReportesDePagoBo modelo){
        this.vista = vista;
        this.modelo = modelo;
    }
    
    public TipoReportesDePagoControlador(){
    }
    
    public void listaTipoReportesDePago(){//Necesario para saber el tipo de pago
        List<TipoReportesDePagoVo> listadoResult = new ArrayList<TipoReportesDePagoVo>();
        listadoResult = modelo.listarTipoReportesDePago();
        
        /*if(listadoResult.size() > 0){
            for (int i = 0; i < listadoResult.size(); i++) {
                vista.jCtpago.addItem(listadoResult.get(i));
                System.out.println(listadoResult.get(i));
            }
            
            
            //JOptionPane.showMessageDialog(vista, "Posee Valores");
        }else{
             //JOptionPane.showMessageDialog(vista, "NO Posee Valores");
        }*/
    }
    
}
