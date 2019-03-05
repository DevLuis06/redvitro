/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.modelo;

/**
 *
 * @author red-conexion by psycho06
 */
public class Auto {

    private int idAuto;
    private Marca marca;
    private TipoAuto tipoAuto;
    private String modelo;
    private Integer puertas;
    private Integer idAnio;

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public TipoAuto getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(TipoAuto tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getPuertas() {
        return puertas;
    }

    public void setPuertas(Integer puertas) {
        this.puertas = puertas;
    }

    public Integer getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(Integer idAnio) {
        this.idAnio = idAnio;
    }
    
    
    
}
