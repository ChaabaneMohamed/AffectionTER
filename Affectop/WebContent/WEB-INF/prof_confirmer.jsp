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
	<div class= "container">
	<div class="progress">
  		<div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width:60%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	</div>
	
	
	<h2>Contenu du mail à envoyer au élèves</h2> 
	</br>
	<p>Balises autorisées: &lt;NOM&gt; &lt;PRENOM&gt; &lt;NUMETU&gt; &lt;LIEN_FORM&gt;</p> 
	<form method="post" action="prof_confirmer?token=${ token }" id="mailform" class="mail">   
		<textarea rows="10" cols="150" name="mail" form="mailform" required="required">${ !empty mail ? mail : 'Mail adressé à <NOM> <PRENOM>, Numéro étudiant: <NUMETU>.
Vous avez été contacté par vos enseignants dans le but de choisir vos options pour le prochain semestre.
Pour ce faire, rendez-vous sur ce site avec le lien suivant : <LIEN_FORM>, et suivez les étapes indiquées.' }</textarea>
	    </br>
	    <input class = "btn btn-info" type="submit" type="submit" value="Tester le contenu du mail et Envoyer" onclick="return confirm('Etes vous sûr de vouloir envoyer les mails au eleves ?')" role = "button"/>
    </form>
    <c:if test="${ result.size() > 0 }">
    	<p class="erreur">Balise incorrect : <c:forEach var="r" items="${ result }"><c:out value="${ r }"/> </c:forEach></p>
    </c:if>
    
    <c:if test="${ result.size() == 0 }">
    	<p class="valid">Contenu Valide, Email Envoyé </p>
    </c:if>
 	</br>
    <h2>Liste des élèves :</h2>   
    <div class ="table-wrapper-scroll-y my-custom-scrollbar">
    <table class = "table table-bordered table-striped mb-0 ">

    		<thead class = "thead-dark">
    			<tr>
	    			<th>Prénom</th>
	    			<th>Nom</th>
	    			<th>email</th>
    			</tr>
    		<thead>
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
    </br>
	<footer>
		<c:if test="${result.size() == 0}">	
		<div class="bouton_confirm">
			<p>
				<a class = "btn btn-primary" type="submit"href="prof_valider?token=${ token }" role = "button">Suivant</a>
			</p>
		</div>
		</c:if>
		<div>
			<p>
				<a class="btn btn-dark" href="prof_redoublant?token=${ token }" role = "button">Retour</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>