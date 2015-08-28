<div class="container">
	<div class="wrapper col-md-4 col-md-offset-4">
		<form action="${pageContext.request.contextPath}/autenticar"
			method="post">
			<h3 class="form-signin-heading">Seja Bem-Vindo</h3>
			<hr class="colorgraph">
			<br> 
			<input type="text" name="usuario.nomeUsuario" value="${usuario.nomeUsuario}"
				placeholder="Usuario" required="" autofocus="" class="form-control" />
			<input type="password" name="usuario.senha" value="${usuario.senha}"
				placeholder="Senha" required="" autofocus="" class="form-control" />

			<div class="actions">
				<button type="submit" class="btn btn-lg btn-primary btn-block">Login</button>
			</div>
		</form>
	</div>
</div>
