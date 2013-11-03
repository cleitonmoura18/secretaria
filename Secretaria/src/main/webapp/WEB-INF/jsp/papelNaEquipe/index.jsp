<head>
	<title>Lista de Papeis na Equipe</title>
</head>
<body>
	<h1>Papeis Nas Equipes</h1>
	<table>
		<tr>
			<th>Nome</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${papelNaEquipeList}" var="papelNaEquipe">
			<tr>
				<td>${papelNaEquipe.nome}</td>
				<td><a href="${pageContext.request.contextPath}/papelNaEquipes/${papelNaEquipe.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/papelNaEquipes/${papelNaEquipe.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/papelNaEquipes/${papelNaEquipe.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/papelNaEquipes/new">New PapelNaEquipe</a> 
</body>