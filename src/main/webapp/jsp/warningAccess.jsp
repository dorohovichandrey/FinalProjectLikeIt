<%@page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
        <script src="${pageContext.servletContext.contextPath}/resource/js/createQuestion.js"></script>

        <title><fmt:message key="text.header"/></title>
    </head>
    <body>

        <%--session.setAttribute("page","page.users");--%>
    <c:set var="page"  value="page.warningAccess" scope="session" />


    <jsp:include page="/jsp/include/navbar.jsp" />

    <!-- main-->


    <div id="main" class="row">

        <div class="row">
            <div class="col-md-4 col-md-offset-4">

                <br>
                <br>
                <br>
                <br>
                <br>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title">Предупреждение</h3>
                    </div>
                    <div class="panel-body">
                        У вас нет прав для проведения данной операции.
                    </div>
                </div>



            </div>
        </div>
    </div>


    <!--main -->

    <jsp:include page="/jsp/include/footer.jsp" />







    </body>
    </html>
</fmt:bundle>