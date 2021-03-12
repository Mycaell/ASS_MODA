package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeGerenciamento extends TelaComMenu{

	private int codigoDoFuncionario;
	private String identificacao;
	public TelaDeGerenciamento(int codigoDoFuncionario, String identificacao) {
		super("Gerência");

		this.codigoDoFuncionario = codigoDoFuncionario;
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		
	}

	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		adicionarBotoes();
	}

	

	private void adicionarLabels() {

//		ADICIONAR A IDENTIFICAÇÃO DO FUNCIONÁRIO LOGADO
		
		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 800, 400);
		setContentPane(label);
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "GERÊNCIA", 195, 10, 450, 100);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		
//
//		System.out.println("gerente: "+funcionarioDTO.getCodigo());
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
//		
	}

	
	private void adicionarBotoes() {		
		OuvinteTelaDeGerente ouvinteTelaDeGerente = new OuvinteTelaDeGerente(this);
		
		
		JPanel painel = new JPanel(new GridLayout(2, 4, 1, 1));
//		painel.setBorder(BorderFactory.createBevelBorder(2));
		
//		painel.setBackground(Color.white);
		
//		Color cor = new Color(#fff4e2);
		
		
		painel.setBackground(Color.decode("#fff4e2"));
		
//		JButton botaoClientes = AdicionadorDeComponentes.adicionarJButton(painel, "Clientes", 0, 0, 0, 0);
//		botaoClientes.setToolTipText(botaoClientes.getText());
//		botaoClientes.setIcon(Icones.ICONE_CLIENTES);
//		botaoClientes.addActionListener(ouvinteTelaDeGerente);
////		deixando o texto do botão na parte SUL
//		botaoClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
////		deixando o icon do botão no CENTRO
//		botaoClientes.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		JButton botaoFuncionarios = AdicionadorDeComponentes.adicionarJButton(painel, "Funcionarios", 0, 0, 0, 0);
		botaoFuncionarios.setToolTipText(botaoFuncionarios.getText());
		botaoFuncionarios.setIcon(Icones.ICONE_FUNCIONARIOS);
		botaoFuncionarios.addActionListener(ouvinteTelaDeGerente);
		botaoFuncionarios.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoFuncionarios.setHorizontalTextPosition(SwingConstants.CENTER);
//		botaoFuncionarios.setBorder(null);
//		
		JButton botaoContabilidade = AdicionadorDeComponentes.adicionarJButton(painel, "Caixa", 0, 0, 0, 0);
		botaoContabilidade.setToolTipText(botaoContabilidade.getText());
		botaoContabilidade.setIcon(Icones.ICONE_REAIS);
		botaoContabilidade.addActionListener(ouvinteTelaDeGerente);
		botaoContabilidade.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoContabilidade.setHorizontalTextPosition(SwingConstants.CENTER);
//		botaoContabilidade.setBorder(null);
		JButton botaoAtendimento = AdicionadorDeComponentes.adicionarJButton(painel, "Atendimento", 0, 0, 0, 0);
		botaoAtendimento.setToolTipText(botaoAtendimento.getText());
		botaoAtendimento.setIcon(Icones.ICONE_ATENDIMENTO);
		botaoAtendimento.addActionListener(ouvinteTelaDeGerente);
		botaoAtendimento.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoAtendimento.setHorizontalTextPosition(SwingConstants.CENTER);
//		botaoAtendimento.setBorder(null);
		JButton botaoIngredientes = AdicionadorDeComponentes.adicionarJButton(painel, "Peças", 0, 0, 0, 0);
		botaoIngredientes.setToolTipText(botaoIngredientes.getText());
		botaoIngredientes.setIcon(Icones.ICONE_PECAS2);
		botaoIngredientes.addActionListener(ouvinteTelaDeGerente);
		botaoIngredientes.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoIngredientes.setHorizontalTextPosition(SwingConstants.CENTER);
//		botaoIngredientes.setBorder(null);
//		JButton botaoPizzas = AdicionadorDeComponentes.adicionarJButton(painel, "Pizzas", 0, 0, 0, 0);
//		botaoPizzas.setToolTipText(botaoPizzas.getText());
//		botaoPizzas.setIcon(Icones.ICONE_PIZZA);
//		botaoPizzas.addActionListener(ouvinteTelaDeGerente);
//		botaoPizzas.setVerticalTextPosition(SwingConstants.BOTTOM);
//		botaoPizzas.setHorizontalTextPosition(SwingConstants.CENTER);
//		
		JButton botaoCartao = AdicionadorDeComponentes.adicionarJButton(painel, "Cartão", 0, 0, 0, 0);
		botaoCartao.setToolTipText(botaoCartao.getText());
		botaoCartao.setIcon(Icones.ICONE_CARTAO);
		botaoCartao.addActionListener(ouvinteTelaDeGerente);
		botaoCartao.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoCartao.setHorizontalTextPosition(SwingConstants.CENTER);
//		botaoCartao.setBorder(null);
//		JButton botaoEntrega = AdicionadorDeComponentes.adicionarJButton(painel, "Entrega", 0, 0, 0, 0);
//		botaoEntrega.setToolTipText(botaoEntrega.getText());
//		botaoEntrega.setIcon(Icones.ICONE_MOTOBOY);
//		botaoEntrega.addActionListener(ouvinteTelaDeGerente);
//		botaoEntrega.setVerticalTextPosition(SwingConstants.BOTTOM);
//		botaoEntrega.setHorizontalTextPosition(SwingConstants.CENTER);
//		
		painel.setBounds(130, 135, 528, 240);
		add(painel);
	}
 
		
	private class OuvinteTelaDeGerente implements ActionListener{

		private TelaDeGerenciamento janela;
		
		public OuvinteTelaDeGerente (TelaDeGerenciamento janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String botao = e.getActionCommand();
			
			if(botao.equals("Clientes")) {
//				TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoGerenteLogado);
//				telaDeClientes.setLocationRelativeTo(janela);
			}else if(botao.equals("Funcionarios")) {
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoFuncionario, identificacao);
				telaDeFuncionarios.setLocationRelativeTo(janela);
			}else if(botao.equals("Caixa")){
				TelaControleDeCaixa telaControleDeCaixa = new TelaControleDeCaixa(codigoDoFuncionario, identificacao);
				telaControleDeCaixa.setLocationRelativeTo(janela);
			}else if(botao.equals("Peças")){
				TelaDePecas telaDePecas = new TelaDePecas(codigoDoFuncionario, false, identificacao);
				telaDePecas.setLocationRelativeTo(janela);
			}else if(botao.equals("Atendimento")){
				TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoFuncionario, identificacao);
				telaDeAtendimento.setLocationRelativeTo(janela);
			}else if(botao.equals("Cartão")){
				TelaBandeira telaBandeira = new TelaBandeira(codigoDoFuncionario, identificacao);
				telaBandeira.setLocationRelativeTo(janela);
			}else if(botao.equals("Entrega")){
//				TelaDeMotoboy telaDeMotoboy = new TelaDeMotoboy(codigoDoGerenteLogado);
//				telaDeMotoboy.setLocationRelativeTo(janela);
			}else if(botao.equals("Ingredientes")) {
//				TelaDeIngredientes telaDeIngredientes = new TelaDeIngredientes(codigoDoGerenteLogado);
//				telaDeIngredientes.setLocationRelativeTo(janela);
			}
			
			janela.dispose();
		}
	}


//	public static void main(String[] args) {
//		new TelaDeGerenciamento(0);
//	}
	
	
	
}
