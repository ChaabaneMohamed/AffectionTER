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
		progress(5);
	</script>
	<div class= "content">
	<h2>Liste des élèves et leurs préférences d'options</h2>
	</br>
	<table>
    	<tr>
    		<td id="gris">Prénom</td>
   			<td id="gris">Nom</td>
   		</tr>
	    <c:forEach var="eleve"  items="${ eleves }">
	       <tr>
	         <td class="eleve" id="gris">
	           	<c:out value="${ eleve.nom }" />	
	         </td>
	       	 <td id="gris">
		        <c:out value="${ eleve.prenom }" />
	         </td>
	         <c:forEach var="option"  items="${ eleve.optionPref }">
   				<td>
   					<c:out value="${ option.nom }"></c:out>
   				</td>
   			</c:forEach>
	       </tr>
	    </c:forEach>  
   	</table>
   	
 	<footer>
		<div class="bouton">
			<p>
				<a href="prof_algo?token=${ token }">Lancer l'algorithme d'affectation</a>
			</p>
		</div>
	</footer>
   	</div>
</body>

</html>