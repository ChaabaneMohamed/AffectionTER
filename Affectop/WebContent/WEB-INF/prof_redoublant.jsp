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
		progress(3);
	</script>
	<div class= "content">
	<h2>Sélectionnez les options validées pour chaque étudiants</h2>
	</br>
	<form method="post" action="prof_redoublant?token=${ token }" id="redform">  
	<table>
    	<tr>
    		<td id="gris">Prénom</td>
   			<td id="gris">Nom</td>
   			<c:forEach var="option"  items="${ options }">
   			<td><c:out value="${ option.nom }"/></td>
   			</c:forEach>
   		</tr>
	    <c:forEach var="eleve"  items="${ eleves }">
	       <tr>
	         <td class="eleve" id="gris">
	           	<c:out value="${ eleve.getNom() }" />	
	         </td>
	       	 <td id="gris">
		        <c:out value="${ eleve.getPrenom() }" />
	         </td>
	         <c:forEach var="option"  items="${ options }">
   				<td>
   					<input type="checkbox" id="redoublant" name="valide${ eleves.indexOf(eleve) +1}_${ options.indexOf(option) +1}" value="${ eleve.prenom } ${ eleve.nom } a valide ${ option.nom }">
    				<label for="redoublant"></label>
   				</td>
   			</c:forEach>
	       </tr>
	    </c:forEach>  
   	</table>
   	<div>
    	<button type="submit">confirmer les redoublants</button>
 	</div>
 	</form>
 	
 	<footer>
		<div class="bouton">
			<p>
				<a href="prof_confirmer?token=${ token }">Suivant</a>
			</p>
		</div>
		
		<div class="bouton_retour">
			<p>
				<a href="prof_option?token=${ token }">Retour</a>
			</p>
		</div>
	</footer>
   	</div>
</body>

</html>