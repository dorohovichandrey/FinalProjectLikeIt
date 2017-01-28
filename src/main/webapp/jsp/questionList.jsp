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

        <title><fmt:message key="${keyForQuestionListHeader}"/></title>
    </head>
    <body>

        <%--session.setAttribute("page","page.users");--%>
    <c:set var="page"  value="page.questionList" scope="session"/>


    <jsp:include page="/jsp/include/navbar.jsp" />
    <!-- main-->
        <div id="main" class="row">

            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4><fmt:message key="${keyForQuestionListHeader}"/></h4></div>
                        <div class="panel-body">

                            <div class="row">

                                <div class="col-md-10 col-md-offset-1">

                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-md-6">SuperScientist <i class="glyphicon glyphicon-star"></i> 125</div>
                                                <div class="col-md-offset-3 col-md-3">17:30 10.01.2017</div>
                                            </div>
                                        </div>
                                        <div class="panel-body">

                                            <div class="row">
                                                <form role="form" id="likeForm" action="${pageContext.request.contextPath}/controller" method="POST">
                                                    <input type="hidden" name="command" value="like" />
                                                    <input type="hidden" name="questionId" value="1223" />
                                                </form>
                                                <div class="col-md-1"><h2> 12<i style="cursor: pointer;" onclick="document.getElementById('likeForm').submit()" class="glyphicon glyphicon-heart-empty"></i></h2></div>
                                                <div class="col-md-11">
                                                    <h4><span class="label label-info">How can it be</span></h4>
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum dapibus mauris vel sapien consectetur, a tempor erat tincidunt. Morbi a augue in tellus faucibus vestibulum. Fusce tellus eros, hendrerit et leo id, aliquam euismod elit. Etiam bibendum leo id nulla fringilla tempus. Sed tempor semper purus eu posuere. Phasellus tincidunt facilisis ipsum ac vulputate. Pellentesque pharetra lacinia nulla, vel tristique urna suscipit nec.
                                                </div>
                                            </div>


                                        </div>
                                        <div class="panel-footer">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <form role="form" id="showQueByThemeForm" action="${pageContext.request.contextPath}/controller" method="POST">
                                                        <input type="hidden" name="command" value="showQuestionsByTheme" />
                                                        <input type="hidden" name="theme" value="sport" />
                                                        <button type="button" class="btn btn-default">sport </button>
                                                    </form>

                                                </div>
                                                <div class="col-md-offset-3 col-md-3">
                                                    <form role="form" id="showQue" action="${pageContext.request.contextPath}/controller" method="POST">
                                                        <input type="hidden" name="command" value="showQuestion" />
                                                        <input type="hidden" name="questionId" value="123" />
                                                        <button type="button" class="btn btn-primary"><i class="glyphicon glyphicon-eye-open"></i> Подробнее</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                    </div>




                                </div>

                            </div>

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