package View_Utilidades;

import java.util.ArrayList;

import DTO.ClienteDTO;

public class IteratorClientes implements Iterator{

	private ArrayList<ClienteDTO> clientes;
	private int posicao=0;
	
	public IteratorClientes(ArrayList<ClienteDTO> clientes) {
		this.clientes = clientes;
	}

	@Override
	public boolean hasNext() {
		if(clientes.size()<=posicao || clientes.get(posicao)==null) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		Object cliente = clientes.get(posicao);
		posicao++;
		return cliente;
	}

	
}
