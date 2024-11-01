<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Formulaire de création d'une nouvelle</title>
    <link type="text/css" rel="stylesheet" 
              href="<c:url value="/assets/css/style.css"/>" />
</head>
<body>
    <%@include file="../jspf/header.jspf" %>    
    <form action="<c:url value='/story/create'/>" method="post">
        <div class="${empty requestScope.errors ? "success" : "error"}">${requestScope.message}</div>
        <fieldset>
            <legend>Créer une nouvelle</legend>
            <div>
                <label for="title">Titre</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="error">${requestScope.errors.title.message}</div><br/>
            <div>
                <label for="content">Corps</label>
                <textarea id="content" name="content" required></textarea>
            </div>
            <div class="error">${requestScope.errors.content.message}</div><br/>
            <div>
                <button type="submit">Créer la nouvelle</button>
            </div>
        </fieldset>
    </form>
    <%@include file="../jspf/footer.jspf" %>
</body>
</html>
