package br.com.softblue.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration /*anota��o para o spring saber que a pagina � de configura��o*/
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandlerImpl();
	}
	
	@Override
	/*M�todo que define como funciona o processo de autentica��o do sistema e permiss�es de acesso de cada usuario*/
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests() /*Permite restringir o acesso com base no HttpServletRequest usando implementa��es de RequestMatcher (ou seja, por meio de padr�es de URL). EX./CLIENTE s� pode acessar quem tem o respectivo login. */
			.antMatchers("/images/**", "/css/**", "/js/**","/public", "/sbpay").permitAll()	/*permite colocar padr�es de acesso, no caso define que as pagina que est�o entre "aspas" s�o de acesso publico*/
			.antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString()) /*toString converte o ENUM para String*/
			.antMatchers("/restaurante/**").hasRole(Role.RESTAURANTE.toString())
			.anyRequest().authenticated() /*define que todas as paginas acima necessitam de autentica��o*/
			.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login-error")
				.successHandler(authenticationSuccessHandler())
				.permitAll()	
			.and()
				.logout().logoutUrl("/logout")
				.permitAll();
		
		
	}
}
