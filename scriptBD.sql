CREATE TABLE IF NOT EXISTS Students (
	studentId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	year INT NOT NULL,
	lastName VARCHAR(50),
	firstName VARCHAR(50),
	numEtudiant INTEGER UNIQUE,
	mail VARCHAR(50),
	token VARCHAR(50),
	step VARCHAR(50));
			
CREATE TABLE IF NOT EXISTS Options (
	optionId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	intitule VARCHAR(50),
	description VARCHAR(500),
	size INTEGER,
	groupId INTEGER,
	year INTEGER);

CREATE TABLE IF NOT EXISTS Preferences (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	choice INTEGER,
	optionId INTEGER,
	numEtudiant INTEGER,
	FOREIGN KEY fk_op(optionId)
	REFERENCES Options(optionId),
	FOREIGN KEY fk_st(numEtudiant)
	REFERENCES Students(numEtudiant));

CREATE TABLE IF NOT EXISTS Repeaters (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	choice INTEGER,
	optionId INTEGER,
	numEtudiant INTEGER,
	FOREIGN KEY fk_preop(optionId)
	REFERENCES Options(optionId),
	FOREIGN KEY fk_prest(numEtudiant)
	REFERENCES Students(numEtudiant));

CREATE TABLE IF NOT EXISTS GroupOp (
	groupId INT NOT NULL PRIMARY KEY,
	optionId INTEGER,
	FOREIGN KEY fk_grop(optionId)
	REFERENCES Options(optionId));
	
ALTER TABLE Options add FOREIGN KEY fk_opgr(groupId) REFERENCES GroupOp(groupId);