<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="i18n.text"/>
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
<a href="parsing?command=change_lang&lang=en_US">English</a>
<a href="parsing?command=change_lang&lang=ru_RU">Русский</a>
</body>
</html>
