package com.vitrocar.model;
// Generated 17/08/2018 01:32:34 AM by Hibernate Tools 4.3.1



/**
 * ClienteAuto generated by hbm2java
 */
public class ClienteAuto  implements java.io.Serializable {


     private int idClienteAuto;
     private Auto auto;
     private Cliente cliente;
     private String serieMotor;
     private String serie;
     private String placas;

    public ClienteAuto() {
    }

	
    public ClienteAuto(int idClienteAuto) {
        this.idClienteAuto = idClienteAuto;
    }
    public ClienteAuto(int idClienteAuto, Auto auto, Cliente cliente, String serieMotor, String serie, String placas) {
       this.idClienteAuto = idClienteAuto;
       this.auto = auto;
       this.cliente = cliente;
       this.serieMotor = serieMotor;
       this.serie = serie;
       this.placas = placas;
    }
   
    public int getIdClienteAuto() {
        return this.idClienteAuto;
    }
    
    public void setIdClienteAuto(int idClienteAuto) {
        this.idClienteAuto = idClienteAuto;
    }
    public Auto getAuto() {
        return this.auto;
    }
    
    public void setAuto(Auto auto) {
        this.auto = auto;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getSerieMotor() {
        return this.serieMotor;
    }
    
    public void setSerieMotor(String serieMotor) {
        this.serieMotor = serieMotor;
    }
    public String getSerie() {
        return this.serie;
    }
    
    public void setSerie(String serie) {
        this.serie = serie;
    }
    public String getPlacas() {
        return this.placas;
    }
    
    public void setPlacas(String placas) {
        this.placas = placas;
    }




}


