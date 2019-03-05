/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.modelo;

import java.io.Serializable;

/**
 *
 * @author red-conexion
 */
public class Pais implements Serializable{
    
    private int idPais;
     private String nombre;
     private String abreviacion;

    public Pais(int idPais, String nombre, String abreviacion) {
        this.idPais = idPais;
        this.nombre = nombre;
        this.abreviacion = abreviacion;
    }

    public Pais() {
    }

   
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }
     
     
    
}
