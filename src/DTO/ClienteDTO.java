package DTO;

import java.util.ArrayList;
import java.util.Date;

public class ClienteDTO {

	private String nome;
	private String sobrenome;
	private char sexo;
	private String cpf;
	private String telefone;
	private String rua;
	private String bairro;
	private int id;
	private int nCasa;
	private Date dataNascimento;
	private int idFuncionario;
	private ArrayList<ClienteDTO> clientes = new ArrayList<>();
	
	private ArrayList<String> cpfDeTodosClientes;
	
	
	
	private int idEndereco;
	
	public ClienteDTO() {}
	
	
	public ClienteDTO(String nome, String sobrenome, char sexo, String cpf, String telefone, String rua, String bairro,
			int nCasa, Date dataNascimento, int idFuncionario) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefone = telefone;
		this.rua = rua;
		this.bairro = bairro;
		this.nCasa = nCasa;
		this.dataNascimento = dataNascimento;
		this.idFuncionario = idFuncionario;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getnCasa() {
		return nCasa;
	}
	public void setnCasa(int nCasa) {
		this.nCasa = nCasa;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public ArrayList<ClienteDTO> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<ClienteDTO> clientes) {
		this.clientes = clientes;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public ArrayList<String> getCpfDeTodosClientes() {
		return cpfDeTodosClientes;
	}
	public void setCpfDeTodosClientes(ArrayList<String> cpfDeTodosClientes) {
		this.cpfDeTodosClientes = cpfDeTodosClientes;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}


	public int getIdEndereco() {
		return idEndereco;
	}


	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}


	
	
}
