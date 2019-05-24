INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step)
	VALUES(2017,'GABIOT','julien','16018927','test.test@etu.univ-amu.fr','1234','SIN301');
	
INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step)
	VALUES(2017,'AISSA','mohammed','13000771','test.test@etu.univ-amu.fr','1235','SIN301');
	
INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step)
	VALUES(2017,'KHEDIJA','omar','15007622','test.test@etu.univ-amu.fr','1236','SIN301');

INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step)
	VALUES(2017,'KHEDIJA','chriss','13000718','test.test@etu.univ-amu.fr','1237','SIN301');

INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step)
	VALUES(2017,'SBROGGIO','ilef','15026324','test.test@etu.univ-amu.fr','1238','SIN301');

INSERT INTO Teachers (lastName,firstName,mail,token)
	VALUES('ProfTest','ProfTest','test.test@etu.univ-amu.fr','1222');

INSERT INTO GroupOp (groupId, optionId)
	VALUES(1, 1);
	
INSERT INTO GroupOp (groupId, optionId)
	VALUES(1, 2);
	
INSERT INTO GroupOp (groupId, optionId)
	VALUES(1, 3);
	
INSERT INTO GroupOp (groupId, optionId)
	VALUES(2, 2);
	
INSERT INTO GroupOp (groupId, optionId)
	VALUES(2, 4);
	
INSERT INTO Options (intitule, mail,size, codeModule , year)
	VALUES('Complexite', 'kevin.perrot@lis-lab.fr', 6, 'FZ5FE698', 2018);
	
INSERT INTO Options (intitule, mail,size, codeModule,year)
	VALUES('Architecture JEE','jean-luc.massat@univ-amu.fr', 3, 'FZ5FE698', 2018);

INSERT INTO Options (intitule, mail,size, codeModule,year)
	VALUES('Fiabilite Logiciel', 'denis.lugiez@univ-amu.fr', 3, 'FZ5FE698', 2018);
	
INSERT INTO Options (intitule, mail,size, codeModule,year)
	VALUES('Genie Logiciel', 'pablo.ARRIGHI@univ-amu.fr',6, 'FZ5FE698', 2018);