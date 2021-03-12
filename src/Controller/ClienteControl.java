package Controller;

import DTO.ClienteDTO;
import Model.Cliente;

public class ClienteControl {

	private Cliente cliente = new Cliente();
	
	public void adicionarCliente(ClienteDTO cdto) {				
		cliente.adicionarCliente(cdto);
	}

	public void removerCliente(ClienteDTO cdto) {
		cliente.removerCliente(cdto);
	}

	public void editarCliente(ClienteDTO cdto) {
		cliente.editarCliente(cdto);
	}

	public ClienteDTO getCliente(ClienteDTO cdto) {
		return cliente.getCliente(cdto);
	}
	
	public ClienteDTO getClientePorCPF(ClienteDTO cdto) {
		return cliente.getClientePorCPF(cdto);
	}


	public ClienteDTO getClientes() {
		return cliente.getClientes();
	}

	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO) {
		return cliente.recuperarCPFDeTodosClientes(clienteDTO);
	}
}
