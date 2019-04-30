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
	<p>Triez les options dans l'ordre de préférence pour chaque jour
	allant de 1 à <c:out value="${ options.size() } :"/></p>
	
   	<form method="post" action="eleve_choix?token=${ token }">
       <table id="t01">
       <tr> <th>Nom de l'option </th>
           <c:forEach var="i"  begin="1" end="${ options.size() }">
           <th> <c:out value="${i}"></c:out> </th>
           </c:forEach>
            </tr>
    <c:forEach var="option"  items="${ options }">
     <p class="option">
                <tr> <td class = "nameOption"> <label for="option"><c:out value="${ option.nom }"/> </label> </td>
                <c:forEach var="i"  begin="1" end="${ options.size() }">
                    <td> <input type="radio" id="i" name="<c:out value="${ i }"/>" value ="<c:out value="${ option.nom }" />" required>
                </c:forEach> </tr>
      </p>
        </c:forEach>
    </table>
        <input type="submit" value="Valider votre choix"/> 
    </form>
	
	<ul>
	    <c:forEach var="c"  items="${ choix }">
	       <li>
	       	 <p>
		     	<c:out value="${ choix.indexOf(c) +1}" /> : <c:out value="${ c}" />
	         </p>
	        </li>
	    </c:forEach>
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
