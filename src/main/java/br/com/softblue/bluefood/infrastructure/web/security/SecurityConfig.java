package br.com.softblue.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration /*anotação para o spring saber que a pagina é de configuração*/
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandlerImpl();
	}
	
	@Override
	/*Método que define como funciona o processo de autenticação do sistema e permissões de acesso de cada usuario*/
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests() /*Permite restringir o acesso com base no HttpServletRequest usando implementações de RequestMatcher (ou seja, por meio de padrões de URL). EX./CLIENTE só pode acessar quem tem o respectivo login. */
			.antMatchers("/images/**", "/css/**", "/js/**","/public", "/sbpay").permitAll()	/*permite colocar padrões de acesso, no caso define que as pagina que estão entre "aspas" são de acesso publico*/
			.antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString()) /*toString converte o ENUM para String*/
			.antMatchers("/restaurante/**").hasRole(Role.RESTAURANTE.toString())
			.anyRequest().authenticated() /*define que todas as paginas acima necessitam de autenticação*/
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
