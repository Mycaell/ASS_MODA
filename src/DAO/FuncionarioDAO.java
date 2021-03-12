package DAO;

import DTO.FuncionarioDTO;

public interface FuncionarioDAO {

	void adicionarFuncionario(FuncionarioDTO fdto);
	
	void adicionarEndereco(FuncionarioDTO fdto);
	
	void adicionarFuncionarioEndereco();
	
	FuncionarioDTO getFuncionario(FuncionarioDTO fdto);

	FuncionarioDTO getFuncionarioViaLoginESenha(FuncionarioDTO fdto);
	
	void removerFuncionario(FuncionarioDTO fdto);

	void removerEndereco(FuncionarioDTO gdto);
	
	void removerFuncionarioEndereco(FuncionarioDTO gdto);
	
	void editarFuncionario(FuncionarioDTO fdto);

	void editarEndereco(FuncionarioDTO fdto);
	
	FuncionarioDTO getFuncionarios();

}
