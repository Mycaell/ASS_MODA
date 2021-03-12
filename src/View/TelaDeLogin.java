package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import Controller.FuncionarioControl;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.FactoryTelaInicial;
import View_Utilidades.Icones;
import View_Utilidades.StrategyLogin;

public class TelaDeLogin extends TelaPadrao{

	
	private JPasswordField login;
	private JPasswordField senha;
	
	public TelaDeLogin() {
		super("Login");
		
		
		this.setVisible(true);
	}
	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		adicionarFields();
		adicionarBotoes();
	}


	private void adicionarLabels() {

		JLabel label = new JLabel(Icones.FUNDO_LOGIN);
		label.setBounds(0, 0, 800, 400);
		setContentPane(label);

		//		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "ASS MODA", 190, 35, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
	
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Login", 195, 155, 60, 30);		
		AdicionadorDeComponentes.adicionarJLabel(this, "Senha",195, 195, 60, 30);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Estado",195, 245, 65, 30);
		
	}
	
	
	private void adicionarFields() {
		login = AdicionadorDeComponentes.adicionarJPasswordField(this, 260, 160, 280, 20);
		senha = AdicionadorDeComponentes.adicionarJPasswordField(this, 260, 200, 280, 20);
	}
	
	private void adicionarBotoes() {
		JButton botaoEntrar = AdicionadorDeComponentes.adicionarJButton(this, "Entrar", 450, 250, 90, 20);
		botaoEntrar.setIcon(Icones.ICONE_LOGIN);
		botaoEntrar.addActionListener(new OuvinteTelaDeLogin(this));
		
	}

	
	private class OuvinteTelaDeLogin implements ActionListener {

		
		private TelaDeLogin telaDeLogin;
		
		public OuvinteTelaDeLogin(TelaDeLogin telaDeLogin) {
			this.telaDeLogin = telaDeLogin;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			
			
			if(botao.equals("Entrar")) {
				String loginDigitado = new String(login.getPassword());
				String senhaDigitada = new String(senha.getPassword());
				
				if(loginDigitado.equals("") || senhaDigitada.equals("")) {
					JOptionPane.showMessageDialog(telaDeLogin,"Preencha todos os campos!","Campo vázio",JOptionPane.ERROR_MESSAGE);
				}else {
					FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
					funcionarioDTO.setLogin(loginDigitado);
					funcionarioDTO.setSenha(senhaDigitada);
					
					FuncionarioControl funcionarioControl = new FuncionarioControl();	
					funcionarioDTO = funcionarioControl.getFuncionarioViaLoginESenha(funcionarioDTO);
					
					String cargo = funcionarioDTO.getCargo();

					
					String identificacao = "<html><u> "+cargo+" ("+funcionarioDTO.getNome()+" : "+funcionarioDTO.getId()+")</u></html>";
					
//				USANDO SIMPLE FACTORI PARA ABRIR A TELA DESEJADA
					if(cargo != null) {
						StrategyLogin factory = new FactoryTelaInicial(); 
						factory.logar(cargo, funcionarioDTO.getId(), identificacao);
						telaDeLogin.dispose();
					}else {
						JOptionPane.showMessageDialog(telaDeLogin, "Você não está cadastrado!", "Dados Inválidos", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	
	public static void main(String[] args) {
		new TelaDeLogin();
	}
	
	
}

