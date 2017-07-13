<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Connexion</title>
		<link type="text/css" rel="stylesheet" href="form.css" />
	</head>
	<body>
		<form method="post" action="<c:url value="login" />">
			<fieldset>
				<legend>Connexion</legend>

				<label for="user_name">Utilisateur <span class="required">*</span></label>
				<input type="text" id="user_name" name="user_name" value="<c:out value="${user.userName}"/>" size="20" maxlength="60" />
				<span class="erreur">${form.errors['user_name']}</span>
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