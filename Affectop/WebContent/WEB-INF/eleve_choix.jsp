<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<script src ="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script>
		<%@ include file="script/script.js"%>
	</script>
	<meta charset="utf-8" />
	<title>Choix</title>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>

	<%@ include file="menu_eleve.jsp" %>
</head>
<body>   
	<div class= "container">
	   <c:if test="${ prefPerGroup == null }">
	<p>Vous avez 2 * ( nombres d'UE) de points à répartir pour chaque Option, et vous devez pour chaque Options mettre des points dans au moins 75% des UE disponibles.  </p>
	</c:if>
	
	   	<form method="post" action="eleve_choix?token=${ token }">
	   		<c:forEach var="j"  begin="1" end="${ groupOp.size() }" >
	   		<div class="trait"></div>
	   			<h3>Option n°<c:out value="${ j }"></c:out> ( Crédits : <c:out value="${ groupOp.get(j).size() * 2 }"></c:out> ) </h3>
		       	<table class = "table" id="t01">
		       	<thead class = "thead-dark">
		      	<tr>
			      	<th>Nom de l'UE </th>
			      	<th>Crédit</th>
		      	</tr>
		      	</thead>
		    	<c:forEach var="i"  begin="1" end="${ options.size() }">
		    		<c:if test="${ groupOp.get(j).contains(options.get(i-1).getId()) }">
			     	<p class="option">
			                <tr>
				                <td class = "nameOption"> <label for="option"><c:out value="${ options.get(i-1).nom }"/> </label> </td>
				                <td><input type="number"  name="<c:out value="${ i-1 }"/><c:out value="${j}"></c:out>" value ="" required>
			                </tr>
			     	</p>
			     	</c:if>
		        </c:forEach>
	    	</table>
	    </c:forEach>
	    <c:if test="${ prefPerGroup == null }">
	    <div class = "bouton_confirm">
	        <input type="submit" class="btn btn-primary" value="Valider vos choix"  />
	        </div>
	        </c:if> 
	    </form>
    
	<ul>
	    <c:forEach var="p"  items="${ prefPerGroup }">
	       <li>
	       	 <p>
		     	<c:out value="${ p.toString()}" />
	         </p>
	        </li>
	    </c:forEach>
	    <c:if test="${ prefPerGroup != null }">
			<p class="erreur">Vos préférences ont été enregistrées</p>
		</c:if>
   	</ul> 
	
	<footer>
	<c:if test="${ prefPerGroup != null }">
			<div class="bouton_confirm" >
				<p>
					<a class="btn btn-primary" href="eleve_valider?token=${ token }" role="button">Suivant</a>
				</p>
			</div>
			</c:if>
	</footer>
	</div>
</body>
</html>
