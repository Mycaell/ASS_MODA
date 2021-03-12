package DTO;

import java.util.ArrayList;

public class BandeiraDTO {

	private int id;
	private String nomeBandeira;
	private ArrayList<BandeiraDTO> bandeiras = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeBandeira() {
		return nomeBandeira;
	}
	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}
	public ArrayList<BandeiraDTO> getBandeiras() {
		return bandeiras;
	}
	public void setBandeiras(ArrayList<BandeiraDTO> bandeiras) {
		this.bandeiras = bandeiras;
	}
	
}
