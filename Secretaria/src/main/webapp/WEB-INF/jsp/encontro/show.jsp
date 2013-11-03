<head>
	<title>Encontro [show]</title>
</head>
<body>
	<p>
		<b>Name:</b>
		${encontro.tema}
	</p>
	<p>
		<b>Data inicio:</b>
		${encontro.dataInicio}
	</p>
	<p>
		<b>Data fim:</b>
		${encontro.dataFim}
	</p>

	<a href="${pageContext.request.contextPath}/encontros/${encontro.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/encontros">Back</a>
</body>