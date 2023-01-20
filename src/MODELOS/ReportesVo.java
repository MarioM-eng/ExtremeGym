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
public class ReportesVo {
    
    private String AdminPago;
    private String nombreCliente;
    private String apellidoCliente;
    private String valor;
    private String asistencias;
    private int Tipo;
    
    public ReportesVo(String AdminPago,String nombreCliente,String apellidoCliente,String valor){
        this.AdminPago = AdminPago;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.valor = valor;
    }
    
    public ReportesVo(String nombreCliente,String apellidoCliente,String asistencias,String valor,int anioMes){
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.asistencias=asistencias;
        this.valor = valor;
        this.Tipo=anioMes;
    }
    
    public ReportesVo(){
        
    }

    public String getIdAdminPago() {
        return AdminPago;
    }

    public void setAdminPago(String idAdminPago) {
        this.AdminPago = idAdminPago;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(String asistencias) {
        this.asistencias = asistencias;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }
    
    
    
    

    
    
    
}
