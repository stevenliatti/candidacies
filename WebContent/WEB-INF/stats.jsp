<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Statistiques</title>
	<link rel="shortcut icon" href="<c:url value="/inc/favicon.ico"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<p><a class="btn btn-primary" href="<c:url value="/candidates"/>"><span class="glyphicon glyphicon-eye-open"></span> Afficher tous les candidats</a></p>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<h1>Statistiques</h1>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				Il y a <em><c:out value="${countAll}" /> candidats</em>.
			</div>
		</div>

		<div class="col-lg-6 table-responsive">
			<table class="table table-bordered table-striped table-condensed table-responsive">
			<caption>Candidats par titre (Mme, M., Mlle)</caption>
				<tr>
					<th>Titre</th>
					<th>Nombre</th>
				</tr>
				<c:forEach items="${titles}" var="title">
				<tr>
					<td><c:out value="${empty title.key ? '-' : title.key}"/></td>
					<td><c:out value="${empty title.value ? '-' : title.value}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="col-lg-6 table-responsive">
			<table class="table table-bordered table-striped table-condensed table-responsive">
			<caption>Candidats par réponses</caption>
				<tr>
					<th>Réponse</th>
					<th>Nombre</th>
				</tr>
				<c:forEach items="${answers}" var="answer">
				<tr>
					<td><c:out value="${empty answer.key ? '-' : answer.key}"/></td>
					<td><c:out value="${empty answer.value ? '-' : answer.value}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="col-lg-6 table-responsive">
			<table class="table table-bordered table-striped table-condensed table-responsive">
			<caption>Candidats par localité</caption>
				<tr>
					<th>Localité</th>
					<th>Nombre</th>
				</tr>
				<c:forEach items="${localities}" var="locality">
				<tr>
					<td><c:out value="${empty locality.key ? '-' : locality.key}"/></td>
					<td><c:out value="${empty locality.value ? '-' : locality.value}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="col-lg-6 table-responsive">
			<table class="table table-bordered table-striped table-condensed table-responsive">
			<caption>Candidats par pays</caption>
				<tr>
					<th>Pays</th>
					<th>Nombre</th>
				</tr>
				<c:forEach items="${countries}" var="country">
				<tr>
					<td><c:out value="${empty country.key ? '-' : country.key}"/></td>
					<td><c:out value="${empty country.value ? '-' : country.value}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="col-lg-6 table-responsive">
			<table class="table table-bordered table-striped table-condensed table-responsive">
			<caption>Candidats par jobs</caption>
				<tr>
					<th>Job</th>
					<th>Nombre</th>
				</tr>
				<c:forEach items="${jobFunctions}" var="jobFunction">
				<tr>
					<td><c:out value="${empty jobFunction.key ? '-' : jobFunction.key}"/></td>
					<td><c:out value="${empty jobFunction.value ? '-' : jobFunction.value}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="col-lg-6 table-responsive">
			<table class="table table-bordered table-striped table-condensed table-responsive">
			<caption>Candidats par type d'envoi</caption>
				<tr>
					<th>Type d'envoi</th>
					<th>Nombre</th>
				</tr>
				<c:forEach items="${sendTypes}" var="sendType">
				<tr>
					<td><c:out value="${empty sendType.key ? '-' : sendType.key}"/></td>
					<td><c:out value="${empty sendType.value ? '-' : sendType.value}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>

	</div>
</body>
</html>
