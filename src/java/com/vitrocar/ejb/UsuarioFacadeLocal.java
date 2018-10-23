/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrocar.ejb;

import com.vitrocar.modelo.Empleado;

/**
 *
 * @author red-conexion
 */
public interface UsuarioFacadeLocal {
    
    
    
    Empleado iniciarSesion(Empleado emp);
    
}
