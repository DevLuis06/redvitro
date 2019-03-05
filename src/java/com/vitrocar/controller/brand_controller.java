/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Anio;
import com.vitrocar.modelo.Marca;
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
public class brand_controller extends DataBase {

    public void brand_insert(Marca marca) throws Exception {

        String nom = marca.getNombre();
        System.out.println("nombre: " + nom);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into marca (nombre) values(?); ");
            ps.setString(1, marca.getNombre());
            ps.execute();
            if (ps != null) {
                System.out.println("Marca  insertada");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca Insertada", "Marca:" + marca.getNombre());

                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public void brand_modify(Marca marca) throws Exception {

        String nom = marca.getNombre();

        System.out.println("Marca modificar: " + nom);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("update marca set nombre= ? where id_marca= ?;");
            ps.setString(1, nom);
            ps.setInt(2, marca.getId_marca());
            ps.execute();
            if (ps != null) {
                System.out.println("Marca modificado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca Modificado", "Marca:" + nom);
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
    public List<Marca> listar() throws Exception {

        List<Marca> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select id_marca, nombre from marca order by id_marca asc;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId_marca(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre"));
                lista.add(marca);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }

    public Marca leerID(Marca marca) throws Exception {
        System.out.println("entra");
        System.out.println("id:" + marca.getId_marca());
        Marca marcas = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("select id_marca, nombre from marca where id_marca=?");
            ps.setInt(1, marca.getId_marca());
            rs = ps.executeQuery();
            while (rs.next()) {
                marcas = new Marca();
                marcas.setId_marca(rs.getInt("id_marca"));
                marcas.setNombre(rs.getString("nombre"));
                System.out.println("id:" + marca.getId_marca());
                System.out.println("marca:" + marca.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }
        return marcas;
    }

    public void brand_delete(Marca marca) throws Exception {

        int id = marca.getId_marca();
        System.out.println("id marca:" + id);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM marca WHERE id_marca= ?;");
            ps.setInt(1, id);
            ps.execute();
            if (ps != null) {
                System.out.println("marca eliminado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca Eliminado", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }
}
