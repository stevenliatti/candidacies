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
		<form method="post" action="create">
			<fieldset>
				<legend>Candidat - Insertion</legend>
				
				<label for="title">Titre<span class="required">*</span></label>
				<input type="radio" name="title" id="title_mme" value="Madame" <c:out value="${candidate.title == 'Madame' ? 'checked' : '' }"/>> Madame
				<input type="radio" name="title" id="title_m" value="Monsieur" <c:out value="${candidate.title == 'Monsieur' ? 'checked' : '' }"/>> Monsieur
				<input type="radio" name="title" id="title_mlle" value="Mademoiselle" <c:out value="${candidate.title == 'Mademoiselle' ? 'checked' : '' }"/>> Mademoiselle
				<span class="error">${form.errors['title']}</span>
				<br />
				
				<label for="last_name">Nom<span class="required">*</span></label>
				<input type="text" id="last_name" name="last_name" value="<c:out value="${empty form.errors ? '' : candidate.lastName }"/>" size="20" maxlength="50" required />
				<span class="error">${form.errors['last_name']}</span>
				<br />
				
				<label for="first_name">Prénom<span class="required">*</span></label>
				<input type="text" id="first_name" name="first_name" value="<c:out value="${empty form.errors ? '' : candidate.firstName }"/>" size="20" maxlength="50" required />
				<span class="error">${form.errors['first_name']}</span>
				<br />
				
				<label for="email">Email</label>
				<input type="email" id="email" name="email" value="<c:out value="${empty form.errors ? '' : candidate.email }"/>" size="30" maxlength="100" />
				<span class="error">${form.errors['email']}</span>
				<br />

				<label for="lives_at">Vit chez</label>
				<input type="text" id="lives_at" name="lives_at" value="<c:out value="${empty form.errors ? '' : candidate.livesAt }"/>" size="30" maxlength="100" />
				<span class="error">${form.errors['lives_at']}</span>
				<br />
				
				<label for="street">Rue</label>
				<input type="text" id="street" name="street" value="<c:out value="${empty form.errors ? '' : candidate.street }"/>" size="30" maxlength="100" />
				<span class="error">${form.errors['street']}</span>
				
				<label for="num_street">Numéro Rue</label>
				<input type="number" id="num_street" name="num_street" value="<c:out value="${empty form.errors ? '' : candidate.numStreet }"/>" size="5" maxlength="10" />
				<span class="error">${form.errors['num_street']}</span>
				<br />
				
				<label for="post_code">NPA</label>
				<input type="number" id="post_code" name="post_code" value="<c:out value="${empty form.errors ? '' : candidate.postCode }"/>" size="5" maxlength="10" />
				<span class="error">${form.errors['post_code']}</span>
				<br />
				
				<label for="locality">Localité</label>
				<input type="text" id="locality" name="locality" value="<c:out value="${empty form.errors ? '' : candidate.locality }"/>" size="20" maxlength="50" />
				<span class="error">${form.errors['locality']}</span>
				<br />
				
				<label for="country">Pays</label>
				<input type="text" id="country" name="country" value="<c:out value="${empty form.errors ? '' : candidate.country }"/>" size="20" maxlength="50" />
				<span class="error">${form.errors['country']}</span>
				<br />
				
				<label for="request_date">Date de demande</label>
				<input type="date" id="request_date" name="request_date" value="<c:out value="${empty form.errors ? '' : candidate.getRequestDateFormatted() }"/>" size="20" maxlength="50" />
				<span class="error">${form.errors['request_date']}</span>
				<br />
				
				<label for="job_type">Job<span class="required">*</span></label>
				<input type="radio" name="job_type" value="poste" checked> poste
				<input type="radio" name="job_type" value="place d'apprentissage"> place d'apprentissage
				<input type="radio" name="job_type" value="place de stage"> place de stage
				<span class="error">${form.errors['job_type']}</span>
				<br />
				
				<label for="job_function">Fonction<span class="required">*</span></label>
				<select id="job_function" name="job_function">
					<option value="infirmier">infirmier</option>
					<option value="animateur">animateur</option>
					<option value="cuisinier">cuisinier</option>
				</select>
				<span class="error">${form.errors['job_function']}</span>
				<br />
				
				<input type="submit" value="Insertion" />
				<br />
			</fieldset>
		</form>
		<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
		
		<script type="text/javascript">
			document.getElementById('title_mme').focus();
		</script>
	</body>
</html>