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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
	
	<h2>Contenu du mail à envoyer au élèves</h2> 
	</br>
	<p>Balises autorisées: &lt;NOM&gt; &lt;PRENOM&gt; &lt;NUMETU&gt; &lt;LIEN_FORM&gt;</p> 
	<form method="post" action="prof_confirmer?token=${ token }" id="mailform" class="mail">   
		<textarea rows="10" cols="150" name="mail" form="mailform" required="required">${ !empty mail ? mail : 'Ecrivez le contenu du mail...' }</textarea>
	    </br>
	    <input type="submit" value="Valider le mail"/>
    </form>
    <c:if test="${ result.size() > 0 }">
    	<p class="erreur">Balise incorrect : <c:forEach var="r" items="${ result }"><c:out value="${ r }"/> </c:forEach></p>
    </c:if>
        
    <h2>Liste des élèves :</h2>  
    <div class="table-wrapper-scroll-y my-custom-scrollbar">  
	    <table class="table table-bordered table-striped mb-0">
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
    </div>
    
    <c:if test="${result.size() > 1}">
    	<p class="error">Balises invalide: <c:forEach var="r"  items="${ result }"> <c:out value="${ r }"></c:out></c:forEach></p>
	</c:if>
	
	<footer>
		<c:if test="${result.size() == 0}">
			<div class="bouton">
				<p>
					<a href="prof_valider?token=${ token }" onclick="return confirm('Etes vous sûr de vouloir envoyer les mails au eleves ?')">Envoyer les mails au elèves</a>
				</p>
			</div>
		</c:if>
		<div class="bouton_retour">
			<p>
				<a href="prof_redoublant?token=${ token }">Retour</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>