<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
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
  <title><fmt:message key="text.registration.title"/></title>
</head>
<body>

<%session.setAttribute("page","page.registration");%>
<div>

<jsp:include page="/jsp/include/navbar.jsp" />

<form role="form" id="registrationForm" action="${pageContext.request.contextPath}/controller" method="POST">
 <input type="hidden" name="command" value="registration" />
 <div class="form-group">
  <label for="login"><fmt:message key="text.registration.login"/></label>
  <input input name="login" type="text" class="form-control" id="login" placeholder="<fmt:message key="text.registration.loginPlaceholder"/>">
  <p class="help-block"><fmt:message key="text.registration.loginInfo"/></p>
 </div>
 <div class="form-group">
  <label for="pass"><fmt:message key="text.registration.password"/></label>
  <input input name="password" type="password" class="form-control" id="pass" placeholder="<fmt:message key="text.registration.passwordPlaceholder"/>">
  <p class="help-block"><fmt:message key="text.registration.passwordInfo"/></p>
 </div>
 <div class="form-group">
  <label for="pass"><fmt:message key="text.registration.passwordConf"/></label>
  <input name="passwordConfirmation" type="password" class="form-control" id="passConfirm" placeholder="<fmt:message key="text.registration.passwordConfPlaceholder"/>">
 </div>
  <div class="form-group">
  <label for="email"><fmt:message key="text.registration.email"/></label>
  <input input name="emailAddr" type="email" class="form-control" id="email" placeholder="<fmt:message key="text.registration.emailPlaceholder"/>">
 </div>
<input type="button" name="submitButton"  class="btn btn-success" onclick="validate(document.getElementById('registrationForm'))" value="<fmt:message key="text.registration.button"/>">
</form>

 <jsp:include page="/jsp/include/footer.jsp" />


<c:if test="${isLoginFree eq false}">

    <script>

     var elems = document.getElementById("registrationForm").elements;
     var container = elems.login.parentNode;
     //container.className = 'form-group has-error';
     var msgElem = document.createElement('div');
     msgElem.className = "alert alert-danger";
     msgElem.role = "alert";
     msgElem.innerHTML = "<fmt:message key="text.registration.error"/>";
     container.appendChild(msgElem);

        document.getElementById("login").value = "${login}";
        document.getElementById("pass").value = "${password}";
        document.getElementById("passConfirm").value = "${passwordConfirmation}";
        document.getElementById("email").value = "${emailAddr}"



    </script>
</c:if>
 </div>
</body>
</html>
 </fmt:bundle>