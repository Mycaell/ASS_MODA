package DAO;

import DTO.ClienteDTO;

public interface ClienteDAO {
	
	public void adicionarCliente(ClienteDTO clienteDTO);
	
	public void adicionarEndereco(ClienteDTO clienteDTO);
	
	public void adicionarClienteEndereco();
	
	
	public void removerCliente(ClienteDTO clienteDTO);
	
	public void removerEndereco(ClienteDTO clienteDTO);
	
	public void removerClienteEndereco(ClienteDTO clienteDTO);
	
	
	public void editarCliente(ClienteDTO clienteDTO);
	
	public void editarEndereco(ClienteDTO clienteDTO);
	
	
	public ClienteDTO getCliente(ClienteDTO clienteDTO);

	public ClienteDTO getClientePorCPF(ClienteDTO cdto);
	
	public ClienteDTO getClientes();

	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO);
	
	
}
