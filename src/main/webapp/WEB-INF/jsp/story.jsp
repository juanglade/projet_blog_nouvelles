
<%--author : Sid --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.id.title}</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        
        <section id="story">
            <h2>${requestScope.id.title}</h2>
            <div>Nouvelle écrite le ${requestScope.id.created} par ${requestScope.id.name}</div>
            <div>${requestScope.id.content}</div>
        </section>
        
        <%--author : Florine Pérabout
            Icons pour voter--%>
        <div>            
               <a class="like" id="like" href="./assets/icons/like_icon.png"> 
               <img src="./assets/icons/like_icon.png" alt="like"> </a>               
        </div>        
        <div>            
               <a class="dislike" id="dislike" href="./assets/icons/dislike_icon.png"> 
               <img src="./assets/icons/dislike_icon.png" alt="dislike"> </a>               
        </div>
        
        <%--author : Florine Pérabout
            Création formulaire pour saisir un commentaire--%>
        <h2>Votre avis : </h2>
        <form action="<c:url value='/comment/create' />" method="post">
                <div class="form-group">
                    <label for="title"> Titre: </label>
                    <input type="text" id="title" name="title" maxlenght="20">
                </div>
                <div class="form-group">
                    <label for="content">Votre commentaire : </label>
                    <textarea id="content" name="content" rows="10" cols="50" maxlength="150" placeholder="Tapez votre commentaire"></textarea>
                </div>
                <div class="form-group">
                    <button type="submit">Envoyer mon commentaire</button>
                </div>
            </form>
                
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
