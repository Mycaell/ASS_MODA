package DAO;

import DTO.FuncionarioDTO;

public class FuncionarioAdapterPostgreSQL extends FuncionarioDAOPostgreSQL implements FuncionarioDAO{

	@Override
	public void adicionarFuncionario(FuncionarioDTO fdto) {
		super.adicionarFuncionario(fdto);
	}

	@Override
	public void adicionarEndereco(FuncionarioDTO fdto) {
		super.adicionarEndereco(fdto);
	}
	
	@Override
	public void adicionarFuncionarioEndereco() {
		super.adicionarFuncionarioEndereco();
	}
	
	@Override
	public FuncionarioDTO getFuncionario(FuncionarioDTO fdto) {
		return super.getFuncionario(fdto);
	}

	@Override
	public FuncionarioDTO getFuncionarioViaLoginESenha(FuncionarioDTO fdto) {
		return super.getFuncionarioViaLoginESenha(fdto);	
	}
	
	@Override
	public void removerFuncionario(FuncionarioDTO fdto) {
		super.removerFuncionario(fdto);
	}
	
	@Override
	public void removerEndereco(FuncionarioDTO fdto) {
		super.removerEndereco(fdto);
	}
	
	@Override
	public void removerFuncionarioEndereco(FuncionarioDTO fdto) {
		super.removerFuncionarioEndereco(fdto);
	}

	@Override
	public void editarFuncionario(FuncionarioDTO fdto) {
		super.editarFuncionario(fdto);
	}
	
	@Override
	public void editarEndereco(FuncionarioDTO fdto) {
		super.editarEndereco(fdto);
	}

	@Override
	public FuncionarioDTO getFuncionarios() {
		return super.getFuncionarios();
	}

}
