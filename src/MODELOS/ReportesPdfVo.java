/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOS;

/**
 *
 * @author mario
 */
public class ReportesPdfVo {
    
    private String fecha;
    private String valor;
    private String tipoRep;
    
    public ReportesPdfVo(String fecha,String valor,String tipoRep){
        
        this.fecha=fecha;
        this.valor=valor;
        this.tipoRep=tipoRep;
        
    }
    
    public ReportesPdfVo(){
        
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipoRep() {
        return tipoRep;
    }

    public void setTipoRep(String tipoRep) {
        this.tipoRep = tipoRep;
    }
    
    
    
}
