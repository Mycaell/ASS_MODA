package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.ClienteControl;
import DTO.ClienteDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaEdicaoCliente extends TelaCadastroCliente {
	
	private int codigoDoFuncionarioLogado;
	private int idCliente;
	private int idEndereco;
	
	private String identificacao;
	public TelaEdicaoCliente(int codigoDoFuncionarioLogado, JTextField campoCPFDaTelaDeAtendimento, int idCliente, int idEndereco, String identificacao) {
		super(codigoDoFuncionarioLogado, campoCPFDaTelaDeAtendimento, identificacao);

		this.codigoDoFuncionarioLogado = codigoDoFuncionarioLogado;
		this.idCliente = idCliente;
		this.idEndereco = idEndereco;
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		preencherDados();
	}

	@Override
	public void adicionarComponentesGraficos() {
		super.adicionarComponentesGraficos();

		this.setTitle("Edição dos Dados do CLiente");
		
		removerComponentes();
		adicionarComponentes();
	}

	private void preencherDados() {
		ClienteControl clienteControl = new ClienteControl();
		
		
//		amoori continua ta bem quando a gente acabar vm descancar pq to cm dor de cabeca ta bem, quer ir tomar algum rmeedio ja n?n dps eu tomo se n passar ta bem
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(idCliente);
		clienteDTO.setIdEndereco(idEndereco);
		
		clienteDTO = clienteControl.getCliente(clienteDTO);
//		System.out.println(idCliente);
//		System.out.println(clienteDTO.getNome());

		JLabel L = super.getL();
		L.setText("EDIÇÃO");
		L.setBounds(245, 25, 450, 80);
		super.getNome().setText(clienteDTO.getNome());
		super.getSobrenome().setText(clienteDTO.getSobrenome());
		
		char sexo = clienteDTO.getSexo();
		if(sexo == 'F') {
			super.getRadioButtonF().setSelected(true);
		}else {
			super.getRadioButtonM().setSelected(true);;
		}
		
		
//		poe na tela e um erro na data parece na tela do java q tela q lista os clientes
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formataData.format(clienteDTO.getDataNascimento());
		
		super.getDataDeNascimento().setText(dataFormatada);
		super.getTelefone().setText(clienteDTO.getTelefone());
		super.getCPF().setText(clienteDTO.getCpf());
		super.getBairro().setText(clienteDTO.getBairro());
		super.getRua().setText(clienteDTO.getRua());
		super.getNumDaCasa().setText(Integer.toString(clienteDTO.getnCasa()));
		
	}

	private void adicionarComponentes() {
		OuvinteBotesTelaEdicaoCliente ouvinte = new OuvinteBotesTelaEdicaoCliente(this);
		
		JButton botaoSalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 300, 380, 90, 20);
		botaoSalvar.setIcon(Icones.ICONE_SALVAR);
		botaoSalvar.addActionListener(ouvinte);
		
		JButton botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 400, 380, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(ouvinte);
				
	}

	private void removerComponentes() {
		remove(super.getBTsalvar());		
	}
	
	
	private class OuvinteBotesTelaEdicaoCliente implements ActionListener{

		private TelaEdicaoCliente telaEdicaoCliente;
		
		
		
		public OuvinteBotesTelaEdicaoCliente(TelaEdicaoCliente telaEdicaoCliente) {
			this.telaEdicaoCliente = telaEdicaoCliente;
		}


		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("Salvar")) {
				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO.setId(idCliente);
				clienteDTO.setIdEndereco(idEndereco);
				
				clienteDTO.setNome(telaEdicaoCliente.getNome().getText());
				clienteDTO.setSobrenome(telaEdicaoCliente.getSobrenome().getText());
				
				
				if(telaEdicaoCliente.getRadioButtonF().isSelected()) {
					clienteDTO.setSexo('F');
				}else {
					clienteDTO.setSexo('M');
				}
				
				SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
				Date dataNasc = null;
				try {
					dataNasc = formataData.parse(telaEdicaoCliente.getDataDeNascimento().getText());
				} catch (ParseException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
				
				clienteDTO.setDataNascimento(dataNasc);
				clienteDTO.setTelefone(telaEdicaoCliente.getTelefone().getText());
				clienteDTO.setCpf(telaEdicaoCliente.getCPF().getText());
				clienteDTO.setBairro(telaEdicaoCliente.getBairro().getText());
				clienteDTO.setRua(telaEdicaoCliente.getRua().getText());
				clienteDTO.setnCasa(Integer.parseInt(telaEdicaoCliente.getNumDaCasa().getText()));
				
				
				ClienteControl clienteControl = new ClienteControl();
				clienteControl.editarCliente(clienteDTO);

				JOptionPane.showMessageDialog(telaEdicaoCliente, "Cliente Editado!");
				
				TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoFuncionarioLogado, identificacao);
				telaDeClientes.setLocationRelativeTo(telaEdicaoCliente);
				telaEdicaoCliente.dispose();
								
				
			}else if(botao.equals("Cancelar")) {
//				CÓDIGO DUPLICADO?
				TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoFuncionarioLogado, identificacao);
				telaDeClientes.setLocationRelativeTo(telaEdicaoCliente);
				telaEdicaoCliente.dispose();
								
			}
			
		}
		
	}
	
}
