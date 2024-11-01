<%-- 
    Document   : profile
    Created on : 8 oct. 2024, 09:20:26
    Author     : Florine Pérabout
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon Profil</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <fieldset>
            <h2>Vos données personnelles</h2>
            <div> Votre email : <c:url value="${sessionScope.person.login}"/></div>
            </br>
            <div> Votre nom : ${empty sessionScope.person ? "&lt;aucun&gt;" : sessionScope.person.name}</div>           
        </fieldset>
        
        <fieldset>
           <section id="nouvelles">
            <h2>Mes nouvelles</h2>  
            <c:forEach items="${requestScope.storiesPerson}" var="story">  
                <article>
                    <h3>${story.title} ${requestScope.vote.quality}</h3>
                    <div>Nouvelle créée le ${story.created}</div>
                    <p>${story.content}</p>
                </article>
                <a href="<c:url value='/personStories'/>">Toutes mes nouvelles</a> 
            </c:forEach>
        </section> 
        </fieldset>
                  
        <form method="post" action="<c:url value='/profile'/>">                
            <fieldset>
                <div> Changer mon mot de passe </div>
                <label for="oldPwd"><span class="mandatory"> Entrez votre mot de passe actuel *</span></label>
                <input type="password" id="oldPwd" name="oldPwd" size="20" maxlength="20" />
                <div class="error"> ${requestScope.errors.oldPwd.message}</div>
                <br />
                <label for="newPwd"> Nouveau mot de passe <span class="mandatory">*</span></label>
                <input type="password" id="newPwd" name="newPwd" size="20" maxlength="20" />
                <div class="error"> ${requestScope.errors.newPwd.message}</div>
                <br />
                <label for="confirm"> Confirmation du nouveau mot de passe <span class="mandatory">*</span></label>
                <input type="password" id="confirm" name="confirm" size="20" maxlength="20" />
                <div class="error"> ${requestScope.errors.confirm.message}</div>
                <br />
                <input type="submit" value="Modifier" class="noLabel" />
            </fieldset>
        </form>
        <fieldset>
            <a href="<c:url value="/person/disable?id=${person.id}" />">Me désinscrire</a>
        </fieldset>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
