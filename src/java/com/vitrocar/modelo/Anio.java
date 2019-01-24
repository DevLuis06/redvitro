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
public class Anio implements Serializable {

    private int id_anio;
    private int anio;

    public int getId_anio() {
        return id_anio;
    }

    public void setId_anio(int id_anio) {
        this.id_anio = id_anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

}
