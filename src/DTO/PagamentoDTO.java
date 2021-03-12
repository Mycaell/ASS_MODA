package DTO;

import java.sql.Time;
import java.sql.Date;

public class PagamentoDTO {
	
	private String tipoCartao;
	private String numCartao;
	private int id;
	private int tipo;
	private int quantParcelas;
	private int idBandeira;
	private Date dataPagamento;
	private Time horaPagamento;
	
	public String getTipoCartao() {
		return tipoCartao;
	}
	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getQuantParcelas() {
		return quantParcelas;
	}
	public void setQuantParcelas(int quantParcelas) {
		this.quantParcelas = quantParcelas;
	}
	public int getIdBandeira() {
		return idBandeira;
	}
	public void setIdBandeira(int idBandeira) {
		this.idBandeira = idBandeira;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Time getHoraPagamento() {
		return horaPagamento;
	}
	public void setHoraPagamento(Time horaPagamento) {
		this.horaPagamento = horaPagamento;
	}
	

}
