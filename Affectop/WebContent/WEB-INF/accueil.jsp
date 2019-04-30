<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Acceuil</title>
		<style>
		<%@ include file="css/stylesheet.css"%>
		</style>
		<%@ include file="menu_eleve.jsp" %>
	</head>
	<body>
	<div class= "content">
		<h2>Bienvenue sur Affectop !</h2>
		
		<p>Partie élève</p>
		<li><a href="/Affectop/eleve_accueil?token=${ token }">Accueil élève</a></li>
		
		<li><a href="/Affectop/eleve_desc?token=${ token }">Description des options</a></li>
		
		<li><a href="/Affectop/eleve_choix?token=${ token }">Choix des options</a></li>
		
		<li><a href="/Affectop/eleve_valider?token=${ token }">Validation des options</a></li>
		
		<p>Partie prof</p>
		<li><a href="/Affectop/prof_accueil?token=${ token }">Accueil prof</a></li>
		
		<li><a href="/Affectop/prof_ajout?token=${ token }">Ajout fichier excel</a></li>
		
		<li><a href="/Affectop/prof_option?token=${ token }">Ajout option</a></li>
		
		<li><a href="/Affectop/prof_redoublant?token=${ token }">Redoublants</a></li>
		
		<li><a href="/Affectop/prof_confirmer?token=${ token }">Confirmation</a></li>
		
		<li><a href="/Affectop/prof_valider?token=${ token }">Validation</a></li>
		
		<li><a href="/Affectop/prof_apercu?token=${ token }">Apercu des affectation</a></li>
		
		<li><a href="/Affectop/prof_algo?token=${ token }">Lancement algorithme</a></li>
		
		<li><a href="/Affectop/prof_mail?token=${ token }">édition des mail</a></li>
		
		<li><a href="/Affectop/prof_final?token=${ token }">Confirmation final</a></li>
	</div>
	</body>
</html>