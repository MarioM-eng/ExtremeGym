/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOS;

import java.sql.Date;

/**
 *
 * @author mario
 */
public class PagoVo {
    
    private int id;
    private int idAdministrador;
    private int idCliente;
    private String valor;
    private Date fechaInicial;
    private Date fechaFinal;
    private int tipoPago;
    private int estado;

    public PagoVo(int id, int idAdministrador, int idCliente, String valor, Date fechaInicial, Date fechaFinal, int tipoPago, int estado) {
        this.id = id;
        this.idAdministrador = idAdministrador;
        this.idCliente = idCliente;
        this.valor = valor;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tipoPago = tipoPago;
        this.estado = estado;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public PagoVo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
