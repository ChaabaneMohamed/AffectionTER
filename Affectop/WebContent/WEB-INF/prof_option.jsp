<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<script src ="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script>
		<%@ include file="script/script.js"%>
	</script>
	<meta charset="utf-8" />
	<title>Test</title>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>
	<%@ include file="menu_eleve.jsp" %>
</head>
<body> 
	<%@ include file="progress.jsp" %>
	<script>
	$('.progress .bar').removeClass().addClass('bar');
		progress(2);
	</script>

	<div class= "content">
	<section>
		<h2>Ajout des options</h2>
	</section>
		<h4>"Nom de la promotion"</h4>
    <ul>
	    <c:forEach var="option"  items="${ options }">
	       <li>
	         <p class="option">
	           	Nom : <c:out value="${ option.getNom() }" /> </br>  	
	         </p>
	         <p class="option">
	           	Jour : <c:out value="${ option.getGroupes() }" /> </br>  	
	         </p>
	       
	       
	        </li>
	       	 <a href="prof_option">Supprimer</a>
	    </c:forEach>
   	</ul>      

	</br>
	<c:if test="${ group == null }">
		<form method="post" action="prof_option?token=${ token }" id="groupform">
			<div class="field-text">
				   	<label for="group" class="font">Nombre de groupe<span class="fb-required">*</span> :</label>
				   	<input type="number" class="form-control" name="group" id="group" required="required" aria-required="true">
			   	</div>
			   	
			<input type="submit" value="Valider le nombre de groupe"/>
		</form>
	</c:if>
	
	<c:if test="${ group != null }">
	<p id="groupform">Nombre de groupe :  ${ !empty group ? group : '' }</p>

   	<form method="post" action="prof_option?token=${ token }" id="opform">
	   	<div class="rendered-form">
		   	<div class="field-text">
			   	<label for="nom" class="font">Nom de l'option<span class="fb-required">*</span> :</label>
			   	<input type="text" class="form-control" name="nom" id="nom" required="required" aria-required="true">
		   	</div>
		   	
		   	<p class="font">Description<span class="fb-required">*</span> : </p>
		   	<textarea rows="4" cols="50" name="description" form="opform" required="required">Description ...</textarea>
		   	
		   	<div class="field-text">
			   	<label for="mail_prof" class="font">Mail de l'enseignant<span class="fb-required">*</span> :</label>
			   	<input type="text" class="form-control" name="mail_prof" id="mail_prof" required="required" aria-required="true">
		   	</div>
		   	
		   	<p class="font">Donnez le groupe<span class="fb-required">*</span> :</p>
		   	<div class="checkbox-group groupes">
		   			<c:forEach var="i" begin="1" end="${ group }">
		   			<input type="checkbox" id="groupe_${ i }" name="groupe_${ i }" value="groupe_${ i }" required>
				    <label for="groupe_${ i }">Groupe <c:out value="${ i }"></c:out></label>
					</c:forEach>
		   	</div>
	   	</div>
	   	</br>
	   	<input type="submit" value="Ajouter l'option"/>
	
   	</form>
   	</c:if>
   	
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_redoublant?token=${ token }">Suivant</a>
			</p>
		</div>
		
		<div class="bouton_retour">
			<p>
				<a href="prof_ajout?token=${ token }">Retour</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>