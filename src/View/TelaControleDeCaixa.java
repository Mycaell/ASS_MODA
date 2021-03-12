package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.CaixaControl;
import DTO.ProdutoDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.GeradorDeRelatorios;
import View_Utilidades.Icones;
import View_Utilidades.Iterator;
import View_Utilidades.IteratorProdutos;

public class TelaControleDeCaixa extends TelaPadrao{

	private DefaultTableModel modelo;
	private JTable tabela;
	private int codigoDoFuncionarioLogado;
	private JTabbedPane aba;
	private JPanel painel;
	private JButton botaoVoltar;
	private JButton botaoRelatorios;
	
	
	private String identificacao;
	public TelaControleDeCaixa(int codigoDoFuncionarioLogado, String identificacao) {
		super("Caixa");		
		this.codigoDoFuncionarioLogado = codigoDoFuncionarioLogado;
		aba = new JTabbedPane();

		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		adicionarAba();
	}

	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		adicionarTabela();
		adicionarBotoes();		
	}

	private void adicionarLabels() {
		CaixaControl caixaControl = new CaixaControl();
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "CAIXA", 270, 35, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
		float lucroDia = caixaControl.lucroDiario().getLucroDiario();
		float lucroMes = caixaControl.lucroMensal().getLucroMensal();
		float valorCaixa = caixaControl.valorDeCaixa().getValorDeCaixa();
		
		AdicionadorDeComponentes.adicionarJLabel(this, "LUCRO DO DIA: R$"+Float.toString(lucroDia), 100, 280, 450, 60);	
		AdicionadorDeComponentes.adicionarJLabel(this, "LUCRO DO MÊS: R$"+Float.toString(lucroMes), 100, 320, 450, 60);		
		AdicionadorDeComponentes.adicionarJLabel(this, "VALOR DE CAIXA: R$"+Float.toString(valorCaixa), 400, 280, 450, 60);
	}

	private void adicionarTabela() {
		CaixaControl caixaControl = new CaixaControl();
		painel = new JPanel(null);		
		painel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		modelo = new DefaultTableModel();		
		tabela = new JTable(modelo);
			
//		***
//		utilizando ITERATOR
//		***
		ArrayList<ProdutoDTO> produtos = caixaControl.getProdutoMaisVendidos().getProdutos();
		
		if(produtos.size() != 0) {
			
			modelo.addColumn("Nome");
			modelo.addColumn("Marca");
			modelo.addColumn("Cor");
			modelo.addColumn("Categoria");
			
			Iterator iterator = new IteratorProdutos(produtos);
			
			while(iterator.hasNext()){
				ProdutoDTO produto = (ProdutoDTO) iterator.next();
				Object[] linha = {produto.getNome(), produto.getMarca(), produto.getCor(), produto.getCategoria()};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há produtos vendidos");
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 633, 140);
		painel.add(scroll);
		
	}
	
	private void adicionarAba() {
		aba = new JTabbedPane();

		adicionarTabela();			
		aba.addTab("Produtos Mais Vendidos", painel);
		aba.setBounds(70, 100, 635, 173);
		add(aba);
		
	}
	
	private void adicionarBotoes() {
		botaoRelatorios = AdicionadorDeComponentes.adicionarJButton(this, "Relatórios", 290, 385, 100, 20);
//		botaoRelatorios.setIcon(Icones.ICONE_VOLTAR);
		botaoRelatorios.addActionListener(new Ouvinte(this));
		
		botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 400, 385, 105, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new Ouvinte(this));

	}
	
	private class Ouvinte implements ActionListener{

		private TelaControleDeCaixa janela;
		
		public Ouvinte(TelaControleDeCaixa janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == botaoVoltar) {
				TelaDeGerenciamento tela = new TelaDeGerenciamento(codigoDoFuncionarioLogado, identificacao);
				tela.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(e.getSource() == botaoRelatorios) {
				String[] relatorios = {"Lucro do Dia", "Lucro do Mês", "Valor de Caixa"};
				Object resultado = JOptionPane.showInputDialog(janela, "Escolha um Relatório", "Relatórios", JOptionPane.QUESTION_MESSAGE, null, relatorios, relatorios[0]);
				
				String nomepdf = null;
				String titulo = null;
				String texto = null;
				
				CaixaControl caixaControl = new CaixaControl();
				
				if(resultado == relatorios[0]) {
					float lucroDia = caixaControl.lucroDiario().getLucroDiario();
					nomepdf = "relatorio_do_dia";
					titulo = "Relatório do Dia";
					texto = "O lucro do dia é no valor de R$ "+ Float.toString(lucroDia);
				}else if(resultado == relatorios[1]) {
					float lucroMes = caixaControl.lucroMensal().getLucroMensal();
					Date data =  new Date();
					Locale local = new Locale("pt","BR");
					DateFormat dateFormat = new SimpleDateFormat("MMMM",local); 				
					nomepdf = "relatorio_do_mes";
					titulo = "Relatório do Mês";
					texto = "O lucro de "+dateFormat.format(data)+", é no valor de R$ "+Float.toString(lucroMes);
				}else if(resultado == relatorios[2]) {
					float valorCaixa = caixaControl.valorDeCaixa().getValorDeCaixa();
					nomepdf = "valor_de_caixa";
					titulo = "Valor de Caixa";
					texto = "O Valor do caixa é de R$ "+Float.toString(valorCaixa);
				}
				
				if(resultado != null) {
					new GeradorDeRelatorios().gerarRelatorios(nomepdf, titulo, texto);
					JOptionPane.showMessageDialog(janela, "Relatório gerado", null, JOptionPane.QUESTION_MESSAGE);
				}
			}
			
		}
		
	}
//	public static void main(String[] args) {
//		new TelaControleDeCaixa(1);
//	}
}
