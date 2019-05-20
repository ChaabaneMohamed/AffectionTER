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
	<div class= "content">
	<p>Triez les options dans l'ordre de préférence pour chaque UE
	allant de 1 à n</p>
	
	   	<form method="post" action="eleve_choix?token=${ token }">
	   		<c:forEach var="j"  begin="1" end="${ groupOp.size() }" >
	   			<h3>Option n°<c:out value="${ j }"></c:out></h3>
		       	<table id="t01">
		      	<tr>
			      	<th>Nom de l'UE </th>
			      	<th>Crédit</th>
		      	</tr>
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
	        <input type="submit" value="Valider vos choix"/>
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
			<p class="erreur">Vos préférences ont été enregistré</p>
		</c:if>
   	</ul> 
	
	<footer>
	<c:if test="${ prefPerGroup != null }">
			<div class="bouton" >
				<p>
					<a href="eleve_valider?token=${ token }">Valider</a>
				</p>
			</div>
			</c:if>
			<div class="bouton_retour">
				<p>
					<a href="eleve_desc?token=${ token }">Précédent</a>
				</p>
			</div>
	</footer>
	</div>
</body>
</html>
