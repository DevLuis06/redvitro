/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.branch_controller;
import com.vitrocar.modelo.Sucursal;
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
@ManagedBean(name = "branchbean_ceo")
@ViewScoped

public class branchbean_ceo implements Serializable {

    
    private String accion = null;
    private List<Sucursal> lstSucursal;
    private Sucursal sucursal;
    
    public String id;

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }
    
    public Sucursal getSucursal() {
                if (sucursal == null) {
            sucursal = new Sucursal();
        }
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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
                this.update_branch();
                break;

        }
    }

    public void insert() {
        branch_controller branch;

        try {
            branch = new branch_controller();
            branch.branch_insert(sucursal);
            
        } catch (Exception e) {
            System.out.println("Error Insertar: " + e);
        }
    }

    private void update_branch() throws Exception {
//        branch_controller branch;
        try {
            branch_controller branch = new branch_controller();
            branch.branch_modify(sucursal);
            this.listar();
            accion = null;
            System.out.println("Accion Mod:"+this.getAccion());
        } catch (Exception e) {
            throw e;
            //System.out.println("Error Insertar: " + e);
        }
    }

    public void delete_branch(Sucursal suc) {
//        branch_controller branch;
        try {
            branch_controller branch = new branch_controller();
            branch.branch_delete(suc);
            this.listar();
        } catch (Exception e) {
            System.out.println("Error eliminar: " + e);
        }
    }

 
    public List<Sucursal> listar() throws Exception {
        branch_controller branch;
        try {
            branch = new branch_controller();
            lstSucursal = branch.listar();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error Listar: " + e);
        }
        return lstSucursal;
    }
    
    public void leerID(Sucursal suc) throws Exception {
        accion = "Modificar";
        branch_controller branch;
        Sucursal sucursal;
        System.out.println("accion:"+this.getAccion());
        try {
            branch = new branch_controller();
            sucursal = branch.leerID(suc);
            System.out.println("presionaste modificar");
            if (sucursal != null) {
                this.sucursal = sucursal;
                System.out.println("chido, entró sucursal");
            }
            if (sucursal == null) {
                System.out.println("Vale pito esto");
            }
        } catch (Exception e) {
            throw e;
        }
    }
  

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Cancelado", ".");
        FacesContext.getCurrentInstance().addMessage(null, msg);
       sucursal = new Sucursal();
        sucursal = null;
        accion = null;
        System.out.println("cancelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (sucursal == null && accion == null) {
            System.out.println("sucursal vacio modificar");
        } else {
            System.out.println("VALEEEEEE VEEEEEEEEEEEEEEEEEERGAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!");
        }
    }
    
    
    public void registrar(){
        accion = "Registrar";
    }
    

    
    
}
