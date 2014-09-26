<div class="panel panel-primary">

		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">
				Lista de Equipes<a
					href="${pageContext.request.contextPath}/equipes/new"
					class="btn btn-success glyphicon glyphicon-plus"
					style="position: absolute; right: -8px; top: -8px"></a>
					<a
					href="${pageContext.request.contextPath}/equipes/ordenar"
					class="btn btn-warning glyphicon glyphicon-sort-by-attributes"
					style="position: absolute; right: 40px; top: -8px"></a>
					<a
					href="${pageContext.request.contextPath}/crachas"
					class="btn btn-warning glyphicon glyphicon-file"
					title ="Baixa o nome dos Integrantes das Equipes para a Confecção dos Crachás"
					style="position: absolute; right: 90px; top: -8px"></a>
			</h3>
		</div>

		<table class="table table-striped table-condensed table-responsive">
			<tr>
				<th>Nome</th>
				<th>Quantidade</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>

			<c:forEach items="${encontro.equipes}" var="equipe">
				<tr>
					<td><h4>
							<a
								href="${pageContext.request.contextPath}/equipes/${equipe.id}">${equipe.name}</a>
						</h4></td>
						<td><h4>
							${equipe.partipacao.size() }
						</h4></td>
					<td><a
						href="${pageContext.request.contextPath}/equipes/${equipe.id}/edit"
						class="btn btn-primary "> 
						<span class="glyphicon glyphicon-pencil"></span>
					</a></td>
					
					<td>
					<a 
						href="${pageContext.request.contextPath}/cracha/${equipe.id}"
						class="btn btn-primary " title ="Baixar o nome dos Jovens para a Confecção de Crachás"/> 
						<span class="glyphicon glyphicon-file" ></span>
					</td>
					<td>
					<a 
						href="${pageContext.request.contextPath}/criarQuadrante/equipe/${equipe.id}"
						class="btn btn-primary " title ="Baixar parcial do quadrante"/> 
						<span class="glyphicon glyphicon-file" ></span>
					</td>	
					
					<td>
						<form
							action="${pageContext.request.contextPath}/equipes/${equipe.id}"
							method="post">
							<input type="hidden" name="_method" value="delete" />
							<button type="submit" onclick="return confirm('Are you sure?')"
								class="btn btn-danger ">
								<span class="glyphicon glyphicon-trash" />
							</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>