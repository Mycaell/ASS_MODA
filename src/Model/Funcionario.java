package Model;

import java.util.Date;

import DAO.FuncionarioAdapterPostgreSQL;
import DTO.FuncionarioDTO;

public class Funcionario {

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
	private String login;
	private String senha;
	private String cargo;
	
	
	
	private FuncionarioAdapterPostgreSQL fAdapter;
	
	public Funcionario () {
		this.fAdapter = new FuncionarioAdapterPostgreSQL();
	}
	
	public void adicionarFuncionario(FuncionarioDTO fdto) {
		fAdapter.adicionarFuncionario(fdto);
		fAdapter.adicionarEndereco(fdto);
		fAdapter.adicionarFuncionarioEndereco();		
	}

	public FuncionarioDTO getFuncionario(FuncionarioDTO fdto) {
		return fAdapter.getFuncionario(fdto);
	}

	public FuncionarioDTO getFuncionarioViaLoginESenha(FuncionarioDTO fdto) {
		return fAdapter.getFuncionarioViaLoginESenha(fdto);	
	}

	public void removerFuncionario(FuncionarioDTO fdto) {
	}

	public void editarFuncionario(FuncionarioDTO fdto) {
		fAdapter.editarFuncionario(fdto);
		fAdapter.editarEndereco(fdto);
	}

	public FuncionarioDTO getFuncionarios() {
		return fAdapter.getFuncionarios();
	}
}
