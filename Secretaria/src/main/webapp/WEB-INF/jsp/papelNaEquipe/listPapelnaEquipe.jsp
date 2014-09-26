	<div class="panel panel-primary">

		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">
				Papeis das Equipes<a
					href="${pageContext.request.contextPath}/papelNaEquipes/new"
					class="btn btn-success glyphicon glyphicon-plus"
					style="position: absolute; right: -8px; top: -8px"></a>
					<a
					href="${pageContext.request.contextPath}/papelNaEquipes/ordenar"
					class="btn btn-warning glyphicon glyphicon-sort-by-attributes"
					style="position: absolute; right: 40px; top: -8px"></a>
			</h3>
		</div>

		<table class="table table-striped table-condensed table-responsive">
			<tr>
				<th>Nome</th>
				<th></th>
				<th></th>
			</tr>

			<c:forEach items="${encontro.papeisNaEquipe}" var="papelNaEquipe">
				<tr>
					<td><h4>
							${papelNaEquipe.nome}
						</h4></td>
					<td>
					<a href="${pageContext.request.contextPath}/papelNaEquipes/${papelNaEquipe.id}/edit" class="btn btn-primary "> 
						<span class="glyphicon glyphicon-pencil" > </span>
					</a></td>
					

					<td>
						<form
							action="${pageContext.request.contextPath}/papelNaEquipes/${papelNaEquipe.id}"
							method="post">
							<input type="hidden" name="_method" value="delete" />
							<button type="submit" onclick="return confirm('Are you sure?')"
								class="btn btn-danger ">
								<span class="glyphicon glyphicon-trash" > </span>
							</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
