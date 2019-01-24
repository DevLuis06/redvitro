/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.anio_controller;
import com.vitrocar.controller.color_controller;
import com.vitrocar.modelo.Anio;
import com.vitrocar.modelo.Color;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author red-conexion
 */
@ManagedBean(name = "anio_bean")
@SessionScoped
public class anio_bean {
    
    private String accion = null;
    private List<Anio> lstAnio;
    private Anio anio;
    
    public String id;

    public List<Anio> getLstAnio() {
        return lstAnio;
    }

    public void setLstSAnio(List<Anio> lstAnio) {
        this.lstAnio = lstAnio;
    }
    
    public Anio getAnio() {
                if (anio == null) {
            anio = new Anio();
        }
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    

    public void operar() throws Exception {
        switch (accion) {
            case "Registrar":
                this.insert_anio();
                break;
            case "Modificar":
                this.update_anio();
                break;

        }
    }

    public void insert_anio() {
        try {
            anio_controller  ani = new anio_controller();
            ani.anio_insert(anio);
            
        } catch (Exception e) {
            System.out.println("Error Insertar: " + e);
        }
    }

    private void update_anio() throws Exception {
        try {
            anio_controller  ani = new anio_controller();
            ani.anio_modify(anio);
            this.listar();
            accion = null;
            System.out.println("Accion Mod:"+this.getAccion());
        } catch (Exception e) {
            throw e;
            //System.out.println("Error Insertar: " + e);
        }
    }

    public void delete_anio(Anio anio) {
//        branch_controller branch;
        try {
            anio_controller  ani = new anio_controller();
            ani.anio_delete(anio);
            this.listar();
        } catch (Exception e) {
            System.out.println("Error eliminar color: " + e);
        }
    }

 
    public List<Anio> listar() throws Exception {
        
        try {
            anio_controller  ani = new anio_controller();
            lstAnio = ani.listar();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error Listar: " + e);
        }
        return lstAnio;
    }
    
    public void leerID(Anio anio) throws Exception {
        accion = "Modificar";
        Anio anios;
        System.out.println("accion:"+this.getAccion());
        try {
            anio_controller  ani = new anio_controller();
            anios = ani.leerID(anio);
            System.out.println("presionaste modificar");
            if (anios != null) {
                this.anio = anios;
                System.out.println("chido, entró sucursal");
            }
            if (anios == null) {
                System.out.println("Vale pito esto");
            }
        } catch (Exception e) {
            throw e;
        }
    }
  

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Cancelado", ".");
        FacesContext.getCurrentInstance().addMessage(null, msg);
       anio = new Anio();
        anio = null;
        accion = null;
        System.out.println("cancelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (anio == null && accion == null) {
            System.out.println("sucursal vacio modificar");
        } else {
            System.out.println("VALEEEEEE VEEEEEEEEEEEEEEEEEERGAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!");
        }
    }
    
    
    public void registrar(){
        accion = "Registrar";
    }
    
    
}
