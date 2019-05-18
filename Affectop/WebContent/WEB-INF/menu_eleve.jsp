<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
    <h3 class="id">
		Bonjour ${ !empty firstname ? firstname : '' } ${ !empty name ? name : '' } ${ !empty numEtudiant ? (numEtudiant) : '' }
	</h3>
    
</body>
</html>