package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.ClienteControl;
import DTO.ClienteDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaCadastroCliente extends TelaPadrao{
	
	private int codigoDoFuncionarioLogado;
	private String identificacao;
	private JTextField nome;
	private JTextField sobrenome;
	private JFormattedTextField telefone;
	private JFormattedTextField CPF;
	private JFormattedTextField dataDeNascimento;
	private JTextField bairro;
	private JTextField rua;
	private JTextField numDaCasa;
	
	private JTextField campoCPFDaTelaDeAtendimento;
	
	private JRadioButton radioButtonF;
	private JRadioButton radioButtonM;
	

	private JButton BTsalvar;
	private JLabel L; 
	
	
	public JLabel getL() {
		return L;
	}


	public void setL(JLabel l) {
		L = l;
	}


	public JButton getBTsalvar() {
		return BTsalvar;
	}


	public void setBTsalvar(JButton bTsalvar) {
		BTsalvar = bTsalvar;
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


	public JTextField getNumDaCasa() {
		return numDaCasa;
	}


	public void setNumDaCasa(JTextField numDaCasa) {
		this.numDaCasa = numDaCasa;
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


	public TelaCadastroCliente(int codigoDoFuncionarioLogado, JTextField campoCPFDaTelaDeAtendimento, String identificacao) {
		super("Cadastramento de Cliente");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.codigoDoFuncionarioLogado = codigoDoFuncionarioLogado;
		this.campoCPFDaTelaDeAtendimento = campoCPFDaTelaDeAtendimento;
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
	
		
//		adicionar a identificação do funcionário que está logado
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
//	

		L = AdicionadorDeComponentes.adicionarJLabel(this, "CADASTRO", 185, 25, 450, 80);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
		
//		110, 180, 80, 20
		
//		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 110, 135, 55, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome", 440, 135, 105, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone", 110, 180, 80, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 505, 225, 75, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Data de Nasc", 430, 180, 145, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Bairro", 110, 225, 70, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Rua", 110, 270, 41, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Nº da casa", 450, 270, 95, 20);

	
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 110, 135, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome", 440, 135, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sexo", 110, 180, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone", 110, 225, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 505, 225, 75, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Data de Nasc", 430, 180, 145, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Bairro", 110, 270, 70, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Rua", 110, 315, 41, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Nº da casa", 450, 270, 95, 20);
		
	}

	
	private void adicionarFields() {
		nome = AdicionadorDeComponentes.adicionarJTextField(this, 170, 135, 210, 20);
		sobrenome = AdicionadorDeComponentes.adicionarJTextField(this, 550, 135, 200, 20);
		
		try {
			telefone = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "(##) ####-####", 193, 225, 90, 20);
			CPF = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 550, 225, 100, 20);
			dataDeNascimento = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "##/##/####", 550, 180, 100, 20);
		}catch (ParseException e) {
		}
		
		
		bairro = AdicionadorDeComponentes.adicionarJTextField(this, 170, 270, 210, 20);
		rua = AdicionadorDeComponentes.adicionarJTextField(this, 170, 315, 210, 20);
		numDaCasa = AdicionadorDeComponentes.adicionarJTextField(this, 550, 270, 50, 20);
	}
	
	
	private void adicionarBotoes() {
		
//		OuvinteTelaDeCadastroDeCliente ouvinte = new OuvinteTelaDeCadastroDeCliente(this);
//		JButton BTvoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 110, 350, 90, 20);
//		BTvoltar.setIcon(Icones.ICONE_VOLTAR);
//		BTvoltar.addActionListener(ouvinte);
		
		
		radioButtonF = new JRadioButton("F");
		radioButtonF.setBounds(170, 180, 40, 20);
		radioButtonM = new JRadioButton("M");
		radioButtonM.setBounds(210, 180, 45, 20);

	    ButtonGroup grupoRadioSexo = new ButtonGroup();
	    grupoRadioSexo.add(radioButtonF);
	    grupoRadioSexo.add(radioButtonM);
	    
	    radioButtonF.setSelected(true);
	    
	    radioButtonF.setBackground(null);
	    radioButtonM.setBackground(null);
	    
	    add(radioButtonF);
	    add(radioButtonM);
		

	    OuvinteBotoesTelaCadastroCliente ouvinte = new OuvinteBotoesTelaCadastroCliente(this);
	    
		JButton BTlimparCampos = AdicionadorDeComponentes.adicionarJButton(this, "Limpar Campos", 110, 380, 145, 20);
		BTlimparCampos.setIcon(Icones.ICONE_VASSOURA);
		BTlimparCampos.addActionListener(ouvinte);
		
		BTsalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 560, 380, 90, 20);
		BTsalvar.setIcon(Icones.ICONE_SALVAR);
		BTsalvar.addActionListener(ouvinte);
	}

	
	private class OuvinteBotoesTelaCadastroCliente implements ActionListener{

		private TelaCadastroCliente telaCadastroCliente;
		
		public OuvinteBotoesTelaCadastroCliente(TelaCadastroCliente telaCadastroCliente) {
			this.telaCadastroCliente = telaCadastroCliente;
		}


		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Salvar")) {
				
				
				ClienteControl clienteController = new ClienteControl();

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
				
//				ClienteDTO clienteDTO = new ClienteDTO(nome.getText(), sobrenome.getText(), sexo, CPF.getText(), telefone.getText(), rua.getText(), bairro.getText(),Integer.parseInt(numDaCasa.getText()), dataDeNascimento.getText(),codigoDoFuncionarioLogado);
				
				ClienteDTO clienteDTO = new ClienteDTO(nome.getText(), sobrenome.getText(), sexo, CPF.getText(), telefone.getText(), rua.getText(), bairro.getText(), Integer.parseInt(numDaCasa.getText()), dataDeNasc, codigoDoFuncionarioLogado);
				
				clienteController.adicionarCliente(clienteDTO);

				JOptionPane.showMessageDialog(telaCadastroCliente, "Cliente Cadastrado!");
				
				
				campoCPFDaTelaDeAtendimento.setText(CPF.getText());
				telaCadastroCliente.dispose();
			}else if (botao.equals("Limpar Campos")) {
				nome.setText("");
				sobrenome.setText("");
				dataDeNascimento.setText("");
				telefone.setText("");
				CPF.setText("");
				bairro.setText("");
				rua.setText("");
				numDaCasa.setText("");
			}
			
		}
		
	}
	
	
//	public static void main(String[] args) {
//		new TelaCadastroCliente(0);
//		
//		
//	}
	
	
	
	
	
	
	
	
	
	
}
