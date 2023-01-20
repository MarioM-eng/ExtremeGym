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
public class AdminVo {//Clase donde guardaremos los atributos de la entidad
    
    private int id;
    private String nombre;
    private String apellido;
    private String identificador;
    private String clave;
    private Date ultimaVez;

    public AdminVo(int id, String nombre, String apellido, String identificador, String clave, Date ultimaVez) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificador = identificador;
        this.clave = clave;
        this.ultimaVez=ultimaVez;
    }
    
    public AdminVo(){
        
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getUltimaVez() {
        return ultimaVez;
    }

    public void setUltimaVez(Date ultimaVez) {
        this.ultimaVez = ultimaVez;
    }
    
    

    

}
