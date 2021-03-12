package DAO;

import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.ProdutoDTO;
import DTO.TamanhoDTO;

public class ProdutoAdapterPostgreSQL extends ProdutoDAOPostgreSQL implements ProdutoDAO{

	@Override
	public ProdutoDTO recuperarIdDeTodasPecas(ProdutoDTO produtoDTO) {
		return super.recuperarIdDeTodasPecas(produtoDTO);
	}
	

	@Override
	public void cadastrarProduto(ProdutoDTO pdto) {
		super.cadastrarProduto(pdto);
	}

	@Override
	public void removerProduto(ProdutoDTO pdto) {
		super.removerProduto(pdto);
	}

	@Override
	public void editarProduto(ProdutoDTO pdto) {
		super.editarProduto(pdto);
	}

	@Override
	public ProdutoDTO getProduto(ProdutoDTO pdto) {
		return super.getProduto(pdto);
	}

	@Override
	public ProdutoDTO getProdutos() {
		return super.getProdutos();
	}

	@Override
	public void adicionarCor(CorDTO cdto) {
		super.adicionarCor(cdto);
	}

	@Override
	public void removerCor(CorDTO cdto) {
		super.removerCor(cdto);
	}

	@Override
	public CorDTO getCores() {
		return super.getCores();
	}

	@Override
	public void adicionarTamanho(TamanhoDTO tdto) {
		super.adicionarTamanho(tdto);
	}

	@Override
	public void removerTamanho(TamanhoDTO tdto) {
		super.removerTamanho(tdto);
	}

	@Override
	public TamanhoDTO getTamanhos() {
		return super.getTamanhos();
	}

	@Override
	public void adicionarCategoria(CategoriaDTO cdto) {
		super.adicionarCategoria(cdto);
	}

	@Override
	public void removerCategoria(CategoriaDTO cdto) {
		super.removerCategoria(cdto);
	}

	@Override
	public CategoriaDTO getCategorias() {
		return super.getCategorias();
	}


}
