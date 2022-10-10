package br.com.softblue.bluefood.infrastructure.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.usuario.Usuario;


@SuppressWarnings("serial")
/*Classe que o Spring utiliza por padr�o para representar um usuario logado*/
public class LoggedUser implements UserDetails{
	/*implementa��o das superClasses das entidades atributos*/
	private Usuario usuario;
	private Role role;
	/*assinatura generics que diz que roles sera uma cole��o de objetos que extendam de GrantedAuthority*/
	private Collection<? extends GrantedAuthority> roles;
	
	/*M�todo Construtor*/
	public LoggedUser(Usuario usuario) {
		this.usuario = usuario;
		
		Role role;
		
		if (usuario instanceof Cliente){
			role = Role.CLIENTE;
			
		}else if (usuario instanceof Restaurante) {
			role = Role.RESTAURANTE;
			
		}else {
			throw new IllegalStateException("O tipo de usu�rio n�o � v�lido");
		}
		
		this.role = role;
		this.roles = List.of(new SimpleGrantedAuthority("ROLE_" + role));
		
	}

	@Override
	/*O spring utiliza para mapear perfis de acesso*/
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	/*Retorna a senha do usuario*/
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	/*verifica se a conta expirou*/
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	/* verirfica se n�o est� bloqueada*/
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	/*verifica se esta habilitada*/
	public boolean isEnabled() {
		return true;
	}
	public Role getRole() {
		return role;
	}
	public Usuario getUsuario() {
		return usuario;
	}

}
