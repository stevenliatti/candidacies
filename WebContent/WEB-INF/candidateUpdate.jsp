<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Candidat - Modification</title>
	<link rel="shortcut icon" href="<c:url value="/inc/favicon.ico"/>" />
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/themes/smoothness/jquery-ui.css" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/bootstrap.min.css"/>" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/my.css"/>" />
</head>
<body>
	<div class="container">
		<div class="row">
			<p class="col-md-12"><a class="btn btn-primary" href="<c:url value="/candidates"/>"><span class="glyphicon glyphicon-eye-open"></span> Afficher les candidats</a></p>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<h1 >Candidat - Modification</h1>
				<hr>
			</div>
		</div>
		
		<form class="col-md-12" method="post" action="<c:url value="update?id=" /><c:out value="${ candidate.id }"/>">
			<fieldset>
				<div class="row">
					<div class="col-md-12">
						<label for="title">Titre :<span class="required">* </span></label>
						<label for="title_mme"><input type="radio" name="title" id="title_mme" value="Madame" <c:out value="${candidate.title == 'Madame' ? 'checked' : '' }"/> required autofocus > Madame</label>
						<label for="title_m"><input type="radio" name="title" id="title_m" value="Monsieur" <c:out value="${candidate.title == 'Monsieur' ? 'checked' : '' }"/>> Monsieur</label>
						<label for="title_mlle"><input type="radio" name="title" id="title_mlle" value="Mademoiselle" <c:out value="${candidate.title == 'Mademoiselle' ? 'checked' : '' }"/>> Mademoiselle</label>
						<span class="error">${form.errors['title']}</span>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-4">
						<label for="lastName">Nom<span class="required">*</span></label>
						<input autocomplete="off" class="form-control" type="text" id="lastName" name="lastName" value="<c:out value="${ candidate.lastName }"/>" size="20" maxlength="50" required />
						<span class="error">${form.errors['lastName']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="firstName">Prénom<span class="required">*</span></label>
						<input autocomplete="off" class="form-control" type="text" id="firstName" name="firstName" value="<c:out value="${ candidate.firstName }"/>" size="20" maxlength="50" required />
						<span class="error">${form.errors['firstName']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="email">Email</label>
						<input autocomplete="off" class="form-control" type="email" id="email" name="email" value="<c:out value="${ candidate.email }"/>" size="30" maxlength="100" />
						<span class="error">${form.errors['email']}</span>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-4">
						<label for="livesAt">Vit chez</label>
						<input autocomplete="off" class="form-control" type="text" id="livesAt" name="livesAt" value="<c:out value="${ candidate.livesAt }"/>" size="30" maxlength="100" />
						<span class="error">${form.errors['livesAt']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="street">Rue</label>
						<input autocomplete="off" class="form-control" type="text" id="street" name="street" value="<c:out value="${ candidate.street }"/>" size="30" maxlength="100" />
						<span class="error">${form.errors['street']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="numStreet">Numéro Rue</label>
						<input autocomplete="off" class="form-control" type="text" id="numStreet" name="numStreet" value="<c:out value="${ candidate.numStreet }"/>" size="5" maxlength="10" />
						<span class="error">${form.errors['numStreet']}</span>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-4">
						<label for="postCode">Code postal (chiffres uniquement)</label>
						<input autocomplete="off" class="form-control" type="number" id="postCode" min="0" name="postCode" value="<c:out value="${ candidate.postCode }"/>" size="5" maxlength="10" />
						<span class="error">${form.errors['postCode']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="locality">Localité</label>
						<input autocomplete="off" class="form-control" type="text" id="locality" name="locality" value="<c:out value="${ candidate.locality }"/>" size="20" maxlength="50" />
						<span class="error">${form.errors['locality']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="country">Pays</label>
						<input autocomplete="off" class="form-control" type="text" id="country" name="country" value="<c:out value="${ candidate.country }"/>" size="20" maxlength="50" />
						<span class="error">${form.errors['country']}</span>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-4">
						<label for="requestDate">Date de demande (format jj.mm.aaaa)</label>
						<input class="form-control" type="date" id="requestDate" name="requestDate" value="<c:out value="${ candidate.getRequestDateFormFormatted() }"/>" size="20" maxlength="50" />
						<span class="error">${form.errors['requestDate']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="initials">Initiales auteur<span class="required">*</span></label>
						<input class="form-control" type="text" id="initials" name="initials" value="<c:out value="${ candidate.initials }"/>" size="10" maxlength="10" required />
						<span class="error">${form.errors['initials']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="jobFunction">Job<span class="required">*</span></label>
						<input autocomplete="off" class="form-control" type="text" id="jobFunction" name="jobFunction" value="<c:out value="${ candidate.jobFunction }"/>" size="20" maxlength="50" required />
						<span class="error">${form.errors['jobFunction']}</span>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-4">
						<label for="answer">Réponse<span class="required">*</span></label>
						<select class="form-control" name="answer">
							<c:forEach items="${answers}" var="answer">
							<option value="<c:out value="${answer.name}"/>" <c:out value="${candidate.answer == answer.name ? 'selected' : '' }"/>><c:out value="${answer.name}"/></option>
							</c:forEach>
						</select>
						<span class="error">${form.errors['answer']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="folder">Annexe dossier ? :<span class="required">* </span></label>
						<input type="radio" name="folder" id="folderYes" value="yes" <c:out value="${candidate.folder == 'yes' ? 'checked' : '' }"/> required > <label for="folderYes">Oui</label>
						<input type="radio" name="folder" id="folderNo" value="no" <c:out value="${candidate.folder == 'no' ? 'checked' : '' }"/>> <label for="folderNo">Non</label>
						<span class="error">${form.errors['folder']}</span>
					</div>
					
					<div class="col-md-4">
						<label for="sendType">Type d'envoi :<span class="required">* </span></label>
						<input type="radio" name="sendType" id="sendTypePDF" value="pdf" <c:out value="${candidate.sendType == 'pdf' ? 'checked' : '' }"/> required > <label for="sendTypePDF">PDF (impression)</label>
						<input type="radio" name="sendType" id="sendTypeEmail" value="email" <c:out value="${candidate.sendType == 'email' ? 'checked' : '' }"/>> <label for="sendTypeEmail">Email</label>
						<span class="error">${form.errors['sendType']}</span>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-4">
						<label for="notTransmitted">Retour courrier ?<span class="required">*</span></label>
						<input type="radio" name="notTransmitted" id="notTransmittedNo" value="no" <c:out value="${candidate.notTransmitted == 'no' ? 'checked' : '' }"/> required > <label for="notTransmittedNo">Non</label>
						<input type="radio" name="notTransmitted" id="notTransmittedYes" value="yes" <c:out value="${candidate.notTransmitted == 'yes' ? 'checked' : '' }"/>> <label for="notTransmittedYes">Oui</label>
						<span class="error">${form.errors['notTransmitted']}</span>
					</div>
				</div>
				<br>
			
				<button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-ok-sign"></span> Modification</button>
				<br />
				<br />
				<p class="info">Les champs marqués d'un * sont obligatoires.</p>
				<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
			</fieldset>
		</form>
	</div>
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js"></script>
	<script type="text/javascript">
	<c:forTokens var="field" items="lastName;firstName;street;locality;country;jobFunction" delims=";">
		$('#${field}').autocomplete({
			source : "<c:url value="/autocomplete" />?field=${field}"
		});
	</c:forTokens>
	</script>
</body>
</html>