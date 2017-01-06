<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.text"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/bootstrap/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/registration.css"/>
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/navbar.css"/>
  <script src="${pageContext.servletContext.contextPath}/resource/js/lib/jquery-3.1.1.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resource/bootstrap/js/bootstrap.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/resource/js/registration.js"></script>
  <title>JS Bin</title>
</head>
<body>
<div>

<jsp:include page="/jsp/include/navbar.jsp" />

<form role="form" id="registrationForm" action="${pageContext.request.contextPath}/controller" method="POST">
 <input type="hidden" name="command" value="registration" />
 <div class="form-group">
  <label for="login">Логин</label>
  <input input name="login" type="text" class="form-control" id="login" placeholder="Введите логин">
  <p class="help-block">Не менее 4 символов. Допустимые символы: буквы, цифры, нижнее подчеркивание.</p>
 </div>
 <div class="form-group">
  <label for="pass">Пароль</label>
  <input input name="password" type="password" class="form-control" id="pass" placeholder="Введите пароль">
  <p class="help-block">Не меннее 6 символов. Хотя бы одну цифру и по букве в каждом регистре м имтииоиоиоотллть</p>
 </div>
 <div class="form-group">
  <label for="pass">Подтверждение пароля</label>
  <input name="passwordConfirmation" type="password" class="form-control" id="passConfirm" placeholder="Подтвердите пароль">
 </div>
  <div class="form-group">
  <label for="email">Email</label>
  <input input name="emailAddr" type="email" class="form-control" id="email" placeholder="Введите email">
 </div>
<input type="button" name="submitButton"  class="btn btn-success" onclick="validate(document.getElementById('registrationForm'))" value="Регистрация">
</form>

 <jsp:include page="/jsp/include/footer.jsp" />


<c:if test="${not empty param.login}">

    <script>

     var elems = document.getElementById("registrationForm").elements;
     var container = elems.login.parentNode;
     //container.className = 'form-group has-error';
     var msgElem = document.createElement('div');
     msgElem.className = "alert alert-danger";
     msgElem.role = "alert";
     msgElem.innerHTML = "Login \""+"${param.login}"+"\" is in use, dream up another one";
     container.appendChild(msgElem);

        document.getElementById("login").value = "${param.login}";
        document.getElementById("pass").value = "${param.password}";
        document.getElementById("passConfirm").value = "${param.passwordConfirmation}";
        document.getElementById("email").value = "${param.emailAddr}"



    </script>
</c:if>
 </div>
</body>
</html>