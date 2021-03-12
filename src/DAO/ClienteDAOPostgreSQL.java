package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ClienteDTO;

public class ClienteDAOPostgreSQL{
	
	private Connection con=null;
	private PreparedStatement pstm=null;
	private ResultSet rset=null;
	
	public void adicionarCliente(ClienteDTO cdto){
		String sql = "INSERT INTO cliente(nome,sobrenome,sexo,data_nascimento,telefone,cpf,id_funcionario) VALUES(?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, cdto.getNome());
			pstm.setString(2, cdto.getSobrenome());
			pstm.setString(3, Character.toString(cdto.getSexo()));
			
			Date dataSQL = new Date(cdto.getDataNascimento().getTime());
			pstm.setDate(4, dataSQL);
			
			pstm.setString(5, cdto.getTelefone());
			pstm.setString(6, cdto.getCpf());
			pstm.setInt(7, cdto.getIdFuncionario());
			
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionarEndereco(ClienteDTO cdto) {

		try {
			String sql = "INSERT INTO endereco(bairro, rua, numero) VALUES(?,?,?)";
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, cdto.getBairro());
			pstm.setString(2, cdto.getRua());
			pstm.setInt(3, cdto.getnCasa());
			
			pstm.execute();
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void adicionarClienteEndereco() {
		try {
			String sql = "SELECT max(c.id) as id_cliente, max(e.id) as id_endereco from cliente as c, endereco as e";
			pstm = con.prepareStatement(sql);

			rset = pstm.executeQuery();

			int id_cliente = 0;
			int id_endereco = 0;
			
			while(rset.next()) {
				id_cliente = rset.getInt("id_cliente");
				id_endereco = rset.getInt("id_endereco");
			}
			
			sql = "INSERT INTO cliente_endereco(id_cliente, id_endereco) VALUES (?,?)";
			pstm = con.prepareStatement(sql);	
			
			pstm.setInt(1, id_cliente);
			pstm.setInt(2, id_endereco);
			
			pstm.execute();
	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void removerCliente(ClienteDTO cdto) {
		String sql = "DELETE FROM cliente WHERE id = ?";
		
		try {
			con = Conexao.getConnection();
			 
			pstm = con.prepareStatement(sql);			 
			pstm.setInt(1, cdto.getId());			 
			pstm.execute();
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}
	
	public void removerEndereco(ClienteDTO cdto) {
		try {
			String sql = "DELETE FROM endereco WHERE id = ?";
			pstm = con.prepareStatement(sql);			 
			pstm.setInt(1, cdto.getIdEndereco());
			pstm.execute();
			
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void removerClienteEndereco(ClienteDTO cdto) {
		try {
			String sql = "DELETE FROM cliente_endereco WHERE id_cliente = ? AND id_endereco = ?";
			pstm = con.prepareStatement(sql);			 
			pstm.setInt(1, cdto.getId());
			pstm.setInt(2, cdto.getIdEndereco()); 
			pstm.execute();
			
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	
	public void editarCliente(ClienteDTO cdto) {
		String sql = "UPDATE cliente SET nome=?, cpf=?, data_nascimento=?, telefone=?, sexo=?, sobrenome=? WHERE id = ?";

			try {
				 con = Conexao.getConnection();
				 pstm = con.prepareStatement(sql);

				 pstm.setString(1, cdto.getNome()); 
				 pstm.setString(2, cdto.getCpf());
				 Date  dataSQL = new Date (cdto.getDataNascimento().getTime());
				 pstm.setDate(3, dataSQL);
				 pstm.setString(4, cdto.getTelefone());
				 pstm.setString(5, Character.toString(cdto.getSexo()));
				 pstm.setString(6, cdto.getSobrenome());
				 pstm.setInt(7, cdto.getId());
				 pstm.execute();
			
				 
			} catch (Exception e) {				 
				 System.out.println("Erro!");
				 e.printStackTrace();
			}
	}

	public void editarEndereco(ClienteDTO cdto) {
		try {
			 String sql = "UPDATE endereco SET numero=?, rua=?, bairro=? WHERE id = ?";
			 pstm = con.prepareStatement(sql);
			 pstm.setInt(1, cdto.getnCasa()); 
			 pstm.setString(2, cdto.getRua());
			 pstm.setString(3, cdto.getBairro());
			 pstm.setInt(4, cdto.getIdEndereco()); 
			 pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ClienteDTO getClientePorCPF(ClienteDTO cdto) {
		String sql = "select c.* from cliente as c where c.cpf = ?";
		
		try {
			con= Conexao.getConnection();
			
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, cdto.getCpf());
			
			rset = pstm.executeQuery();			 
			
			while(rset.next()) {					
				cdto.setId(rset.getInt("id"));
				cdto.setNome(rset.getString("nome"));
				cdto.setSobrenome(rset.getString("sobrenome"));
				String sexo = rset.getString("sexo");
				cdto.setSexo(sexo.charAt(0));
				cdto.setCpf(rset.getString("cpf"));
				cdto.setTelefone(rset.getString("telefone"));
				cdto.setDataNascimento(rset.getDate("data_nascimento"));
			}
		} catch (Exception e) {
			System.out.println("Erro!");
		}
		return cdto;
	}
	
	
	
	public ClienteDTO getCliente(ClienteDTO cdto) {
//		String sql = "select c.* from cliente as c where c.cpf = ?";
		
//		String sql = "select c.*, c.id as id_cliente, e.*, e.id as id_endereco from cliente as c, endereco as e, cliente_endereco as c_e where c.id = c_e.id_cliente and e.id = c_e.id_endereco";
		
		String sql = "select c.*, c.id as id_cliente, e.*, e.id as id_endereco from cliente as c, endereco as e where c.id = ? and e.id = ?";
		ClienteDTO c =new ClienteDTO();
		try {
			con= Conexao.getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, cdto.getId());
			pstm.setInt(2, cdto.getIdEndereco());
			
			rset = pstm.executeQuery();			 
			
			while(rset.next()) {					
				c.setId(rset.getInt("id_cliente"));
				c.setNome(rset.getString("nome"));
				c.setSobrenome(rset.getString("sobrenome"));
				String sexo = rset.getString("sexo");
				c.setSexo(sexo.charAt(0));
				c.setCpf(rset.getString("cpf"));
				c.setTelefone(rset.getString("telefone"));
				c.setDataNascimento(rset.getDate("data_nascimento"));
//				cadastra outro la pra vc ver, vai dar certo do nada eu acredito amor vm ver oq e
				 c.setBairro(rset.getString("bairro"));
				 c.setRua(rset.getString("rua"));
				 c.setnCasa(rset.getInt("numero"));
				 c.setIdEndereco(rset.getInt("id_endereco"));
				
			}
		} catch (Exception e) {
			System.out.println("Erro!");
		}
		return c;
	}

	public ClienteDTO getClientes() {
		String sql = "select c.*, c.id as id_cliente, e.*, e.id as id_endereco from cliente as c, endereco as e, cliente_endereco as c_e where c.id = c_e.id_cliente and e.id = c_e.id_endereco order by nome asc";
		
		ArrayList<ClienteDTO> dadosDosClientes = new ArrayList<ClienteDTO>();	
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				ClienteDTO cliente = new ClienteDTO();
//				tava dando erro por causa desse metodo
				cliente.setId(rset.getInt("id_cliente"));
				cliente.setNome(rset.getString("nome"));
				cliente.setSobrenome(rset.getString("sobrenome"));
				
				String sexo = rset.getString("sexo");
				cliente.setSexo(sexo.charAt(0));
				
				cliente.setCpf(rset.getString("cpf"));
				cliente.setTelefone(rset.getString("telefone"));
				cliente.setDataNascimento(rset.getDate("data_nascimento"));
				
				cliente.setBairro(rset.getString("bairro"));
				cliente.setRua(rset.getString("rua"));
				cliente.setnCasa(rset.getInt("numero"));
				cliente.setIdEndereco(rset.getInt("id_endereco"));
				
				dadosDosClientes.add(cliente);
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setClientes(dadosDosClientes);
		return clienteDTO;
	}
	
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO) {
		String sql = "SELECT cpf FROM cliente WHERE cpf LIKE '"+clienteDTO.getCpf()+"%' ORDER BY cpf";
		
		ArrayList<String> cpfs = new ArrayList<String>();
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			ResultSet resultado = pstm.executeQuery();
			
			while(resultado.next()) {
				cpfs.add(resultado.getString("cpf"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		clienteDTO.setCpfDeTodosClientes(cpfs);
		return clienteDTO;
	}
}
