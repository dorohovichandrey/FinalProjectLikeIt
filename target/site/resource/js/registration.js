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

    function validate(form) {
        var elems = form.elements;

        var errorCounter=0;
        resetError(elems.login.parentNode);
        if (!(/^[A-z0-9_]{4,29}$/.test(elems.login.value))) {
            errorCounter++;
            showError(elems.login.parentNode, ' ������� ���������� �����.');
        }

        resetError(elems.password.parentNode);
        if (!elems.password.value) {
            errorCounter++;
            showError(elems.password.parentNode, ' ������� ������.');
        } else{
            var str=elems.password.value;
            if((str.length < 6)||(str.search(/[0-9]/) == -1) ||(str.search(/[a-z]/) == -1)||(str.search(/[A-Z]/) == -1)) {
                errorCounter++;
                showError(elems.password.parentNode, ' ������� ���������� ������.');
            }
        }

	resetError(elems.passwordConfirmation.parentNode);
        if (elems.password.value != elems.passwordConfirmation.value) {
            errorCounter++;
            showError(elems.passwordConfirmation.parentNode, ' ������ �� ���������.');
        } 
        

	

        resetError(elems.emailAddr.parentNode);
        if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(elems.emailAddr.value))) {
            errorCounter++;
            showError(elems.emailAddr.parentNode, ' ������� ���������� email.');
        }

        if(errorCounter==0)
        {
            document.getElementById("registrationForm").submit();
        }

    }


