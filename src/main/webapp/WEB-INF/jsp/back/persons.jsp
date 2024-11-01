<%-- 
    Document   : persons
    Created on : 8 oct. 2024, 09:19:12
    Author     : Caroline Casteras
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion utilisateurs</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../../jspf/adminHeader.jspf" %>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.persons}" 
                           var="person">
                    <tr>
                        <td>${person.id}</td>
                        <td>${person.login}</td>
                        <td>${person.name}</td>
                        <td>${person.status}</td>
                        <td><a href="<c:url value="/back/person/delete?id=${person.id}" />">Supprimer</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <%@include file="../../jspf/footer.jspf" %>
    </body>
</html>

