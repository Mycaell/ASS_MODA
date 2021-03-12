package Model;

import DAO.CaixaAdapterPostgreSQL;
import DTO.CaixaDTO;
import DTO.ProdutoDTO;

public class Caixa {

	CaixaAdapterPostgreSQL caixaAdapterPostgreSLQ;
	
	public Caixa() {
		this.caixaAdapterPostgreSLQ = new CaixaAdapterPostgreSQL();
	}

	public CaixaDTO lucroDiario() {
		return caixaAdapterPostgreSLQ.lucroDiario();
	}

	public CaixaDTO lucroMensal() {
		return caixaAdapterPostgreSLQ.lucroMensal();
	}

	public CaixaDTO valorDeCaixa() {
		return caixaAdapterPostgreSLQ.valorDeCaixa();
	}
	
	public ProdutoDTO getProdutoMaisVendidos() {
		return caixaAdapterPostgreSLQ.getProdutoMaisVendidos();
	}
}
