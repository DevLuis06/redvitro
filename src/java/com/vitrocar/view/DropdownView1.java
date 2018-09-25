package com.vitrocar.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yeltsin
 */
@ManagedBean
@ViewScoped
public class DropdownView1 implements Serializable {
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String Marca; 
    private String Modelo;
    private String Anio;
    private Map<String,String> Marcas;
    private Map<String,String> Modelos;
    private Map<String,String> Anios;
     
    @PostConstruct
    public void init() {
        Marcas  = new HashMap<String, String>();
        Marcas.put("Audi", "Audi");
        Marcas.put("Mazda", "Mazda");
        Marcas.put("BMW", "BMW");
         
        Map<String,String> map = new HashMap<String, String>();
        map.put("A3", "A3");
        map.put("A6 Sedan", "A6 Sedan");
        map.put("R8", "R8");
        data.put("Audi", map);
         
        map = new HashMap<String, String>();
        map.put("CX-5", "CX-5");
        map.put("CX-3", "CX-3");
        map.put("Mazda 2", "Mazda 2");
        data.put("Mazda", map);
         
        map = new HashMap<String, String>();
        map.put("M140i", "M140i");
        map.put("M240i", "M240i");
        map.put("550i", "550i");
        data.put("BMW", map);
    }
    public Map<String, Map<String, String>> getData() {
        return data;
    }
 
    public String getMarca() {
        return Marca;
    }
 
    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
 
    public String getModelo() {
        return Modelo;
    }
 
    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }
    
    public String getAnio(){
        return Anio;
    }
    
    public void setAnio(String Anio){
        this.Anio = Anio;
    }
 
    public Map<String, String> getMarcas() {
        return Marcas;
    }
 
    public Map<String, String> getModelos() {
        return Modelos;
    }
 
    public void onMarcaChange() {
        if(Marca !=null && !Marca.equals(""))
            Modelos = data.get(Marca);
        else
            Modelos = new HashMap<String, String>();
    }
    public void onModeloChange(){
        if(Modelo !=null && !Modelo.equals(""))
            Modelos = data.get(Marca);
        else
            Modelos = new HashMap<String, String>();
   
    }
}
