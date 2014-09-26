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
			<div class="form-group">
				<label for="precisaPapelNaEquipe" class="col-lg-2 control-label">Precisa de Papel?</label>
					
					Sim <input type="radio" class="form-control-inline" name="equipe.precisaPapelNaEquipe"
						value="true" id="name" placeholder="A equipe precisa informar Papel do participante" 
						<c:if test="${equipe.precisaPapelNaEquipe}">
							checked="checked"
						</c:if>
						>
					   Não <input type="radio" class="form-control-inline" name="equipe.precisaPapelNaEquipe" 
						value="false" id="name" placeholder="A equipe precisa informar Papel do participante"
						<c:if test="${equipe.precisaPapelNaEquipe ne true}">
							checked="checked"
						</c:if> >
						
			</div>
			<div class="form-group">
				<label for="circulo" class="col-lg-2 control-label">Círculo?</label>
					
					Sim <input type="radio" class="form-control-inline" name="equipe.circulo"
						value="true" id="name" placeholder="A equipe precisa informar Papel do participante" 
						<c:if test="${equipe.circulo}">
							checked="checked"
						</c:if>
						>
					   Não <input type="radio" class="form-control-inline" name="equipe.circulo" 
						value="false" id="name" placeholder="A equipe precisa informar Papel do participante"
						<c:if test="${equipe.circulo ne true}">
							checked="checked"
						</c:if> >
						
			</div>
			
			<input type="hidden" name="equipe.ordemImpressao"
						value="${equipe.ordemImpressao}"/>
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

