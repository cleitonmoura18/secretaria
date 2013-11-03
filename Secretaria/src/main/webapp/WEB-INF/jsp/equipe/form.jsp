<div class="well">
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/equipes" method="post"
		id="cadastroEquipes">
		<c:if test="${not empty equipe.id}">
			<input type="hidden" name="equipe.id" value="${equipe.id}" />
			<input type="hidden" name="_method" value="put" />
		</c:if>
		<fieldset>
			<c:choose>
				<c:when test="${empty paroquia.id}">
					<legend>Cadastro de Equipes</legend>
				</c:when>
				<c:otherwise>
					<legend>Editar Equipes</legend>
				</c:otherwise>
			</c:choose>

			<div class="form-group">
				<label for="inputEmail1" class="col-lg-2 control-label">Equipe</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="equipe.name"
						value="${equipe.name}" id="name" placeholder="Nome da Equipe" required>
				</div>
			</div>
			<div class="form-group text-right">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default btn-primary "
						id="submit">Salvar</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<a href="${pageContext.request.contextPath}/equipes">Back</a>

