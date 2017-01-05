<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.text"/>
<nav class="navbar navbar-default navbar-fixed-bottom" id="navbar">
  <div class="container-fluid">

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><p class="navbar-text"><i class="glyphicon glyphicon-copyright-mark"></i> 2017 Dorohovich Andrey. All rights reserved.</p></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
       
        <li><a href="#"><img src="${pageContext.servletContext.contextPath}/resource/img/russian_flag.jpg" alt="РУС" style="width: 32px; height: 32px;"></a></li>
        <li> <a href="#"><img src="${pageContext.servletContext.contextPath}/resource/img/american_flag.jpg" alt="EN" style="width: 32px; height: 32px;"></a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
