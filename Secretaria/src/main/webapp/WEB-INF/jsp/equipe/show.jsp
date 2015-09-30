
<head>
<title>Lista de Encontros da Paróquia: ${paroquia.name}</title>
</head>
<body>
		<div class="page-header" align="center">
		<h1>Encontro: ${equipe.encontro.tema}</h1>
		<h2>${equipe.name}</h2>
	</div>
	<div class="panel panel-primary">

		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">
				Participantes<a
					href="${pageContext.request.contextPath}/equipe/${equipe.id}/participacaos/new"
					class="btn btn-success glyphicon glyphicon-plus"
					style="position: absolute; right: -8px; top: -8px"></a>
			</h3>
		</div>

		<%@include file="../participacao/listParticipacao.jsp"%>
			<c:forEach items="${pessoasList}" var="pessoa" varStatus="s">      
            <input type="checkbox" name="usuario.regras[${s.index}].codRegra" value="${regra.codRegra}"> ${regra.perfil}      
            </c:forEach> 
	</div>
</body>
