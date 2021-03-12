package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ProdutoControl;
import DTO.ProdutoDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.Iterator;
import View_Utilidades.IteratorProdutos;

public class TelaDePecas extends  TelaPadrao{

	private JTable tabela;
	private DefaultTableModel modelo;
	
	private int codigoDoGerenteLogado;
	
	
	private JTabbedPane aba;
	
	private JPanel painelPecas;
	
	
	private boolean vimDaTelaDeAtendimento;
	
	private String identificacao;
	
	public TelaDePecas(int codigoDoGerenteLogado, boolean vimDaTelaDeAtendimento, String identificacao) {
		super("Peças - ASS MODA");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.vimDaTelaDeAtendimento = vimDaTelaDeAtendimento;
		
		
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		adicionarBotoes();
	}

	
	@Override
	public void adicionarComponentesGraficos() {
		adicionarMenu(); 
		adicionarAba();
	}
	
	
	
	private void adicionarMenu() {
		
		OuvinteBotoesTelaDeProdutos ouvinte = new OuvinteBotoesTelaDeProdutos(this);

		JMenuBar barraDeMenu = new JMenuBar();

		JMenu opcoes = new JMenu();
		opcoes.setIcon(Icones.ICONE_ENGRENAGEM);
		barraDeMenu.add(opcoes);
		
		
		JMenuItem itemSair = new JMenuItem("Sair");
		itemSair.setIcon(Icones.ICONE_SAIR);
		itemSair.addActionListener(ouvinte);
		opcoes.add(itemSair);
		
		
		JMenuItem itemConfiguracoes = new JMenuItem("Configurações");
		itemConfiguracoes.setIcon(Icones.ICONE_ENGRENAGEM);
		itemConfiguracoes.addActionListener(ouvinte);
		opcoes.add(itemConfiguracoes);
		
		
		barraDeMenu.add(opcoes);
		
		this.setJMenuBar(barraDeMenu);
	}


	private void adicionarAba() {
		aba = new JTabbedPane();
		adicionarLabels();
		adicionarTabelaDePecas();
		
		
		
		aba.addTab("Dados das pizzas", painelPecas);
		aba.setBounds(80, 115, 625, 245);
		add(aba);
		
	}

	

	
	private void adicionarLabels() {
		
		
		painelPecas = new JPanel(null);
		painelPecas.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "PEÇAS", 265, 15, 450, 80);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
	}

	private void adicionarBotoes() {

		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//
//		GerenteController gerenteController = new GerenteController();
//		String cargo = gerenteController.recuperarCargo(funcionarioDTO).getCargo();
//
		
		OuvinteBotoesTelaDeProdutos ouvinte = new OuvinteBotoesTelaDeProdutos(this);
		
		JButton verIngredientes;
		
		if(vimDaTelaDeAtendimento) {
			verIngredientes = AdicionadorDeComponentes.adicionarJButton(painelPecas, "Ver Descrição", 230, 180, 200, 20);
		}else {
//			cargo = gerente
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelPecas, "Voltar", 0, 180, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(ouvinte);
			
			
			JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(painelPecas, "Editar", 170, 180, 90, 20);
			botaoEditar.setIcon(Icones.ICONE_EDICAO);
			botaoEditar.addActionListener(ouvinte);
			
			JButton adicionarSabor = AdicionadorDeComponentes.adicionarJButton(painelPecas, "Adicionar Peça", 475, 163, 145, 20);
			adicionarSabor.setIcon(Icones.ICONE_MAIS);
			adicionarSabor.addActionListener(ouvinte);
			
			JButton removerSabor = AdicionadorDeComponentes.adicionarJButton(painelPecas, "Remover Peça", 475, 188, 145, 20);
			removerSabor.setIcon(Icones.ICONE_MENOS);
			removerSabor.addActionListener(ouvinte);
			
			
			verIngredientes = AdicionadorDeComponentes.adicionarJButton(painelPecas, "Ver Descrição", 290, 180, 170, 20);
			
		}
		
		verIngredientes.setIcon(Icones.ICONE_OLHO_ABERTO);
		verIngredientes.addActionListener(ouvinte);
		
		
	}
	
	private void adicionarTabelaDePecas() {
		
		
		ProdutoControl produtoController = new ProdutoControl();
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		ArrayList<ProdutoDTO> produtos = produtoController.getProdutos().getProdutos();
		
		if(produtos.size() != 0) {
			
			modelo.addColumn("ID");
			modelo.addColumn("Nome");
			modelo.addColumn("Preço");
			modelo.addColumn("Categoria");
			modelo.addColumn("Quantidade");
			modelo.addColumn("Cor");
			modelo.addColumn("Tamanho");
			modelo.addColumn("Marca");
			modelo.addColumn("Descrição");
			
			
//			escondendo a coluda ID e modo de preparo
			tabela.getColumnModel().getColumn(0).setMinWidth(0);
			tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			tabela.getColumnModel().getColumn(8).setMinWidth(0);
			tabela.getColumnModel().getColumn(8).setMaxWidth(0);

			
			Iterator iterator = new IteratorProdutos(produtos);
			
			while(iterator.hasNext()){
				ProdutoDTO produto = (ProdutoDTO) iterator.next();
				Object[] linha = {produto.getId(), produto.getNome(), produto.getPreco(), produto.getCategoria(), produto.getQuantEstoque(), produto.getCor(), produto.getTamanho(), produto.getMarca(), produto.getDescricao()};
				modelo.addRow(linha);
			}
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há peças");
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 622, 160);
		painelPecas.add(scroll);

	}
	
	private class OuvinteBotoesTelaDeProdutos implements ActionListener{
		
		private TelaDePecas telaDeProdutos;

		public OuvinteBotoesTelaDeProdutos(TelaDePecas telaDeProdutos) {
			this.telaDeProdutos = telaDeProdutos;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			
			int linhaSelecionada = tabela.getSelectedRow();

//			FuncionarioControl funcionarioController = new FuncionarioControl();
			
			if(botao.equals("Editar")) {
				
					
				if(linhaSelecionada != -1) {
					
					Object obj = tabela.getValueAt(tabela.getSelectedRow(),0);
					int idProduto =Integer.parseInt(String.valueOf(obj));
					TelaEditarProduto janela = new TelaEditarProduto(codigoDoGerenteLogado, idProduto, identificacao);
					janela.setLocationRelativeTo(telaDeProdutos);
					telaDeProdutos.dispose();
					
				}else {
					JOptionPane.showMessageDialog(telaDeProdutos, "Selecione uma peça!", "Nenhuma peça selecionada", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Adicionar Peça")) {
				
				TelaCadastrarProduto telaCadastrarProduto = new TelaCadastrarProduto(codigoDoGerenteLogado, identificacao);
				telaCadastrarProduto.setLocationRelativeTo(telaDeProdutos);
				telaDeProdutos.dispose();
				
			}else if(botao.equals("Remover Peça")) {
				
				if(linhaSelecionada != -1) {
					int opcao = JOptionPane.showConfirmDialog(telaDeProdutos, "Você tem certeza que quer excluir essa peça?","Excluir Peça",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						int IdDaPecaSelecionada = (int) tabela.getValueAt(linhaSelecionada, 0);

						
						
						ProdutoDTO produtoDTO = new ProdutoDTO();
						produtoDTO.setId(IdDaPecaSelecionada);
						
						ProdutoControl produtoControl = new ProdutoControl();
						produtoControl.removerProduto(produtoDTO);

						JOptionPane.showMessageDialog(telaDeProdutos, "Peça removida!");
						
						modelo.removeRow(linhaSelecionada);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeProdutos, "Selecione uma peça!", "Nenhuma peça selecionada", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Ver Descrição")) {
				
				if(linhaSelecionada != -1) {
					String nomeDaPecaSelecionada = (String) tabela.getValueAt(linhaSelecionada, 8);
					
					TelaDeDescricaoDaPeca telaDeDescricaoDaPeca = new TelaDeDescricaoDaPeca(nomeDaPecaSelecionada);
					telaDeDescricaoDaPeca.setLocationRelativeTo(telaDeProdutos);
					
				}else {
					JOptionPane.showMessageDialog(telaDeProdutos, "Selecione uma peça!", "Nenhuma peça selecionada", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}else if(botao.equals("Voltar")) {
				TelaDeGerenciamento telaDeGerenciamento = new TelaDeGerenciamento(codigoDoGerenteLogado,identificacao);
				telaDeGerenciamento.setLocationRelativeTo(telaDeProdutos);
				telaDeProdutos.dispose();
				
			}else if(botao.equals("Configurações")) {
				TelaConfiguracoesProduto telaConfiguracoes = new TelaConfiguracoesProduto(codigoDoGerenteLogado, identificacao);
				telaConfiguracoes.setLocationRelativeTo(telaDeProdutos);
				telaDeProdutos.dispose();
			}
		}
	}
	
}
