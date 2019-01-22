
package com.vitrocar.view;

import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class SelectOneView {
     
    private String option;
     private boolean disable;
    
    public String getOption() {
        return option;
    }
  
    public boolean isDisable() {
       return disable;
    }
    public void setDisable(boolean disable) {
       this.disable = disable;
    }
 
    public void setOption(String option) {
        this.option = option;
        
       
        System.out.println(option);
        
        if("asegurado".equals(option)){
            System.out.println("Funciono");
            System.out.println("disabled="+option);
            disable = true;
            System.out.println("boolean="+disable);
        }else{
           
            System.out.println("disabled="+option);
        }
        //option = "true";
        //System.out.println("debe ser true: "+option);
        
    }
}