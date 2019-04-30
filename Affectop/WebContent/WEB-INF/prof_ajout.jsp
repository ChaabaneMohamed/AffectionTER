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
	<%@ include file="progress.jsp" %>
	<script>
		$('.progress .circle').removeClass().addClass('circle');
		$('.progress .bar').removeClass().addClass('bar');
		progress(1);
	</script>
	<div class= "content">
	<p>Ajoutez un fichier excel dans le format suivant: <c:out value="${ path }"/></p>
	<div class="tableau">
	<table>
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

    <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    <form id="upload" method="post" action="prof_ajout?token=${ token }" enctype="multipart/form-data">
        <fieldset>
		<legend>Ajoutez votre fichier</legend>
		
		<div>
			<label for="fichier">Fichier à envoyer:</label>
			<input type="file" id="fichier" name="fichier" accept=".xls,.xlsx"/>
			<div id="filedrag">or drop file here</div>
		</div>
		</br>
		<div id="submitbutton">
			<button type="submit">Confirmer le fichier</button>
		</div>
		
		</fieldset>
    </form>
    

	<footer>
		<div class="bouton">
			<p>
				<a href="prof_option?token=${ token }">Suivant</a>
			</p>
		</div>
	</footer>
	</div>
</body>	
</html>
