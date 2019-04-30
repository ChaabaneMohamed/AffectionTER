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
		progress(4);
	</script> 
	<div class= "content">
	
	<p>
		La liste d'options à bien été enregistrée et les mails envoyés au élèves.
	</p>
	<p>Dans la prochaine page, vous pourrez avoir un aperçu de la liste des élèves qui se sont inscrits ainsi que de leurs préférences d'options.</p>
	</div>
	
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_apercu?token=${ token }">Suivant</a>
			</p>
		</div>
	</footer>
</body>
</html>