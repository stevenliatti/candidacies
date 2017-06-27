SET NAMES utf8;

DROP DATABASE IF EXISTS candidacies;

CREATE DATABASE candidacies CHARACTER SET 'utf8';

USE candidacies;

CREATE TABLE IF NOT EXISTS localities (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS countries (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	username varchar(20) NOT NULL UNIQUE,
	password varchar(255) NOT NULL,
	last_name varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
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
	last_name varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
	lives_at varchar(100),
	email varchar(100),
	street varchar(100),
	num_street varchar(10),
	post_code varchar(10),
	locality varchar(50) REFERENCES localities(name),
	country varchar(50) REFERENCES countries(name),
	request_date date,
	insert_date datetime NOT NULL,
	update_date datetime NOT NULL,
	send_date datetime NOT NULL,
	writer varchar(5) REFERENCES users(initials),
	job_type varchar(20) REFERENCES jobs_types(title),
	job_function varchar(20) REFERENCES jobs_functions(title),
	answer varchar(50) REFERENCES answers(name)

	-- CONSTRAINT fk_locality FOREIGN KEY (locality) REFERENCES localities(name)
)

ENGINE=INNODB;
