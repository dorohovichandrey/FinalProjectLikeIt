<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.01.2017
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<jsp:forward page="/controller" >
    <jsp:param name="command" value="show_my_questions" />
    <jsp:param name="searchParam" value="${user.id}" />
</jsp:forward>
</body>
</html>
