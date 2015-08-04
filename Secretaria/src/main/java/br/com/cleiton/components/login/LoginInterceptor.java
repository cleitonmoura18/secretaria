package br.com.cleiton.components.login;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.controlador.LoginController;

@Intercepts
public class LoginInterceptor implements Interceptor {

	private Result result;
	private UsuarioSession userSession;

	public LoginInterceptor(Result result, UsuarioSession userSession) {
		this.result = result;
		this.userSession = userSession;
	}

	public boolean accepts(ResourceMethod method) {
		return !(method.getMethod().isAnnotationPresent(Public.class)
				|| method.getResource().getType().isAnnotationPresent(Public.class));
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) {
		if (userSession.isLogged()) {
			stack.next(method, resourceInstance);
		} else {
			result.redirectTo(LoginController.class).login();
		}
	}

}