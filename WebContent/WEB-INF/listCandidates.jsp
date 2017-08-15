<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Liste des candidats</title>
	<link rel="shortcut icon" href="<c:url value="/inc/favicon.ico"/>" />
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/themes/smoothness/jquery-ui.css" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-10">
				<a class="btn btn-danger" href="<c:url value="/logout" />">Déconnexion</a>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-10">
				<h1><a href="<c:url value="/candidates" />">Liste des 100 derniers candidats (max)</a></h1>
			</div>
			<div class="col-lg-offset-8 col-lg-2">
				<a class="btn btn-primary" href="<c:url value="/stats" />">Statistiques</a>
			</div>
			<c:if test="${sessionScope.sessionUser == admin}">
			<div class="col-lg-2">
				<a class="btn btn-warning" href="<c:url value="/admin/answers" />">Liste des réponses</a>
			</div>
			</c:if>
		</div>
	
		<div class="row">
			<div class="col-lg-12">
				<a href="<c:url value="/create"/>" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Créer candidat</a>
				<a href="<c:url value="/generate"/>" class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> Générer lettres du jour (<c:out value="${countPDF}" /> pdf, <c:out value="${countEmail}" /> mail)</a>
				<p><span class="error">${message}</span></p>
				<c:remove var="message" scope="session" />
			</div>
		</div>
		
		<form method="post" action="<c:url value="candidates" />">
			<div class="row">
				<div class="col-lg-3">
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> Générer lettres des candidats sélectionnés</button>
				</div>

				<div class="col-lg-8">
					<label for="search">Rechercher par nom ou prénom</label>
					<input autocomplete="off" type="search" name="search" size="30" maxlength="100" title="Saisir la recherche" placeholder="Rechercher"/>
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Rechercher</button>
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-lg-12">
					<label for="number">Nombre de candidats à afficher (du dernier au premier inscrits)</label>
					<input autocomplete="off" id="number" type="number" name="number" min="0" title="Saisir le nombre de candidats à afficher"/>
					<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-eye-open"></span> Nombre de candidats à afficher</button>
					<button onclick="document.getElementById('number').value = <c:out value="${countAll}" />;" class="btn btn-success" type="submit"><span class="glyphicon glyphicon-eye-open"></span> Afficher tous les candidats (<c:out value="${countAll}" />)</button>
				</div>
			</div>
			<br>

			<div class="row">
				<div class="col-lg-3">
					<span style="font-weight: bold;">Afficher : </span>
					<label for="answer">Par réponse </label>
					<select name="answer">
						<option value="all" selected>Toutes</option>
						<c:forEach items="${answers}" var="answer">
						<option value="<c:out value="${answer.name}"/>"><c:out value="${answer.name}"/></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-2">
					<label for="jobFunction">Par job </label>
					<select name="jobFunction">
						<option value="all" selected>Tous</option>
						<c:forEach items="${jobs}" var="job">
						<option value="<c:out value="${job}"/>"><c:out value="${job}"/></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-2">
					<label for="locality">Par localité </label>
					<select name="locality">
						<option value="all" selected>Toutes</option>
						<c:forEach items="${localities}" var="locality">
						<option value="<c:out value="${locality}"/>"><c:out value="${locality}"/></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-2">
					<label for="country">Par pays </label>
					<select name="country">
						<option value="all" selected>Tous</option>
						<c:forEach items="${countries}" var="country">
						<option value="<c:out value="${country}"/>"><c:out value="${country}"/></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-2">
					<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-eye-open"></span> Afficher selon les critères</button>
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
