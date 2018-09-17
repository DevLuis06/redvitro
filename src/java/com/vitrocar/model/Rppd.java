package com.vitrocar.model;
// Generated 17/08/2018 01:32:34 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * Rppd generated by hbm2java
 */
public class Rppd  implements java.io.Serializable {


     private int idRrpd;
     private OrdenServicio ordenServicio;
     private BigDecimal recibi;
     private BigDecimal cantidad;
     private Date fechaElaboracion;

    public Rppd() {
    }

	
    public Rppd(int idRrpd) {
        this.idRrpd = idRrpd;
    }
    public Rppd(int idRrpd, OrdenServicio ordenServicio, BigDecimal recibi, BigDecimal cantidad, Date fechaElaboracion) {
       this.idRrpd = idRrpd;
       this.ordenServicio = ordenServicio;
       this.recibi = recibi;
       this.cantidad = cantidad;
       this.fechaElaboracion = fechaElaboracion;
    }
   
    public int getIdRrpd() {
        return this.idRrpd;
    }
    
    public void setIdRrpd(int idRrpd) {
        this.idRrpd = idRrpd;
    }
    public OrdenServicio getOrdenServicio() {
        return this.ordenServicio;
    }
    
    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }
    public BigDecimal getRecibi() {
        return this.recibi;
    }
    
    public void setRecibi(BigDecimal recibi) {
        this.recibi = recibi;
    }
    public BigDecimal getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    public Date getFechaElaboracion() {
        return this.fechaElaboracion;
    }
    
    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }




}


