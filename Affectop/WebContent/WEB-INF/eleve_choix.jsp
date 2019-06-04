<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<script src ="http://code.jquery.com/jquery-2.1.0.min.js"></script>
	<script>
		<%@ include file="script/script.js"%>
	</script>
	<meta charset="utf-8" />
	<title>Choix</title>
	<style>
		<%@ include file="css/stylesheet.css"%>
	</style>

	<%@ include file="menu_eleve.jsp" %>
</head>
<body>
	<div class="container">
		<c:if test="${ prefPerGroup == null }">

			<c:if test="${ validation == false }">
				<div class="alert alert-danger" role="alert">Le formulaire a
					été mal rempli. Veuillez recommencer.</div>
			</c:if>
			<p>Le nombre de points à répartir pour chaque
				Option est indiqué. Au plus 70% des crédits peuvent être placés sur une UE.
    50% des UE doivent avoir au minimum un crédit.
    La totalité des crédits doit être répartie sur l’ensemble des UE.</p>


			<form method="post" action="eleve_choix?token=${ token }">
				<c:forEach var="j" begin="1" end="${ groupOp.size() }">
					<div class="trait"></div>
					<h3>
						Option n°
						<c:out value="${ j }"></c:out>
						( Crédits :
						<c:if test="${ groupOp.get(j).size() < 5 }"> 10 
						max =   7 )</c:if>
						<c:if test="${ groupOp.get(j).size() >= 5 }"><c:out value="${ Math.round(((groupOp.get(j).size()) * (groupOp.get(j).size() -1))/2)  }"></c:out>
						max = <c:out value="${  Math.round((((groupOp.get(j).size()) * (groupOp.get(j).size() -1))/2 * 0.7))  }"></c:out>) </c:if>
						
					</h3>
					<table class="table" id="t01">
						<thead class="thead-dark">
							<tr>
								<th>Nom de l'UE</th>
								<th>Crédit</th>
							</tr>
						</thead>
						<c:forEach var="i" begin="1" end="${ options.size() }">
							<c:if
								test="${ groupOp.get(j).contains(options.get(i-1).getId()) && (! optionRep.contains(options.get(i-1)))}">
									<p class="option">
									<tr>
										<td><label for="option"><c:out
													value="${ options.get(i-1).nom }" /> </label></td>
										<td><input type="number" min = "0"
											name="<c:out value="${ i-1 }"/><c:out value="${j}"></c:out>"
											value="" required></td>
									</tr>
									</p>
							</c:if>
						</c:forEach>
					</table>
				</c:forEach>
				<c:if test="${ prefPerGroup == null }">
					<div class="bouton_confirm">
						<input type="submit" class="btn btn-primary"
							value="Valider vos choix" />
					</div>
				</c:if>
			</form>
		</c:if>

		<ul>
			<c:forEach var="p" items="${ prefPerGroup }">
				<li>
					<p>
						<c:out value="${ p.toString()}" />
					</p>
				</li>
			</c:forEach>
			<c:if test="${ prefPerGroup != null }">
				<p class="erreur">Vos préférences ont été enregistrées</p>
			</c:if>
		</ul>

		<footer>
			<c:if test="${ prefPerGroup != null }">
				<div class="bouton_confirm">
					<p>
						<a class="btn btn-primary" href="eleve_valider?token=${ token }"
							role="button">Suivant</a>
					</p>
				</div>
			</c:if>
		</footer>
	</div>
</body>
</html>
