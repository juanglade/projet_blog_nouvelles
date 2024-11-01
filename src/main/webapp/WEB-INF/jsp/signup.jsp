<%-- 
    Document   : signup
    Created on : 8 oct. 2024, 09:20:14
    Author     : Caroline
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <div class="${empty requestScope.errors ? "success" : "error"}">${requestScope.message}</div>
        <form method="post" action="<c:url value="/signup"/>">
            <fieldset>
                <legend>Inscription</legend>
                <label for="name">
                    Nom d'utilisateur <span class="mandatory">*</span>
                </label>
                <input type="text" id="name" name="name" 
                       value="<c:out value="${requestScope.person.name}"/>"
                       size="20" maxlength="20" />
                <div class="error">${requestScope.errors.name.message}</div><br/>
                <label for="login">
                    Adresse email <span class="mandatory" >*</span>
                </label>
                <input type="text" id="login" name="login" 
                       value="<c:out value="${requestScope.person.login}"/>"
                       size="20" maxlength="40"/>
                <div class="error">${requestScope.errors.login.message}</div><br/>
                <label for="pwd">
                    Mot de passe <span class="mandatory">*</span>
                </label>
                <input type="password" id="pwd" name="pwd"
                       value="" size="20" maxlength="20"/>
                <div class="error">${requestScope.errors.pwd.message}</div><br/>
                <label for="confirm">
                    Confirmation <span class="mandatory">*</span>
                </label>
                <input type="password" id="confirm" name="confirm" value=""
                       size="20" maxlength="20"/>
                <input type="submit" value="Inscription" class="noLabel" />
                <div class="error">${requestScope.errors.confirm.message}</div><br/>
                <p><span class="mandatory">*</span>Champs obligatoires</p>
            </fieldset>
        </form>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
