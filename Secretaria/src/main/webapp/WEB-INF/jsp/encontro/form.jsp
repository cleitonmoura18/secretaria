<div class="well">
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/encontros" method="post">
		<c:if test="${not empty encontro.id}">
			<input type="hidden" name="encontro.id" value="${encontro.id}" />
			<input type="hidden" name="_method" value="put" />
		</c:if>
		<fieldset>

			<!-- Form Name -->
			<legend>Encontro</legend>

			<div class="form-group">
				<label for="inputEmail1" class="col-lg-2 control-label">Tema</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="encontro.tema" required="required"
						value="${encontro.tema}" id="tema" placeholder="Tema do Encontro">
				</div>
			</div>

			<div class="form-group">
				<label for="lema" class="col-lg-2 control-label">Lema</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" name="encontro.lema" required="required"
						value="${encontro.lema}" id="lema" placeholder="Lema do Encontro">
				</div>
			</div>
			<div class="form-group">
				<label for="dataInicio" class="col-lg-2 control-label">Data de início</label>
				<div class="col-lg-10">
					<input type="date" class="form-control" name="encontro.dataInicio" required="required"
						value="<fmt:formatDate value="${encontro.dataInicio}" pattern="yyyy-MM-dd"/>" id="dataInicio" >
				</div>
			</div>
			<div class="form-group">
				<label for="dataFim" class="col-lg-2 control-label">Data de Fim</label>
				<div class="col-lg-10">
					<input type="date" class="form-control" name="encontro.dataFim" required="required"
						value="<fmt:formatDate value="${encontro.dataFim}" pattern="yyyy-MM-dd"/>" id="dataFim" >
				</div>
			</div>
			<div class="control-group text-right">
				<label class="control-label" for="singlebutton"></label>
				<div class="controls">
					<button id="singlebutton" name="singlebutton"
						class="btn btn-primary">Salvar</button>
				</div>
			</div>

		</fieldset>
	</form>
</div>
<a href="${pageContext.request.contextPath}/encontros">Back</a>
