/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author red-conexion psycho06
 */
@ManagedBean(name = "principal")
@RequestScoped
public class main {

    private String marca;
    private String modelo;
    private String anio;
    
    private String marc;
    private String model;
    
    private List<String> get_marca = new ArrayList<>();
    private List<String> get_modelo = new ArrayList<>();
    private List<String> get_anio = new ArrayList<>();

    
    
    
    
    public List<String> get_category_marca() {
        try {
            DataBase db = new DataBase();

            Connection connection = null;
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vitrocar", "postgres", "123456");
            connection = db.connection();
            PreparedStatement ps = null;
            ps = connection.prepareStatement("select * from marca;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                get_marca.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return get_marca;
    }

    public List<String> get_category_modelo() {
        
        marc = marca;
        System.out.println("marca 2.1: " + marca);
        try {
            DataBase db = new DataBase();

            Connection connection = null;
            connection = db.connection();
            PreparedStatement ps = null;
            ps = connection.prepareStatement("select modelo from auto, marca where auto.id_marca = marca.id_marca and marca.nombre = '" + marca + "';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                get_modelo.add(rs.getString("modelo"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
          System.out.println("prueba 0.5");
        System.out.println("marca 0.5 : " + marc+"\n");
        return get_modelo;
        
        
        
    }

//    public void onModeloChange() {
//        model = modelo;
//        System.out.println("Modelo 100:"+model);
//        if (marca != null && !marca.equals("")) {
//            get_fuckanio(marca, modelo);
//        }
////        else
////            Modelos = new HashMap<String, String>();
//    }

    public List<String> get_fuckanio(String marca, String modelo) {
        System.out.println("prueba");
        System.out.println("marca 3 : " + marca);
        System.out.println("modelo : " + modelo);
        try {
            DataBase db = new DataBase();

            Connection connection = null;
            connection = db.connection();
            PreparedStatement ps = null;
            ps = connection.prepareStatement("select anio.anio as año from auto, marca, anio where anio.id_anio = auto.id_anio and auto.id_marca = marca.id_marca and marca.nombre = '" + marca + "' and auto.modelo='" + modelo + "';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                get_anio.add(rs.getString("año"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return get_anio;
    }

    public main() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public List<String> getGet_marca() {
        return get_marca;
    }

    public void setGet_marca(List<String> get_marca) {
        this.get_marca = get_marca;
    }

    public List<String> getGet_modelo() {
        return get_modelo;
    }

    public void setGet_modelo(List<String> get_modelo) {
        this.get_modelo = get_modelo;
    }

    public List<String> getGet_anio() {
        return get_anio;
    }

    public void setGet_anio(List<String> get_anio) {
        this.get_anio = get_anio;
    }

}
