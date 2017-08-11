<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Lettres générées</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<p><a class="btn btn-primary" href="<c:url value="/candidates"/>"><span class="glyphicon glyphicon-eye-open"></span> Afficher tous les candidats</a></p>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<p><a href="<c:url value="/download/${generatedFileName}.pdf"/>" class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> Télécharger le pdf</a></p>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<h1>Emails</h1>
			</div>
		</div>
		
		<c:forEach items="${candidatesEmail}" var="candidate">
		<div class="row">
			<div class="col-lg-12">
				<p>Infos de <c:out value="${candidate.title}"/> <c:out value="${candidate.firstName}"/> <c:out value="${candidate.lastName}"/> (id : <c:out value="${candidate.id}"/>)</p>
				<p><a href="mailto:<c:out value="${candidate.email}"/>"><c:out value="${candidate.email}"/></a></p>
				<input class="form-group" type="text" value="<c:out value="${candidate.answerTitle}"/>"/>
				<br>
				<textarea class="col-xs-12 col-lg-8" name="textarea" rows="20" ><c:out value="${candidate.letter}"/></textarea>
			</div>
		</div>
		<br>
		<hr class="black">
		<br>
		</c:forEach>
	</div>
</body>
</html>
