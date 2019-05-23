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
		    		<th id="gris">Nom</th>
		   			<th id="gris">Prénom</th>
		   			<c:forEach var="option"  items="${ options }">
		   				<c:if test="${ groupOp.get(j).contains(option.getId()) }">
		   					<th><c:out value="${ option.nom }"/></th>
		   				</c:if>
		   			</c:forEach>
		   		</tr>
			    <c:forEach var="e"  begin="1" end="${ eleves.size() }">
			       <tr>
			         <td class="eleve" id="gris">
			           	<c:out value="${ eleves.get(e-1).getNom() }" />	
			         </td>
			       	 <td id="gris">
				        <c:out value="${ eleves.get(e-1).getPrenom() }" />
			         </td>
			         <c:forEach var="o"  begin="0" end="${ options.size()-1 }">
			         	
			         	<c:if test="${ groupOp.get(j).contains(options.get(o).getId()) }">
			         		<c:forEach var="p"  items="${ prefs.get(eleves.get(e-1).getNumEtudiant()) }">
			         			<c:if test="${ p.getGroupId() == j }">
				         			<c:if test="${ p.getOptionId() == options.get(o).getId() }">
				         				<td><c:out value="${ p.getChoice() }"/></td>
				         			</c:if>
			         			</c:if>
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