<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n.text">
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="text.header"/></title>
</head>
<body>

<h5>Choose parsing type:</h5>
<form name="parserChooserForm" action="controller" method="POST">
    <input type="hidden" name="command" value="parsing" />
    <select name="parserType">
        <option value="sax">SAX</option>
        <option value="dom">DOM</option>
        <option value="stax">StAX</option>
    </select>
    <input type="submit" name="button" value="Pars XML"/>
</form>
<c:out value="${locale}"/>
<fmt:message key="text.header"/>
<fmt:message key="text.loginerror"/>
<a href="controller?command=language&lang=en">English</a>
<a href="controller?command=language&lang=ru">Русский</a>
<a href="controller?command=registration">Show users</a>
<a href="/LikeIt/jsp/registration.jsp">REGISTRATION</a>
<a href="/LikeIt/jsp/logIn.jsp"><fmt:message key="text.logIn.error"/></a>
<ctg:info-time/>

</body>
</html>
</fmt:bundle>
