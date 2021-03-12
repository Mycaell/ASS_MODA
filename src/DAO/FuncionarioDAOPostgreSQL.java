package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.FuncionarioDTO;

public class FuncionarioDAOPostgreSQL{

	private Connection con=null;
	private PreparedStatement pstm=null;
	private ResultSet rset=null;
	
	public void adicionarFuncionario(FuncionarioDTO fdto) {
		String sql = "INSERT INTO funcionario(nome,sobrenome,sexo,cpf,telefone,data_nascimento,login,senha,cargo)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, fdto.getNome());
			pstm.setString(2, fdto.getSobrenome());
			pstm.setString(3, Character.toString(fdto.getSexo()));
			
			pstm.setString(4, fdto.getCpf());
			pstm.setString(5, fdto.getTelefone());

			Date dataSQL = new Date(fdto.getDataNascimento().getTime());
			pstm.setDate(6, dataSQL);
			
			pstm.setString(7, fdto.getLogin());
			pstm.setString(8, fdto.getSenha());
			pstm.setString(9, fdto.getCargo());
			
			pstm.execute();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionarEndereco(FuncionarioDTO edto) {
		
		String sql = "INSERT INTO endereco(bairro, rua, numero) VALUES(?,?,?)";
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, edto.getBairro());
			pstm.setString(2, edto.getRua());
			pstm.setInt(3, edto.getNumCasa());
			
			pstm.execute();
			
		}catch (Exception e) {
			
		}		
	}
	
	void adicionarFuncionarioEndereco() {
		String sql = "SELECT max(f.id) as id_funcionario, max(e.id) as id_endereco from funcionario as f, endereco as e";
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();

			int id_funcionario = 0;
			int id_endereco = 0;
			
			while(rset.next()) {
				id_funcionario = rset.getInt("id_funcionario");
				id_endereco = rset.getInt("id_endereco");
			}
			
			sql = "INSERT INTO funcionario_endereco(id_funcionario, id_endereco) VALUES (?,?)";
			pstm = con.prepareStatement(sql);	
			
			pstm.setInt(1, id_funcionario);
			pstm.setInt(2, id_endereco);
			
			pstm.execute();
			
		}catch (Exception e) {
			
		}
	}
		
	public FuncionarioDTO getFuncionarios() {
		String sql = "select f.*, f.id as id_funcionario, e.*, e.id as id_endereco from funcionario as f, endereco as e, funcionario_endereco as f_e where f.id = f_e.id_funcionario and e.id = f_e.id_endereco order by nome asc";
		
		ArrayList<FuncionarioDTO> dadosDosFuncionarios = new ArrayList<FuncionarioDTO>();	
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				FuncionarioDTO funcionario = new FuncionarioDTO();
				
				funcionario.setId(rset.getInt("id_funcionario"));
				funcionario.setNome(rset.getString("nome"));
				funcionario.setSobrenome(rset.getString("sobrenome"));
				funcionario.setLogin(rset.getString("login"));
				funcionario.setSenha(rset.getString("senha"));
				funcionario.setCargo(rset.getString("cargo"));
				 
				String sexo = rset.getString("sexo");
				funcionario.setSexo(sexo.charAt(0));
					
				funcionario.setCpf(rset.getString("cpf"));
				funcionario.setTelefone(rset.getString("telefone"));
				funcionario.setDataNascimento(rset.getDate("data_nascimento"));
					
				funcionario.setBairro(rset.getString("bairro"));
				funcionario.setRua(rset.getString("rua"));
				funcionario.setNumCasa(rset.getInt("numero"));
				funcionario.setIdEndereco(rset.getInt("id_endereco"));
				
				dadosDosFuncionarios.add(funcionario);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setFuncionarios(dadosDosFuncionarios);
		return funcionarioDTO;
	}

	public void removerFuncionario(FuncionarioDTO fdto) {
		String sql = "DELETE FROM funcionario WHERE id = ?";
		 
		try {
			con = Conexao.getConnection();
			 
			pstm = con.prepareStatement(sql);			 
			pstm.setInt(1, fdto.getId());			 
			pstm.execute();
						
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}

	public void removerEndereco(FuncionarioDTO fdto) {
		String sql = "DELETE FROM endereco WHERE id = ?";
		 
		try {
			con = Conexao.getConnection();
						
			pstm = con.prepareStatement(sql);	
			pstm.setInt(1, fdto.getIdEndereco());
			pstm.execute();
			
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}
		
	public void removerFuncionarioEndereco(FuncionarioDTO fdto) {
		String sql = "DELETE FROM funcionario_endereco WHERE id_funcionario = ? AND id_endereco = ?";
		 
		try {
			con = Conexao.getConnection();
						
			pstm = con.prepareStatement(sql);	
			pstm = con.prepareStatement(sql);	
			pstm.setInt(1, fdto.getId());
			pstm.setInt(2, fdto.getIdEndereco()); 
			pstm.execute();
			
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}
	
	public void editarFuncionario(FuncionarioDTO fdto) {
		String sql = "UPDATE funcionario SET nome=?, cpf=?, data_nascimento=?, telefone=?, sexo=?, sobrenome=?, login=?, senha=?, cargo=? WHERE id = ?";

		try {
			 con = Conexao.getConnection();
			 pstm = con.prepareStatement(sql);

			 pstm.setString(1, fdto.getNome()); 
			 pstm.setString(2, fdto.getCpf());
			 Date  dataSQL = new Date (fdto.getDataNascimento().getTime());
			 pstm.setDate(3, dataSQL);
			 pstm.setString(4, fdto.getTelefone());
			 pstm.setString(5, Character.toString(fdto.getSexo()));
			 pstm.setString(6, fdto.getSobrenome());
			 pstm.setString(7, fdto.getLogin());
			 pstm.setString(8, fdto.getSenha());
			 pstm.setString(9, fdto.getCargo());
			 pstm.setInt(10, fdto.getId());
			 pstm.execute();
		
		} catch (Exception e) {				 
			 System.out.println("Erro!");
			 e.printStackTrace();
		}
	}
	
	public void editarEndereco(FuncionarioDTO fdto) {
		String sql = "UPDATE endereco SET numero=?, rua=?, bairro=? WHERE id = ?";

		try {
			 con = Conexao.getConnection();
			 pstm = con.prepareStatement(sql);
			 pstm.setInt(1, fdto.getNumCasa()); 
			 pstm.setString(2, fdto.getRua());
			 pstm.setString(3, fdto.getBairro());
			 pstm.setInt(4, fdto.getIdEndereco()); 
			 pstm.execute();
		} catch (Exception e) {				 
			 System.out.println("Erro!");
			 e.printStackTrace();
		}
	}

//	usado apenas no login
	public FuncionarioDTO getFuncionarioViaLoginESenha(FuncionarioDTO fdto) {
		String sql = "select f.* from funcionario as f where f.login = ? and f.senha = ?";
		
			try {
				con= Conexao.getConnection();
				pstm = con.prepareStatement(sql);
				pstm.setString(1, fdto.getLogin());
				pstm.setString(2, fdto.getSenha());
				rset = pstm.executeQuery();			 

				 while(rset.next()) {					
					 fdto.setId(rset.getInt("id"));
					 fdto.setNome(rset.getString("nome"));
					 fdto.setSobrenome(rset.getString("sobrenome"));
					 fdto.setLogin(rset.getString("login"));
					 fdto.setSenha(rset.getString("senha"));
					 fdto.setCargo(rset.getString("cargo"));
					 
					 String sexo = rset.getString("sexo");
					 fdto.setSexo(sexo.charAt(0));
						
					 fdto.setCpf(rset.getString("cpf"));
					 fdto.setTelefone(rset.getString("telefone"));
					 fdto.setDataNascimento(rset.getDate("data_nascimento"));
						
				 }
				
			} catch (Exception e) {
					 
				System.out.println("achei Erro!");
			}
			return fdto;
	}
	
	
	
	public FuncionarioDTO getFuncionario(FuncionarioDTO fdto) {
		String sql = "select f.*, f.id as id_funcionario, e.*, e.id as id_endereco from funcionario as f, endereco as e where f.id = ? and e.id = ?";
		
//		FuncionarioDTO f =new FuncionarioDTO();		 
			try {
				con= Conexao.getConnection();
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, fdto.getId());
				pstm.setInt(2, fdto.getIdEndereco());
				rset = pstm.executeQuery();			 

				 while(rset.next()) {					
					 fdto.setId(rset.getInt("id_funcionario"));
					 fdto.setNome(rset.getString("nome"));
					 fdto.setSobrenome(rset.getString("sobrenome"));
					 fdto.setLogin(rset.getString("login"));
					 fdto.setSenha(rset.getString("senha"));
					 fdto.setCargo(rset.getString("cargo"));
					 
					 String sexo = rset.getString("sexo");
					 fdto.setSexo(sexo.charAt(0));
						
					 fdto.setCpf(rset.getString("cpf"));
					 fdto.setTelefone(rset.getString("telefone"));
					 fdto.setDataNascimento(rset.getDate("data_nascimento"));
						
					 fdto.setBairro(rset.getString("bairro"));
					 fdto.setRua(rset.getString("rua"));
					 fdto.setNumCasa(rset.getInt("numero"));
					 fdto.setIdEndereco(rset.getInt("id_endereco"));
				 }
				
			} catch (Exception e) {
					 
				System.out.println("achei Erro!");
			}
			return fdto;
	}

}
