package DAO;

import DTO.ClienteDTO;

public class ClienteAdapterPostgreSQL extends ClienteDAOPostgreSQL implements ClienteDAO{

	@Override	
	public void adicionarCliente(ClienteDTO cdto) {				
		super.adicionarCliente(cdto);
	}
	
	
	@Override
	public void adicionarEndereco(ClienteDTO cdto) {
		super.adicionarEndereco(cdto);
	}

	@Override
	public void adicionarClienteEndereco() {
		super.adicionarClienteEndereco();
	}
	
	
	@Override
	public void removerCliente(ClienteDTO cdto) {
		super.removerCliente(cdto);
	}

	@Override
	public void removerEndereco(ClienteDTO cdto) {
		super.removerEndereco(cdto);
	}

	@Override
	public void removerClienteEndereco(ClienteDTO cdto) {
		super.removerClienteEndereco(cdto);
	}
	
	
	@Override
	public void editarCliente(ClienteDTO cdto) {
		super.editarCliente(cdto);
	}

	@Override
	public void editarEndereco(ClienteDTO cdto) {
		super.editarEndereco(cdto);
	}
	
	@Override
	public ClienteDTO getCliente(ClienteDTO cdto) {
		return super.getCliente(cdto);
	}

	@Override
	public ClienteDTO getClientePorCPF(ClienteDTO cdto) {
		return super.getClientePorCPF(cdto);
	}
	
	@Override
	public ClienteDTO getClientes() {
		return super.getClientes();
	}

	@Override
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO) {
		return super.recuperarCPFDeTodosClientes(clienteDTO);
	}
}
