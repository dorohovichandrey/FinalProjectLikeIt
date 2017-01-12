<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">

<nav class="navbar navbar-default navbar-fixed-bottom" id="footer">
  <div class="container-fluid">

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><p class="navbar-text"><i class="glyphicon glyphicon-copyright-mark"></i> <fmt:message key="text.footer.copyright"/></p></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
          <form id = "ruForm" action="${pageContext.request.contextPath}/controller" method = "post" style = "display:none;">
              <input type="hidden" name="command" value="language" />
              <input type="hidden" name="lang" value="ru"/>
          </form>
          <form id = "enForm" action="${pageContext.request.contextPath}/controller" method = "post" style = "display:none;">
              <input type="hidden" name="command" value="language" />
              <input type="hidden" name="lang" value="en"/>
          </form>

       
        <li><span><input type="image" src="${pageContext.servletContext.contextPath}/resource/img/russian_flag.jpg" alt="РУС" onclick="document.getElementById('ruForm').submit()" style="width: 32px; height: 32px; margin-right: 10px; margin-top: 10px"></span></li>
        <li><span><input type="image" src="${pageContext.servletContext.contextPath}/resource/img/american_flag.jpg" alt="EN" onclick="document.getElementById('enForm').submit()" style="width: 32px; height: 32px; margin-right: 10px; margin-top: 10px"></span></li>



      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</fmt:bundle>
