var step1 = document.querySelector(".step1");
var step2 = document.querySelector(".step2");

window.onload = function(){
    initialDisplay();
    hideDriverInfo();
}

function initialDisplay(){
    step1.style.display = "block"
    step2.style.display = "none";
}

function step2Display(){
    step2.style.display = "block";
    step1.style.display = "none";
}

function toStep2(){
    var toStep2Button = document.getElementById("to-step-2");
    toStep2Button.onclick = step2Display();
}

function toStep1(){
    var toStep1Button = document.getElementById("to-step-1");
    toStep1Button.onclick = initialDisplay();
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