
<table class="table table-striped table-condensed table-responsive">
	<tr>
		<th>Tema</th>
		<th>Data inicio</th>
		<th>Data fim</th>
		<th></th>
		<th></th>
	</tr>

	<c:forEach items="${encontroList}" var="encontro">
		<tr>
			<td><h4>
					<a
						href="${pageContext.request.contextPath}/encontros/${encontro.id}">${encontro.tema}</a>
				</h4></td>
			<td><h4><fmt:formatDate value="${encontro.dataInicio}" pattern="dd/MM/yyyy"/></h4></td>
			<td><h4><fmt:formatDate value="${encontro.dataFim}" pattern="dd/MM/yyyy"/></h4></td>
			<td>
				<a href="${pageContext.request.contextPath}/encontros/${encontro.id}/edit"
				class="btn btn-primary ">
					<span class="glyphicon glyphicon-pencil" />
				</a>
			</td>


			<td>
				<form
							action="${pageContext.request.contextPath}/encontros/${encontro.id}"
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