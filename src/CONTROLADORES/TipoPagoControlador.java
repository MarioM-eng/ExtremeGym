/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADORES;

import MODELOS.TipoPagoBo;
import MODELOS.TipoPagoVo;
import VISTAS.VistaPago;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class TipoPagoControlador {
    
    private VistaPago vista;
    private TipoPagoBo modelo;
    
    public TipoPagoControlador(VistaPago vista,TipoPagoBo modelo){
        this.vista = vista;
        this.modelo = modelo;
        
        llenarComboBox();
    }
    
    public TipoPagoControlador(){
    }
    
    public void llenarComboBox(){//Necesario para saber el tipo de pago
        List<TipoPagoVo> listadoResult = new ArrayList<TipoPagoVo>();
        listadoResult = modelo.listarTipoPago();
        
        if(listadoResult.size() > 0){
            for (int i = 0; i < listadoResult.size(); i++) {
                vista.jCtpago.addItem(listadoResult.get(i));
                System.out.println(listadoResult.get(i));
            }
            
            
            //JOptionPane.showMessageDialog(vista, "Posee Valores");
        }else{
             //JOptionPane.showMessageDialog(vista, "NO Posee Valores");
        }
    }
    
    public void setValor(TipoPagoVo p){
        
        int resul = this.modelo.setValor(p);
        
        if(resul == 1){
            JOptionPane.showMessageDialog(vista, "Valor actualizado con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "Falla al cambiar valor","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
