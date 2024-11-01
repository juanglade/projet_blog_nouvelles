<%-- 
    Document   : 404
    Created on : 8 oct. 2024, 09:18:29
    Author     : stag
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ressource inconnue</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <h2>Erreur 404</h2>
        <h3>Ressource inconnue</h3>
        <p>Vous avez tenté d'accéder à une page qui n'existe pas !</p>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
