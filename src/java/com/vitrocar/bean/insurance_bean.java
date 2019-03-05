/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.color_controller;
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
@ManagedBean(name = "insurance_bean")
@SessionScoped
public class insurance_bean {
    private String accion = null;
    private List<Color> lstColor;
    private Color colores;
    
    public String id;

    public List<Color> getLstColor() {
        return lstColor;
    }

    public void setLstSucursal(List<Color> lstColor) {
        this.lstColor = lstColor;
    }
    
    public Color getColor() {
                if (colores == null) {
            colores = new Color();
        }
        return colores;
    }

    public void setColor(Color color) {
        this.colores = colores;
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
                this.insert_color();
                break;
            case "Modificar":
                this.update_color();
                break;

        }
    }

    public void insert_color() {
        try {
            color_controller col = new color_controller();
            col.color_insert(colores);
            
        } catch (Exception e) {
            System.out.println("Error Insertar: " + e);
        }
    }

    private void update_color() throws Exception {
        try {
            color_controller col = new color_controller();
            col.color_modify(colores);
            this.listar();
            accion = null;
            System.out.println("Accion Mod:"+this.getAccion());
        } catch (Exception e) {
            throw e;
            //System.out.println("Error Insertar: " + e);
        }
    }

    public void delete_color(Color color) {
//        branch_controller branch;
        try {
            color_controller col = new color_controller();
            col.color_delete(color);
            this.listar();
        } catch (Exception e) {
            System.out.println("Error eliminar color: " + e);
        }
    }

 
    public List<Color> listar() throws Exception {
        
        try {
            color_controller col = new color_controller();
            lstColor = col.listar();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error Listar: " + e);
        }
        return lstColor;
    }
    
    public void leerID(Color color) throws Exception {
        accion = "Modificar";
        Color colors;
        System.out.println("accion:"+this.getAccion());
        try {
            color_controller col = new color_controller();
            colors = col.leerID(color);
            System.out.println("presionaste modificar");
            if (colors != null) {
                this.colores = colors;
                System.out.println("chido, entró sucursal");
            }
            if (colors == null) {
                System.out.println("Vale pito esto");
            }
        } catch (Exception e) {
            throw e;
        }
    }
  

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Cancelado", ".");
        FacesContext.getCurrentInstance().addMessage(null, msg);
       colores = new Color();
        colores = null;
        accion = null;
        System.out.println("cancelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (colores == null && accion == null) {
            System.out.println("sucursal vacio modificar");
        } else {
            System.out.println("VALEEEEEE VEEEEEEEEEEEEEEEEEERGAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!");
        }
    }
    
    
    public void registrar(){
        accion = "Registrar";
    }
    
}
