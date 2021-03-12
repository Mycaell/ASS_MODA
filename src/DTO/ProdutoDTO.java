package DTO;

import java.util.ArrayList;

public class ProdutoDTO {

	private String nome;
	private String cor;
	private String marca;
	private String descricao;
	private String tamanho;
	private String categoria;
	private int id;
	private int quantEstoque;
	private int idFuncionario;
	private float preco;
	private ArrayList<ProdutoDTO> produtos = new ArrayList<>();
	
	private ArrayList<String> idDeTodosProdutos = new ArrayList<>();
	
	private int quantidade;
	
	public ProdutoDTO(String nome, String cor, String marca, String descricao, String tamanho, String categoria,
			int quantEstoque, int idFuncionario, float preco) {
		super();
		this.nome = nome;
		this.cor = cor;
		this.marca = marca;
		this.descricao = descricao;
		this.tamanho = tamanho;
		this.categoria = categoria;
		this.quantEstoque = quantEstoque;
		this.idFuncionario = idFuncionario;
		this.preco = preco;
	}
	
	public ProdutoDTO() {}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantEstoque() {
		return quantEstoque;
	}
	public void setQuantEstoque(int quantEstoque) {
		this.quantEstoque = quantEstoque;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public ArrayList<ProdutoDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public ArrayList<String> getIdDeTodosProdutos() {
		return idDeTodosProdutos;
	}
	public void setIdDeTodosProdutos(ArrayList<String> idDeTodosProdutos) {
		this.idDeTodosProdutos = idDeTodosProdutos;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
