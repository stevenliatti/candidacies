SET NAMES utf8;

DROP DATABASE IF EXISTS candidacies;
DROP USER IF EXISTS 'candidacies'@'localhost';

CREATE DATABASE candidacies CHARACTER SET 'utf8';
CREATE USER 'candidacies'@'localhost' IDENTIFIED BY 'candidacies';
GRANT ALL ON candidacies.* TO 'candidacies'@'localhost';

USE candidacies;

CREATE TABLE IF NOT EXISTS lastNames (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS firstNames (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS streets (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS localities (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS countries (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS jobFunctions (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS answers (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE,
	title varchar(100) NOT NULL,
	content text NOT NULL
);

CREATE TABLE IF NOT EXISTS candidates (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title varchar(15) NOT NULL,
	lastName varchar(50) NOT NULL REFERENCES lastNames(name),
	firstName varchar(50) NOT NULL REFERENCES firstNames(name),
	email varchar(100),
	livesAt varchar(100),
	street varchar(100) REFERENCES streets(name),
	numStreet varchar(10),
	postCode varchar(10),
	locality varchar(50) REFERENCES localities(name),
	country varchar(50) REFERENCES countries(name),
	requestDate date,
	insertDate datetime NOT NULL,
	updateDate datetime NOT NULL,
	initials varchar(10) NOT NULL,
	jobFunction varchar(50) NOT NULL REFERENCES jobFunctions(name),
	answer varchar(50) NOT NULL REFERENCES answers(name),
	answerTitle varchar(100) NOT NULL REFERENCES answers(title),
	folder varchar(3) NOT NULL,
	sendType varchar(10) NOT NULL,
	letter text,
	notTransmitted varchar(3)
)

ENGINE=INNODB;

INSERT INTO streets (name) VALUES ('Rue de la Servette'), ('Rue de Lyon'), ('Chemin des Marais'), ('Route de Veyrier');
INSERT INTO localities (name) VALUES ('Genève'), ('Carouge'), ('Meyrin'), ('Veyrier');
INSERT INTO countries (name) VALUES ('Suisse'), ('France'), ('Allemagne'), ('Belgique');
INSERT INTO jobFunctions (name) VALUES ('infirmier'), ('animateur'), ('aide-soignant'), ('cuisinier');

INSERT INTO answers (name, title, content) 
VALUES 
('négatif', 'Réponse négative',

'<title>,

Nous accusons réception de votre candidature <requestDate> qui a retenu toute notre attention et nous vous remercions de l’intérêt manifesté pour l’EMS Les Châtaigniers.

Malheureusement, sans que cela ne remette en cause la qualité de votre candidature, nous sommes au regret de vous informer que nous ne pouvons lui apporter une suite favorable.

Dès lors, nous vous encourageons à déposer votre candidature en ligne sur le site de l’AGEMS (Association Genevoise des établissements médico-sociaux).

Nous vous souhaitons de trouver rapidement une opportunité conforme à vos aspirations et vous adressons, <title>, nos sincères salutations.

'),

('négatif six mois', 'Réponse négative après mise en suspens',

'<title>,

Nous faisons suite à notre courrier <requestDate> par lequel nous vous informions que votre candidature était conservée pour une durée de 6 mois.

Malheureusement, n’ayant eu aucun poste à vous proposer durant cette période, nous nous permettons de vous retourner votre dossier. 

Nous vous remercions de la confiance et de l’intérêt manifesté pour l’EMS Les Châtaigniers et vous souhaitons de trouver rapidement un emploi conforme à vos aspirations.

Veuillez agréer, <title>, nos sincères salutations.

'),

('suspens six mois', 'Mise en suspens de votre dossier de candidature',

'<title>,

Nous accusons réception de votre candidature spontanée <requestDate> qui a retenu toute notre attention.

Néanmoins, compte tenu des éléments positifs de votre parcours et sauf avis contraire de votre part, nous souhaitons conserver votre dossier si toutefois un poste correspondant à votre profil venait à se libérer.

Aussi, nous vous encourageons à déposer votre candidature en ligne sur le site de l’AGEMS (Association Genevoise des établissements médico-sociaux).

En vous remerciant de l’intérêt que vous portez à l’EMS Les Châtaigniers, nous vous prions d’agréer, <title>, nos salutations distinguées.

');

INSERT INTO candidates (title, lastName, firstName, email, livesAt, street, numStreet, 
	postCode, locality, country, requestDate, insertDate, updateDate, initials, 
	jobFunction, answer, answerTitle, folder, sendType, notTransmitted) 

VALUES 
	("Madame", "Dupont", "Jessica", "Jessica@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "France", CURDATE(), NOW(), NOW(), 
	"sl", "infirmier", "négatif", "Réponse négative", "yes", "pdf", "no"),

	("Monsieur", "Dupont", "Jean", "Jean@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Allemagne", CURDATE(), NOW(), NOW(), 
	"sl", "infirmier", "négatif six mois", "Réponse négative après mise en suspens", "yes", "pdf", "no"),

	("Mademoiselle", "Dupuis", "Nina", "Nina@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", CURDATE(), NOW(), NOW(), 
	"sl", "animateur", "suspens six mois", "Mise en suspens de votre dossier de candidature", "no", "pdf", "no"),

	("Monsieur", "Dupont", "Fred", "Fred@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", CURDATE(), NOW(), NOW(), 
	"sl", "cuisinier", "négatif", "Réponse négative", "yes", "pdf", "no"),

	("Monsieur", "Dupont", "Max", "Max@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", NULL, NOW(), NOW(), 
	"sl", "cuisinier", "négatif", "Réponse négative", "yes", "email", "no"),

	("Monsieur", "Dupont", "Paul", "Max@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", NULL, NOW(), NOW(), 
	"sl", "cuisinier", "négatif", "Réponse négative", "yes", "email", "no");
