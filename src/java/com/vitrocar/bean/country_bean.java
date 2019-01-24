/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.branch_controller;
import com.vitrocar.controller.country_controller;
import com.vitrocar.modelo.Pais;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author red-conexion by Luis D' León
 */
@ManagedBean(name = "countrybean_ceo")
@ViewScoped
public class country_bean implements Serializable{
       
    private String accion = null;
    private List<Pais> lstPais;
    private Pais pais;
    
    public String id;

    
    public Pais getPais() {
                if (pais == null) {
            pais = new Pais();
        }
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
                this.insert();
                break;
            case "Modificar":
                this.update_country();
                break;

        }
    }

    public void insert() {
        country_controller country;
        try {
            country = new country_controller();
            country.country_insert(pais);
            
        } catch (Exception e) {
            System.out.println("Error Insertar: " + e);
        }
    }

    private void update_country() throws Exception {
//        branch_controller branch;
        try {
            country_controller country = new country_controller();
            country.country_modify(pais);
            this.list();
            accion = null;
            System.out.println("Accion Mod:"+this.getAccion());
        } catch (Exception e) {
            throw e;
            //System.out.println("Error Insertar: " + e);
        }
    }

    public void delete_branch(Pais pais) {
//        branch_controller branch;
        try {
            country_controller country = new country_controller();
            country.country_delete(pais);
            this.list();
        } catch (Exception e) {
            System.out.println("Error eliminar: " + e);
        }
    }

 
    public List <Pais>list() throws Exception {
        country_controller country;
        try {
            country = new country_controller();
            lstPais = country.list();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error: " + e);
        }
        return lstPais;
    }
    
    public void leerID(Pais country) throws Exception {
        accion = "Modificar";
        country_controller countrycon;
        Pais pais;
        System.out.println("accion:"+this.getAccion());
        try {
            countrycon = new country_controller();
            pais = countrycon.readID(country);
            System.out.println("presionaste modificar");
            if (pais != null) {
                this.pais = pais;
                System.out.println("chido, entró sucursal");
            }
            if (pais == null) {
                System.out.println("Vale pito esto");
            }
        } catch (Exception e) {
            throw e;
        }
    }
     public void reset() {
        PrimeFaces.current().resetInputs("diaForm:diaPanel");
    }

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Cancelado", ".");
        FacesContext.getCurrentInstance().addMessage(null, msg);
       pais = new Pais();
        pais = null;
        accion = null;
        System.out.println("cancelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (pais == null && accion == null) {
            System.out.println("pais vacio modificar");
        } else {
            System.out.println("VALEEEEEE VEEEEEEEEEEEEEEEEEERGAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!");
        }
    }
    
    
    public void registrar(){
        accion = "Registrar";
    }
}
