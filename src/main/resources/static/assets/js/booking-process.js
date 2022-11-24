var step1 = document.querySelector(".step1");
var step2 = document.querySelector(".step2");

window.onload = function(){
    initialDisplay();
    hideDriverInfo();
    let dateChangeInputs = document.querySelectorAll("input[class*='date-change']");
    dateChangeInputs.forEach(function(e){
        e.onchange = function(){
            let startDate = new Date(document.querySelector("#startDate").value) ;
            let endDate = new Date(document.querySelector("#endDate").value) ;
            let date = dateCalculate(startDate, endDate);
            document.querySelector("#totalDay").innerHTML = '' + date;
            let price = document.querySelector("#pricePerDay").innerHTML;
            document.querySelector("#totalPrice").innerHTML = '' + date * price;
        };
    });
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

    let renterLicense = document.getElementById("renterDrivingLicense");
    let driverLicense = document.getElementById("driverDrivingLicense");

    var checkbox = document.getElementById("notRenter");
    checkbox.onchange = function(){
        if(checkbox.checked == true){
            driverInfo.style.display = "";
            renterLicense.required = false;
            driverLicense.required = true;
        } else {
            driverInfo.style.display = "none";
            renterLicense.required = true;
            driverLicense.required = false;
        }
    }
}

function dateCalculate(startDate, endDate){
    let diffTime = endDate - startDate;
    let diffDate = Math.floor(diffTime/(24*3600*1000))+1;
    console.log(diffDate);
    return diffDate;
}

