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
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/logIn.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/navbar.css"/>
    <script src="${pageContext.servletContext.contextPath}/resource/js/lib/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resource/bootstrap/js/bootstrap.min.js"></script>

    <title><fmt:message key="text.logIn.title"/></title>
</head>
<body>

<%session.setAttribute("page","page.logIn");%>
<div>

    <jsp:include page="/jsp/include/navbar.jsp" />
    <form role="form" id="loginForm" action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="logIn" />
        <div class="form-group">
            <label for="login"><fmt:message key="text.logIn.login"/></label>
            <input name="login" type="text" class="form-control" id="login" placeholder="<fmt:message key="text.logIn.loginPlaceholder"/>">
        </div>
        <div class="form-group">
            <label for="pass"><fmt:message key="text.logIn.password"/></label>
            <input name="password" type="password" class="form-control" id="pass" placeholder="<fmt:message key="text.logIn.passwordPlaceholder"/>">
        </div>
        <input type="submit" class="btn btn-success" value="<fmt:message key="text.logIn.button"/>">
    </form>

    <jsp:include page="/jsp/include/footer.jsp" />


    <c:if test="${not empty logInFailed}">
        <script>

            var elems = document.getElementById("loginForm").elements;
            var container = document.getElementById("pass").parentNode;
            //container.className = 'form-group has-error';

            var msgElem = document.createElement('div');
            msgElem.className = "alert alert-danger";
            msgElem.role = "alert";
            msgElem.innerHTML ="<fmt:message key="text.logIn.error"/>";
            container.appendChild(msgElem);

            document.getElementById("login").value = "${login}";
            document.getElementById("pass").value = "${password}";

        </script>
    </c:if>


</div>


</body>
</html>
</fmt:bundle>