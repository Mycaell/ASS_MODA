package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controller.FuncionarioControl;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaEdicaoFuncionario extends TelaCadastroFuncionario{

	private int codigoDoFuncionarioLogado;
	private int idFuncionario;
	private int idEndereco;
	
	private String identificacao;
	
	public TelaEdicaoFuncionario(int codigoDoFuncionarioLogado, int idFuncionario, int idEndereco, String identificacao) {
		super(codigoDoFuncionarioLogado, identificacao);
	
		this.idFuncionario = idFuncionario;
		this.idEndereco = idEndereco;
		
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		preencherDados();
	}

	@Override
	public void adicionarComponentesGraficos() {
		super.adicionarComponentesGraficos();

		this.setTitle("Edição de Dados do Funcionário");
		removerComponentes();
		adicionarBotoes();
	
	}
	
	private void removerComponentes() {
		remove(super.getBotaoCancelar());
		remove(super.getBotaoSalvar());
	}

	private void adicionarBotoes() {
		OuvinteBotesTelaEdicaoFuncionario ouvinte = new OuvinteBotesTelaEdicaoFuncionario(this);
		
		JButton botaoSalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 300, 380, 90, 20);
		botaoSalvar.setIcon(Icones.ICONE_SALVAR);
		botaoSalvar.addActionListener(ouvinte);
		
		JButton botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 400, 380, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(ouvinte);
		
	}
	
	private void preencherDados() {
		
		FuncionarioControl funcionarioControl = new FuncionarioControl();
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setId(idFuncionario);
		funcionarioDTO.setIdEndereco(idEndereco);
		
		
		funcionarioDTO = funcionarioControl.getFuncionario(funcionarioDTO);
		
		
		JLabel L = super.getL();
		L.setText("EDIÇÃO");
		L.setBounds(245, 15, 450, 80);
		super.getNome().setText(funcionarioDTO.getNome());
		super.getSobrenome().setText(funcionarioDTO.getSobrenome());
//		pegar o sexo
		
		char sexo = funcionarioDTO.getSexo();
		if(sexo == 'F') {
			super.getRadioButtonF().setSelected(true);
		}else {
			super.getRadioButtonM().setSelected(true);;
		}
		
		
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formataData.format(funcionarioDTO.getDataNascimento());
		
		super.getDataDeNascimento().setText(dataFormatada);
		super.getTelefone().setText(funcionarioDTO.getTelefone());
		super.getCPF().setText(funcionarioDTO.getCpf());
		super.getBairro().setText(funcionarioDTO.getBairro());
		super.getRua().setText(funcionarioDTO.getRua());
		super.getNumeroDaCasa().setText(Integer.toString(funcionarioDTO.getNumCasa()));
		super.getFuncionarios().setSelectedItem(funcionarioDTO.getCargo());
		super.getLogin().setText(funcionarioDTO.getLogin());
		super.getSenha().setText(funcionarioDTO.getSenha());
		super.getSenhaRevelada().setText(funcionarioDTO.getSenha());
		
	}
	
	
	
	private class OuvinteBotesTelaEdicaoFuncionario implements ActionListener{

		private TelaEdicaoFuncionario telaEdicaoFuncionario;
		
		
		
		public OuvinteBotesTelaEdicaoFuncionario(TelaEdicaoFuncionario telaEdicaoFuncionario) {
			this.telaEdicaoFuncionario = telaEdicaoFuncionario;
		}


		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Salvar")) {
				
				FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
				funcionarioDTO.setId(idFuncionario);
				funcionarioDTO.setIdEndereco(idEndereco);
				funcionarioDTO.setNome(telaEdicaoFuncionario.getNome().getText());
				funcionarioDTO.setSobrenome(telaEdicaoFuncionario.getSobrenome().getText());
				
				
				if(telaEdicaoFuncionario.getRadioButtonF().isSelected()) {
					funcionarioDTO.setSexo('F');
				}else {
					funcionarioDTO.setSexo('M');
				}
				
				SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
				Date dataNasc = null;
				try {
					dataNasc = formataData.parse(telaEdicaoFuncionario.getDataDeNascimento().getText());
				} catch (ParseException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
				
				funcionarioDTO.setDataNascimento(dataNasc);
				funcionarioDTO.setTelefone(telaEdicaoFuncionario.getTelefone().getText());
				funcionarioDTO.setCpf(telaEdicaoFuncionario.getCPF().getText());
				funcionarioDTO.setBairro(telaEdicaoFuncionario.getBairro().getText());
				funcionarioDTO.setRua(telaEdicaoFuncionario.getRua().getText());
				funcionarioDTO.setNumCasa(Integer.parseInt(telaEdicaoFuncionario.getNumeroDaCasa().getText()));
				funcionarioDTO.setCargo((String) telaEdicaoFuncionario.getFuncionarios().getSelectedItem());
				funcionarioDTO.setLogin(telaEdicaoFuncionario.getLogin().getText());
				funcionarioDTO.setSenha(new String(telaEdicaoFuncionario.getSenha().getPassword()));
				
				FuncionarioControl funcionarioControl = new FuncionarioControl();
				
				funcionarioControl.editarFuncionario(funcionarioDTO);

				JOptionPane.showMessageDialog(telaEdicaoFuncionario, "Funcionário Editado!");
				
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoFuncionarioLogado, identificacao);
				telaDeFuncionarios.setLocationRelativeTo(telaEdicaoFuncionario);
				telaEdicaoFuncionario.dispose();
								
				
			}else if(botao.equals("Cancelar")) {
//				CÓDIGO DUPLICADO?
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoFuncionarioLogado, identificacao);
				telaDeFuncionarios.setLocationRelativeTo(telaEdicaoFuncionario);
				telaEdicaoFuncionario.dispose();
								
			}
			
		}
		
	}
	
	
	
	
	
}
