<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Candidat - Insertion</title>
		<link type="text/css" rel="stylesheet" href="form.css" />
	</head>
	<body>
		<p><a href="<c:url value="/"/>">Retour à l'index</a></p>
		
		<form method="post" action="<c:url value="create" />">
			<fieldset>
				<legend>Candidat - Insertion</legend>
				
				<label for="title">Titre<span class="required">*</span></label>
				<input type="radio" name="title" id="title_mme" value="Madame" <c:out value="${candidate.title == 'Madame' ? 'checked' : '' }"/> required > Madame
				<input type="radio" name="title" id="title_m" value="Monsieur" <c:out value="${candidate.title == 'Monsieur' ? 'checked' : '' }"/>> Monsieur
				<input type="radio" name="title" id="title_mlle" value="Mademoiselle" <c:out value="${candidate.title == 'Mademoiselle' ? 'checked' : '' }"/>> Mademoiselle
				<span class="error">${form.errors['title']}</span>
				<br />
				
				<label for="lastName">Nom<span class="required">*</span></label>
				<input type="text" id="lastName" name="lastName" value="<c:out value="${empty form.errors ? '' : candidate.lastName }"/>" size="20" maxlength="50" required />
				<span class="error">${form.errors['lastName']}</span>
				<br />
				
				<label for="firstName">Prénom<span class="required">*</span></label>
				<input type="text" id="firstName" name="firstName" value="<c:out value="${empty form.errors ? '' : candidate.firstName }"/>" size="20" maxlength="50" required />
				<span class="error">${form.errors['firstName']}</span>
				<br />
				
				<label for="email">Email</label>
				<input type="email" id="email" name="email" value="<c:out value="${empty form.errors ? '' : candidate.email }"/>" size="30" maxlength="100" />
				<span class="error">${form.errors['email']}</span>
				<br />

				<label for="livesAt">Vit chez</label>
				<input type="text" id="livesAt" name="livesAt" value="<c:out value="${empty form.errors ? '' : candidate.livesAt }"/>" size="30" maxlength="100" />
				<span class="error">${form.errors['livesAt']}</span>
				<br />
				
				<label for="street">Rue</label>
				<input type="text" id="street" name="street" value="<c:out value="${empty form.errors ? '' : candidate.street }"/>" size="30" maxlength="100" />
				<span class="error">${form.errors['street']}</span>
				
				<label for="numStreet">Numéro Rue</label>
				<input type="text" id="numStreet" name="numStreet" value="<c:out value="${empty form.errors ? '' : candidate.numStreet }"/>" size="5" maxlength="10" />
				<span class="error">${form.errors['numStreet']}</span>
				<br />
				
				<label for="postCode">Code postal (chiffres uniquement)</label>
				<input type="number" id="postCode" name="postCode" value="<c:out value="${empty form.errors ? '' : candidate.postCode }"/>" size="5" maxlength="10" />
				<span class="error">${form.errors['postCode']}</span>
				<br />
				
				<label for="locality">Localité</label>
				<input type="text" id="locality" name="locality" value="<c:out value="${empty form.errors ? '' : candidate.locality }"/>" size="20" maxlength="50" />
				<span class="error">${form.errors['locality']}</span>
				<br />
				
				<label for="country">Pays</label>
				<input type="text" id="country" name="country" value="<c:out value="${empty form.errors ? '' : candidate.country }"/>" size="20" maxlength="50" />
				<span class="error">${form.errors['country']}</span>
				<br />
				
				<label for="requestDate">Date de demande (format jj.mm.aaaa)</label>
				<input type="date" id="requestDate" name="requestDate" value="<c:out value="${empty form.errors ? '' : candidate.getRequestDateFormFormatted() }"/>" size="20" maxlength="50" />
				<span class="error">${form.errors['requestDate']}</span>
				<br />
				
				<label for="initials">Initiales auteur<span class="required">*</span></label>
				<input type="text" id="initials" name="initials" value="<c:out value="${empty form.errors ? '' : candidate.initials }"/>" size="10" maxlength="10" required />
				<span class="error">${form.errors['initials']}</span>
				<br />
				
				<label for="jobFunction">Job<span class="required">*</span></label>
				<input type="text" id="jobFunction" name="jobFunction" value="<c:out value="${empty form.errors ? '' : candidate.jobFunction }"/>" size="20" maxlength="50" required />
				<span class="error">${form.errors['jobFunction']}</span>
				<br />
				
				<label for="answer">Réponse<span class="required">*</span></label>
				<input type="radio" name="answer" id="negative" value="negative" <c:out value="${candidate.answer == 'negative' ? 'checked' : '' }"/> required > Négatif
				<input type="radio" name="answer" id="negativeSixMonths" value="negativeSixMonths" <c:out value="${candidate.answer == 'negativeSixMonths' ? 'checked' : '' }"/>> Négatif après six mois
				<input type="radio" name="answer" id="suspendSixMonths" value="suspendSixMonths" <c:out value="${candidate.answer == 'suspendSixMonths' ? 'checked' : '' }"/>> Suspens pour six mois
				<span class="error">${form.errors['answer']}</span>
				<br />
				
				<label for="folder">Annexe dossier ?<span class="required">*</span></label>
				<input type="radio" name="folder" id="folderYes" value="yes" <c:out value="${candidate.folder == 'yes' ? 'checked' : '' }"/> required > Oui
				<input type="radio" name="folder" id="folderNo" value="no" <c:out value="${candidate.folder == 'no' ? 'checked' : '' }"/>> Non
				<span class="error">${form.errors['folder']}</span>
				<br />
				
				<label for="sendType">Type d'envoi<span class="required">*</span></label>
				<input type="radio" name="sendType" id="sendTypePDF" value="pdf" <c:out value="${candidate.sendType == 'pdf' ? 'checked' : '' }"/> required > PDF (impression)
				<input type="radio" name="sendType" id="sendTypeEmail" value="email" <c:out value="${candidate.sendType == 'email' ? 'checked' : '' }"/>> Email
				<span class="error">${form.errors['sendType']}</span>
				<br />
				
				<input type="submit" value="Insertion" />
				<br />
				<p class="info">Les champs marqués d'un * sont obligatoires.</p>
			</fieldset>
		</form>
		<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
		
		<script type="text/javascript">
			document.getElementById('title_mme').focus();
		</script>
	</body>
</html>