<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Réponse - Insertion</title>
	<link rel="shortcut icon" href="<c:url value="/inc/favicon.ico"/>" />
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/themes/smoothness/jquery-ui.css" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container">
		<div class="row">
			<a class="btn btn-primary" href="<c:url value="/candidates"/>" ><span class="glyphicon glyphicon-eye-open"></span> Afficher les candidats</a>
			<a class="btn btn-warning" href="<c:url value="/admin/answers"/>" ><span class="glyphicon glyphicon-eye-open"></span> Afficher toutes les réponses</a>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<h1 >Réponse - Insertion</h1>
				<hr>
			</div>
		</div>
		
		<form class="col-md-12" method="post" action="<c:url value="createAnswer" />">
			<fieldset>
				<div class="row">
					<div class="col-md-2">
						<p class="col-md-12">Les champs suivants sont à disposition : </p>
						<ul>
							<c:forEach items="${fields}" var="field">
							<li><c:out value="${field}"/></li>
							</c:forEach>
						</ul>
					</div>
					<div class="col-md-10">
						<div class="col-md-4">
							<label for="name">Nom court<span class="required">*</span> </label>
							<input autocomplete="off" class="form-control" type="text" id="name" name="name" value="<c:out value="${empty form.errors ? '' : answer.name }"/>" size="20" maxlength="50" placeholder="Nom court" required />
							<span class="error">${form.errors['name']}</span>
						</div>
						
						<div class="col-md-4">
							<label for="title">Titre<span class="required">*</span> </label>
							<input autocomplete="off" class="form-control" type="text" id="title" name="title" value="<c:out value="${empty form.errors ? '' : answer.title }"/>" size="20" maxlength="50" placeholder="Titre" required />
							<span class="error">${form.errors['title']}</span>
						</div>
						
						<div class="col-md-12">
							<p style="font-weight: bold;" for="content">Contenu<span class="required">*</span> </p>
							<textarea id="content" class="col-xs-12 col-lg-8" name="content" rows="20" required><c:out value="${empty form.errors ? '' : answer.content }"/></textarea>
							<span class="error">${form.errors['content']}</span>
						</div>

						<div class="col-md-12">
							<input type="checkbox" id="hide" name="hide" <c:out value="${answer.hide == 'on' ? 'checked' : ''}"/>>
							<label for="hide">Masquer la réponse lors de création de candidat</label>
						</div>
					</div>
				</div>

				<button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-ok-sign"></span> Insertion</button>
				<br />
				<br />
				<p class="info">Les champs marqués d'un * sont obligatoires.</p>
				<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
			</fieldset>
		</form>

		<c:if test="${!empty answer.id}">
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
				<tr>
					<td><c:out value="${answer.id}"/></td>
					<td><c:out value="${answer.name}"/></td>
					<td><c:out value="${answer.title}"/></td>
					<td><c:out value="${answer.content}"/></td>
					<td><c:out value="${answer.hide}"/></td>	
					<td><a href="<c:url value="/admin/updateAnswer?id="/><c:out value="${answer.id}"/>">Modifier</a></td>
				</tr>
			</table>
		</div>
		</c:if>
	</div>
</body>
</html>