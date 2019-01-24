package com.vitrocar.bean;

import com.vitrocar.controller.branch_controller;
import com.vitrocar.controller.state_controller;
import com.vitrocar.modelo.Estado;
import com.vitrocar.modelo.Pais;
import com.vitrocar.modelo.Sucursal;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.PrimeFaces;

/**
 *
 * @author red-conexion by Luis D' León
 */
@ManagedBean(name = "state_bean")
@ViewScoped

public class state_bean implements Serializable {

    private String accion = null;
    private List<Estado> lstEstado;
    private Estado estado;
    private String selectedCountry = null;

    public String id;
    private List<Pais> lstPais;

    public List<Pais> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<Pais> lstPais) {
        this.lstPais = lstPais;
    }

    public List<Estado> getLstEstado() {
        return lstEstado;
    }

    public void setLstEstado(List<Estado> lstEstado) {
        this.lstEstado = lstEstado;
    }

    public Estado getEstado() {
        if (estado == null) {
            estado = new Estado();
        }
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
                this.insert_state();
                break;
            case "Modificar":
                this.update_state();
                break;

        }
    }

    public void insert_state() {
        try {
            state_controller state = new state_controller();
            state.insert_state(estado);
        } catch (Exception e) {
            System.out.println("Error Insertar: " + e);
        }
    }

    private void update_state() throws Exception {
//        branch_controller branch;
        try {
            state_controller state = new state_controller();
            state.state_modify(estado);
            this.listar();
        } catch (Exception e) {
            throw e;
            //System.out.println("Error Insertar: " + e);
        }
    }

    public void delete_state(Estado est) {
        try {
            state_controller state = new state_controller();
            state.state_delete(est);
            this.listar();
        } catch (Exception e) {
            System.out.println("Error eliminar: " + e);
        }
    }

    public void changeCountry(ValueChangeEvent event) {
        selectedCountry = event.getNewValue().toString();
        System.out.println("PRUEBA: " + selectedCountry);
    }

    /*
    Lista la tabla
     */
    public void listar() throws Exception {
        try {
            state_controller state = new state_controller();
            lstEstado = state.listar(selectedCountry);
        } catch (Exception e) {
//            throw e;
            System.out.println("Error: " + e);
        }
    }

    public void list_country() throws Exception {
        try {
            state_controller state = new state_controller();
            lstPais = state.listarPais();
        } catch (Exception e) {
//            throw e;
            System.out.println("Error: " + e);
        }
    }

    public void leerID(Estado est) throws Exception {
        Estado estado;
        try {
            state_controller state = new state_controller();
            estado = state.leerID(est);
            System.out.println("presionaste modificar");
            if (estado != null) {
                this.estado = estado;
                System.out.println("chido, entró");
            }
            if (estado == null) {
                System.out.println("Vale pito esto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Cancelado", ".");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        estado = new Estado();
        estado = null;
        accion = null;
        System.out.println("cancelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (estado == null && accion == null) {
            System.out.println("empleado vacio modificar");
        } else {
            System.out.println("VALEEEEEE VEEEEEEEEEEEEEEEEEERGAAAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!!!!");
        }
    }

}
