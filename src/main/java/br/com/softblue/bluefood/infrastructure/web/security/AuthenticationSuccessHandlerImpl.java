package br.com.softblue.bluefood.infrastructure.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.com.softblue.bluefood.util.SecurityUtils;
		
/* classe utilizada pelo spring data para direcinar o usuario para o local correto*/
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			
		Role role = SecurityUtils.loggedUser().getRole();
		
		if (role == Role.CLIENTE) {
			response.sendRedirect("cliente/home");
			
		}else if (role ==Role.RESTAURANTE) {
			response.sendRedirect("restaurante/home");
			
		} else {
			throw new IllegalStateException("erro na autenticação");
		}
	}

}
