<head>
<title>Lista de Paróquia</title>
</head>
<body>
	<div class="panel panel-primary">

		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">
				Paróquias<a
					href="${pageContext.request.contextPath}/paroquias/new"
					class="btn btn-success glyphicon glyphicon-plus"
					style="position: absolute; right: -8px; top: -8px"></a>
			</h3>
		</div>

		<table class="table   table-responsive table-bordered" align="center">
			<tr>
				<th>Nome</th>
				<th>Editar</th>
				<th>Excluir</th>
			</tr>

			<c:forEach items="${paroquiaList}" var="paroquia">
				<tr>
					<td><h4><a
						href="${pageContext.request.contextPath}/paroquias/${paroquia.id}/listaEncontros">
							${paroquia.name} </a></h4></td>
					<td><a
						href="${pageContext.request.contextPath}/paroquias/${paroquia.id}/edit"
						class="btn btn-primary "><span
							class="glyphicon glyphicon-pencil" /></a></td>
					<td>
						<form
							action="${pageContext.request.contextPath}/paroquias/${paroquia.id}"
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