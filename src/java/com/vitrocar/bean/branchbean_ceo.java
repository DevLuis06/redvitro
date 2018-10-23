/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.branch_controller;
import com.vitrocar.modelo.Empleado;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author red-conexion
 */

@ManagedBean
@RequestScoped
public class branchbean_ceo {
    
    private List<Empleado> lstEmpleado;
    
    
    public void listar() throws Exception{
        branch_controller branch;
        try {
            branch = new branch_controller();
            lstEmpleado = branch.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
    }
    
    
    
}
