/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;


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
@ManagedBean(name = "branch")
@RequestScoped
public class branch_controller extends DataBase {

    private String nombre;
    private String direccion;
   

    private String sucursal;

   

    public void branch_insert(Sucursal suc) throws Exception {

        String nom = suc.getNombre();
        String dir = suc.getDireccion();
        String abr = suc.getAbreviacion();
        System.out.println("nombre: " + nom + "\ndireccion:" + dir + "\nAbreviacion: " + abr);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into sucursal (nombre,direccion,abreviacion) values(?,?,?); ");
            ps.setString(1, suc.getNombre());
            ps.setString(2, suc.getDireccion());
            ps.setString(3, suc.getAbreviacion());
            ps.execute();
            if (ps != null) {
                System.out.println("Empleado insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucursal Insertada", "Nombre:" + suc.getNombre());

                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
                
        }
    }

    public void branch_modify(Sucursal suc) throws Exception {

        String nom = suc.getNombre();
        String dir = suc.getDireccion();
        String abre = suc.getAbreviacion();

        System.out.println("nombre: " + nom + "\ndireccion:" + dir + "\nUser: " + abre);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("update sucursal set nombre = ?, direccion = ?, abreviacion = ? where id_sucursal= ?;");
            ps.setString(1, nom);
            ps.setString(2, dir);
            ps.setString(3, abre);
            
            ps.setInt(4, suc.getIdSucursal());
            ps.execute();
            if (ps != null) {
                System.out.println("Empleado insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucursal Modificado", "Nombre:" + nom);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }
    
    public List<Sucursal> listar() throws Exception {
   
        List<Sucursal> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select id_sucursal, nombre, direccion,abreviacion from sucursal order by id_sucursal asc;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Sucursal suc = new Sucursal();
                suc.setIdSucursal(rs.getInt("id_sucursal"));
                suc.setNombre(rs.getString("nombre"));
                suc.setDireccion(rs.getString("direccion"));
                suc.setAbreviacion(rs.getString("abreviacion"));
                lista.add(suc);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }

    public Sucursal leerID(Sucursal suc) throws Exception {
        System.out.println("entra");
        System.out.println("id:" + suc.getIdSucursal());
        Sucursal sucursal = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("select id_sucursal, nombre, direccion, abreviacion from sucursal where id_sucursal =?");
            ps.setInt(1, suc.getIdSucursal());
            rs = ps.executeQuery();
            while (rs.next()) {
                sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("id_sucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setAbreviacion(rs.getString("abreviacion"));
                System.out.println("id:" + suc.getIdSucursal());
                System.out.println("nombre sucursal:" + suc.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }
        return sucursal;
    }

    public void branch_delete(Sucursal suc) throws Exception {

        int id = suc.getIdSucursal();
        System.out.println("id sucursal:" + id);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM sucursal WHERE id_sucursal= ?;");
            ps.setInt(1, id);
            ps.execute();
            if (ps != null) {
                System.out.println("Empleado eliminado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucursal Eliminada", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }
    
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
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
