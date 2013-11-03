
<head>
<title>Lista de Encontros da Paróquia: ${paroquia.name}</title>
</head>
<body>
		
	<div class="panel panel-primary">

		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">
				Lista de Encontros<a
					href="${pageContext.request.contextPath}/encontros/new"
					class="btn btn-success glyphicon glyphicon-plus"
					style="position: absolute; right: -8px; top: -8px"></a>
			</h3>
		</div>

		<%@include file="../encontro/list.jsp"%>

	</div>
</body>
