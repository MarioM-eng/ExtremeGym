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
public class EstadoClienteVo {
    
    private int id;
    private String estado;

    public EstadoClienteVo(int id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public EstadoClienteVo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return estado ;
    }
    
    
    
}
