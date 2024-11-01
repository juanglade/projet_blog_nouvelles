<%--
    Document   : comments
    Created on : 8 oct. 2024, 09:19:52
    Author     : Florine Pérabout
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Commentaires signalés</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value='/assets/css/style.css' />" />
    </head>
    <body>
        <%@include file="../../jspf/adminHeader.jspf" %>
        <h1>Commentaires signalés</h1>
        <c:forEach items="${requestScope.comment.status}">
            <article>
                <h3>${comment.title} ${comment.content}</h3>
                <button type="submit" value="Desapproved">Désapprouvé</button>
                <button type="submit" value="Approved"> Approuvé </button>
                <c:choose>
                    <c:when test="${status == 0}">
                        <p>Commentaire conforme au réglement</p>                        
                    </c:when>
                    <c:when test="${status == 1}">
                        <p>Commentaire supprimé</p>
                        <p>${comment.title == null} </p>
                        <p>${comment.content == "Commentaire non conforme au réglement"}</p>
                    </c:when>
                </c:choose>                                    
            </article>
        </c:forEach>
        <%@include file="../../jspf/footer.jspf" %>
    </body>
</html>
