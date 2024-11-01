<%-- 
    Created on : 9 oct. 2024
    Author     : Julien Anglade
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/home.css"/>" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        
        <section id="homeTop3">
            <h2>TOP 3 des nouvelles de la semaine :</h2>
            <!--Conditions des 3 meilleures notes et sur les 7 derniers jours à prévoir-->
            <div id="divTop3" class="grid-layout">
            <c:forEach items="${requestScope.stories3}" var="story">
                <article>
                    <h3>${story.title}</h3>
                    <div class="texte-italique-petit">Nouvelle écrite le ${story.created} 
                        par ${story.name}</div>
                    <div class="texte-content-petit">${story.content}</div>
                    <div><a href="<c:url value="/story?id=${story.id}"/>">Lire la suite</a></div>
                </article>
            </c:forEach>
            </div>
        </section>
        
        <section id="homeStories10">
            <h2>Voici le résumé des 10 dernières nouvelles :</h2>
            <!--afficher que les 200 premiers caractères-->
            <div id="div10" class="grid-layout">
                <c:forEach items="${requestScope.stories10}" var="story">
                    <article>
                        <h3>${story.title}</h3>
                        <!-- Troncature du contenu à 200 caractères -->
                        <div class='texte-italique-petit'>Nouvelle écrite le ${story.created} par ${story.name}</div>
                        <div class="texte-content-petit">
                            <c:choose>
                                <c:when test="${fn:length(story.content) > 200}">
                                    ${story.content.substring(0, 200)}... 
                                    <!-- Lien "Voir la suite" vers l'histoire complète -->
                                    <a href="<c:url value='/story?id=${story.id}' />">Voir la suite</a>
                                </c:when>
                                <c:otherwise>
                                    ${story.content}
                                    <div><a href="<c:url value="/story?id=${story.id}"/>">Lire la suite</a></div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </article>
                </c:forEach>
            </div>
        </section>
        
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
