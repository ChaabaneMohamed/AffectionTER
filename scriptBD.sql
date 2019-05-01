CREATE TABLE IF NOT EXISTS Students (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	year INT NOT NULL,
	lastName VARCHAR(50),
	firstName VARCHAR(50),
	numEtudiant VARCHAR(8),
	mail VARCHAR(50),
	token VARCHAR(50),
	step VARCHAR(50));
			
CREATE TABLE IF NOT EXISTS Options (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	intitule VARCHAR(50),
	description VARCHAR(50),
	size INTEGER,
	optionGroup INTEGER,
	year INTEGER);
