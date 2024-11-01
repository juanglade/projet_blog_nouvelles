<%-- 
    Document   : personStories
    Created on : 8 oct. 2024, 09:21:42
    Author     : Florine Pérabout
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mes nouvelles</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <fieldset>
             <c:forEach 
                items="${requestScope.story.id_person}" var="article">
                <article>
                    <h3>${story.title} ${requestScope.vote.quality}</h3>
                    <div>Article créé le ${story.created}</div>
                    <div>${story.content}</div>
                </article>
            </c:forEach>
        </fieldset>        
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
