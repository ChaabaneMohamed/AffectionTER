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

<div class= "container">
		<div class="progress">
 		<div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 40%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	</div>	
	
	<section>
		<h2>Ajout des options</h2>
	</section>
    <ul>
	    <c:forEach var="option"  items="${ options }">
	       <li>
	         <p class="option">
	           	Nom : <c:out value="${ option.getNom() }" /> </br>  	
	         </p>
	         <p class="option">
	        	 Option :
	         	<c:forEach var="gr"  items="${ groupOp }" >
	         		<c:if test="${ gr.getOptionId() == option.getId() }">
	           			<c:out value="${ gr.getGroupId() }" /> 
	           		</c:if>
	           	</c:forEach>	
	         </p>
	       
	       
	        </li>
	       	 <a href="prof_option">Supprimer</a>
	    </c:forEach>
   	</ul>      

	</br>
	<c:if test="${ group == null }">
		<form method="post" action="prof_option?token=${ token }" id="groupform">
			<div class="field-text">
				   	<label for="group" class="font">Nombre d'options <span class="fb-required">*</span> :</label>
				   	<input type="number" class="form-control" name="group" id="group" required="required" aria-required="true">
			   	</div>
			   	
			<input class="btn btn-info"  type="submit" value="Valider le nombre de d'option"/>
		</form>
	</c:if>
		</br>
	
	<c:if test="${ group != null }">

   	<form method="post" action="prof_option?token=${ token }" id="opform">
	   	<div class="rendered-form">
		   	<div class="field-text">
			   	<label for="nom" class="font">Nom de l'UE<span class="fb-required">*</span> :</label>
			   	<input type="text" class="form-control" name="nom" id="nom" required="required" aria-required="true">
		   	</div>
		   	
		   	<!--<p class="font">Description<span class="fb-required">*</span> : </p>
		   	<textarea rows="4" cols="50" name="description" form="opform" required="required">Description ...</textarea>  -->
		   	
		   	<div class="field-text">
			   	<label for="mail_prof" class="font">Mail de l'enseignant<span class="fb-required">*</span> :</label>
			   	<input type="email" class="form-control" name="mail_prof" id="mail_prof" required="required" aria-required="true">
		   	</div>
		   	
		   	<div class="field-text">
			   	<label for="size" class="font">Effectif<span class="fb-required">*</span> :</label>
			   	<input type="number" class="form-control" name="size" id="size" required="required" aria-required="true">
		   	</div>
		   	
		   	<p class="font">Donnez les Différentes Options<span class="fb-required">*</span> :</p>
		   	<div class="checkbox-group groupes">
		   			<c:forEach var="i" begin="1" end="${ group }">
		   			<input type="checkbox" id="groupe_${ i }" name="groupe_${ i }" value="groupe_${ i }" required>
				    <label for="groupe_${ i }">Option <c:out value="${ i }"></c:out></label>
					</c:forEach>
		   	</div>
	   	</div>
	   	</br>
	   	<input class="btn btn-info" type="submit" value="Ajouter l'option"/>
   	</form>
   	</c:if>
   	</br>
	<footer>
	<c:if test="${ group != null }">
		<div class="bouton_confirm">
			<p>
				<a  class="btn btn-primary" href="prof_redoublant?token=${ token }" role="button" >Suivant</a>
			</p>
		</div>
		</c:if>
		
		<div >
			<p>
				<a class="btn btn-dark" href="prof_ajout?token=${ token }" role="button" >Retour </a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>