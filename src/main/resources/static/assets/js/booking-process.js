var step1 = document.querySelector(".step1");
var step2 = document.querySelector(".step2");
var step3 = document.querySelector(".step3");

window.onload = function(){
    initialDisplay();
    hideDriverInfo();
}

function initialDisplay(){
    step1.style.display = "block"
    step2.style.display = "none";
    step3.style.display = "none";
}

function step2Display(){
    step2.style.display = "block";
    step1.style.display = "none";
    step3.style.display = "none"
}

function step3Display(){
    step3.style.display = "block";
    step1.style.display = "none";
    step2.style.display = "none"
}

function hideDriverInfo(){
    var driverInfo = document.getElementById("driver");
    driverInfo.style.display = "none";

    var checkbox = document.getElementById("notRenter");
    checkbox.onchange = function(){
        if(checkbox.checked){
            driverInfo.style.display = "";
        } else {
            driverInfo.style.disaplay = "none"
        }
    }
}