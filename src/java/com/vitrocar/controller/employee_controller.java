/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import com.vitrocar.modelo.Empleado;
import com.vitrocar.modelo.Puesto;
import com.vitrocar.modelo.Sucursal;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author red-conexion
 */
@ManagedBean(name = "employee")
@SessionScoped
public class employee_controller extends DataBase implements Serializable {

    private String nombre;
    private String direccion;
    private String users;
    private String passwd;

    public String sucursal;

    //private String Sucursales;
    private List<String> get_sucursal = new ArrayList<>();
//    Empleado emp = new Empleado();

    public void branch_insert(Empleado empl) throws Exception {

        String nom = this.getNombre();
        String dir = this.getDireccion();
        String usr = this.getUsers();
        String pw = this.getPasswd();
        System.out.println("nombre: " + nom + "\ndireccion:" + dir + "\nUser: " + usr + "\nPasswd: " + pw);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("insert into empleado (nombre,direccion,users,passwd) values(?,?,?,?); ");
            ps.setString(1, empl.getNombre());
            ps.setString(2, empl.getDireccion());
            ps.setString(3, empl.getUsers());
            ps.setString(4, empl.getPasswd());
            ps.execute();
            if (ps != null) {
                System.out.println("Empleado insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Insertado", "Nombre:" + empl.getNombre());

                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public void branch_modify_employee(Empleado emp) throws Exception {

        String nom = emp.getNombre();
        String dir = emp.getDireccion();
        String usr = emp.getUsers();
        String pw = emp.getPasswd();
        System.out.println("nombre: " + nom + "\ndireccion:" + dir + "\nUser: " + usr + "\nPasswd: " + pw);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("update empleado set nombre = ?, direccion = ?, users = ?, passwd = ? where id_empleado = ?;");
            ps.setString(1, nom);
            ps.setString(2, dir);
            ps.setString(3, usr);
            ps.setString(4, pw);
            ps.setInt(5, emp.getIdEmpleado());
            ps.execute();
            if (ps != null) {
                System.out.println("Empleado insertado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Modificado", "Nombre:" + nom);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
    }

//    public List<String> get_sucursal() {
//        try {
//            DataBase db = new DataBase();
//            Connection connection = null;
//            connection = db.connection();
//            PreparedStatement ps = null;
//            ps = connection.prepareStatement("select * from sucursal;");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                get_sucursal.add(rs.getString("nombre"));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return get_sucursal;
//    }
    
    
    /*
    *Esta función llena el SelectOneMenu para filtrar la búsqueda.
    */
    public List<Sucursal> listarSucursal() throws Exception {
        List<Sucursal> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        try {
            con = db.connection();
            st = con.prepareStatement("select id_sucursal, nombre, direccion, abreviacion from sucursal order by id_sucursal asc;");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
                Sucursal suc = new Sucursal();
//                suc.setIdSucursal(rs.getInt("id_sucursal"));
                suc.setNombre(rs.getString("nombre"));
//                suc.setDireccion(rs.getString("direccion"));
//                suc.setAbreviacion(rs.getString("abreviacion"));
                lista.add(suc);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.close(con);
        }
        return lista;
    }
    
    
    /**
     *Esta funcion sirve para buscar el Id de sucursal para filtrar la tabla
     **/
    public int buscar(String Suc) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();
        int id = 0;
        try {
            con = db.connection();
            st = con.prepareStatement("select id_sucursal from sucursal where nombre = ?;");
            st.setString(1, Suc);
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

    public List<Empleado> listar(String Sucursal) throws Exception {
        System.out.println("Sucursal Seleccionada Controller:" + Sucursal);
        int id = 0;

        id = this.buscar(Sucursal);

        System.out.println("id recibido: " + id);
        List<Empleado> lista;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        DataBase db = new DataBase();

        try {
            con = db.connection();
            st = con.prepareStatement("select e.id_empleado,e.nombre, e.direccion, e.users, e.passwd, p.nombre from empleado e, puesto p, sucursal s"
                    + " where p.id_puesto = e.id_puesto and s.id_sucursal = e.id_sucursal and e.id_sucursal =? ;");
            st.setInt(1, id);
//            st = con.prepareStatement("select * from empleado;");

            rs = st.executeQuery();
            lista = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
//                Empleado emple = rs.getObject(i, Empleado);
//                lista.add(emp);
                Empleado puest = new Empleado();
                puest.setIdEmpleado(rs.getInt("id_empleado"));
                puest.setNombre(rs.getString("nombre"));
                puest.setDireccion(rs.getString("direccion"));
                puest.setUsers(rs.getString("users"));
                puest.setPasswd(rs.getString("passwd"));
//                puest.setPuesto((Puesto) rs.getObject("id_puesto"));
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
        System.out.println("entra");
        System.out.println("id:" + emp.getIdEmpleado());
        Empleado emple = null;
        ResultSet rs;
        DataBase db = new DataBase();
        Connection con = null;
        try {
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("select id_empleado,nombre,direccion,users,passwd from empleado where id_empleado=?");
            ps.setInt(1, emp.getIdEmpleado());
            rs = ps.executeQuery();
            while (rs.next()) {
                emple = new Empleado();
                emple.setIdEmpleado(rs.getInt("id_empleado"));
                emple.setNombre(rs.getString("nombre"));
                emple.setDireccion(rs.getString("direccion"));
                emple.setUsers(rs.getString("users"));
                emple.setPasswd(rs.getString("passwd"));

                System.out.println("id:" + emple.getIdEmpleado());
                System.out.println("nombre:" + emple.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }

        return emple;
    }

    public void branch_delete_employee(Empleado emp) throws Exception {

        int id = emp.getIdEmpleado();
        System.out.println("id empleado:" + id);

        try {
            DataBase db = new DataBase();
            Connection con;
            con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM empleado WHERE id_empleado = ?;");
            ps.setInt(1, id);
            ps.execute();
            if (ps != null) {
                System.out.println("Empleado eliminado");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Eliminado", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            connection().close();
        }
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
//    public Empleado getEmp() {
//        return emp;
//    }
//
//    public void setEmp(Empleado emp) {
//        this.emp = emp;
//    }
    public List<String> get_sucursal_prue() {
        try {
            DataBase db = new DataBase();

            Connection connection = null;
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vitrocar", "postgres", "123456");
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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
