<head>
	<title>participacao [index]</title>
</head>
<body>
	<h1>Listing participacaos</h1>

	<table>
		<tr>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${participacaoList}" var="participacao">
			<tr>
				<td><a href="${pageContext.request.contextPath}/participacaos/${participacao.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/participacaos/${participacao.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/participacaos/${participacao.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/participacaos/new">New participacao</a> 
</body>