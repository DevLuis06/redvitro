/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

//import com.vitrocar.ejb.UsuarioFacade;
import com.vitrocar.ejb.UsuarioFacadeLocal;
import com.vitrocar.modelo.Empleado;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author red-conexion
 */
@Named
@ViewScoped
public class indexController implements Serializable{
        
    @EJB    
    private UsuarioFacadeLocal EJBEmpleado;
    
    private Empleado empleado;
    
    @PostConstruct
    public void init(){
        empleado = new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    public String iniciarSesion(){
        String redireccion = null;
        try {
            EJBEmpleado.iniciarSesion(empleado);
            System.out.println("emp"+EJBEmpleado.iniciarSesion(empleado));
            redireccion = "/Template/Principal";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error!!"));
            
            }
        return redireccion;
    }
    
}
