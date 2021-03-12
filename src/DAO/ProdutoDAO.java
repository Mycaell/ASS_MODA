package DAO;

import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.ProdutoDTO;
import DTO.TamanhoDTO;

public interface ProdutoDAO {

	public ProdutoDTO recuperarIdDeTodasPecas(ProdutoDTO produtoDTO);
	
	void cadastrarProduto(ProdutoDTO pdto);

	void removerProduto(ProdutoDTO pdto);
	
	void editarProduto(ProdutoDTO pdto);

	ProdutoDTO getProduto(ProdutoDTO pdto);
	
	public ProdutoDTO getProdutos();
	
	void adicionarCor(CorDTO cdto);
	
	void removerCor(CorDTO cdto);
	
	CorDTO getCores();
	
	void adicionarTamanho(TamanhoDTO tdto);
	
	void removerTamanho(TamanhoDTO tdto);
	
	TamanhoDTO getTamanhos();
	
	void adicionarCategoria(CategoriaDTO cdto);
	
	void removerCategoria(CategoriaDTO cdto);
	
	CategoriaDTO getCategorias();
}
