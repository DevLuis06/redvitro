function toggle(id) {
    var element = document.getElementById(id);
    var sobget = document.getElementsByClassName("ui-helper-hidden-accessible");
    
    var element2 = document.getElementById("j_idt6:j_idt17:j_idt19:0");
    
    
 alert(element2.value);
    for (var i = 0; i < 4; i++) {
        console.log(sobget[i].value);
        if(sobget[i].value === "asegurado"){
           console.log(sobget[i].value+": 0"); 
        }else{
            
        }
       
        
       
        
       /* if(element.style.display === 'block' && sobget[i].value === "asegurado") {
            element.classList.remove("ui-state-disabled");
            element.classList.add("ui-state-default");
            element.style.display = "";
            alert("hecho");
            break;
        }else{
            /*element.classList.remove("ui-state-default");
            element.classList.add("ui-state-disabled");
            element.style.display = "block";*/
        // --}
        
        
        /*else if(element.style.display === 'block' && sobget[i].value === "general"){
            element.classList.remove("ui-state-default");
            element.classList.add("ui-state-disabled");
            element.style.display = "block";
        }/*else if(){
            element.classList.remove("ui-state-default");
            element.classList.add("ui-state-disabled");
            element.style.display = "block";
        }*/
    }                                                   
}