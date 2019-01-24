package com.vitrocar.controller;

import com.vitrocar.modelo.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author red-conexion by Luis D' León
 */
@ManagedBean(name = "country")
@ViewScoped
public class country_controller extends DataBase {

    private String nombre;
    private String direccion;

    public void country_insert(Pais pais) throws Exception {

        String nom = pais.getNombre();
        String abr = pais.getAbreviacion();
        System.out.println("nombre pais: " + nom + "\nAbreviacion: " + abr);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into pais (nombre,abreviacion) values(?,?); ");
            ps.setString(1, pais.getNombre());
            ps.setString(2, pais.getAbreviacion());
            ps.execute();
            if (ps != null) {
                System.out.println("País insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "País Insertado", "Nombre:" + pais.getNombre());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public void country_modify(Pais pais) throws Exception {

        String nom = pais.getNombre();
        String abre = pais.getAbreviacion();
        System.out.println("nombre pais mod: " + nom + "\nAbreviación: " + abre);
        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("update pais set nombre = ?, abreviacion = ? where id_pais= ?;");
            ps.setString(1, nom);
            ps.setString(2, abre);

            ps.setInt(3, pais.getIdPais());
            ps.execute();
            if (ps != null) {
                System.out.println("País Modificado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "País Modificado", "Nombre:" + nom);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }

    public List<Pais> list() throws Exception {

        List<Pais> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select id_pais, nombre,abreviacion from pais order by id_pais asc;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {

                Pais pais = new Pais();
                pais.setIdPais(rs.getInt("id_pais"));
                pais.setNombre(rs.getString("nombre"));
                pais.setAbreviacion(rs.getString("abreviacion"));
                lista.add(pais);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }

    public Pais readID(Pais country) throws Exception {
        System.out.println("entra");
        System.out.println("id pais:" + country.getIdPais());
        Pais pais = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("select id_pais, nombre, abreviacion from pais where id_pais=?");
            ps.setInt(1, country.getIdPais());
            rs = ps.executeQuery();
            while (rs.next()) {
                pais = new Pais();
                pais.setIdPais(rs.getInt("id_pais"));
                pais.setNombre(rs.getString("nombre"));
                pais.setAbreviacion(rs.getString("abreviacion"));
                System.out.println("id:" + country.getIdPais());
                System.out.println("nombre sucursal:" + country.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }
        return pais;
    }

    public void country_delete(Pais pais) throws Exception {

        int id = pais.getIdPais();
        System.out.println("id sucursal:" + id);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM pais WHERE id_pais= ?;");
            ps.setInt(1, id);
            ps.execute();
            if (ps != null) {
                System.out.println("Paiís eliminado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "País Eliminado", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
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
