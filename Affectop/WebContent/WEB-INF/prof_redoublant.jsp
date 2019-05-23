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
  		<div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 50%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	</div>
	<h2>Sélectionnez les UE validées pour chaque étudiant redoublant</h2>
	</br>
	<form method="post" action="prof_redoublant?token=${ token }" id="redform">  
		<table class = "table table-striped">
		<thead class = "thead-dark">
	    	<tr>
	    		<th>Prénom</th>
	   			<th>Nom</th>
	   			<c:forEach var="option"  items="${ options }">
	   			<th><c:out value="${ option.nom }"/></th>
	   			</c:forEach>
	   		</tr>
	   	</thead>
		    <c:forEach var="e"  begin="0" end="${ eleves.size()-1 }">
		       <tr>
		         <td>
		           	<c:out value="${ eleves.get(e).getNom() }" />	
		         </td>
		       	 <td>
			        <c:out value="${ eleves.get(e).getPrenom() }" />
		         </td>
		         <c:forEach var="o"  begin="0" end="${ options.size()-1 }">
	   				<td>
	   					<input type="checkbox" id="redoublant" name="valide${e}_${o}" value="${ true }">
	    				<label for="redoublant"></label>
	   				</td>
	   			</c:forEach>
		       </tr>
		    </c:forEach>  
	   	</table>
	   	<div>
	    	<button class = "btn btn-info" type="submit">Confirmer les redoublants</button>
	 	</div>
 	</form>
 	</br>
 	<footer>
		<div class="bouton_confirm">
			<p>
				<a class="btn btn-primary"href="prof_confirmer?token=${ token }" role="button" >Suivant</a>
			</p>
		</div>
		<div>
			<p>
				<a class="btn btn-dark" href="prof_option?token=${ token }" role="button">Retour</a>
			</p>
		</div>
	</footer>
   	</div>
</body>

</html>