package com.vitrocar.model;
// Generated 17/08/2018 01:32:34 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Uso generated by hbm2java
 */
public class Uso  implements java.io.Serializable {


     private int idUso;
     private String nombre;
     private Set<Conductor> conductors = new HashSet<Conductor>(0);

    public Uso() {
    }

	
    public Uso(int idUso) {
        this.idUso = idUso;
    }
    public Uso(int idUso, String nombre, Set<Conductor> conductors) {
       this.idUso = idUso;
       this.nombre = nombre;
       this.conductors = conductors;
    }
   
    public int getIdUso() {
        return this.idUso;
    }
    
    public void setIdUso(int idUso) {
        this.idUso = idUso;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Conductor> getConductors() {
        return this.conductors;
    }
    
    public void setConductors(Set<Conductor> conductors) {
        this.conductors = conductors;
    }




}


