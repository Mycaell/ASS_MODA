package View_Utilidades;

import View.TelaDeAtendimento;
import View.TelaDeGerenciamento;

public class FactoryTelaInicial implements StrategyLogin {

	@Override
	public void logar(String cargo, int codigo, String identificacao) {
		switch(cargo) {
		case "root":
		case "Gerente":
			new TelaDeGerenciamento(codigo,identificacao);
			break;
		case "Atendente":
			new TelaDeAtendimento(codigo,identificacao);
			break;
		}
	}

}
