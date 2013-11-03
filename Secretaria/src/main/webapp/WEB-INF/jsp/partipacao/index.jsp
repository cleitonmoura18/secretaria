<head>
	<title>Partipacao [index]</title>
</head>
<body>
	<h1>Listing Partipacaos</h1>

	<table>
		<tr>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${partipacaoList}" var="partipacao">
			<tr>
				<td><a href="${pageContext.request.contextPath}/partipacaos/${partipacao.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/partipacaos/${partipacao.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/partipacaos/${partipacao.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/partipacaos/new">New Partipacao</a> 
</body>