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
public class TipoPagoVo {
    
    private int id;
    private String nombre;
    private int valor;
    
    public TipoPagoVo(int id, String nombre, int valor){
        
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        
    }
    
    public TipoPagoVo(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return getNombre();
    }
    
    
    
}
