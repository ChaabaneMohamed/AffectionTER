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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


		<%@ include file="menu_eleve.jsp" %>
	</head>
	
	<body>
	<div class= "container">
		<h1>Bienvenue sur Affectop !</h2>
		
		<p class ="jumbotron">Bienvenue sur le site Affectop, créé dans le but de permettre aux élèves de classer les choix de leurs options par ordre de préférence.
Une fois triées, les options vous seront attribuées ultérieurement en fonction du nombre de places disponibles.</p>
	<footer>
		<div class = "bouton_confirm">
			<p>
				<a class="btn btn-primary" href="eleve_choix?token=${ token }" role="button">Suivant</a>
			</p>
		</div>
	</footer>
	</div>
	</body>
</html>