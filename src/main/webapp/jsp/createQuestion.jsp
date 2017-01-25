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
        <script src="${pageContext.servletContext.contextPath}/resource/js/createQuestion.js"></script>

        <title><fmt:message key="text.header"/></title>
    </head>
    <body>

        <%--session.setAttribute("page","page.users");--%>
    <c:set var="page"  value="page.editProfie" scope="session"/>


    <jsp:include page="/jsp/include/navbar.jsp" />
    <!-- main-->

        <div id="main" class="row">
            <div class="row">
                <form role="form" id="createQueForm" class="col-md-6 col-md-offset-3">
                    <div class="form-group">
                        <label for="themeID">Ответ</label>
                        <select name="theme" class="form-control" id="themeID">
                            <option value="sax">разное</option>
                            <option value="dom">спорт</option>
                            <option value="stax">прога</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="headerId">Заголовок</label>
                        <input input name="header" type="text" class="form-control" id="headerId" placeholder="Введите Заголовок">
                        <p class="help-block">Должен содержать от 10 до 100 символов</p>
                    </div>
                    <div class="form-group">
                        <label for="questionTextID">Текст вопроса</label>
                        <textarea name="questionText" class="form-control" id="questionTextID" rows="8" placeholder="Введите вопрос"></textarea>
                        <p class="help-block">Должен содержать от 10 до 1000 символов</p>
                    </div>
                    <input type="button" name="submitButton"  class="btn btn-success" onclick="validateCreateQue(document.getElementById('createQueForm'))" value="Задать вопрос"/>
                </form>
            </div>


        </div>
    <!--main -->

    <jsp:include page="/jsp/include/footer.jsp" />







    </body>
    </html>
</fmt:bundle>
