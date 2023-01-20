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
public class ClienteVo {
    
    private String id;
    private String nombre;
    private String apellido;
    private String celular;
    private int estado;
    private Date regis;
    private int admin;

    
    //Se crean 2 constructores (Sobre carga de métodos). Uno con parametros y otro por si los parametros se le ingresan la momento de instanciarlo.

    public ClienteVo(String id, String nombre, String apellido, String celular, int estado, Date regis, int admin) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.estado = estado;
        this.regis = regis;
        this.admin = admin;
    }

    public ClienteVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getRegis() {
        return regis;
    }

    public void setRegis(Date regis) {
        this.regis = regis;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
    
}
