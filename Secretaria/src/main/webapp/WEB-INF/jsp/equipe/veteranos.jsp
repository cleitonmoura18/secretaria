
<head>
<title>Lista de Encontros da Paróquia: ${paroquia.name}</title>
</head>
<body>
	<c:set var="equipe" value="${listaParticipacao.equipe}" />
	<div class="page-header" align="center">
		<h3>Encontro: ${equipe.encontro.tema}</h3>
		<h4>${equipe.name}</h4>
	</div>
	<div class="panel panel-primary">

		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">
				Participantes</h3>
		</div>

		<table class="table table-striped table-condensed table-responsive">
			<tr>
				<th>Nome</th>
				<th>Conjugue</th>
				<th></th>
				<th></th>

			</tr>
			<c:forEach items="${equipe.partipacao}" var="participacao">
				<tr>
					<td>${participacao.pessoa.nome}</td>
					<td>${participacao.pessoa.nomeConjugue}</td>
					<td><a
						href="${pageContext.request.contextPath}/participacaos/${participacao.id}/edit"
						class="btn btn-primary "> <span
							class="glyphicon glyphicon-pencil"></span>
					</a></td>
					<td>
						<form
							action="${pageContext.request.contextPath}/participacaos/${participacao.id}"
							method="post">
							<input type="hidden" name="_method" value="delete" />
							<button type="submit" onclick="return confirm('Are you sure?')"
								class="btn btn-danger ">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<form action="<c:url value="/equipes/participacao/novas"/>"
			method="POST">
			<input type="hidden" name="listaParticipacao.equipe.id"
				value="${listaParticipacao.equipe.id}">
			<div class="form-group">
				<label for="nome" class="col-sm-3 control-label">Papel Na
					Equipe:</label>
				<div class="col-sm-9">
					<select name="listaParticipacao.papelNaEquipe.id"
						id="papelNaequipe" class="form-control">
						<option value=""></option>
						<c:forEach items="${equipe.encontro.papeisNaEquipe}" var="papel">
				${listaParticipacao.papelNaEquipe.id}
					<option value="${papel.id}"
								<c:if test="$listaParticipacao.papelNaEquipe.id==papel.id}">selected="selected"</c:if>>${papel.nome}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<c:forEach items="${listaParticipacao.pessoas}" var="pessoa"
				varStatus="s">
				<input type="checkbox"
					name="listaParticipacao.pessoas[${s.index}].id"
					value="${pessoa.id}"> ${pessoa.nomeFormatado}        
          </c:forEach>
			<div class="control-group text-right">
				<label class="control-label" for="singlebutton"></label>
				<div class="controls">
					<button id="singlebutton" name="singlebutton"
						class="btn btn-primary">Salvar</button>
				</div>
			</div>
		</form>

	</div>
</body>
