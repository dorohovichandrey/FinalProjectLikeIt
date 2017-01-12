<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">


<nav class="navbar navbar-default navbar-inverse" id="navbar">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">LikeIt</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="#">Пользователи<span class="sr-only">(current)</span></a></li>
        <li role="separator" class="divider"></li>
        <li><a href="#">Темы</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Вопросы<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Свежие</a></li>
            <li><a href="#">Популярные</a></li>
            <li><a href="#">Без ответа</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Задать вопрос</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Ключевые слова">
        </div>
        <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i> Поиск</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-user"></i> Администрирование<span class="caret">
          </span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Забаненные пользователи <span class="badge">5</span></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Жалобы и предложения <span class="badge">21</span></a></li>
          </ul>
        </li>
        <c:choose>
          <c:when test="${empty user}">
            <li><a href="/LikeIt/jsp/registration.jsp">Зарегистрироваться</a></li>
            <li><a href="/LikeIt/jsp/logIn.jsp"><i class="glyphicon glyphicon-log-in"></i> Войти</a></li>
          </c:when>
          <c:otherwise>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-user"></i> ${user.login}<span class="caret">
          </span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Ваш профиль</a></li>
                <li><a href="#">Ваши вопросы <span class="badge">11</span></a></li>
                <li><a href="#">Ваши ответы <span class="badge">5</span></a></li>
                <li role="separator" class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=logOut"><i class="glyphicon glyphicon-log-out"></i> Выйти</a></li>
              </ul>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->


</nav>
</fmt:bundle>
