
package DTO;

import java.util.ArrayList;
import java.util.Date;

public class FuncionarioDTO {

	private String nome;
	private String sobrenome;
	private String login;
	private String senha;
	private String cpf;
	private String cargo;
	private String telefone;
	private char sexo;
	private String rua;
	private String bairro;
	private int numCasa;
	private int id;
	private Date dataNascimento;
	private ArrayList<FuncionarioDTO> funcionarios = new ArrayList<>();
	
	private int idEndereco;
	
	
	
	public FuncionarioDTO() {
		
	}
	
	public FuncionarioDTO(String nome, String sobrenome, char sexo, String cpf, String telefone, String login,
			String senha, String cargo, String bairro, String rua, int numCasa, Date dataDeNasc) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
		this.cargo = cargo;
		this.bairro = bairro;
		this.rua = rua;
		this.numCasa = numCasa;
		this.dataNascimento = dataDeNasc;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public ArrayList<FuncionarioDTO> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(ArrayList<FuncionarioDTO> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumCasa() {
		return numCasa;
	}
	public void setNumCasa(int numCasa) {
		this.numCasa = numCasa;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public int getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
	

}
