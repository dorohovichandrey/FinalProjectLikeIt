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
    <c:set var="page"  value="page.questionPlusAnswers" scope="session"/>


    <jsp:include page="/jsp/include/navbar.jsp" />
    <!-- main-->

        <div id="main" class="row">

            <div class="row">
                <div class="col-md-8 col-md-offset-2">


                    <!-- -->
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-6"><c:out value="${ question.user.login }" /> <i class="glyphicon glyphicon-star"></i> <c:out value="${ question.user.rating }" /></div>
                                <div class="col-md-offset-3 col-md-3"><fmt:formatDate type="both" value="${question.dateAndTime}" /></div>
                            </div>
                        </div>
                        <div class="panel-body">

                            <div class="row">
                                <form role="form" id="likeForm${ question.id }" action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="like_question" />
                                    <input type="hidden" name="questionId" value="${ question.id }" />
                                </form>
                                <div class="col-md-1"><h2> <c:out value="${ question.rating }" /><i style="cursor: pointer;" onclick="document.getElementById('likeForm${ question.id }').submit()" class="glyphicon glyphicon-heart-empty"></i></h2></div>
                                <div class="col-md-11">
                                    <h4><span class="label label-info"><c:out value="${ question.header }" /></span></h4>
                                    <c:out value="${ question.text }" />
                                </div>
                            </div>


                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col-md-1">
                                    <form role="form" id="showQueByThemeForm" action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="showQuestionsByTheme" />
                                        <input type="hidden" name="theme" value="sport" />
                                        <button type="button" class="btn btn-default">sport </button>
                                    </form>
                                </div>
                                <div class="col-md-1">
                                    <form role="form" id="showQueByThemeForm${ question.id }" action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="show_questions_by_theme" />
                                        <input type="hidden" name="searchParam" value="${ question.theme.id }" />
                                            <%--<button type="button" class="btn btn-default"><c:out value="${ elem.theme.name }" /> </button>--%>
                                        <input type="submit" class="btn btn-default" value="<c:out value="${ question.theme.name }" />"/>
                                    </form>

                                </div>
                                <div class="col-md-offset-7 col-md-3">
                                    <form role="form" id="answerform${ question.id }" action="${pageContext.request.contextPath}/controller" method="POST">
                                        <input type="hidden" name="command" value="prepare_for_creating_answer" />
                                        <input type="hidden" name="questionId" value="${ question.id }" />
                                        <%--<button type="button" class="btn btn-primary">Ответить <i class="glyphicon glyphicon-pencil"></i></button>--%>
                                        <input type="submit" class="btn btn-primary" value="ответить"/>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>

                    <!-- -->


                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Ответы</h4></div>
                        <div class="panel-body">

                            <div class="row">

                                <div class="col-md-10 col-md-offset-1">



                                    <c:forEach var="answer" items="${answerList}" varStatus="status">
                                    <div class="panel panel-info">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-md-6"><c:out value="${ answer.user.login }" /> <i class="glyphicon glyphicon-star"></i> <c:out value="${ answer.user.rating }" /></div>
                                                <div class="col-md-offset-3 col-md-3"><fmt:formatDate type="both" value="${question.dateAndTime}" /></div>
                                            </div>
                                        </div>
                                        <div class="panel-body">

                                            <div class="row">
                                                <form role="form" id="AnswlikeForm${ answer.id }" action="${pageContext.request.contextPath}/controller" method="POST">
                                                    <input type="hidden" name="command" value="like_answer" />
                                                    <input type="hidden" name="questionId" value="1223" />
                                                </form>
                                                <div class="col-md-1"><h2> <c:out value="${ answer.rating }" /><i style="cursor: pointer;" onclick="document.getElementById('likeForm').submit()" class="glyphicon glyphicon-heart"></i></h2></div>
                                                <div class="col-md-11">
                                                    <!--<h4><span class="label label-info">Question header</span></h4>-->
                                                    <c:out value="${ answer.text }" />
                                                </div>
                                            </div>


                                        </div>
                                        <div class="panel-footer">
                                            <div class="row">
                                                <%--<div class="col-md-3">
                                                    <form role="form" id="edit${ answer.user.login }" action="${pageContext.request.contextPath}/controller" method="POST">
                                                        <input type="hidden" name="command" value="edit_question" />
                                                        <input type="hidden" name="answerId" value="${ answer.id }" />
                                                        <button type="button" class="btn btn-default"><i class="glyphicon glyphicon-wrench"></i> Редактировать</button>
                                                    </form>
                                                </div>--%>

                                               <%-- <div class="col-md-offset-6 col-md-3">
                                                    <form role="form" id="deleteQueMsgForm${ answer.id }" action="${pageContext.request.contextPath}/controller" method="POST">
                                                        <input type="hidden" name="command" value="" />
                                                        <input type="hidden" name="questionId" value="sport" />
                                                        <button type="button" class="btn btn-danger">Удалить </button>
                                                    </form>
                                                </div> --%>

                                            </div>
                                        </div>

                                    </div>
                                    </c:forEach>

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