package DTO;

import java.util.ArrayList;

public class CategoriaDTO {

	private int id;
	private String categoria;
	private ArrayList<CategoriaDTO> categorias = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public ArrayList<CategoriaDTO> getCategorias() {
		return categorias;
	}
	public void setCategorias(ArrayList<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}
	
}
