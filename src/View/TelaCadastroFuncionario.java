package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.FuncionarioControl;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.OuvinteDoOlho;

public class TelaCadastroFuncionario extends TelaComMenu{

	private int codigoDoFuncionarioLogado;
	private String identificacao;
	private JLabel L;
	private JTextField nome;
	private JTextField sobrenome;
	private JFormattedTextField dataDeNascimento;
	
	private JTextField bairro;
	private JTextField rua;
	private JTextField numeroDaCasa;
	
	
	
	
	private JFormattedTextField telefone;
	private JFormattedTextField CPF;
	private JTextField login;
	private JPasswordField senha;
	private JPasswordField confirmacaoDeSenha;
	
	private JComboBox<Object> funcionarios;

	private JLabel olho;
	private JTextField senhaRevelada;
	
	private JRadioButton radioButtonF;
	private JRadioButton radioButtonM;
	
	
	private JLabel labelSenha;
	private JLabel labelConfimacaoSenha;
	
	
	
	private JButton botaoSalvar;
	private JButton botaoCancelar;
	
	
	
	
	
	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(JButton botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public JButton getBotaoCancelar() {
		return botaoCancelar;
	}

	public void setBotaoCancelar(JButton botaoCancelar) {
		this.botaoCancelar = botaoCancelar;
	}

	public JLabel getL() {
		return L;
	}

	public void setL(JLabel l) {
		L = l;
	}

	public JTextField getNome() {
		return nome;
	}

	public void setNome(JTextField nome) {
		this.nome = nome;
	}

	public JTextField getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(JTextField sobrenome) {
		this.sobrenome = sobrenome;
	}

	public JFormattedTextField getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(JFormattedTextField dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public JTextField getBairro() {
		return bairro;
	}

	public void setBairro(JTextField bairro) {
		this.bairro = bairro;
	}

	public JTextField getRua() {
		return rua;
	}

	public void setRua(JTextField rua) {
		this.rua = rua;
	}

	public JTextField getNumeroDaCasa() {
		return numeroDaCasa;
	}

	public void setNumeroDaCasa(JTextField numeroDaCasa) {
		this.numeroDaCasa = numeroDaCasa;
	}

	public JFormattedTextField getTelefone() {
		return telefone;
	}

	public void setTelefone(JFormattedTextField telefone) {
		this.telefone = telefone;
	}

	public JFormattedTextField getCPF() {
		return CPF;
	}

	public void setCPF(JFormattedTextField cPF) {
		CPF = cPF;
	}

	public JTextField getLogin() {
		return login;
	}

	public void setLogin(JTextField login) {
		this.login = login;
	}

	public JPasswordField getSenha() {
		return senha;
	}

	public void setSenha(JPasswordField senha) {
		this.senha = senha;
	}

	public JComboBox<Object> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(JComboBox<Object> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public JTextField getSenhaRevelada() {
		return senhaRevelada;
	}

	public void setSenhaRevelada(JTextField senhaRevelada) {
		this.senhaRevelada = senhaRevelada;
	}

	public JRadioButton getRadioButtonF() {
		return radioButtonF;
	}

	public void setRadioButtonF(JRadioButton radioButtonF) {
		this.radioButtonF = radioButtonF;
	}

	public JRadioButton getRadioButtonM() {
		return radioButtonM;
	}

	public void setRadioButtonM(JRadioButton radioButtonM) {
		this.radioButtonM = radioButtonM;
	}

	public TelaCadastroFuncionario(int codigoDoFuncionarioLogado, String identificacao) {
		super("Cadastramento de Funcionário");
		
		this.codigoDoFuncionarioLogado = codigoDoFuncionarioLogado;
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		
	}

	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		adicionarFields();
		adicionarBotoes();
	}

	

	private void adicionarLabels() {
	
//		ADICIONAR A IDENTIFICAÇÃO DO FUNCIONÁRIO LOGADO
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
//	

		
		L = AdicionadorDeComponentes.adicionarJLabel(this, "CADASTRO", 185, 15, 450, 80);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
//		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 110, 135, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome", 440, 135, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sexo", 110, 165, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Data de Nascimento", 370, 165, 180, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone", 110, 195, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 500, 195, 75, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Bairro", 110, 225, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Nº da casa", 450, 225, 95, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Rua", 110, 255, 41, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Login", 110, 282, 55, 25);
		labelSenha = AdicionadorDeComponentes.adicionarJLabel(this, "Senha", 110, 312, 55, 20);
		labelConfimacaoSenha= AdicionadorDeComponentes.adicionarJLabel(this, "Confirme a Senha", 110, 342, 160, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Cargo", 490, 255, 55, 23);
		
		
	}

	
	private void adicionarFields() {
		nome = AdicionadorDeComponentes.adicionarJTextField(this, 170, 135, 110, 20);
		sobrenome = AdicionadorDeComponentes.adicionarJTextField(this, 550, 135, 115, 20);
		
		bairro = AdicionadorDeComponentes.adicionarJTextField(this, 170, 225, 220, 20);
		rua = AdicionadorDeComponentes.adicionarJTextField(this, 170, 255, 220, 20);
		numeroDaCasa = AdicionadorDeComponentes.adicionarJTextField(this, 550, 225, 50, 20);		
		
		login = AdicionadorDeComponentes.adicionarJPasswordField(this, 170, 285, 110, 20);
		confirmacaoDeSenha = AdicionadorDeComponentes.adicionarJPasswordField(this, 280	, 345, 110, 20);

		try {
			dataDeNascimento = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "##/##/####", 550, 165, 115, 20);
			CPF = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 550, 195, 115, 20);
			telefone = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "(##) ####-####", 200, 195, 110, 20);
		}catch (ParseException e) {

		}
		
	}



	private void adicionarBotoes() {
		
		OuvinteBotesTelaCadastroFuncionario ouvinte = new OuvinteBotesTelaCadastroFuncionario(this);
		
		botaoSalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 300, 380, 90, 20);
		botaoSalvar.setIcon(Icones.ICONE_SALVAR);
		botaoSalvar.addActionListener(ouvinte);
		
		botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 400, 380, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(ouvinte);
		
		radioButtonF = new JRadioButton("F");
		radioButtonF.setBounds(170, 165, 40, 20);
		radioButtonM = new JRadioButton("M");
		radioButtonM.setBounds(210, 165, 45, 20);

	    ButtonGroup grupoRadioSexo = new ButtonGroup();
	    grupoRadioSexo.add(radioButtonF);
	    grupoRadioSexo.add(radioButtonM);
	    
	    radioButtonF.setSelected(true);
	    
	    radioButtonF.setBackground(null);
	    radioButtonM.setBackground(null);
	    
	    add(radioButtonF);
	    add(radioButtonM);
	    
	    ImageIcon imgOlho = Icones.ICONE_OLHO_FECHADO;
	    olho = new JLabel(imgOlho);
	    olho.setToolTipText("revelar senha");
	    olho.setBounds(285, 315, 30, 20);
	    add(olho);
	    
	    senha = AdicionadorDeComponentes.adicionarJPasswordField(this, 170, 315, 110, 20);
	    senhaRevelada = AdicionadorDeComponentes.adicionarJTextField(this, 170, 315,110, 20);
	    senhaRevelada.setVisible(false);
	    olho.addMouseListener(new OuvinteDoOlho(this,senha,senhaRevelada,olho));
	    
	    ArrayList<String> cargos = new ArrayList<String>();
	    cargos.add("Atendente");
	    cargos.add("Gerente");

	    
	    funcionarios = new JComboBox<>(cargos.toArray());
	    funcionarios.setBounds(550, 255, 115, 20);
	    add(funcionarios);
	}
	

	private class OuvinteBotesTelaCadastroFuncionario implements ActionListener{

		private TelaCadastroFuncionario telaCadastroFuncionario;
		
		
		
		public OuvinteBotesTelaCadastroFuncionario(TelaCadastroFuncionario telaCadastroFuncionario) {
			this.telaCadastroFuncionario = telaCadastroFuncionario;
		}


		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Salvar")) {
				
				char sexo;
				
				if(radioButtonF.isSelected()) {
					sexo = 'F';
				}else {
					sexo = 'M';
				}
				
				
				Date dataDeNasc = null;
				try {
					SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
					dataDeNasc = formataData.parse(dataDeNascimento.getText());
				}catch (ParseException er) {
				}
				
				FuncionarioDTO FuncionarioDTO = new FuncionarioDTO(nome.getText(), sobrenome.getText(), sexo, CPF.getText(), telefone.getText(), login.getText(), new String(senha.getPassword()), (String) funcionarios.getSelectedItem(),  bairro.getText(), rua.getText(), Integer.parseInt(numeroDaCasa.getText()), dataDeNasc);
				
				FuncionarioControl funcionarioController = new FuncionarioControl();
				funcionarioController.adicionarFuncionario(FuncionarioDTO);;

				JOptionPane.showMessageDialog(telaCadastroFuncionario, "Funcionário Cadastrado!");
				
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoFuncionarioLogado, identificacao);
				telaDeFuncionarios.setLocationRelativeTo(telaCadastroFuncionario);
				telaCadastroFuncionario.dispose();
								
				
			}else if(botao.equals("Cancelar")) {
//				CÓDIGO DUPLICADO?
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoFuncionarioLogado, identificacao);
				telaDeFuncionarios.setLocationRelativeTo(telaCadastroFuncionario);
				telaCadastroFuncionario.dispose();
								
			}
			
		}
		
	}
	

	
//	public static void main(String[] args) {
//		new TelaCadastroFuncionario(0);
//	}
	
	
}
