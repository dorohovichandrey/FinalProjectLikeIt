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
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/standart.css"/>
        <script src="${pageContext.servletContext.contextPath}/resource/js/lib/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resource/bootstrap/js/bootstrap.min.js"></script>

        <title><fmt:message key="${keyForTableHeader}"/></title>
    </head>
    <body>

    <%session.setAttribute("page","page.users");%>


        <jsp:include page="/jsp/include/navbar.jsp" />
        <!-- main-->

        <div id="main" class="row">
            <div class="row">
                <div class="col-md-offset-2 col-md-8">

                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading"><fmt:message key="${keyForTableHeader}"/></div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><fmt:message key="text.users.rating"/></th>
                                    <th><fmt:message key="text.users.login"/></th>
                                    <th><fmt:message key="text.users.email"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="elem" items="${users}" varStatus="status">
                                    <tr>
                                        <td><c:out value="${ elem.rating }" /></td>
                                        <td><c:out value="${ elem.login }" /></td>
                                        <td><c:out value="${ elem.email}" /></td>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
