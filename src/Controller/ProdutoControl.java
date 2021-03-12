package Controller;

import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.ProdutoDTO;
import DTO.TamanhoDTO;
import Model.Produto;

public class ProdutoControl {
	private Produto produto = new Produto();
	
	public ProdutoDTO recuperarIdDeTodasPecas(ProdutoDTO produtoDTO) {
		return produto.recuperarIdDeTodasPecas(produtoDTO);
	}
	
	public void cadastrarProduto(ProdutoDTO pdto) {
		produto.cadastrarProduto(pdto);
	}

	public void removerProduto(ProdutoDTO pdto) {
		produto.removerProduto(pdto);
	}

	public void editarProduto(ProdutoDTO pdto) {
		produto.editarProduto(pdto);
	}

	public ProdutoDTO getProduto(ProdutoDTO pdto) {
		return produto.getProduto(pdto);
	}

	public ProdutoDTO getProdutos() {
		return produto.getProdutos();
	}

	public void adicionarCor(CorDTO cdto) {
		produto.adicionarCor(cdto);
	}

	public void removerCor(CorDTO cdto) {
		produto.removerCor(cdto);
	}

	public CorDTO getCores() {
		return produto.getCores();
	}

	public void adicionarTamanho(TamanhoDTO tdto) {
		produto.adicionarTamanho(tdto);
	}

	public void removerTamanho(TamanhoDTO tdto) {
		produto.removerTamanho(tdto);
	}

	public TamanhoDTO getTamanhos() {
		return produto.getTamanhos();
	}

	public void adicionarCategoria(CategoriaDTO cdto) {
		produto.adicionarCategoria(cdto);
	}

	public void removerCategoria(CategoriaDTO cdto) {
		produto.removerCategoria(cdto);
	}

	public CategoriaDTO getCategorias() {
		return produto.getCategorias();
	}
}
