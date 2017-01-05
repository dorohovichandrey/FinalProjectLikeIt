<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <style>
        td select,
        td input {
            width: 150px;
        }

        label {
            display: block;
        }

        .error input,
        .error textarea {
            border: 1px solid red;
        }

        .error {
            color: red;
        }

        #registrationForm{
            width: 250px;
            height: 250px;
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin: auto;
        }
    </style>
</head>

<body>


<form id="registrationForm"  action="${pageContext.request.contextPath}/controller" method="POST">
    <input type="hidden" name="command" value="registration" />
    <table>
        <tr>
            <td>Логин</td>
            <td>
                <input name="login" type="text">
            </td>
        </tr>
        <tr>
            <td>Ваш пароль</td>
            <td>
                <input name="password" type="password">
            </td>
        </tr>
        <tr>
            <td>Повторите пароль</td>
            <td>
                <input name="passwordConfirmation" type="password">
            </td>
        </tr>
        <tr>
            <td>email</td>
            <td>
                <input name="emailAddr" type="text">
            </td>
        </tr>
    </table>



    <input type="button" onclick="validate(document.getElementById('registrationForm'))" value="Зарегистрироваться">
</form>
<a href="/LikeIt/index.jsp">BACK</a>

<script>
    function showError(container, errorMessage) {
        container.className = 'error';
        var msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
    }

    function resetError(container) {
        container.className = '';
        if (container.lastChild.className == "error-message") {
            container.removeChild(container.lastChild);
        }
    }

    function validate(form) {
        var elems = form.elements;

        var errorCounter=0;
        resetError(elems.login.parentNode);
        if (!(/^[A-z0-9_]{4,29}$/.test(elems.login.value))) {
            errorCounter++;
            showError(elems.login.parentNode, ' Введите корректный логин.');
        }

        resetError(elems.password.parentNode);
        if (!elems.password.value) {
            errorCounter++;
            showError(elems.password.parentNode, ' Укажите пароль.');
        } else if (elems.password.value != elems.passwordConfirmation.value) {
            errorCounter++;
            showError(elems.password.parentNode, ' Пароли не совпадают.');
        } else{
            var str=elems.password.value;
            if((str.length < 6)||(str.search(/[0-9]/) == -1) ||(str.search(/[a-z]/) == -1)||(str.search(/[A-Z]/) == -1)) {
                errorCounter++;
                showError(elems.password.parentNode, ' Введите корректный пароль.');
            }
        }

        resetError(elems.emailAddr.parentNode);
        if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(elems.emailAddr.value))) {
            errorCounter++;
            showError(elems.emailAddr.parentNode, ' Введите корректный email.');
        }

        if(errorCounter==0)
        {
            document.getElementById("registrationForm").submit();
        }




    }
</script>


</body>

</html>
