/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.bean;

import com.vitrocar.controller.employee_controller;
import com.vitrocar.modelo.Empleado;
import com.vitrocar.modelo.Sucursal;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author red-conexion
 */

@ManagedBean(name = "vehicle_bean")
@ViewScoped
public class vehicle_bean implements Serializable  {
    private List<Empleado> lstEmpleado;
    private Empleado empleado;
    private String accion = null;
    public String sucursal = null;
    private String selectedCountry = null;

    private Long prueba = null;

    private List<Sucursal> lstSucursal;

    public Long getPrueba() {
        return prueba;
    }

    public void setPrueba(Long prueba) {
        this.prueba = prueba;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

//    @PostConstruct
//    public void init() {
////        empleado = new Empleado();
//        empleado = null;
//    }
//    public void reset(){
//        Empleado empleado = new Empleado();
//        empleado = null;
//        this.empleado.setIdEmpleado(0);
//        this.empleado.setNombre("");
//        this.empleado.setDireccion("");
//        this.empleado.setUsers("");
//        this.empleado.setPasswd("");
//    }
    public Empleado getEmpleado() {
        if (empleado == null) {
            empleado = new Empleado();
        }
        return empleado;
//        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        if (empleado == null) {
            empleado = new Empleado();
        }
//        return empleado;
//        this.empleado = empleado;
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
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
                this.update_employee();
                break;

        }
    }

    public void insert() {
        employee_controller employee;

        try {
            employee = new employee_controller();
            employee.branch_insert(empleado);
        } catch (Exception e) {
            System.out.println("Error Insertar: " + e);
        }
    }

    private void update_employee() throws Exception {
//        branch_controller branch;
        try {
            employee_controller employee = new employee_controller();
            employee.branch_modify_employee(empleado);
            this.listar();
        } catch (Exception e) {
            throw e;
            //System.out.println("Error Insertar: " + e);
        }
    }

    public void delete_employee(Empleado empl) {
//        branch_controller branch;
        try {
            employee_controller employee = new employee_controller();
            employee.branch_delete_employee(empl);
            this.listar();
        } catch (Exception e) {
            System.out.println("Error eliminar: " + e);
        }
    }

//    public void update_employee(){
//        System.out.println("modificar");
//        System.out.println("empleado"+empleado);
//        branch_controller branch; 
//        try {
//            branch = new branch_controller();
//            branch.branch_modify_employee(empleado);
//        } catch (Exception e) {
//            System.out.println("Error Modificar: "+e);
//        }
//    }
    public void changeBranch(ValueChangeEvent event) {
        selectedCountry = event.getNewValue().toString();
        System.out.println("PRUEBA: " + selectedCountry);
    }

    public void listar() throws Exception {
        employee_controller employee;
//        System.out.println("Sucursal Bean Selected: "+prueba);
//        System.out.println("Sucursal seleccionada: "+sucursal);

        try {
            employee = new employee_controller();
            lstEmpleado = employee.listar(selectedCountry);
        } catch (Exception e) {
//            throw e;
            System.out.println("Error: " + e);
        }
    }

    public void list_branch() throws Exception {
        employee_controller employee;
        try {
            employee = new employee_controller();
            lstSucursal = employee.listarSucursal();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error: " + e);
        }
    }

    public void leerID(Empleado emp) throws Exception {
        employee_controller employee;
        Empleado empl;
        try {
            employee = new employee_controller();
            empl = employee.leerID(emp);
            System.out.println("presionaste modificar");
            if (empl != null) {
                this.empleado = empl;
                System.out.println("chido, entró");
            }
            if (empl == null) {
                System.out.println("Vale pito esto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Cancelado", ".");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        empleado = new Empleado();
        empleado = null;
        accion = null;
        System.out.println("cancelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (empleado == null && accion == null) {
            System.out.println("empleado vacio modificar");
        } else {
            System.out.println("VALEEEEEE VEEEEEEEEEEEEEEEEEERGAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!");
        }
    }

    public void clearForm() {
        this.empleado.setIdEmpleado(0);
        this.empleado.setNombre(null);
        System.out.println("limpiar");
    }
}
