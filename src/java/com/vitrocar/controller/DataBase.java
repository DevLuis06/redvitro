/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.controller;

import java.sql.*;

/**
 *
 * @author kubuntu
 */
public class DataBase {
    
    public Connection connection() throws Exception {
    
    Connection db = null;        
    Class.forName("org.postgresql.Driver"); //cargar el driver manualmente
    String urlDatabase =  "jdbc:postgresql://localhost:5432/vitrocar";
  
    db = DriverManager.getConnection(urlDatabase,  "postgres", "bell");
    
    return db;
    
}
    
    public ResultSet Query(String sql) throws Exception {
        ResultSet resultado;
        try {
            Statement sentencia = this.connection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        return resultado;
    }
    
    
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
