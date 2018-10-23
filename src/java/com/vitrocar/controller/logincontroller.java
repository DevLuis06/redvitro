/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Empleado;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.metamodel.relational.Database;

/**
 *
 * @author red-conexion
 */
@ManagedBean(name = "login")
public class logincontroller implements Serializable {

    Empleado emp = new Empleado();

    public String iniciarSesion() {
        String redireccion = null;

        try {
            DataBase db = new DataBase();
            Connection con = null;
            PreparedStatement st;
            ResultSet rs;
            FacesMessage mensaje = null;

            con = db.connection();
            st = con.prepareStatement("select id_puesto from empleado where users = ? and passwd = ?");
            st.setString(1, emp.getUsers());
            st.setString(2, emp.getPasswd());
            rs = st.executeQuery();

            while (rs.next()) {
                String puesto = rs.getString("id_puesto");
                System.out.println("puesto" + puesto);
                int pues = Integer.parseInt(puesto);

                if (puesto != null) {
                    switch (pues) {

                        case 1:
                            redireccion = "/ceo/main.xhtml";
                            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", emp.getUsers());
                            break;
                        case 2:
                            redireccion = "/manager/manager-dashboard.xhtml";
                            break;
                        case 3:
                            redireccion = "/sales/sales-dashboard.xhtml";
                            break;
                        case 4:
                            redireccion = "/technical/technical-dashboard.xhtml";
                            break;
                    }
                }else{
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                             "Credenciales no válidas");
                }

            }

        } catch (Exception e) {
            System.out.println("Error Login: " + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!!"));
        }
        return redireccion;
    }

    public Empleado getEmp() {
        return emp;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

}
