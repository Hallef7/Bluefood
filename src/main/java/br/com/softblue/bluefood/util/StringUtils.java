package br.com.softblue.bluefood.util;

import java.util.Collection;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StringUtils {
	/* metodo que verifica if se a String ta vazia*/
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
	/* str.trin tira o espaço em branco das pontas, .length = 0 retorna o tamanho da String*/
	return str.trim().length() == 0;
	}
	
	/*Método que retorna a String criptografada passando como parametro rawString/ isEmpty verifica se String da senha esta vazia. return null*/
	public static String encrypt (String rawString) {
		if (isEmpty(rawString)) {
			return null;
		}
		/* caso não esteja vazia a String/PasswordEnconder chama o algoritmo interno de criptografia*/
		PasswordEncoder  encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder.encode(rawString);

	}
	
	public static String concatenate(Collection<String> strings) {
		if (strings == null || strings.size() == 0) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		String delimiter = ", ";
		boolean first = true;
		
		for (String string : strings) {
			if(!first) {
				sb.append(delimiter);
			}
			sb.append(string);
			first = false;
		}
		
		return sb.toString();
	}
}