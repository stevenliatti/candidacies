<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Liste des candidats</title>
</head>
<body>
	<p><a href="<c:url value="/create"/>"><button>Créer candidat</button></a></p>
	<p><a href="<c:url value="/generate"/>"><button>Générer les lettres des candidats du jour (<c:out value="${countPDF}" /> en pdf et <c:out value="${countEmail}" /> par mail)</button></a></p>
	<p><span class="error">${message}</span></p>
	<c:remove var="message" scope="session" />
	
	<h1>Liste des candidats</h1>
	
	<form method="post" action="<c:url value="candidates" />">
		<input type="submit" value="Générer Lettres" />
		
		<table>
			<tr>
				<th></th>
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
				<th>Date demande</th>
				<th>Date insertion</th>
				<th>Date modification</th>
				<th>Auteur</th>
				<th>Job</th>
				<th>Réponse</th>
				<th>Mail/PDF</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${candidates}" var="candidate">
			<tr>
				<td><input type="checkbox" name="ids" value="<c:out value="${candidate.id}"/>"></td>
				<td><c:out value="${candidate.id}"/></td>
				<td><c:out value="${candidate.getShortTitle()}"/></td>
				<td><c:out value="${candidate.lastName}"/></td>
				<td><c:out value="${candidate.firstName}"/></td>
				<td><a href="mailto:<c:out value="${candidate.email}"/>"><c:out value="${candidate.email}"/></a></td>
				<td><c:out value="${candidate.livesAt}"/></td>
				<td><c:out value="${candidate.street}"/></td>
				<td><c:out value="${candidate.numStreet}"/></td>
				<td><c:out value="${candidate.postCode}"/></td>
				<td><c:out value="${candidate.locality}"/></td>
				<td><c:out value="${candidate.country}"/></td>
				<td><c:out value="${candidate.getRequestDateFormatted()}"/></td>
				<td><c:out value="${candidate.getInsertDateFormatted()}"/></td>
				<td><c:out value="${candidate.getUpdateDateFormatted()}"/></td>
				<td><c:out value="${candidate.initials}"/></td>
				<td><c:out value="${candidate.jobFunction}"/></td>
				<td><c:out value="${candidate.answer}"/></td>
				<td><c:out value="${candidate.sendType}"/></td>
				<td><a href="<c:url value="/update?id="/><c:out value="${candidate.id}"/>">Modifier</a></td>
				<td><a href="<c:url value="/delete?id="/><c:out value="${candidate.id}"/>">Supprimer</a></td>
			</tr>
			</c:forEach>
			<tr>
				<th></th>
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
				<th>Date demande</th>
				<th>Date insertion</th>
				<th>Date modification</th>
				<th>Auteur</th>
				<th>Job</th>
				<th>Réponse</th>
				<th>Mail/PDF</th>
				<th></th>
				<th></th>
			</tr>
		</table>
	</form>
</body>
</html>
