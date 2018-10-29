/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.ejb;

import com.vitrocar.modelo.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jboss.weld.bean.builtin.AbstractFacade;

/**
 * q
 * @author red-conexion
 */
public class UsuarioFacade  implements UsuarioFacadeLocal{
    @PersistenceContext(unitName ="primePU")
    private EntityManager em;
    
    protected EntityManager getEntityManager(){
        return em;
    }
    
    
    public Empleado iniciarSesion(Empleado emp){
        Empleado  empleado = null;
        String consulta;
        try {
            consulta = "FROM Empleado e where e.users = ?1 and e.passwd = 2?";
            Query query = em.createQuery(consulta);
            query.setParameter(1,emp.getUsers());
            query.setParameter(2,emp.getPasswd());
            
            List<Empleado> lista = query.getResultList();
            if (!lista.isEmpty()) {
                empleado = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            em.close();
        }
        return empleado;
    }

//    @Override
//    public Empleado iniciarSesion(Empleado emp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
}
