package Controller;

import DTO.CaixaDTO;
import DTO.ProdutoDTO;
import Model.Caixa;

public class CaixaControl {

	Caixa caixa = new Caixa();
	
	public CaixaDTO lucroDiario() {
		return caixa.lucroDiario();
	}

	public CaixaDTO lucroMensal() {
		return caixa.lucroMensal();
	}

	public CaixaDTO valorDeCaixa() {
		return caixa.valorDeCaixa();
	}
	
	public ProdutoDTO getProdutoMaisVendidos() {
		return caixa.getProdutoMaisVendidos();
	}
}
