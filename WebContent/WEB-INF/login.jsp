<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Connexion</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/form.css"/>" />
	</head>
	<body>
		<form method="post" action="<c:url value="login" />">
			<fieldset>
				<legend>Connexion</legend>

				<label for="userName">Utilisateur <span class="required">*</span></label>
				<input type="text" id="userName" name="userName" value="<c:out value="${user.userName}"/>" size="20" maxlength="60" />
				<span class="erreur">${form.errors['userName']}</span>
				<br />

				<label for="password">Mot de passe <span class="requis">*</span></label>
				<input type="password" id="password" name="password" value="" size="20" maxlength="20" />
				<span class="erreur">${form.errors['password']}</span>
				<br />

				<input type="submit" value="Connexion" />
				<br />

				<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
				
				<c:if test="${!empty sessionScope.sessionUser}">
				<p class="success">Bonjour : ${sessionScope.sessionUser.userName}</p>
				</c:if>
			</fieldset>
		</form>
	</body>
</html>