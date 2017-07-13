package forms;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.DAOException;
import dao.ObjectDAO;

public class LoginForm extends Form {

	public LoginForm(ObjectDAO objectDAO) {
		super(objectDAO);
	}
	
	public User loginUser(HttpServletRequest request) {
		String userName = getField(request, userNameField);
		String password = getField(request, passwordField);
		User user = null;
		result = "errors";
		
		try {
			userName = validateName(userName, userNameField, "Merci de saisir un nom d'utilisateur.");
			password = validateName(password, passwordField, "Merci de saisir un mot de passe.");

//			user = new User(null, userName, password, getField(request, lastNameField), getField(request, firstNameField), 
//					getField(request, initialsField));
			
			user = new User(null, userName, password, "bob", "max", "bm");
			
			if (errors.isEmpty()) {
				result = "success";
			}
		} catch (DAOException e) {
			result = "Échec de la connexion.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
