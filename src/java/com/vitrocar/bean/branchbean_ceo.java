/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.branch_controller;
import com.vitrocar.model.Empleado;
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
    private Empleado empleado;

//    public void insert(){
//        //branch_controller branch;
//        try {
//           branch_controller branch = new branch_controller();
//            branch.branch_insert(empleado);
//        } catch (Exception e) {
//            System.out.println("Error Insertar: "+e);
//        }
//    }
    
    
    
    public void listar() throws Exception {
        branch_controller branch;
        try {
            branch = new branch_controller();
            lstEmpleado = branch.listar();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error: " + e);
        }
    }

    public void leerID(Empleado emp) throws Exception {
        branch_controller branch;
        Empleado empl;
        try {
            branch = new branch_controller();
            empl = branch.leerID(emp);
            System.out.println("precionaste modificar");
            if (empl != null) {
                this.empleado = empl;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
    }

}
