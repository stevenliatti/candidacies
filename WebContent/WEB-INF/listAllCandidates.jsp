<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Tous les candidats</title>
</head>
<body>
	<p><a href="<c:url value="/"/>">Retour à l'index</a></p>
	
	<h1>Tous les candidats</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>Titre</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Email</th>
			<th>Vit chez</th>
			<th>Rue</th>
			<th>N° rue</th>
			<th>NPA</th>
			<th>Localité</th>
			<th>Pays</th>
			<th>Date de demande</th>
			<th>Date d'insertion</th>
			<th>Date de modification</th>
			<th>Date d'envoi</th>
			<th>Auteur</th>
			<th>Job Fonction</th>
			<th>Réponse</th>
		</tr>
		<c:forEach items="${candidates}" var="candidate">
		<tr>
			<td><c:out value="${candidate.id}"></c:out></td>
			<td><c:out value="${candidate.title}"></c:out></td>
			<td><c:out value="${candidate.lastName}"></c:out></td>
			<td><c:out value="${candidate.firstName}"></c:out></td>
			<td><c:out value="${candidate.email}"></c:out></td>
			<td><c:out value="${candidate.livesAt}"></c:out></td>
			<td><c:out value="${candidate.street}"></c:out></td>
			<td><c:out value="${candidate.numStreet}"></c:out></td>
			<td><c:out value="${candidate.postCode}"></c:out></td>
			<td><c:out value="${candidate.locality}"></c:out></td>
			<td><c:out value="${candidate.country}"></c:out></td>
			<td><c:out value="${candidate.getRequestDateFormatted()}"></c:out></td>
			<td><c:out value="${candidate.getInsertDateFormatted()}"></c:out></td>
			<td><c:out value="${candidate.getUpdateDateFormatted()}"></c:out></td>
			<td><c:out value="${candidate.getSendDateFormatted()}"></c:out></td>
			<td><c:out value="${candidate.writer}"></c:out></td>
			<td><c:out value="${candidate.jobFunction}"></c:out></td>
			<td><c:out value="${candidate.answer}"></c:out></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
