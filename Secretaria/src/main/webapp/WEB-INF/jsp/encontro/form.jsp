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
			<div class="form-group">
				<label for="imprimirCirculo" class="col-lg-2 control-label">Precisa de Papel?</label>
					
					Sim <input type="radio" class="form-control-inline" name="encontro.imprimirCirculo"
						value="true" id="name" placeholder="A equipe precisa informar Papel do participante" 
						<c:if test="${encontro.imprimirCirculo}">
							checked="checked"
						</c:if>
						>
					   Não <input type="radio" class="form-control-inline" name="encontro.imprimirCirculo" 
						value="false" id="name" placeholder="Deve imprimir o circulo que o jovem fez o encontro"
						<c:if test="${encontro.imprimirCirculo ne true}">
							checked="checked"
						</c:if> >
						
			</div>
				<div class="form-group">
				<label for="modelo1Coluna" class="col-lg-2 control-label">Imprimir Quadrante em uma coluna?</label>
					
					Sim <input type="radio" class="form-control-inline" name="encontro.modelo1Coluna"
						value="true" id="name" placeholder="O quadrante base vai ser de uma coluna" 
						<c:if test="${encontro.modelo1Coluna}">
							checked="checked"
						</c:if>
						>
					   Não <input type="radio" class="form-control-inline" name="encontro.modelo1Coluna" 
						value="false" id="name" placeholder="O quadrante base vai ser 1 coluna"
						<c:if test="${encontro.modelo1Coluna ne true}">
							checked="checked"
						</c:if> >
						
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
