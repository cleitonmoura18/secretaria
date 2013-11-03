<head>
	<title>Pessoa [index]</title>
</head>
<body>
	<h1>Listing Pessoas</h1>

	<table>
		<tr>
			<th>Nome</th>
			<th>Data nascimento</th>
			<th>Email</th>
			<th>Endereco</th>
			<th>Bairro</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${pessoaList}" var="pessoa">
			<tr>
				<td>${pessoa.nome}</td>
				<td>${pessoa.dataNascimento}</td>
				<td>${pessoa.email}</td>
				<td>${pessoa.endereco}</td>
				<td>${pessoa.bairro}</td>
				<td><a href="${pageContext.request.contextPath}/pessoas/${pessoa.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/pessoas/${pessoa.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/pessoas/${pessoa.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/pessoas/new">New Pessoa</a> 
</body>