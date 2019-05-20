<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>
<meta charset="utf-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top, navnav">
  <div class="container">
    <a class="navbar-brand" href="index?token=${ token }">
          <img src="images/logo2.png" alt="">
        </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Services</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
    
      </ul>
    </div>
  </div>
    <a class="navbar-brand" href="https://www.univ-amu.fr/">
          <img src="images/logoamu.png" alt="">
        </a>
  

</nav>
<div class="container">
  <h2 class="mt-4, id">Bonjour ${ !empty firstname ? firstname : '' } ${ !empty name ? name : '' } ${ !empty numEtudiant ? (numEtudiant) : '' }</h1>
  </div>
</body>
</html>