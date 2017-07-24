<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Index</title>
</head>
<body>
	<h1>Index</h1>
	
	<p><a href="<c:url value="/create"/>">Créer candidat</a></p>
	<p><a href="<c:url value="/candidates"/>">Afficher tous les candidats</a></p>
	<p><a href="<c:url value="/generate"/>">Générer le pdf des candidats du jour</a></p>
	<p><span class="error">${message}</span></p>
	<c:remove var="message" scope="session" />
</body>
</html>
