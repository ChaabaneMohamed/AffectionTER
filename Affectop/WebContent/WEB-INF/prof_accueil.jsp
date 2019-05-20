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
		<h2>Bienvenue sur Affectop !</h2>
		
		<p>Bienvenue sur le site Affectop, créé dans le but de permettre aux élèves de classer les choix de leurs options par ordre de préférence.
Une fois triées, les options vous seront attribuées ultérieurement en fonction du nombre de places disponibles.</p>

		 
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