<%-- 
    Document   : adminStories
    Created on : 8 oct. 2024, 09:19:42
    Author     : SID
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Stories</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value='/assets/css/style.css' />" />
    </head>
    <body>
        <%@include file="../../jspf/adminHeader.jspf" %>
        
        <section id="admin-stories">
            <h2>Gestion des nouvelles</h2>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>Created</th>
                        <th>Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Boucle à travers les histoires récupérées -->
                    <c:forEach items="${requestScope.stories}" var="story">
                        <tr>
                            <td>${story.id}</td>
                            <td>${story.title}</td>
                            <td>${story.content}</td>
                            <td>${story.created}</td>
                            <td>${story.name}</td> <!-- Affichez le nom de l'auteur -->
                            <td>
                                <a href="<c:url value='/back/delete/story?id=${story.id}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
        
        <%@include file="../../jspf/footer.jspf" %>
    </body>
</html>
