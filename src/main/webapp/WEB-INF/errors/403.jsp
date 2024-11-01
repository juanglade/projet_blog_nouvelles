<%-- 
    Document   : 403
    Created on : 8 oct. 2024, 09:18:21
    Author     : stag
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accès interdit</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <h2>Erreur 403</h2>
        <h3>Accès interdit</h3>
        <p>Vous avez tenté d'accéder à une page qui vous est interdite !</p>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
