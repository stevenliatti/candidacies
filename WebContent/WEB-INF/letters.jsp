<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lettres générées</title>
</head>
<body>
	<p><a href="<c:url value="/"/>">Afficher tous les candidats</a></p>
	
	<p><a href="<c:url value="/download/${generatedFileName}.pdf"/>">Télécharger le pdf</a></p>
	
	<h1>Emails</h1>
	
	<c:forEach items="${candidatesEmail}" var="candidate">
	<div>
		<p><a href="mailto:<c:out value="${candidate.email}"/>"><c:out value="${candidate.email}"/></a></p>
		<input type="text" value="<c:out value="${candidate.answerTitle}"/>"/>
		<br>
		<textarea name="textarea" rows="20" cols="150"><c:out value="${candidate.letter}"/></textarea>
	</div>
	</c:forEach>

</body>
</html>
