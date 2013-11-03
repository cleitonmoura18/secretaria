<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>
<div class="well">
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/paroquias" method="post"
		id="cadastroParoquia">
		<c:if test="${not empty paroquia.id}">
			<input type="hidden" name="paroquia.id" value="${paroquia.id}" />
			<input type="hidden" name="_method" value="put" />
		</c:if>
		<fieldset>
			<c:choose>
			<c:when test="${empty paroquia.id}">
				<legend>Cadastro de Paróquia</legend>
			</c:when>
			<c:otherwise>
				<legend>Editar Paróquia</legend>
			</c:otherwise>
			</c:choose>

			<div class="form-group">
				<label for="inputEmail1" class="col-lg-2 control-label">Paróquia</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="paroquia.name"
						value="${paroquia.name}" id="name" placeholder="Nome da Paroquia">
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
<a href="${pageContext.request.contextPath}/paroquias">Back</a>

