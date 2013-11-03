<head>
<title>Lista de Equipes</title>
</head>
<body>
	<div class="page-header" align="center">
		<h1>Encontro: ${encontro.tema}</h1>
	</div>
	<div class="panel panel-primary">

		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">
				Lista de Equipes<a
					href="${pageContext.request.contextPath}/equipes/new"
					class="btn btn-success glyphicon glyphicon-plus"
					style="position: absolute; right: -8px; top: -8px"></a>
			</h3>
		</div>

		<table class="table table-striped table-condensed table-responsive">
			<tr>
				<th>Nome</th>
				<th></th>
				<th></th>
			</tr>

			<c:forEach items="${encontro.equipes}" var="equipe">
				<tr>
					<td><h4>
							<a
								href="${pageContext.request.contextPath}/equipes/${equipe.id}">${equipe.name}</a>
						</h4></td>
					<td><a
						href="${pageContext.request.contextPath}/equipes/${equipe.id}/edit"
						class="btn btn-primary "> <span
							class="glyphicon glyphicon-pencil" />
					</a></td>
					

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
</body>