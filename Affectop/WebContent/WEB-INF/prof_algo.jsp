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
	<%@ include file="progress.jsp" %>
	<script>
	$('.progress .bar').removeClass().addClass('bar');
		progress(6);
	</script> 
	<div class= "content">
	
	<p>
		L'algorithme d'affectation a bien été lancé.
	</p>
	<p>Dans la prochaine page, vous pourrez envoyer des mails généralisés aux élèves avec leur affectation d'options, et un mail au secrétariat afin qu'ils aient connaissance de ces affectations.</p>
	</div>
	
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_mail?token=${ token }">Suivant</a>
			</p>
		</div>
	</footer>
</body>
</html>