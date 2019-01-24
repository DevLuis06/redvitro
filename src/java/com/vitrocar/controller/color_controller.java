/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Color;
import com.vitrocar.modelo.Sucursal;
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
public class color_controller extends DataBase{
    
  
    
    public void color_insert(Color col) throws Exception {

        String nom = col.getNombre();
        System.out.println("nombre: " + nom);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into color (nombre) values(?); ");
            ps.setString(1, col.getNombre());
            ps.execute();
            if (ps != null) {
                System.out.println("Color insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Color Insertado", "Nombre:" + col.getNombre());

                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
                
        }
    }

    public void color_modify(Color col) throws Exception {

        String nom = col.getNombre();

        System.out.println("nombre: " + nom);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("update color set nombre = ? where id_color= ?;");
            ps.setString(1, nom);
            ps.setInt(2, col.getId_color());
            ps.execute();
            if (ps != null) {
                System.out.println("Color modificado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Color Modificado", "Nombre:" + nom);
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
    public List<Color> listar() throws Exception {
   
        List<Color> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select id_color, nombre from color order by id_color asc;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Color col = new Color();
                col.setId_color(rs.getInt("id_color"));
                col.setNombre(rs.getString("nombre"));
                lista.add(col);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }

    public Color leerID(Color col) throws Exception {
        System.out.println("entra");
        System.out.println("id:" + col.getId_color());
        Color color = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("select id_color, nombre from color where id_color =?");
            ps.setInt(1, col.getId_color());
            rs = ps.executeQuery();
            while (rs.next()) {
                color = new Color();
                color.setId_color(rs.getInt("id_color"));
                color.setNombre(rs.getString("nombre"));
                System.out.println("id:" + col.getId_color());
                System.out.println("nombre color:" + col.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }
        return color;
    }

    public void color_delete(Color col) throws Exception {

        int id = col.getId_color();
        System.out.println("id sucursal:" + id);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM color WHERE id_color= ?;");
            ps.setInt(1, id);
            ps.execute();
            if (ps != null) {
                System.out.println("color eliminado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Color Eliminado", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }
    
}
