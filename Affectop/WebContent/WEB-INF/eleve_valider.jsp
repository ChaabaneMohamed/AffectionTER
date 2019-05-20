<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Validation</title>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>

	<%@ include file="menu_eleve.jsp" %>
</head>
<body>   
	<div class= "container">
	<p>
		Le choix de vos options a bien été enregistré.</br>
		Vous avez jusqu'au xx/xx/xxxx pour modifier vos choix.
	</p>

	<footer>
		
			<p>
				<a class="btn btn-dark" href="eleve_choix?token=${ token }" role="button">Retour au choix des options</a>
			</p>
	
	</footer>
	</div>
</body>
</html>
