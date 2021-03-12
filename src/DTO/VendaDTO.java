package DTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class VendaDTO {
	
//	daria pra gambiarrar aqui kkk
	
	private int  numVenda;
	private int idFuncionario;
	private int idCliente;
	private int idPagamento;
	private int quantParcelas;
	private ArrayList<VendaDTO> vendas = new ArrayList<>();
	
	private ArrayList<ProdutoDTO> produtosDaVenda = new ArrayList<ProdutoDTO>();
	
	
	private String CpfCliente;
	private float preco;
	private Timestamp dataCriacao;
	private int pagamento;
	
	private String tipoDeCartao;
	
	public int getNumVenda() {
		return numVenda;
	}
	public void setNumVenda(int numVenda) {
		this.numVenda = numVenda;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}
	public int getQuantParcelas() {
		return quantParcelas;
	}
	public void setQuantParcelas(int quantParcelas) {
		this.quantParcelas = quantParcelas;
	}
	public ArrayList<VendaDTO> getVendas() {
		return vendas;
	}
	public void setVendas(ArrayList<VendaDTO> vendas) {
		this.vendas = vendas;
	}
	public String getCpfCliente() {
		return CpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		CpfCliente = cpfCliente;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getPagamento() {
		return pagamento;
	}
	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
	}
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public ArrayList<ProdutoDTO> getProdutosDaVenda() {
		return produtosDaVenda;
	}
	public void setProdutosDaVenda(ArrayList<ProdutoDTO> produtosDaVenda) {
		this.produtosDaVenda = produtosDaVenda;
	}
	public String getTipoDeCartao() {
		return tipoDeCartao;
	}
	public void setTipoDeCartao(String tipoDeCartao) {
		this.tipoDeCartao = tipoDeCartao;
	}
	

	
	
}




