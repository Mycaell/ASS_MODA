package DAO;

import DTO.CaixaDTO;
import DTO.ProdutoDTO;

public interface CaixaDAO {

	CaixaDTO lucroDiario();
	
	CaixaDTO lucroMensal();
	
	CaixaDTO valorDeCaixa();

	ProdutoDTO getProdutoMaisVendidos();
}
