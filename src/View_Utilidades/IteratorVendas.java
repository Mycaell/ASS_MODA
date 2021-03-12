package View_Utilidades;

import java.util.ArrayList;

import DTO.VendaDTO;

public class IteratorVendas implements Iterator{

	private ArrayList<VendaDTO> vendas;
	private int posicao=0;
	
	public IteratorVendas(ArrayList<VendaDTO> vendas) {
		this.vendas = vendas;
	}

	@Override
	public boolean hasNext() {
		if(vendas.size()<=posicao || vendas.get(posicao)==null) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		Object venda = vendas.get(posicao);
		posicao++;
		return venda;
	}

}
