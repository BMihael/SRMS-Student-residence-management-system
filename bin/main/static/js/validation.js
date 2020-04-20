 function passwordValidation(){
    var password = document.getElementById("password").value
    var confirmPass = document.getElementById("confirmPass").value
    var checkbox = document.getElementById("checkbox").checked

    var button = document.getElementById("button")
    if(password === confirmPass && password) {
        document.getElementById("notMatch").innerHTML = '';
        checkbox ? button.disabled = false : button.disabled = true;
    }
    else {
        document.getElementById("notMatch").innerHTML = "Password and confirm password do not match.";
        button.disabled = true
    }
}

function validatePrivatePolicy(){
    var checkbox = document.getElementById("checkbox").checked
    var button = document.getElementById("button")
    checkbox ? button.disabled = false : button.disabled = true;
}