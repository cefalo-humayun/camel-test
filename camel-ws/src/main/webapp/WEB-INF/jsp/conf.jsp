<%@page contentType="text/html; ISO-8859-1" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Configuration file Status</title>
    <style type="text/css">
        table { margin: 1em; border: 1px solid #ccc; font-size: 1.5em; }
        td, th { padding: .3em; }
        .white {color: white}
    </style>
</head>
<body>
<table border="1">
    <caption>File Status</caption>
    <th>File Name</th>
    <th>Status</th>
    <c:forEach items="${map}" var="file">
            <c:if test="${file.value eq true}">
                <tr style="background-color: #A00000;">
                    <td><span class="white"><c:out value="${file.key}"/></span></td>
                    <td><span class="white">Changed</span></td>
                </tr>
            </c:if>
            <c:if test="${file.value eq false}">
                <tr style="background-color: #00A000">
                    <td><span class="white"><c:out value="${file.key}"/></span></td>
                    <td><span class="white">Unchanged</span></td>
                </tr>
            </c:if>
    </c:forEach>
</table>
<br/>
<form action="${baseURL}config/reload" method="post">
    <input type="submit" value="Reload" name="submit" style="font-size: 1.5em; margin: 1em;">
</form>
</body>
</html>
