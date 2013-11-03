<head>
	<title>Pessoa [show]</title>
</head>
<body>
	<p>
		<b>Nome:</b>
		${pessoa.nome}
	</p>
	<p>
		<b>Data nascimento:</b>
		${pessoa.dataNascimento}
	</p>
	<p>
		<b>Email:</b>
		${pessoa.email}
	</p>
	<p>
		<b>Endereco:</b>
		${pessoa.endereco}
	</p>
	<p>
		<b>Bairro:</b>
		${pessoa.bairro}
	</p>

	<a href="${pageContext.request.contextPath}/pessoas/${pessoa.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/pessoas">Back</a>
</body>