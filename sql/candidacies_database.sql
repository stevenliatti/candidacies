SET NAMES utf8;

DROP DATABASE IF EXISTS candidacies;

CREATE DATABASE candidacies CHARACTER SET 'utf8';

USE candidacies;

CREATE TABLE IF NOT EXISTS localities (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS countries (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS jobs_functions (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title varchar(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS answers (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE,
	content text NOT NULL
);

CREATE TABLE IF NOT EXISTS candidates (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title varchar(15) NOT NULL,
	lastName varchar(50) NOT NULL,
	firstName varchar(50) NOT NULL,
	email varchar(100),
	livesAt varchar(100),
	street varchar(100),
	numStreet varchar(10),
	postCode varchar(10),
	locality varchar(50) REFERENCES localities(name),
	country varchar(50) REFERENCES countries(name),
	requestDate date,
	insertDate datetime NOT NULL,
	updateDate datetime NOT NULL,
	sendDate datetime,
	initials varchar(10) NOT NULL,
	jobFunction varchar(50) REFERENCES jobs_functions(title),
	answer varchar(50) NOT NULL REFERENCES answers(name),
	folder varchar(3) NOT NULL
)

ENGINE=INNODB;

INSERT INTO localities (name) VALUES ('Genève'), ('Carouge'), ('Meyrin'), ('Veyrier');

INSERT INTO countries (name) VALUES ('Suisse'), ('France'), ('Allemagne'), ('Belgique');

INSERT INTO candidates (title, lastName, firstName, email, livesAt, street, numStreet, 
	postCode, locality, country, requestDate, insertDate, updateDate, sendDate, initials, 
	jobFunction, answer, folder) VALUES ("Madame", "Dupont", "Jessica", "max@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", NOW(), NOW(), NOW(), 
	NULL, "sl", "infirmier", "negative", "yes");

INSERT INTO candidates (title, lastName, firstName, email, livesAt, street, numStreet, 
	postCode, locality, country, requestDate, insertDate, updateDate, sendDate, initials, 
	jobFunction, answer, folder) VALUES ("Monsieur", "Dupont", "Jean", "max@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", NOW(), NOW(), NOW(), 
	NULL, "sl", "infirmier", "negativeSixMonths", "yes");

INSERT INTO candidates (title, lastName, firstName, email, livesAt, street, numStreet, 
	postCode, locality, country, requestDate, insertDate, updateDate, sendDate, initials, 
	jobFunction, answer, folder) VALUES ("Mademoiselle", "Dupuis", "Nina", "max@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", NOW(), NOW(), NOW(), 
	NULL, "sl", "animateur", "suspendSixMonths", "no");

INSERT INTO candidates (title, lastName, firstName, email, livesAt, street, numStreet, 
	postCode, locality, country, requestDate, insertDate, updateDate, sendDate, initials, 
	jobFunction, answer, folder) VALUES ("Monsieur", "Dupont", "Fred", "max@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", NOW(), NOW(), NOW(), 
	NULL, "sl", "cuisinier", "negative", "yes");

INSERT INTO candidates (title, lastName, firstName, email, livesAt, street, numStreet, 
	postCode, locality, country, requestDate, insertDate, updateDate, sendDate, initials, 
	jobFunction, answer, folder) VALUES ("Monsieur", "Dupont", "Max", "max@mail.com", 
	"Bob", "Rue des tests", "42", "999", "Test Ville", "Suisse", NULL, NOW(), NOW(), 
	NULL, "sl", "cuisinier", "negative", "yes");
