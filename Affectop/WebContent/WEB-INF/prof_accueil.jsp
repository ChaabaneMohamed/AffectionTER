<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Accueil</title>
		
		<style>
			<%@ include file="css/stylesheet.css"%>
		</style>
		<%@ include file="menu_eleve.jsp" %>
	</head>
	<body>
	<div class= "container">
		<div class="progress">
 		<div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 10%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	</div>	
		<h2>Bienvenue sur Affectop !</h2>
		
		<p>Bienvenue sur le site Affectop, créé dans le but de permettre aux élèves de classer les choix de leurs options par ordre de préférence.
	Sur cette plateforme, vous devrez créer des ensembles d'options à proposer aux étudiants, qu'ils trieront ensuite par ordre de préférence, afin d'obtenir une attribution optimale pour chaque
	étudiant.</p>

		 
	<footer>
		<div class="bouton_confirm">
			<p>
				<a class = "btn btn-primary" href="prof_ajout?token=${ token }" role="button" >Suivant</a>
			</p>
		</div>
	</footer>
	</div>
	</body>
</html>