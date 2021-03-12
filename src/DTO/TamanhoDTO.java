package DTO;

import java.util.ArrayList;

public class TamanhoDTO {

	private int id;
	private String tamanho;
	private ArrayList<TamanhoDTO> tamanhos = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public ArrayList<TamanhoDTO> getTamanhos() {
		return tamanhos;
	}
	public void setTamanhos(ArrayList<TamanhoDTO> tamanhos) {
		this.tamanhos = tamanhos;
	}
	
}
