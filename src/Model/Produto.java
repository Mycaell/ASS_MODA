package Model;

import DAO.ProdutoAdapterPostgreSQL;
import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.ProdutoDTO;
import DTO.TamanhoDTO;

public class Produto {

	private int id;
	
	private String nome;
	private float preco;
	private String categoria;
	private int quantEstoque;
	private String cor;
	private char tamanho;
	private String marca;
	private String descricao;
	
	
	
	private ProdutoAdapterPostgreSQL pAdapter;
	
	public Produto() {
		this.pAdapter = new ProdutoAdapterPostgreSQL();
	}

	public ProdutoDTO recuperarIdDeTodasPecas(ProdutoDTO produtoDTO) {
		return pAdapter.recuperarIdDeTodasPecas(produtoDTO);
	}
	
	
	public void cadastrarProduto(ProdutoDTO pdto) {
		pAdapter.cadastrarProduto(pdto);
	}

	public void removerProduto(ProdutoDTO pdto) {
		pAdapter.removerProduto(pdto);
	}

	public void editarProduto(ProdutoDTO pdto) {
		pAdapter.editarProduto(pdto);
	}

	public ProdutoDTO getProduto(ProdutoDTO pdto) {
		return pAdapter.getProduto(pdto);
	}
	public ProdutoDTO getProdutos() {
		return pAdapter.getProdutos();
	}

	public void adicionarCor(CorDTO cdto) {
		pAdapter.adicionarCor(cdto);
	}

	public void removerCor(CorDTO cdto) {
		pAdapter.removerCor(cdto);
	}

	public CorDTO getCores() {
		return pAdapter.getCores();
	}

	public void adicionarTamanho(TamanhoDTO tdto) {
		pAdapter.adicionarTamanho(tdto);
	}

	public void removerTamanho(TamanhoDTO tdto) {
		pAdapter.removerTamanho(tdto);
	}

	public TamanhoDTO getTamanhos() {
		return pAdapter.getTamanhos();
	}

	public void adicionarCategoria(CategoriaDTO cdto) {
		pAdapter.adicionarCategoria(cdto);
	}

	public void removerCategoria(CategoriaDTO cdto) {
		pAdapter.removerCategoria(cdto);
	}

	public CategoriaDTO getCategorias() {
		return pAdapter.getCategorias();
	}



}
