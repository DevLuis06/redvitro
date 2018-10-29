///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vitrocar.dao;
//
//import com.vitrocar.model.Empleado;
//import hibernate.HibernateUtil;
//import org.hibernate.Session;
//
//
//
///**
// *
// * @author red-conexion
// */
//public class UsuarioDaoImpl implements UsuarioDao{
//
//    @Override
//    public Empleado findbyEmpleado(Empleado empleado) {
//        Empleado model = null;
//        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
//        String sql ="";
//        try {
//            sesion.beginTransaction();
//            model = (Empleado) sesion.createQuery(sql).uniqueResult();
//            sesion.beginTransaction().begin();
//        } catch (Exception e) {
//            sesion.beginTransaction().rollback();
//        }
//        return model;
//    }
//
//    @Override
//    public Empleado login(Empleado empleado) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
