<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Candidats - Connexion</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
	</head>
	<body>
		<div class="container">
		<form method="post" action="<c:url value="login" />">
			<fieldset>
				<legend>Candidats - Connexion</legend>

				<label for="password">Mot de passe <span class="requis">*</span></label>
				<input type="password" id="password" name="password" value="" size="20" maxlength="20" required />

				<input type="submit" value="Connexion" />
				<br />
			</fieldset>
		</form>
		</div>
	</body>
</html>