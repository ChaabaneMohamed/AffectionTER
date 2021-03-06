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

	<div class= "container">
	<div class="progress">
  		<div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 95%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	</div>
	</br>
	
	<form method="post" action="prof_mail?token=${ token }"> 
		<h2>Contenu du mail à envoyer au élèves</h2> 
		</br>  
		<p>Balise autorisées: &lt;NOM&gt; &lt;PRENOM&gt; &lt;NUMETU&gt; &lt;LIENPDF&gt;</p>
		<textarea rows="10" cols="150" name="mail_eleve" form="mailform" required="required">${ !empty mail_eleve ? mail_eleve : "Mail adressé à <NOM> <PRENOM>, Numéro étudiant: <NUMETU>
L'attribution de vos options est terminée. Vous trouverez dans le lien suivant les unités d'enseignement qui vous ont été affectées : <LIENPDF>.
Adressez-vous à vos enseignants si vous avez une question sur l'affectation proposée.
Mail envoyé automatiquement." }</textarea>
	    <c:if test="${ result1.size() > 0 }">
    		<p class="erreur">Balise incorrect : <c:forEach var="r" items="${ result1 }"><c:out value="${ r }"/> </c:forEach></p>
 		</c:if>

	    <h2>Mail pour le secrétariat</h2>
	    </br>
	    <p>Balise autorisées: &lt;LIENSCO&gt;</p>
	    <textarea rows="10" cols="150" name="mail_secretariat" form="mailform" required="required">${ !empty mail_secretariat ? mail_secretariat : "Mail adressé au secrétariat.
Ci-joint le PDF comprenant l'affectation aux UE proposées pour chaque élève : <LIENSCO>
	    " }</textarea>
	    <c:if test="${ result2.size() > 0 }">
    		<p class="erreur">Balise incorrect : <c:forEach var="r" items="${ result2 }"><c:out value="${ r }"/> </c:forEach></p>
   		</c:if>
	    
	    <input class = "btn btn-info" type="submit"value="Tester le contenu des mail et Envoyer" onclick="return confirm('Etes vous sûr de vouloir envoyer les mails au eleves ?')" role = "button"/>
    </form>
    </br>
    <c:if test="${ result1.size() == 0 && result2.size() == 0}">
    	<p class="valid">Contenu Valide, Email Envoyé</p>
    </c:if>
    
        
	<footer>
		<div class="bouton_confirm ">
			<p>
				<a class = "btn btn-primary" href="prof_final?token=${ token }" ">Suivant</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>