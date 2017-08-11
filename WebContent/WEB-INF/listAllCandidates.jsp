<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Liste des candidats</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1><a href="<c:url value="/candidates" />">Liste des candidats (100 derniers)</a></h1>
			</div>
		</div>
	
		<div class="row">
			<div class="col-lg-12">
				<p><a href="<c:url value="/create"/>" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Créer candidat</a></p>
				<p><a href="<c:url value="/generate"/>" class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> Générer les lettres des candidats du jour (<c:out value="${countPDF}" /> en pdf et <c:out value="${countEmail}" /> par mail)</a></p>
				<p><span class="error">${message}</span></p>
				<c:remove var="message" scope="session" />
			</div>
		</div>
		
		<form method="post" action="<c:url value="candidates" />">
			<div class="row">
				<div class="col-lg-12">
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> Générer les lettres des candidats sélectionnés</button>
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-lg-12">
					<label for="search">Rechercher</label>
					<input type="search" name="search" size="30" maxlength="100" title="Saisir la recherche" placeholder="Rechercher"/>
					<input type="radio" name="type" id="typeName" value="name" checked /> <label for="typeName">Nom ou prénom</label>
					<input type="radio" name="type" id="typeJob" value="job" /> <label for="typeJob">Job</label>
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Rechercher</button>
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-lg-12">
					<label for="number">Nombre de candidats à afficher (du dernier au premier inscrits)</label>
					<input type="number" name="number" min="0" title="Saisir le nombre de candidats à afficher"/>
					<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-eye-open"></span> Nombre de candidats à afficher</button>
				</div>
			</div>
			<br>
			
			<div class="col-lg-12 table-responsive">
				<table class="table table-bordered table-striped table-condensed table-responsive">
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
						<th>Date de demande</th>
						<th>Date d'insertion</th>
						<th>Date de modification</th>
						<th>Auteur</th>
						<th>Job</th>
						<th>Réponse</th>
						<th>Mail/PDF</th>
						<th>Retour courrier</th>
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
						<td><a href="mailto:<c:out value="${candidate.email}"/>"><c:out value="${empty candidate.email ? '-' : candidate.email}"/></a></td>
						<td><c:out value="${empty candidate.livesAt ? '-' : candidate.livesAt}"/></td>
						<td><c:out value="${empty candidate.street ? '-' : candidate.street}"/></td>
						<td><c:out value="${empty candidate.numStreet ? '-' : candidate.numStreet}"/></td>
						<td><c:out value="${empty candidate.postCode ? '-' : candidate.postCode}"/></td>
						<td><c:out value="${empty candidate.locality ? '-' : candidate.locality}"/></td>
						<td><c:out value="${empty candidate.country ? '-' : candidate.country}"/></td>
						<td><c:out value="${empty candidate.getRequestDateFormatted() ? '-' : candidate.getRequestDateFormatted()}"/></td>
						<td><c:out value="${candidate.getInsertDateFormatted()}"/></td>
						<td><c:out value="${candidate.getInsertDateFormatted() == candidate.getUpdateDateFormatted() ? '-' : candidate.getUpdateDateFormatted()}"/></td>
						<td><c:out value="${candidate.initials}"/></td>
						<td><c:out value="${candidate.jobFunction}"/></td>
						<td><c:out value="${candidate.answer}"/></td>
						<td><c:out value="${candidate.sendType}"/></td>
						<td><c:out value="${candidate.notTransmitted == 'no' ? '-' : 'Retour'}"/></td>
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
						<th>Date de demande</th>
						<th>Date d'insertion</th>
						<th>Date de modification</th>
						<th>Auteur</th>
						<th>Job</th>
						<th>Réponse</th>
						<th>Mail/PDF</th>
						<th>Retour courrier</th>
						<th></th>
						<th></th>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
