function aseg_clk(){
    var combo_ = document.getElementById('j_idt6:j_idt17:asegu');
    var input_gara = document.getElementById("j_idt6:j_idt17:ref");
    var input_cort = document.getElementById("j_idt6:j_idt17:aut");
    
    //si está block se habilita y todos los demas se deshabilitan
    if(combo_.style.display === 'block'){
        combo_.classList.remove("ui-state-disabled");
        input_gara.classList.add("ui-state-disabled");
        input_gara.style.display = "block";
        input_cort.classList.add("ui-state-disabled");
        input_cort.style.display = "block";
        combo_.style.display = "";
    }else{
    //
        combo_.classList.add("ui-state-disabled");
        combo_.style.display = "block";
        input_gara.style.display = "";
        input_cort.style.display = "";
    }
}

function gara_clk(){
    var input_gara = document.getElementById("j_idt6:j_idt17:ref");
    var combo_ = document.getElementById('j_idt6:j_idt17:asegu');
    var input_cort = document.getElementById("j_idt6:j_idt17:aut");
    
    if(input_gara.style.display === 'block'){
        input_gara.classList.remove("ui-state-disabled");
        input_cort.classList.add("ui-state-disabled");
        input_cort.style.display = "block";
        combo_.classList.add("ui-state-disabled");
        combo_.style.display = "block";
        input_gara.style.display = "";
    }else{
        input_gara.classList.add("ui-state-disabled");
        input_gara.style.display = "block";
        input_cort.style.display = "";
        combo_.style.display = "";
    }
}

function cort_clk(){
    var input_cort = document.getElementById("j_idt6:j_idt17:aut");
    var input_gara = document.getElementById("j_idt6:j_idt17:ref");
    var combo_ = document.getElementById('j_idt6:j_idt17:asegu');
    
    if(input_cort.style.display === 'block'){
        input_cort.classList.remove("ui-state-disabled");
        input_gara.classList.add("ui-state-disabled");
        input_gara.style.display = "block";
        combo_.classList.add("ui-state-disabled");
        combo_.style.display = "block";
        input_cort.style.display = "";
    }else{
        input_cort.classList.add("ui-state-disabled");
        input_cort.style.display = "block";
        input_gara.style.display = "";
        combo_.style.display = "";
    }
}
