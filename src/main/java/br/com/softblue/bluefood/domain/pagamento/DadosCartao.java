package br.com.softblue.bluefood.domain.pagamento;

import javax.validation.constraints.Pattern;

/*classe para agrupar dados de cartão de crédito*/
public class DadosCartao {
	
	@Pattern(regexp = "\\d{16}", message = "O número do cartão é inválido ")
	private String numCartao;
	
	public String getNumCartao() {
		return numCartao;
	}
	
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
}
