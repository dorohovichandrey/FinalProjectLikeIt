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
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/standart.css"/>
        <script src="${pageContext.servletContext.contextPath}/resource/js/lib/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resource/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resource/js/editProfile.js"></script>

        <title><fmt:message key="text.header"/></title>
    </head>
    <body>

        <%--session.setAttribute("page","page.users");--%>
    <c:set var="page"  value="page.editProfie" scope="session"/>


    <jsp:include page="/jsp/include/navbar.jsp" />
    <!-- main-->

        <div id="main" class="row">
            <div class="row">
                <form role="form" id="passForm" class="col-md-4 col-md-offset-4">
                    <div class="form-group">
                        <label for="curPass">Текущий пароль</label>
                        <input input name="curPass" type="password" class="form-control" id="curPass" placeholder="Введите пароль">
                    </div>
                    <div class="form-group">
                        <label for="newPass">Новый пароль</label>
                        <input input name="password" type="password" class="form-control" id="newPass" placeholder="Введите пароль">
                        <p class="help-block">Не меннее 6 символов. Хотя бы одну цифру и по букве в каждом регистре м имтииоиоиоотллть</p>
                    </div>
                    <div class="form-group">
                        <label for="newPassConfirm">Подтверждение нового пароля</label>
                        <input name="passwordConfirmation" type="password" class="form-control" id="newPassConfirm" placeholder="Подтвердите пароль">
                    </div>
                    <input type="button" name="submitButton"  class="btn btn-success" onclick="validateChangePass(document.getElementById('passForm'))" value="Сменить пароль"/>
                </form>
            </div>
            <br>
            <div class="row">
                <form role="form" id="emailForm" class="col-md-4 col-md-offset-4">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input  name="emailAddr" type="email" class="form-control" id="email" placeholder="Введите email">
                    </div>
                    <input type="button" name="submitButton"  class="btn btn-success" onclick="validateChangeEmail(document.getElementById('emailForm'))" value="Сменить email"/>
                </form>
            </div>

        </div>


    <!--main -->

    <jsp:include page="/jsp/include/footer.jsp" />







    </body>
    </html>
</fmt:bundle>
