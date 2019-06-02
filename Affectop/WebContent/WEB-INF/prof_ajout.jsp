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
  <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 20%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
</div>
	
	<p>Ajoutez un fichier excel dans le format suivant:</p>

	<c:if test="${ empty fichier }">
		<div>
		<table class="table" >
		<thead class = "thead-dark">
		  <tr>
		    <td>Nom</td>
		    <td>Prénom</td>
		    <td>Carte</td>
		    <td>Étape</td>
		    <td>Nom de l'étape</td>
		    <td>VET</td>
		    <td>Année</td>
		    <td>Courriel</td>
		  </tr>
		  </thead>
		  <tr>
		    <td>Martin</td>
		    <td>Dupond</td>
		    <td>15019999</td>
		    <td>xxx999</td>
		    <td>AMU.Xx Informatique</td>
		    <td>Vxxx</td>
		    <td>xxxx</td>
		    <td>martin.dupond@etu.univ-amu.fr</td>
		  </tr>
		  <tr>
		    <td>Jacques</td>
		    <td>Chirac</td>
		    <td>15012086</td>
		    <td>xxx916</td>
		    <td>AMU.Xx Informatique</td>
		    <td>Vxxx</td>
		    <td>xxxx</td>
		    <td>jacques.chirac@etu.univ-amu.fr</td>
		  </tr>
		  <tr>
		    <td>...</td>
		    <td>...</td>
		    <td>...</td>
		    <td>...</td>
		    <td>...</td>
		    <td>...</td>
		    <td>...</td>
		    <td>...</td>
		  </tr>
		</table>
		</div>
		
		<a href="datas/temp.xlsx" download>Cliquez ici pour télecharger un exemple</a>
	</c:if>
	
	<c:if test="${ !empty fichier }">
	
	  <div class="table-wrapper-scroll-y my-custom-scrollbar">
		<table class="table table-bordered table-striped mb-0">
	    		<tr>
	    			<th id="gris">Prénom</td>
	    			<th id="gris">Nom</td>
	    			<th>Email</td>
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
   	</c:if>

	<c:if test="${ empty fichier }">
    <form id="upload" method="post" action="prof_ajout?token=${ token }" enctype="multipart/form-data">
        <fieldset>
		<legend>Ajoutez votre fichier</legend>
		
		<div>
			<label for="fichier">Fichier à envoyer:</label>
			<input type="file" id="fichier" name="fichier" accept=".xls,.xlsx"/>
		</div>
		</br>
		<div id="submitbutton">
			<button class="btn btn-primary bouton_confirm" type="submit">Confirmer le fichier</button>
		</div>
		
		</fieldset>
		<c:if test="${ error }"><p class="erreur"><c:out value="Le fichier est non valide !" /></p></c:if>
    </form>
    </c:if>
    <c:if test="${ !empty fichier }"><p class="valid"><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    
	<c:if test="${ !empty fichier }">
	<footer>
		<div class="bouton_confirm">
			<p>
				<a class="btn btn-primary" href="prof_option?token=${ token }" role="button" >Suivant</a>
			</p>
		</div>
	</footer>
	</c:if>
	</div>
</body>	
</html>
