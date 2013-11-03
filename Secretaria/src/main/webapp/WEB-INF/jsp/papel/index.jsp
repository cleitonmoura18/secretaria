<head>
	<title>Papel [index]</title>
</head>
<body>
	<h1>Listing Papels</h1>

	<table>
		<tr>
			<th>Name</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${papelList}" var="papel">
			<tr>
				<td>${papel.name}</td>
				<td><a href="${pageContext.request.contextPath}/papels/${papel.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/papels/${papel.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/papels/${papel.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/papels/new">New Papel</a> 
</body>