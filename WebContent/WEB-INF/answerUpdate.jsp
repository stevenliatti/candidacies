<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Réponse - Modification</title>
	<link rel="shortcut icon" href="<c:url value="/inc/favicon.ico"/>" />
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/themes/smoothness/jquery-ui.css" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container">
		<div class="row">
			<p class="col-md-12"><a class="btn btn-primary" href="<c:url value="/candidates"/>" ><span class="glyphicon glyphicon-eye-open"></span> Afficher les candidats</a></p>
		</div>

		<div class="row">
			<p class="col-md-12"><a class="btn btn-warning" href="<c:url value="/admin/answers"/>" ><span class="glyphicon glyphicon-eye-open"></span> Afficher toutes les réponses</a></p>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<h1 >Réponse - Modification</h1>
				<hr>
			</div>
		</div>

		<div class="row">
			<p class="col-md-12">Les champs à disposition sont les suivants : </p>
		</div>
		
		<form class="col-md-12" method="post" action="<c:url value="updateAnswer?id=" /><c:out value="${answer.id}"/>">
			<fieldset>
				<div class="row">
					<div class="col-md-4">
						<label for="name">Nom court<span class="required">*</span> </label>
						<input autocomplete="off" class="form-control" type="text" id="name" name="name" value="<c:out value="${answer.name}"/>" size="20" maxlength="50" placeholder="Nom court" required />
						<span class="error">${form.errors['name']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="title">Titre<span class="required">*</span> </label>
						<input autocomplete="off" class="form-control" type="text" id="title" name="title" value="<c:out value="${answer.title}"/>" size="20" maxlength="50" placeholder="Titre" required />
						<span class="error">${form.errors['title']}</span>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-12">
						<label for="content">Contenu<span class="required">*</span> </label>
						<textarea id="content" class="col-xs-12 col-lg-8" name="content" rows="20" ><c:out value="${answer.content}"/></textarea>
						<span class="error">${form.errors['content']}</span>
					</div>
				</div>
				<br>

				<input type="checkbox" id="hide" name="hide" <c:out value="${answer.hide == 'on' ? 'checked' : ''}"/>>
				<label for="hide">Masquer la réponse lors de création de candidat</label>
				<br>
				
				<button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-ok-sign"></span> Modification</button>
				<br />
				<br />
				<p class="info">Les champs marqués d'un * sont obligatoires.</p>
				<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
			</fieldset>
		</form>
	</div>
</body>
</html>