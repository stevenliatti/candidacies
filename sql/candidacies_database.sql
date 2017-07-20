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

CREATE TABLE IF NOT EXISTS users (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	userName varchar(20) NOT NULL UNIQUE,
	password char(56) NOT NULL,
	lastName varchar(50) NOT NULL,
	firstName varchar(50) NOT NULL,
	initials varchar(5) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS jobs_types (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title varchar(20) NOT NULL UNIQUE
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
	sendDate datetime NOT NULL,
	writer varchar(5) NOT NULL REFERENCES users(initials),
	jobFunction varchar(50) REFERENCES jobs_functions(title),
	answer varchar(50) NOT NULL REFERENCES answers(name)

	-- CONSTRAINT fk_locality FOREIGN KEY (locality) REFERENCES localities(name)
)

ENGINE=INNODB;

INSERT INTO localities (name) VALUES ('Genève'), ('Carouge'), ('Meyrin'), ('Veyrier');

INSERT INTO countries (name) VALUES ('Suisse'), ('France'), ('Allemagne'), ('Belgique');
