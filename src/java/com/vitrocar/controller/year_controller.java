/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Anio;
import com.vitrocar.modelo.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author red-conexion
 */
public class year_controller extends DataBase{
     
    public void anio_insert(Anio ani) throws Exception {

        int nom = ani.getAnio();
        System.out.println("nombre: " + nom);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into anio (anio) values(?); ");
            ps.setInt(1, ani.getAnio());
            ps.execute();
            if (ps != null) {
                System.out.println("Año insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Año Insertado", "Año:" + ani.getAnio());

                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
                
        }
    }

    public void anio_modify(Anio ani) throws Exception {

        int nom = ani.getAnio();

        System.out.println("año modificar: " + nom);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("update anio set anio= ? where id_anio= ?;");
            ps.setInt(1, nom);
            ps.setInt(2, ani.getId_anio());
            ps.execute();
            if (ps != null) {
                System.out.println("Año modificado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Año Modificado", "Año:" + nom);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }
    
    
    
    /*
    Función para llenar la tabla 
    */  
    public List<Anio> listar() throws Exception {
   
        List<Anio> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select id_anio, anio from anio order by id_anio asc;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Anio ani = new Anio();
                ani.setId_anio(rs.getInt("id_anio"));
                ani.setAnio(rs.getInt("anio"));
                lista.add(ani);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }

    public Anio leerID(Anio ani) throws Exception {
        System.out.println("entra");
        System.out.println("id:" + ani.getId_anio());
        Anio anios = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("select id_anio, anio from anio where id_anio=?");
            ps.setInt(1, ani.getId_anio() );
            rs = ps.executeQuery();
            while (rs.next()) {
                anios = new Anio();
                anios.setId_anio(rs.getInt("id_anio"));
                anios.setAnio(rs.getInt("anio"));
                System.out.println("id:" + ani.getId_anio());
                System.out.println("año:" + ani.getAnio());
            }
        } catch (Exception e) {
            throw e;
        }
        return anios;
    }

    public void anio_delete(Anio ani) throws Exception {

        int id = ani.getId_anio();
        System.out.println("id sucursal:" + id);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM anio WHERE id_anio= ?;");
            ps.setInt(1, id);
            ps.execute();
            if (ps != null) {
                System.out.println("año eliminado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Año Eliminado", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }
    
}
