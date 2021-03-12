package DTO;

import java.util.ArrayList;

public class CorDTO {

	private int id;
	private String nomeCor;
	private ArrayList<CorDTO> cores = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCor() {
		return nomeCor;
	}
	public void setNomeCor(String nomeCor) {
		this.nomeCor = nomeCor;
	}
	public ArrayList<CorDTO> getCores() {
		return cores;
	}
	public void setCores(ArrayList<CorDTO> cores) {
		this.cores = cores;
	}
	
}
