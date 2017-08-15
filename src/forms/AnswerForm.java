package forms;

import static beans.Bean.contentField;
import static beans.Bean.hideField;
import static beans.Bean.idField;
import static beans.Bean.nameField;
import static beans.Bean.titleField;

import javax.servlet.http.HttpServletRequest;

import beans.Answer;
import dao.AnswerDAO;
import dao.DAOException;

public class AnswerForm extends Form {
	private AnswerDAO answerDAO;

	public AnswerForm(AnswerDAO answerDAO) {
		super();
		this.answerDAO = answerDAO;
	}

	public Answer createAnswer(HttpServletRequest r) {
		String name = getField(r, nameField);
		String title = getField(r, titleField);
		String content = getField(r, contentField);
		String hide = getFieldCheckbox(r, hideField);
		Answer answer = null;
		
		try {
			name = validateString(name, nameField, "Merci de saisir un nom court.");
			title = validateString(title, titleField, "Merci de saisir un titre");

			answer = new Answer(null, name, title, content, hide);

			if (errors.isEmpty()) {
				answerDAO.create(answer);
				result = "Réponse bien insérée";
			}
			else {
				result = "Erreurs dans le formulaire";
			}
		} catch (DAOException e) {
			result = "Échec de la création d'une réponse.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return answer;
	}
	
	public Answer updateAnswer(HttpServletRequest r) {
		Long id = Long.parseLong(getField(r, idField));
		String name = getField(r, nameField);
		String title = getField(r, titleField);
		String content = getField(r, contentField);
		String hide = getFieldCheckbox(r, hideField);
		Answer updateAnswer = null;
		
		try {
			name = validateString(name, nameField, "Merci de saisir un nom court.");
			title = validateString(title, titleField, "Merci de saisir un titre");
			
			Answer answer = answerDAO.read(id);
			updateAnswer = new Answer(answer.getId(), name, title, content, hide);

			if (errors.isEmpty()) {
				answerDAO.update(updateAnswer);
				result = "Réponse bien modifiée";
			}
			else {
				result = "Erreurs dans le formulaire";
			}
		} catch (DAOException e) {
			result = "Échec de la modification d'une réponse.";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return updateAnswer;
	}
}
