package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.BandeiraControl;
import Controller.VendaControl;
import DTO.BandeiraDTO;
import DTO.PagamentoDTO;
import DTO.VendaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.GeradorDeRelatorios;
import View_Utilidades.Icones;
import View_Utilidades.TeclasPermitidas;

public class TelaDePagamentoCartao extends TelaPadrao{

	
	private int codigoDoAtendenteLogado;

	private JLabel labelValor;
	private JLabel labelValorCifrao;
	
	private JTextField fieldNumCartao;
	
	private JComboBox<Object> bandeiras;
	private JComboBox<Object> tipoCartao;
	private JComboBox<Integer> mes;
	private JComboBox<Object> ano;
	private JComboBox<Integer> parcelas;
	
	
	private VendaDTO vendaDTO;
	private PagamentoDTO pagamentoDTO;
	private TelaDeAtendimento telaAtendimentoAtual;
	
	private String identificacao;
	
	public TelaDePagamentoCartao(int codigoDoAtendenteLogado, VendaDTO vendaDTO, PagamentoDTO pagamentoDTO, TelaDeAtendimento telaAtendimentoAtual, String identificacao) {
		super("Pagamento");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(520, 500);
		
		this.codigoDoAtendenteLogado = codigoDoAtendenteLogado;
		this.vendaDTO = vendaDTO;
		this.pagamentoDTO = pagamentoDTO;
		this.telaAtendimentoAtual = telaAtendimentoAtual;
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		adicionarLabels();
	}
	
	
	@Override
	public void adicionarComponentesGraficos() {
		adicionarFields();
		adicionarBotoes();
		
	}

	
	
	private void adicionarLabels() {

				
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "CARTÃO", 110, 30, 450, 70);
		L.setFont(new Font("Times new Roman", Font.BOLD, 70));
		L.setForeground(Color.DARK_GRAY);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Titular", 20, 135, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Número", 20, 170, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Código de Segurança", 20, 205, 200, 25);
		AdicionadorDeComponentes.adicionarJLabel(this, "Validade", 20, 240, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Mês", 165, 240, 60, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Ano", 305, 240, 60, 20);
		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Parcelas", 20, 275, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Bandeira", 320, 170, 80, 20);
		labelValorCifrao = AdicionadorDeComponentes.adicionarJLabel(this, "Valor: 1x de R$", 20, 310, 150, 20);
		labelValor = AdicionadorDeComponentes.adicionarJLabel(this, Float.toString(vendaDTO.getPreco()), 160, 310, 200, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Tipo", 360, 205, 60, 20);		
	}


	private void adicionarFields() {

		JLabel label = new JLabel(Icones.FUNDO_PAGAMENTO);
		label.setBounds(0, 0, 800, 500);
		setContentPane(label);
		
		AdicionadorDeComponentes.adicionarJTextField(this, 100, 135, 405, 20);
		fieldNumCartao = AdicionadorDeComponentes.adicionarJTextField(this, 100, 170, 200, 20);
		fieldNumCartao.setDocument(new TeclasPermitidas());
		JTextField codSeg = AdicionadorDeComponentes.adicionarJTextField(this, 218, 208, 80, 20);
		codSeg.setDocument(new TeclasPermitidas());
		
	}

	
	
	private void adicionarBotoes() {
		
		  
//		PREENCHER ESSE ARRAY COM OS DADOS DO BANCO
		BandeiraControl bandeiraControl = new BandeiraControl();
	    ArrayList<BandeiraDTO> tiposDeBandeiras = bandeiraControl.getBandeiras().getBandeiras();
	    
	    ArrayList<String> tiposDeBandeirasString = new ArrayList<String>();
	    
	    for (BandeiraDTO bandeira : tiposDeBandeiras) {
	    	tiposDeBandeirasString.add(bandeira.getNomeBandeira());
		}
		bandeiras = new JComboBox<>(tiposDeBandeirasString.toArray());
		bandeiras.setBounds(405, 170, 100, 20);
		add(bandeiras);
			
		
		OuvinteBotoesTelaPagamentoCartao ouvinte = new OuvinteBotoesTelaPagamentoCartao(this);
//		**
		
		
	    ArrayList<String> tiposDeCartao = new ArrayList<String>();
	    tiposDeCartao.add("Crédito");
	    tiposDeCartao.add("Débito");
	    
	    tipoCartao = new JComboBox<>(tiposDeCartao.toArray());
	    tipoCartao.setBounds(405, 205, 100, 20);
	    add(tipoCartao);
		
	    tipoCartao.addActionListener(ouvinte);
	    
//		**
		Integer[] array_meses = {1,2,3,4,5,6,7,8,9,10,11,12};
		mes = new JComboBox<>(array_meses);
		mes.setBounds(105, 240, 50, 20);
		add(mes);
	    
	    
//		
////		**
//		
	    ArrayList<Integer> array_anos = new ArrayList<Integer>();

	    Calendar dataAtual = Calendar.getInstance();
	    int anoAtual = dataAtual.get(Calendar.YEAR);
	    
	    
	    for (int i = anoAtual; i <= anoAtual + 15; i++) {
			array_anos.add(i);
		}
	    
	    ano = new JComboBox<>(array_anos.toArray());
	    ano.setBounds(218, 240, 80, 20);
	    add(ano);
//		
		
//		**
		
		
		Integer[] array_parcelas = {1,2,3,4,5,6,7,8,9,10,11,12};
	    parcelas = new JComboBox<>(array_parcelas);
	    parcelas.setBounds(105, 275, 50, 20);
	    add(parcelas);
		parcelas.addActionListener(ouvinte);
		
	    
		JButton BotaoFinalizar = AdicionadorDeComponentes.adicionarJButton(this, "Finalizar", 140, 430, 105, 20);
		BotaoFinalizar.setIcon(Icones.ICONE_AVANCAR);
		BotaoFinalizar.addActionListener(ouvinte);
		
		JButton BotaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 290, 430, 100, 20);
		BotaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		BotaoVoltar.addActionListener(ouvinte);		
	}

	private class OuvinteBotoesTelaPagamentoCartao implements ActionListener{

		private TelaDePagamentoCartao telaDePagamentoCartao;
		
		public OuvinteBotoesTelaPagamentoCartao(TelaDePagamentoCartao telaDePagamentoCartao) {
			this.telaDePagamentoCartao = telaDePagamentoCartao;
		}



		@Override
		public void actionPerformed(ActionEvent e) {

			
			String botao = e.getActionCommand();
			
			if(botao.equals("Finalizar")) {
				
				pagamentoDTO.setTipo(1);
				pagamentoDTO.setNumCartao(fieldNumCartao.getText());
				
				BandeiraControl bandeiraControl = new BandeiraControl();
				
				BandeiraDTO bdto = new BandeiraDTO();
				bdto.setNomeBandeira((String) bandeiras.getSelectedItem());
				
				bdto = bandeiraControl.getIdBandeira(bdto);
				
				pagamentoDTO.setIdBandeira(bdto.getId());
				
				int qtdParcelas = 0;
				
				if(tipoCartao.getSelectedItem().equals("Crédito")) {
					qtdParcelas = (int) parcelas.getSelectedItem();
				}				

				pagamentoDTO.setQuantParcelas(qtdParcelas);
				
				pagamentoDTO.setTipoCartao((String)tipoCartao.getSelectedItem());
				
				VendaControl vendaControl = new VendaControl();
				vendaControl.efetuarVendaCartao(vendaDTO, pagamentoDTO);
				GeradorDeRelatorios geradorDeRelatorios = new GeradorDeRelatorios();
				geradorDeRelatorios.gerarNotaFiscal(vendaDTO, pagamentoDTO);
				
				JOptionPane.showMessageDialog(telaDePagamentoCartao, "Compra Finalizada com sucesso!", "Compra", JOptionPane.INFORMATION_MESSAGE);
				TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoAtendenteLogado, identificacao);
				telaDePagamentoCartao.dispose();
				telaAtendimentoAtual.dispose();
				
			}else if(botao.equals("Voltar")) {
				TelaDePagamento telaDePagamento = new TelaDePagamento(codigoDoAtendenteLogado, vendaDTO, telaAtendimentoAtual, identificacao);
				telaDePagamento.setLocationRelativeTo(telaDePagamentoCartao);
				telaDePagamentoCartao.dispose();
			}else {
//				testando eventos lançados pelo comboBox que contém o tipo de cartão
				
				if(tipoCartao.getSelectedItem().equals("Crédito")) {
//					crédito
//					parcelas.setEditable(true);
					parcelas.setEnabled(true);
					int qtdParcelas = (int) parcelas.getSelectedItem();
					
					labelValorCifrao.setText("Valor: "+qtdParcelas+"x de R$");
					
					float valorParcela = vendaDTO.getPreco() / qtdParcelas;
					
					labelValor.setText(Float.toString(valorParcela));
					labelValor.setBounds(160, 310, 200, 20);
				
					
				}else if(tipoCartao.getSelectedItem().equals("Débito")){
//					débito
//					parcelas.setEditable(false);
					parcelas.setEnabled(false);
					
					labelValorCifrao.setText("Valor: R$ ");
					labelValor.setText(Float.toString(vendaDTO.getPreco()));
					labelValor.setBounds(115, 310, 200, 20);
				}else {
//					lógica que será executada quando for clicado em alguma parcela
					
//					CÓDIGO DUPLICADO 
					int qtdParcelas = (int) parcelas.getSelectedItem();
					
					labelValorCifrao.setText("Valor: "+qtdParcelas+"x de R$");
					
					float valorParcela = vendaDTO.getPreco() / qtdParcelas;
					
					labelValor.setText(Float.toString(valorParcela));
					labelValor.setBounds(160, 310, 200, 20);
				
				}
				
			}
			
		}
		
	}

	

	
//	essa teala ainda vai ter o campo parcelas?sim, poe o valor da parcela uhm
	
//	public static void main(String[] args) {
//		new TelaDePagamentoCartao();
//	}
	
}
