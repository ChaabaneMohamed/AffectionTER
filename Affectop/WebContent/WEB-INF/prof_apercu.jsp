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
	<h2>Liste des élèves et leurs préférences d'UE pour chaque options</h2>
	<form method="post" action="prof_apercu?token=${ token }" id="redform">  
		<div>
	    	<button type="submit">rafraîchir</button>
	 	</div>
	 	<c:forEach var="j"  begin="1" end="${ groupOp.size()}">
	 		<h3>Option n°<c:out value="${ j }"></c:out></h3>
			<table>
		    	<tr>
		    		<th id="gris">Prénom</th>
		   			<th id="gris">Nom</th>
		   			<c:forEach var="option"  items="${ options }">
		   				<c:if test="${ groupOp.get(j).contains(option.getId()) }">
		   					<th><c:out value="${ option.nom }"/></th>
		   				</c:if>
		   			</c:forEach>
		   		</tr>
			    <c:forEach var="e"  begin="0" end="${ eleves.size()-1 }">
			       <tr>
			         <td class="eleve" id="gris">
			           	<c:out value="${ eleves.get(e).getNom() }" />	
			         </td>
			       	 <td id="gris">
				        <c:out value="${ eleves.get(e).getPrenom() }" />
			         </td>
			         <c:forEach var="o"  begin="1" end="${ options.size() }">
			         	<c:if test="${ groupOp.get(j).contains(options.get(0).getId()) }">
			         		<c:forEach var="p"  begin="1" end="${ prefs.get(eleves.get(e).getNumEtudiant()).size() }">
			         			<td>a</td>
			         		</c:forEach>
		   				</c:if>
		   			</c:forEach>
			       </tr>
			    </c:forEach>  
		   	</table>
	   	</c:forEach>
 	</form>
   	
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