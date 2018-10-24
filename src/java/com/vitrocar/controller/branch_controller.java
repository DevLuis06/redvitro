/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author red-conexion
 */
@ManagedBean(name = "branch")
@RequestScoped
public class branch_controller extends DataBase{

    
    private String sucursal;
    
    private String Sucursales;
    
    
    private List<String> get_sucursal = new ArrayList<>();
    Empleado emp = new Empleado();
    
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
        System.out.println("sucursal:"+Sucursales);
        
        List<Empleado> lista;
        Connection con = null;
        ResultSet rs= null;
        PreparedStatement st = null;
         DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select e.nombre, e.direccion, e.users from empleado e, puesto p where p.id_puesto = e.id_puesto;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            
            while (rs.next()) {
                Empleado puest = new Empleado();
                puest.setNombre(rs.getString("nombre"));
                puest.setDireccion(rs.getString("direccion"));
                puest.setUsers(rs.getString("users"));
                lista.add(puest);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
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
    
    
    

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public List<String> getGet_sucursal() {
        return get_sucursal;
    }

    public void setGet_sucursal(List<String> get_sucursal) {
        this.get_sucursal = get_sucursal;
    }

    public Empleado getEmp() {
        return emp;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public String getSucursales() {
        return Sucursales;
    }

    public void setSucursales(String Sucursales) {
        this.Sucursales = Sucursales;
    }
    
    
    
    
    

}
