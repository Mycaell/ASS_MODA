package Model;

import java.util.Date;

import DAO.ClienteAdapterPostgreSQL;
import DTO.ClienteDTO;

public class Cliente {

	private int id;
	
	private String nome;
	private String sobrenome;
	private char sexo;
	private String cpf;
	private String telefone;
	private Date dataNascimento;
	private String bairro;
	private String rua;
	private int nCasa;

	
	private ClienteAdapterPostgreSQL clienteAdapter;
	
	
	public Cliente() {
		this.clienteAdapter = new ClienteAdapterPostgreSQL();
	}
	
	public void adicionarCliente(ClienteDTO clienteDTO) {				
		clienteAdapter.adicionarCliente(clienteDTO);
		clienteAdapter.adicionarEndereco(clienteDTO);
		clienteAdapter.adicionarClienteEndereco();
	}

	public void removerCliente(ClienteDTO clienteDTO) {
		clienteAdapter.removerCliente(clienteDTO);
		clienteAdapter.removerEndereco(clienteDTO);
		clienteAdapter.removerClienteEndereco(clienteDTO);
	}

	public void editarCliente(ClienteDTO clienteDTO) {
		clienteAdapter.editarCliente(clienteDTO);
		clienteAdapter.editarEndereco(clienteDTO);
	}

	public ClienteDTO getCliente(ClienteDTO clienteDTO) {
		return clienteAdapter.getCliente(clienteDTO);
	}

	public ClienteDTO getClientePorCPF(ClienteDTO cdto) {
		return clienteAdapter.getClientePorCPF(cdto);
	}
	
	public ClienteDTO getClientes() {
		return clienteAdapter.getClientes();
	}
	
	

	
	
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO){
		
		StringBuilder stringBuilder = new StringBuilder(clienteDTO.getCpf());
		
		int tamCPF = clienteDTO.getCpf().length();
		
		if(tamCPF > 3 && tamCPF < 7) {
//			System.out.println("colocar o 1º ponto");
			stringBuilder.insert(3, ".");
//			System.out.println(stringBuilder.toString());
		}else if (tamCPF > 6 && tamCPF < 10) {
//			System.out.println("colocar o 2º ponto");
			stringBuilder.insert(3, ".");
			stringBuilder.insert(7, ".");
//			System.out.println(stringBuilder.toString());
		}else if (tamCPF > 9) {
//			System.out.println("colocar o hífen");
			stringBuilder.insert(3, ".");
			stringBuilder.insert(7, ".");
			stringBuilder.insert(11, "-");
//			System.out.println(stringBuilder.toString());
		}

		
		clienteDTO.setCpf(stringBuilder.toString());
//		System.out.println("C:"+ clienteDTO.getCPF());
		
		return clienteAdapter.recuperarCPFDeTodosClientes(clienteDTO);
	}
	
}
