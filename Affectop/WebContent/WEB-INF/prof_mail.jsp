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
	<section>  
		<h2>Edition des mails</h2>
	</section>

	<form method="post" action="prof_mail?token=${ token }">   
		<h4>Mail pour les élèves</h4>
		<p>Balise autorisées: &lt;NOM&gt; &lt;PRENOM&gt; &lt;LISTE_AFFECTATION&gt;</p>
		<textarea rows="10" cols="150" name="mail_eleve" form="mailform" required="required">${ !empty mail_eleve ? mail_eleve : 'Ecrivez le contenu du mail...' }</textarea>
	    <c:if test="${ result1.size() > 0 }">
    		<p class="erreur">Balise incorrect : <c:forEach var="r" items="${ result1 }"><c:out value="${ r }"/> </c:forEach></p>
 		</c:if>
	    
	    <h4>Mail pour le secrétariat</h4>
	    <p>Balise autorisées: &lt;NOM&gt; &lt;PRENOM&gt; &lt;LISTE_AFFECTATION&gt;</p>
	    <textarea rows="10" cols="150" name="mail_secretariat" form="mailform" required="required">${ !empty mail_secretariat ? mail_secretariat : 'Ecrivez le contenu du mail...' }</textarea>
	    <c:if test="${ result2.size() > 0 }">
    		<p class="erreur">Balise incorrect : <c:forEach var="r" items="${ result2 }"><c:out value="${ r }"/> </c:forEach></p>
   		</c:if>
	    
	    <input class = "btn btn-info" type="submit"value="Valider les mails"/>
    </form>
        
	<footer>
		<div class="bouton_confirm ">
			<p>
				<a class = "btn btn-primary" href="prof_final?token=${ token }" onclick="return confirm('Etes vous sûr de vouloir envoyer les mails ?')" role="button">Envoyer les mails</a>
			</p>
		</div>
	</footer>
	</div>
</body>
</html>