
<table class="table table-striped table-condensed table-responsive">
	<tr>
		<th>Nome</th>
		<th>Conjugue</th>
		<th>E-mail</th>
		<th>Papel</th>
		<th>Data de Nascimento</br>Casamento</th>
		<th>Telefone</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${equipe.partipacao}" var="participacao">
		<tr>
			<td><h4>${participacao.pessoa.nome}	</h4></td>
			<td><h4>${participacao.pessoa.nomeConjugue}	</h4></td>
			<td><h4>${participacao.pessoa.email}</h4></td>
			<td><h4>${participacao.papelNaEquipe.nome}</h4></td>
			<td><h4><fmt:formatDate value="${participacao.pessoa.dataNascimento}" pattern="dd/MM/yyyy"/></h4></td>
			<td><h4>${participacao.pessoa.fonesTemplate}</h4></td>
			<td>
				<a href="${pageContext.request.contextPath}/participacaos/${participacao.id}/edit"
				class="btn btn-primary ">
					<span class="glyphicon glyphicon-pencil"></span>
				</a>
			</td>


			<td>
				<form
							action="${pageContext.request.contextPath}/participacaos/${participacao.id}"
							method="post">
							<input type="hidden" name="_method" value="delete" />
							<button type="submit" onclick="return confirm('Are you sure?')"
								class="btn btn-danger ">
								<span class="glyphicon glyphicon-trash" ></span>
							</button>
						</form>
			</td>
		</tr>
	</c:forEach>
</table>