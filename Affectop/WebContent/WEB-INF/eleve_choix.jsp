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
		   		<h3>UE n°<c:out value="${ j }"></c:out>: </h3>
		       	<table id="t01">
		      	<tr> <th>Nom de l'UE </th>
		           <c:forEach var="i"  begin="1" end="${ groupOp.get(j).size() }">
		           		<th> <c:out value="${i}"></c:out> </th>
		           </c:forEach>
		      	</tr>
		    	<c:forEach var="option"  items="${ options }">
		    		<c:if test="${ groupOp.get(j).contains(option.getId()) }">
			     	<p class="option">
			                <tr> <td class = "nameOption"> <label for="option"><c:out value="${ option.nom }"/> </label> </td>
			                <c:forEach var="i"  begin="1" end="${ groupOp.get(j).size() }">
			                    <td> <input type="radio" id="i<c:out value="${j}"></c:out>" name="<c:out value="${ i }"/><c:out value="${j}"></c:out>" value ="<c:out value="${ option.nom }" />" required>
			                </c:forEach> </tr>
			     	</p>
			     	</c:if>
		        </c:forEach>
	    	</table>
	    </c:forEach>
	        <input type="submit" value="Valider vos choix"/> 
	    </form>
    
	<ul>
	    <c:forEach var="c"  items="${ choix }">
	       <li>
	       	 <p>
		     	<c:out value="${ c.toString()}" />
	         </p>
	        </li>
	    </c:forEach>
	    <c:if test="${ choix != null }">
			<p class="erreur">Vos préférences ont été enregistré</p>
		</c:if>
   	</ul> 
	
	<footer>
			<div class="bouton">
				<p>
					<a href="eleve_valider?token=${ token }">Valider</a>
				</p>
			</div>
			<div class="bouton_retour">
				<p>
					<a href="eleve_desc?token=${ token }">Précédent</a>
				</p>
			</div>
	</footer>
	</div>
</body>
</html>
