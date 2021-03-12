package Controller;

import DTO.FuncionarioDTO;
import Model.Funcionario;

public class FuncionarioControl {

	Funcionario funcionario = new Funcionario();
	
	public void adicionarFuncionario(FuncionarioDTO fdto) {
		funcionario.adicionarFuncionario(fdto);
	}

	public FuncionarioDTO getFuncionario(FuncionarioDTO fdto) {
		return funcionario.getFuncionario(fdto);
	}
	
	public FuncionarioDTO getFuncionarioViaLoginESenha(FuncionarioDTO fdto) {
		return funcionario.getFuncionarioViaLoginESenha(fdto);	
	}
		
	

	public void removerFuncionario(FuncionarioDTO fdto) {
		funcionario.removerFuncionario(fdto);
	}

	public void editarFuncionario(FuncionarioDTO fdto) {
		funcionario.editarFuncionario(fdto);
	}

	public FuncionarioDTO getFuncionarios() {
		return funcionario.getFuncionarios();
	}
	
	
	

	
}
