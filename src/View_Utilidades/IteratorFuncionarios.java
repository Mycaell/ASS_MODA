package View_Utilidades;

import java.util.ArrayList;

import DTO.FuncionarioDTO;

public class IteratorFuncionarios implements Iterator{

	private ArrayList<FuncionarioDTO> funcionarios;
	private int posicao=0;
	
	
	public IteratorFuncionarios(ArrayList<FuncionarioDTO> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public boolean hasNext() {
		if(funcionarios.size()<=posicao || funcionarios.get(posicao)==null) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		Object funcionario = funcionarios.get(posicao);
		posicao++;
		return funcionario;
	}
}
