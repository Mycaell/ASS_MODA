package DAO;

import DTO.CaixaDTO;
import DTO.ProdutoDTO;

public class CaixaAdapterPostgreSQL extends CaixaDAOPostgreSQL implements CaixaDAO{

	@Override
	public CaixaDTO lucroDiario() {
		return super.lucroDiario();
	}

	@Override
	public CaixaDTO lucroMensal() {
		return super.lucroMensal();
	}

	@Override
	public CaixaDTO valorDeCaixa() {
		return super.valorDeCaixa();
	}
	
	@Override
	public ProdutoDTO getProdutoMaisVendidos() {
		return super.getProdutoMaisVendidos();
	}
}
