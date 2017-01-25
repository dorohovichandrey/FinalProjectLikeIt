function showErrorWithMsg(container, errorMessage) {
    container.className = 'form-group has-error';
    var msgElem = document.createElement('div');
    msgElem.className = "alert alert-danger";
    msgElem.role = "alert";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}




function showError(container, errorMessage) {
    container.className = 'form-group has-error';
    var msgElem = document.createElement('div');
    msgElem.className = "alert alert-danger";
    msgElem.role = "alert";
    msgElem.innerHTML = errorMessage;
    //container.appendChild(msgElem);
}

function resetError(container) {
    container.className = 'form-group';
    if (container.lastChild.className == "alert alert-danger") {
        container.removeChild(container.lastChild);
    }
}

function validateCreateQue(form) {
    var elems = form.elements;

    var errorCounter=0;



    resetError(elems.header.parentNode);
    if (elems.header.value.length < 10 || elems.header.value.length > 100) {
        errorCounter++;
        showError(elems.header.parentNode, 'Length greater then 100 or lower 10');
    }

    resetError(elems.questionText.parentNode);
    if (elems.questionText.value.length < 10 || elems.questionText.value.length > 1000) {
        errorCounter++;
        showError(elems.questionText.parentNode, 'Length greater then 1000 or lower 10');
    }

    if(errorCounter==0){
        document.getElementById("createQueForm").submit();
    }

}

