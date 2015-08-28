<head>
<title>Usuario ${usuario.nomeUsuario}</title>
</head>
<body>
	<p>
		<b>Nome:</b> ${usuario.nomeUsuario}

	</p>
	<p>
		<b>E-mail:</b> ${usuario.email}
	</p>
	<a href="${pageContext.request.contextPath}/usuarios/${usuario.idUsuario}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/usuarios">Back</a>
</body>