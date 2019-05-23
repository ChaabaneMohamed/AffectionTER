<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<script src ="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script>
		<%@ include file="script/script.js"%>
	</script>
	<meta charset="utf-8" />
	<title>Validation</title>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>
	<%@ include file="menu_eleve.jsp" %>
</head>
<body>  
	<div class= "container">
	<div class="progress">
  		<div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 90%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	</div>
	
	<p>
		L'algorithme d'affectation a bien été lancé.
	</p>
	<p>Dans la prochaine page, vous pourrez envoyer des mails généralisés aux élèves avec leur affectation d'options, et un mail au secrétariat afin qu'ils aient connaissance de ces affectations.</p>
	
	
	<footer>
		<div class="bouton_confirm">
			<p>
				<a  class = "btn btn-primary" href="prof_mail?token=${ token }" role ="button">Suivant</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>