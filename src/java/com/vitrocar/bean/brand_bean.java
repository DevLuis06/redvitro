/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.brand_controller;
import com.vitrocar.controller.year_controller;
import com.vitrocar.modelo.Marca;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author red-conexion
 */
@ManagedBean(name = "brand_bean")
@SessionScoped
public class brand_bean implements Serializable {

    private String accion = null;
    private List<Marca> lstBrand;
    private Marca marca;

    public String id;

    public List<Marca> getLstBrand() {
        return lstBrand;
    }

    public void setLstBrand(List<Marca> lstBrand) {
        this.lstBrand = lstBrand;
    }

    public Marca getBrand() {
        if (marca == null) {
            marca = new Marca();
        }
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
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
                this.insert_brand();
                break;
            case "Modificar":
                this.update_brand();
                break;

        }
    }

    public void insert_brand() {
        try {
            brand_controller brand = new brand_controller();
            brand.brand_insert(marca);
            accion = null;

        } catch (Exception e) {
            System.out.println("Error Insertar: " + e);
        }
    }

    private void update_brand() throws Exception {
        try {
            brand_controller brand = new brand_controller();
            brand.brand_modify(marca);
            this.listar();
            accion = null;
            System.out.println("Accion Mod:" + this.getAccion());
        } catch (Exception e) {
            throw e;
            //System.out.println("Error Insertar: " + e);
        }
    }

    public void delete_anio(Marca marca) {
        try {
            brand_controller brands = new brand_controller();
            brands.brand_delete(marca);
            this.listar();
        } catch (Exception e) {
            System.out.println("Error eliminar marca: " + e);
        }
    }

    public List<Marca> listar() throws Exception {

        try {
            brand_controller brand = new brand_controller();
            lstBrand = brand.listar();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error Listar: " + e);
        }
        return lstBrand;
    }

    public void leerID(Marca marca) throws Exception {
        accion = "Modificar";
        Marca marcas;
        System.out.println("accion:" + this.getAccion());
        try {
            brand_controller brand = new brand_controller();
            marcas = brand.leerID(marca);
            System.out.println("presionaste modificar");
            if (marcas != null) {
                this.marca = marcas;
                System.out.println("chido, entró sucursal");
            }
            if (marcas == null) {
                System.out.println("Vale pito esto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Cancelado", ".");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        marca = new Marca();
        marca = null;
        accion = null;
        System.out.println("cancelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (marca == null && accion == null) {
            System.out.println("sucursal vacio modificar");
        } else {
            System.out.println("VALEEEEEE VEEEEEEEEEEEEEEEEEERGAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!");
        }
    }

    public void registrar() {
        accion = "Registrar";
    }
}
