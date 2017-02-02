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

function validateCreateAnsw(form) {
    var elems = form.elements;

    var errorCounter=0;


    showError(elems.answerText.parentNode, 'Length greater then 1000 or lower 10');

    resetError(elems.answerText.parentNode);
    if (elems.answerText.value.length < 10 || elems.answerText.value.length > 1000) {
        errorCounter++;
        showError(elems.answerText.parentNode, 'Length greater then 1000 or lower 10');
    }

    if(errorCounter==0){
        document.getElementById("createAnswForm").submit();
    }

}
