<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result page</title>
</head>
<body>
<h2><c:out value="${typeOfParsing}" /></h2>
<table border="1">
    <thead>
    <tr>
        <th colspan="8">Precious gem</th>
    </tr>
    <tr>
        <th rowspan="2">name</th>
        <th colspan="2">origin</th>
        <th colspan="3">visual parameters</th>
        <th rowspan="2">carats</th>
        <th rowspan="2">selling on auction</th>
    </tr>
    <tr>
        <th>country</th>
        <th>city</th>
        <th>colour</th>
        <th>transparency</th>
        <th>verges</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="elem" items="${prGems}" varStatus="status">
        <tr>
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.origin.country }" /></td>
            <td><c:out value="${ elem.origin.city }" /></td>
            <td><c:out value="${ elem.visualParameters.colour }" /></td>
            <td><c:out value="${ elem.visualParameters.transparencyInPercents }" /></td>
            <td><c:out value="${ elem.visualParameters.numberOfVerges }" /></td>
            <td><c:out value="${ elem.valueInCarat }" /></td>
            <td><c:out value="${ elem.sellingOnlyOnAuction }" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<hr>
<br>
<table border="1">
    <thead>
    <tr>
        <th colspan="8">Semirecious gem</th>
    </tr>
    <tr>
        <th rowspan="2">name</th>
        <th colspan="2">origin</th>
        <th colspan="3">visual parameters</th>
        <th rowspan="2">carats</th>
        <th rowspan="2">rarity</th>
    </tr>
    <tr>
        <th>country</th>
        <th>city</th>
        <th>colour</th>
        <th>transparency</th>
        <th>verges</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="elem" items="${semiprGems}" varStatus="status">
        <tr>
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.origin.country }" /></td>
            <td><c:out value="${ elem.origin.city }" /></td>
            <td><c:out value="${ elem.visualParameters.colour }" /></td>
            <td><c:out value="${ elem.visualParameters.transparencyInPercents }" /></td>
            <td><c:out value="${ elem.visualParameters.numberOfVerges }" /></td>
            <td><c:out value="${ elem.valueInCarat }" /></td>
            <td><c:out value="${ elem.rarity }" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2><c:out value="${res}"/></h2>
<a href="/LikeIt/index.jsp">BACK</a>
</body>
</html>

