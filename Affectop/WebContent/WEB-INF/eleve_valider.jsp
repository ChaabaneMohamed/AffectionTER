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
	<div class= "content">
	<p>
		Le choix de vos options a bien été enregistré.</br>
		Vous avez jusqu'au xx/xx/xxxx pour modifier vos choix.
	</p>

	<footer>
		<div class="bouton_retour">
			<p>
				<a href="eleve_choix?token=${ token }">Retour au choix des options</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>
