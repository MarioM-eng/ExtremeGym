/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADORES;

import MODELOS.EstadoClienteBo;
import MODELOS.EstadoClienteVo;
import VISTAS.VistaCliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mario
 */
public class EstadoClienteControlador {
    
    private VistaCliente vista;
    private EstadoClienteBo modelo;

    public EstadoClienteControlador(VistaCliente vista, EstadoClienteBo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        llenarComboBox();
    }

    public EstadoClienteControlador() {
    }
    
    
    
    public void llenarComboBox(){//Necesatio para saber la categoria del cliente
        List<EstadoClienteVo> listadoResult = new ArrayList<EstadoClienteVo>();
        listadoResult = modelo.listarEstadoCliente();
        
        if(listadoResult.size() > 0){
            for (int i = 0; i < listadoResult.size(); i++) {
                vista.jCestadoMod.addItem(listadoResult.get(i));
            }
            
            
            //JOptionPane.showMessageDialog(vista, "Posee Valores");
        }else{
             //JOptionPane.showMessageDialog(vista, "NO Posee Valores");
        }
    }
    
    public static EstadoClienteVo estado(int id){//
        EstadoClienteVo result;
        EstadoClienteBo mod = new EstadoClienteBo();
        result = mod.listarEstadoClientePorId(id);
        return result;
    }
    
}
