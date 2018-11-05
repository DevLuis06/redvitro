/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Empleado;
import com.vitrocar.modelo.Puesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.metamodel.relational.Database;

/**
 *
 * @author red-conexion
 */
@ManagedBean(name = "branch")
@RequestScoped
public class branch_controller extends DataBase {

    private String sucursal;

    private String Sucursales;

    private List<String> get_sucursal = new ArrayList<>();
    Empleado emp = new Empleado();

    
    public void branch_insert(Empleado empl) throws Exception{
        try {
            DataBase db = new DataBase();
            Connection con=null;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into empleado (nombre, direccion, users,passwd) values('?','?','?','?'); ");
            ps.setString(1, empl.getNombre());
            ps.setString(2, empl.getDireccion());
            ps.setString(3,empl.getUsers());
            ps.setString(4,empl.getPasswd());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            
        }
    }
    
    
    
    public List<String> get_sucursal() {
        try {
            DataBase db = new DataBase();
            Connection connection = null;
            connection = db.connection();
            PreparedStatement ps = null;
            ps = connection.prepareStatement("select * from sucursal;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                get_sucursal.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return get_sucursal;
    }

    public List<Empleado> listar() throws Exception {
        Sucursales = sucursal;
        System.out.println("sucursal:" + Sucursales);

        List<Empleado> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select e.nombre, e.direccion, e.users, p.nombre from empleado e, puesto p where p.id_puesto = e.id_puesto;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
//                Empleado emple = rs.getObject(i, Empleado);
//                lista.add(emp);
                Empleado puest = new Empleado();

                puest.setNombre(rs.getString("nombre"));
                puest.setDireccion(rs.getString("direccion"));
                puest.setUsers(rs.getString("users"));
//                puest.setPuesto(rs.);
                lista.add(puest);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }

    public Empleado leerID(Empleado emp) throws Exception {
        Empleado emple = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("SELECT nombre, direccion, users FROM empleado where id_empleado=?");
            ps.setInt(1, emp.getIdEmpleado());
            rs = ps.executeQuery();
            while (rs.next()) {
                emple = new Empleado();
                emple.setNombre(rs.getString("nombre"));
                emple.setDireccion(rs.getString("direccion"));
                emple.setUsers(rs.getString("users"));
            }
        } catch (Exception e) {
        }

        return emple;
    }

//    
//    public List<Empleado> listar() throws Exception{
//        //System.out.println("sucursal: "+getSucursal());
//        List<Empleado> lista;
//        ResultSet rs= null;
//        try {
//            this.connection();
//            PreparedStatement ps = this.connection().prepareCall("select e.nombre, e.direccion, e.users from empleado e, puesto p where p.id_puesto = e.id_puesto ; ");
//            ps.setString(1, this.getSucursal());
//            rs = ps.executeQuery();
//            lista = new ArrayList();
//            while (rs.next()) {                
//                    Empleado em = new Empleado();
//                    em.setNombre(rs.getString("nombre"));
//                    em.setDireccion(rs.getString("direccion"));
//                    em.setUsers(rs.getString("users"));
//                    lista.add(em);
//            }
//            
//        } catch (Exception e) {
//            throw e;
//        }
//        return lista;
//    }
    public Empleado getEmp() {
        return emp;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

}
