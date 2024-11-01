<%-- 
    Created on : 9 oct. 2024
    Author     : Julien Anglade
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Admin</title>
        <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
    </head>
    <body>
        <%@include file="../../jspf/adminHeader.jspf" %>
        <section id="homeAdmin">
            <h2>Bienvenue (${sessionScope.person.name})</h2>
            <h3>Choisis une action :</h3>
            <ul>
                <li><a href="<c:url value="/back/story"/>">Gérer les nouvelles</a></li>
                <li><a href="<c:url value="/back/person"/>">Gérer les inscrits</a></li>
                <li><a href="<c:url value="/back/comment"/>">Commentaires signalés</a></li>
            </ul>
        </section>
        <%@include file="../../jspf/footer.jspf" %>
    </body>
</html>