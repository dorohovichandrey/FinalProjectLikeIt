


function resetError(container) {
    container.className = 'form-group';
    if (container.lastChild.className == "alert alert-danger") {
        container.removeChild(container.lastChild);
    }
}

function showFgHasError(container){
    container.className = 'form-group has-error';
}

function showFgHasErrorPlusAllert(container, msg){
    container.className = 'form-group has-error';
    var msgElem = document.createElement('div');
    msgElem.className = "alert alert-danger";
    msgElem.role = "alert";
    msgElem.innerHTML = msg;
    container.appendChild(msgElem);
}



function validatePassword(formGroup, password){
    resetError(fg);
    var str=password;
    if((str.length < 6)||(str.search(/[0-9]/) == -1) ||(str.search(/[a-z]/) == -1)||(str.search(/[A-Z]/) == -1)) {
        showFgHasError(formGroup);
        return 1;
    }
    return 0;

}


function validateLogin(fg, login){
    resetError(fg);
    if (!(/^[A-z0-9_]{4,29}$/.test(login))) {
        showFgHasError(fg);
        return 1;

    }
    return 0;

}



function validatePassConfirmation(fg, passConfirm, pass){
    resetError(fg);
    if(passConfirm != pass)
    {
        showFgHasError(fg);
        return 1;
    }
    return 0;
}

function validateEmail(fg, email){
    resetError(fg);
    if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))) {
        showFgHasError(fg);
        return 1;
    }
    return 0;
}

function validateChangePassForm(form)
{

    var curPass = form.elements.curPass;
    var newPass = form.elements.newPass;
    var newPassConfirm = form.elements.newPassConfirm;

    var errorCounter = 0;


    errorCounter += validatePassword(newPass.parentNode, newPass.value);
    errorCounter += validatePassConfirmation(newPassConfirm.parentNode, newPassConfirm.value, newPass.value);

    if(errorCounter==0)
    {
        form.submit();
    }

}


