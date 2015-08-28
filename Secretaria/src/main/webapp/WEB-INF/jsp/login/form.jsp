<form action="${pageContext.request.contextPath}/usuarios" method="post">

	<c:if test="${not empty usuario.idUsuario}">
		<input type="hidden" name="usuario.idUsuario"
			value="${usuario.idUsuario}" />
		<input type="hidden" name="_method" value="put" />
	</c:if>

	<div class="field">
		Nome:<br /> <input type="text" name="usuario.nomeUsuario"
			value="${usuario.nomeUsuario}" /> 
	</div>
	<div class="field">
		E-mail:<input type="email"
			name="usuario.email" value="${usuario.email}" /> 
	</div>
	
	<div class="field">
		Senha:<input
			type="password" name="usuario.senha" value="${usuario.senha}" />
	</div>
	<div class="field">
		Repetir Senha:<input
			type="password" name="usuario.senhaRepetida" value="${usuario.senhaRepetida}" />
	</div>

	<div class="actions">
		<button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/usuarios">Back</a>
