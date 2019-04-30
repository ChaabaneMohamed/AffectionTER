 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src ="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script>
		<%@ include file="script/script.js"%>
	</script>
	<meta charset="utf-8" />
	<title>Confirmation</title>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>
	<%@ include file="menu_eleve.jsp" %>
</head>
<body> 
	<%@ include file="progress.jsp" %>
	<script>
	$('.progress .bar').removeClass().addClass('bar');
		progress(4);
	</script>
	<div class= "content">
	<section>  
		<h2>Récapitulatif</h2>
	</section>
	
	<h2>Contenu du mail</h2> 
	</br>
	<form method="post" action="prof_confirmer?token=${ token }" id="mailform" class="mail">   
		<textarea rows="10" cols="150" name="mail" form="mailform" required="required">${ !empty mail ? mail : 'Ecrivez le contenu du mail...' }</textarea>
	    </br>
	    <input type="submit" value="Valider le mail"/>
    </form>
    <c:if test="${ result.size() > 0 }">
    	<p class="erreur">Balise incorrect : <c:forEach var="r" items="${ result }"><c:out value="${ r }"/> </c:forEach></p>
    </c:if>
        
    <h2>Liste des élèves :</h2>    
    <table class="liste">
    		<tr>
    			<td id="gris">Prénom</td>
    			<td id="gris">Nom</td>
    			<td>email</td>
    		</tr>
	    <c:forEach var="eleve"  items="${ eleves }">
	       <tr>
	         <td class="eleve" id="gris">
	           	<c:out value="${ eleve.nom }" />	
	         </td>
	       	 <td id="gris">
		        <c:out value="${ eleve.prenom }" />
	         </td>
	         <td>
		        <c:out value="${ eleve.mail }" />
	         </td>
	       </tr>
	    </c:forEach>  
   	</table>     
        
    <h2>Liste des options :</h2>
    <div class="listeop">  
    <ul>
	    <c:forEach var="option"  items="${ options }">
	       <li>
	         <p class="option">
	           	<c:out value="${ option.nom }" /> : </br>  	
	         </p>
	       	 <p>
		        Description : <c:out value="${ option.description }" />
	         </p>
	        </li>
	    </c:forEach>
   	</ul>   
   	</div>     
   	
	
	<footer>
		<div class="bouton">
			<p>
				<a href="prof_valider?token=${ token }" onclick="return confirm('Etes vous sûr de vouloir envoyer les mails au eleves ?')">Envoyer les mails au elèves</a>
			</p>
		</div>
		
		<div class="bouton_retour">
			<p>
				<a href="prof_redoublant?token=${ token }">Retour</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>