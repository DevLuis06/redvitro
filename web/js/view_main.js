function toggle(id) {
    var element = document.getElementById(id);
    var sobget = document.getElementsByClassName("ui-helper-hidden-accessible");
    
    for (var i = 0; i < 4; i++) {
        console.log(sobget[i].value);
        if(element.style.display === 'block' && sobget[i].value === "asegurado") {
            element.classList.remove("ui-state-disabled");
            element.classList.add("ui-state-default");
            element.style.display = "";
            alert("hecho");
            
        } else if(element.style.display === 'block' && sobget[i].value === "general"){
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