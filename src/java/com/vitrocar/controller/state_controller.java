/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Estado;
import com.vitrocar.modelo.Pais;
import com.vitrocar.modelo.Sucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author red-conexion by Luis D' León
 */
@ManagedBean(name = "state_con")
@RequestScoped
public class state_controller extends DataBase {

    private String nombre;
    private String direccion;

    private String estado;

    public void insert_state(Estado est) throws Exception {

        String nom = est.getNombre();
        int pais = est.getPais();

        System.out.println("nombre: " + nom + "\nPaís:" + pais);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into estado (nombre,pais) values(?,?); ");
            ps.setString(1, est.getNombre());
            ps.setInt(2, est.getPais());
            ps.execute();
            if (ps != null) {
                System.out.println("Empleado insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Insertado", "Nombre:" + est.getNombre());

                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public void state_modify(Estado est) throws Exception {

        String nom = est.getNombre();
        int pais = est.getPais();

        System.out.println("nombre: " + nom + "\nID Pais:" + pais);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("update estado set nombre = ?, id_pais= ? where id_estado= ?;");
            ps.setString(1, nom);
            ps.setInt(2, pais);
            ps.setInt(3, est.getIdEstado());
            ps.execute();
            if (ps != null) {
                System.out.println("Estado Modificado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Modificado", "Nombre:" + nom);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }
    
    public int buscar(String Pais) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        int id = 0;
        try {
            con = db.connection();
            st = con.prepareStatement("select id_pais from pais where nombre = ?;");
            st.setString(1, Pais);
            rs = st.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return id;
    }
    

    public List<Estado> listar(String Pais) throws Exception {

        int id = 0;
        id = this.buscar(Pais);
        List<Estado> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        int i = 1;
        try {
            con = db.connection();
            st = con.prepareStatement("select id_estado,estado.nombre, pais.nombre country from estado join pais on estado.id_pais = ? order by id_estado asc;");
            st.setInt(1, id);
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Estado est = new Estado();
                est.setIdEstado(rs.getInt("id_estado"));
                est.setNombre(rs.getString("nombre"));
//                est.setPais((int) rs.get);
//                est.toString().replace("0", "México");

                lista.add(est);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }

    
    public List<Pais> listarPais() throws Exception {
        List<Pais> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select nombre from pais order by id_pais asc;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
                Pais pais = new Pais();
//                suc.setIdSucursal(rs.getInt("id_sucursal"));
                pais.setNombre(rs.getString("nombre"));
//                suc.setDireccion(rs.getString("direccion"));
//                suc.setAbreviacion(rs.getString("abreviacion"));
                lista.add(pais);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }
    
    
    public Estado leerID(Estado est) throws Exception {
        System.out.println("entra");
        System.out.println("id:" + est.getIdEstado());
        Estado estado = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("select id_estado, nombre from estado where id_estado =?");
            ps.setInt(1, est.getIdEstado());
            rs = ps.executeQuery();
            while (rs.next()) {
                estado = new Estado();
                estado.setIdEstado(rs.getInt("id_estado"));
                estado.setNombre(rs.getString("nombre"));
//                estado.setPais(rs.getInt("id_pais"));
                System.out.println("id:" + est.getIdEstado());
                System.out.println("nombre estado:" + est.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }
        return estado;
    }

    public void state_delete(Estado est) throws Exception {

        int id = est.getIdEstado();
        System.out.println("id estado:" + id);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM estado WHERE id_estado= ?;");
            ps.setInt(1, id);
            ps.execute();
            if (ps != null) {
                System.out.println("Estado eliminado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Eliminado", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
