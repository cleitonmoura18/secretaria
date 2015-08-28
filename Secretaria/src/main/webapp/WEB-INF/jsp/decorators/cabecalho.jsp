<div class="navbar navbar-default navbar-static-top navbar-inverse">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span><span
				class="icon-bar"></span> <span class="icon-bar"></span><span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="">Encontro de Jovens Com Cristo</a>
	</div>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${pageContext.request.contextPath}/encontros">Home</a></li>
			<c:if test="${not empty usuarioSession.user}">
			<li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
			</c:if>
		</ul>
	</div>
</div>