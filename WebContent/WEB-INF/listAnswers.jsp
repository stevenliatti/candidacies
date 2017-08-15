<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Liste des réponses</title>
	<link rel="shortcut icon" href="<c:url value="/inc/favicon.ico"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<p class="col-md-12"><a class="btn btn-primary" href="<c:url value="/candidates"/>" ><span class="glyphicon glyphicon-eye-open"></span> Afficher les candidats</a></p>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<h1>Liste des réponses</h1>
			</div>
		</div>
	
		<div class="row">
			<div class="col-lg-12">
				<a href="<c:url value="/admin/createAnswer"/>" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Créer réponse</a>
			</div>
		</div>
		<br>
		
		<div class="col-lg-12 table-responsive">
			<table class="col-md-12 table table-bordered table-striped table-condensed">
				<tr>
					<th>Id</th>
					<th>Nom court</th>
					<th>Titre (apparait dans les lettres)</th>
					<th>Contenu</th>
					<th>Masqué</th>
					<th></th>
				</tr>
				<c:forEach items="${answers}" var="answer">
				<tr>
					<td><c:out value="${answer.id}"/></td>
					<td><c:out value="${answer.name}"/></td>
					<td><c:out value="${answer.title}"/></td>
					<td><c:out value="${answer.content}"/></td>
					<td><c:out value="${answer.hide}"/></td>
					<td><a href="<c:url value="/admin/updateAnswer?id="/><c:out value="${answer.id}"/>">Modifier</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
