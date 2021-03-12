package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controller.ClienteControl;
import Controller.VendaControl;
import DTO.ClienteDTO;
import DTO.PagamentoDTO;
import DTO.VendaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.GeradorDeRelatorios;
import View_Utilidades.Icones;


public class TelaDePagamento extends TelaPadrao{

	private int codigoDoAtendenteLogado;
	private JComboBox<Object> formaDePagamento;
	private VendaDTO vendaDTO;
	private TelaDeAtendimento telaAtendimentoAtual;
	
	private String identificacao;
	public TelaDePagamento(int codigoDoAtendenteLogado, VendaDTO vendaDTO, TelaDeAtendimento telaAtendimentoAtual, String identificacao) {
		super("Pagamento");
			
		this.setSize(525, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.codigoDoAtendenteLogado = codigoDoAtendenteLogado;
		this.vendaDTO = vendaDTO;
		this.telaAtendimentoAtual = telaAtendimentoAtual;
		adicionarLabels();
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
 	}





	@Override
	public void adicionarComponentesGraficos() {
		adicionarFields();
		adicionarBotoes();
	}
	
	


	private void adicionarLabels() {
		
		
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "PAGAMENTO", 30, 30, 500, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 70));
		L.setForeground(Color.DARK_GRAY);
		
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCpf(vendaDTO.getCpfCliente());
		
		ClienteControl clienteControl = new ClienteControl();
		
		clienteDTO = clienteControl.getClientePorCPF(clienteDTO);
		
		vendaDTO.setIdCliente(clienteDTO.getId());
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome: "+clienteDTO.getNome(), 20, 135, 400, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Sobrenome: "+clienteDTO.getSobrenome(), 20, 170, 400, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF: "+vendaDTO.getCpfCliente(), 20, 205, 400, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Telefone: "+clienteDTO.getTelefone(), 20, 240, 400, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Data: ", 20, 275, 80, 20);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Hora: ", 280, 275, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Valor: "+vendaDTO.getPreco(), 20, 310, 400, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Forma:", 20, 345, 65, 20);
		
	
		
		Date dataAtual = Calendar.getInstance().getTime();
		SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:MM");
		SimpleDateFormat sdf_data = new SimpleDateFormat("dd/MM/yyyy");
		
		String data = sdf_data.format(dataAtual);
		
		String hora = sdf_hora.format(dataAtual);
	
		AdicionadorDeComponentes.adicionarJLabel(this, data, 75, 275, 100, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, hora, 335, 275, 100, 20);
		
		
	}

	private void adicionarFields() {
		
	}

	private void adicionarBotoes() {
		
		JLabel label = new JLabel(Icones.FUNDO_PAGAMENTO);
		label.setBounds(0, 0, 800, 500);
		setContentPane(label);
		
		
//		Preencher a partir do BANCO?
		String[] tiposDePagamentos = {"À vista", "Cartão"};
		formaDePagamento = new JComboBox<>(tiposDePagamentos);
		formaDePagamento.setBounds(95, 345, 80, 20);
		add(formaDePagamento);
	    
		
		OuvinteBotoesTelaDePagamento ouvinte = new OuvinteBotoesTelaDePagamento(this);
		
		JButton BotaoContinuar = AdicionadorDeComponentes.adicionarJButton(this, "Avançar", 140, 430, 105, 20);
		BotaoContinuar.setIcon(Icones.ICONE_AVANCAR);
		BotaoContinuar.addActionListener(ouvinte);
		
		JButton BotaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 290, 430, 105, 20);
		BotaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		BotaoCancelar.addActionListener(ouvinte);		
	}

	private class OuvinteBotoesTelaDePagamento implements ActionListener{

		
		private TelaDePagamento telaDePagamento;
		
		public OuvinteBotoesTelaDePagamento(TelaDePagamento telaDePagamento) {
			this.telaDePagamento = telaDePagamento;
		}



		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			PagamentoDTO pdto = new PagamentoDTO();
			
			Date dataAtual = Calendar.getInstance().getTime();
			
			java.sql.Date dataPagamento = new java.sql.Date(dataAtual.getTime());
			
//			Date dataPagamento = new Date(dataAtual.getTime());
			Time horaPagamento = new Time(dataAtual.getTime());
			
			
			Timestamp criacao = new Timestamp(dataAtual.getTime());
			vendaDTO.setDataCriacao(criacao);
			
			pdto.setTipo(0);
			pdto.setDataPagamento(dataPagamento);
			pdto.setHoraPagamento(horaPagamento);
			
			VendaControl vendaControl = new VendaControl();
			
			if(botao.equals("Avançar")) {
				if(formaDePagamento.getSelectedItem().equals("Cartão")) {
					TelaDePagamentoCartao telaDePagamentoCartao = new TelaDePagamentoCartao(codigoDoAtendenteLogado, vendaDTO, pdto, telaAtendimentoAtual, identificacao);
					telaDePagamentoCartao.setLocationRelativeTo(telaDePagamento);
					telaDePagamento.dispose();
					
				}else {
					vendaControl.efetuarVendaAVista(vendaDTO, pdto);
					GeradorDeRelatorios geradorDeRelatorios = new GeradorDeRelatorios();
					geradorDeRelatorios.gerarNotaFiscal(vendaDTO, pdto);
					
					JOptionPane.showMessageDialog(telaDePagamento, "Compra Finalizada com sucesso!", "Compra", JOptionPane.INFORMATION_MESSAGE);
					TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoAtendenteLogado, identificacao);
					telaDePagamento.dispose();
					telaAtendimentoAtual.dispose();
				}
				
			}else if(botao.equals("Cancelar")) {
				telaDePagamento.dispose();
			}
			
		}
		
	}
	
	

//	public static void main(String[] args) {
//		new TelaDePagamento(0, "s");
//	}
//	
//	
}
