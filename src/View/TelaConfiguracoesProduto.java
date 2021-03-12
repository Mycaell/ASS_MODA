 package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controller.ProdutoControl;
import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.TamanhoDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaConfiguracoesProduto extends TelaComMenu{
	
	private int codigoDoFuncionarioLogado;
	private DefaultTableModel modeloCores;
	private JTable tabelaCores;
	private DefaultTableModel modeloTamanhos;
	private JTable tabelaTamanhos;
	private DefaultTableModel modeloCategorias;
	private JTable tabelCategorias;
	private JButton botaoAddCor;
	private JButton botaoAddCategoria;
	private JButton botaoAddTamanho;
	private JButton botaoRemoverCor;
	private JButton botaoRemoverCategoria;
	private JButton botaoRemoverTamanho;
	private JButton botaoCancelar;

	private String identificacao;
	public TelaConfiguracoesProduto(int codigoDoFuncionarioLogado, String identificacao) {
		super("Configurações de Produto");
		this.codigoDoFuncionarioLogado = codigoDoFuncionarioLogado;
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
	}

	@Override
	public void adicionarComponentesGraficos() {
		adicionarBotoes();
		adicionarTabelas();
		adicionarLabels();
	}

	private void adicionarTabelas() {		
		ProdutoControl produtoControl = new ProdutoControl();
		
		modeloCores = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		modeloCores.addColumn("Cor");
//		modeloCores.addColumn("Id");		
		tabelaCores = new JTable(modeloCores);
		JTableHeader cabecalho1 = tabelaCores.getTableHeader();
		cabecalho1.setFont(new Font("Comic Sans", Font.BOLD, 18));
		cabecalho1.setForeground(Color.BLACK);
		for (CorDTO c: produtoControl.getCores().getCores()) {
			Object[] linha = new Object[]{
					c.getNomeCor(),
//					c.getId()
			};
			modeloCores.addRow(linha);
		}																				
		try {
			tabelaCores.addRowSelectionInterval(0, 0);
		}catch(Exception e) {
//			modelo1.addColumn("Não há Cores");
			
//			modeloCores.addRow(new Object[] {"Não há Cores"});
		
//			vai tirar?sim e besteira isso deix sem ndmsm ta mas dx comentado ai
		}
		
//		tabelaCores.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//		tabelaCores.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
//		tabelaCores.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//		tabelaCores.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 );  

		JScrollPane scroll1 = new JScrollPane(tabelaCores);
		scroll1.setBounds(20, 135, 200, 150);
		add(scroll1);
				
				
		modeloTamanhos = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		modeloTamanhos.addColumn("Tamanho");
//		modeloTamanhos.addColumn("Id");
		tabelaTamanhos = new JTable(modeloTamanhos); 
		JTableHeader cabecalho2 = tabelaTamanhos.getTableHeader();
		cabecalho2.setFont(new Font("Comic Sans", Font.BOLD, 18));
		cabecalho2.setForeground(Color.BLACK);
		for (TamanhoDTO t: produtoControl.getTamanhos().getTamanhos()) {
			Object[] linha = new Object[]{
					t.getTamanho(),
//					t.getId()
			};
			modeloTamanhos.addRow(linha);
		}																				
		try {
			tabelaTamanhos.addRowSelectionInterval(0, 0);
		}catch(Exception e) {
//			modelo2.addColumn("Não há Tamanhos");
//			modeloTamanhos.addRow(new Object[] {"Não há Tamanhos"});
			
		}
//		tabelaTamanhos.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//		tabelaTamanhos.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
//		tabelaTamanhos.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//		tabelaTamanhos.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 ); 
//		
		
		
		JScrollPane scroll2 = new JScrollPane(tabelaTamanhos);
		scroll2.setBounds(560, 135, 200, 150);
		add(scroll2);
		
		modeloCategorias = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		modeloCategorias.addColumn("Categoria");
//		modeloCategorias.addColumn("Id");
		tabelCategorias = new JTable(modeloCategorias);
		JTableHeader cabecalho3 = tabelCategorias.getTableHeader();
		cabecalho3.setFont(new Font("Comic Sans", Font.BOLD, 18));
		cabecalho3.setForeground(Color.BLACK);
		for (CategoriaDTO t: produtoControl.getCategorias().getCategorias()) {
			Object[] linha = new Object[]{
					t.getCategoria(),
//					t.getId()
			};
			modeloCategorias.addRow(linha);
		}																				
		try {
			tabelCategorias.addRowSelectionInterval(0, 0);
		}catch(Exception e) {
//			modelo3.addColumn("Não há Categorias");
//			modeloCategorias.addRow(new Object[] {"Não há Categorias"});
		}
//		tabelCategorias.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//		tabelCategorias.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
//		tabelCategorias.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//		tabelCategorias.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 ); 

		
		JScrollPane scroll3 = new JScrollPane(tabelCategorias);
		scroll3.setBounds(290, 135, 200, 150);
		add(scroll3);
		
	}

	private void adicionarBotoes() {
		
		Ouvinte ouvinte = new Ouvinte(this);
		
		
		botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 400, 380, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);	
		botaoCancelar.addActionListener(ouvinte);

		botaoAddCor = AdicionadorDeComponentes.adicionarJButton(this, "Adicionar", 20, 305, 90, 20);
		botaoAddCor.addActionListener(ouvinte);

		botaoRemoverCor = AdicionadorDeComponentes.adicionarJButton(this, "Remover", 130, 305, 90, 20);
		botaoRemoverCor.addActionListener(ouvinte);

		botaoAddCategoria = AdicionadorDeComponentes.adicionarJButton(this, "Adicionar", 290, 305, 90, 20);
		botaoAddCategoria.addActionListener(ouvinte);
		
		botaoRemoverCategoria = AdicionadorDeComponentes.adicionarJButton(this, "Remover", 400, 305, 90, 20);
		botaoRemoverCategoria.addActionListener(ouvinte);
		
		botaoAddTamanho = AdicionadorDeComponentes.adicionarJButton(this, "Adicionar", 560, 305, 90, 20);
		botaoAddTamanho.addActionListener(ouvinte);
		
		botaoRemoverTamanho = AdicionadorDeComponentes.adicionarJButton(this, "Remover", 670, 305, 90, 20);
		botaoRemoverTamanho.addActionListener(ouvinte);
	} 
	
	private void adicionarLabels() {
		
//		ADICIONAR A IDENTIFICAÇÃO DO FUNCIONÁRIO LOGADO
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
	
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Configurações", 160, 40, 500, 80);
		L.setFont(new Font("Times new Roman", Font.BOLD, 65));
		L.setForeground(Color.DARK_GRAY);
//		
	}
	
	private class Ouvinte implements ActionListener{

		private TelaConfiguracoesProduto janela;
		
		public Ouvinte(TelaConfiguracoesProduto janela ) {
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object botao = e.getSource();

			
			ProdutoControl pControl = new ProdutoControl();
			
			if(botao == botaoCancelar) {
				TelaDePecas tela = new TelaDePecas(codigoDoFuncionarioLogado, false, identificacao);
				tela.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao == botaoAddCor) {
				String cor = JOptionPane.showInputDialog(janela, "Digite a cor", "NOVA COR", JOptionPane.QUESTION_MESSAGE);
				if(cor!=null) {
					CorDTO cordto = new CorDTO();
					cordto.setNomeCor(cor);
					pControl.adicionarCor(cordto);
					JOptionPane.showMessageDialog(janela, "Nova cor adicionada", null, JOptionPane.INFORMATION_MESSAGE);
					modeloCores.addRow(new String[] {cor});
				
				}
			}else if(botao == botaoAddCategoria) {
				String categoria = JOptionPane.showInputDialog(janela, "Digite a categoria", "NOVA CATEGORIA", JOptionPane.QUESTION_MESSAGE);
				if(categoria!=null) {
					CategoriaDTO categoriadto = new CategoriaDTO();
					categoriadto.setCategoria(categoria);
					pControl.adicionarCategoria(categoriadto);
					JOptionPane.showMessageDialog(janela, "Nova categoria adicionada", null, JOptionPane.INFORMATION_MESSAGE);
					modeloCategorias.addRow(new String[] {categoria});
				}
			}else if(botao == botaoAddTamanho) {
				String tamanho = JOptionPane.showInputDialog(janela, "Digite o tamanho", "NOVO TAMANHO", JOptionPane.QUESTION_MESSAGE);
				if(tamanho!=null) {
					TamanhoDTO tamanhodto = new TamanhoDTO();
					tamanhodto.setTamanho(tamanho);
					pControl.adicionarTamanho(tamanhodto);
					JOptionPane.showMessageDialog(janela, "Novo tamanho adicionado", null, JOptionPane.INFORMATION_MESSAGE);
					modeloTamanhos.addRow(new String[] {tamanho});
				}
			}else if(botao == botaoRemoverCor) {
				int linhaSelecionada =tabelaCores.getSelectedRow();
//				
				if(linhaSelecionada!=-1) {

					int opcao = JOptionPane.showConfirmDialog(janela, "Tem certeza que quer excluir essa cor?","Excluir Cor",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						String cor = (String) tabelaCores.getValueAt(linhaSelecionada, 0);
						CorDTO cordto= new CorDTO(); 
//					cordto.setId(id);
						cordto.setNomeCor(cor);
						pControl.removerCor(cordto);
						JOptionPane.showMessageDialog(janela, "Cor removida!", null, JOptionPane.INFORMATION_MESSAGE);
						modeloCores.removeRow(linhaSelecionada);
						
					}
					
				}else {
					JOptionPane.showMessageDialog(janela, "Selecione uma cor!", null, JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao == botaoRemoverCategoria) {
				int linhaSelecionada =tabelCategorias.getSelectedRow();

				if(linhaSelecionada!=-1) {
					
					int opcao = JOptionPane.showConfirmDialog(janela, "Tem certeza que quer excluir essa categoria?","Excluir Categoria",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						String categoria = (String) tabelCategorias.getValueAt(linhaSelecionada, 0);
						CategoriaDTO categoriadto= new CategoriaDTO(); 
						categoriadto.setCategoria(categoria);
						pControl.removerCategoria(categoriadto);
						JOptionPane.showMessageDialog(janela, "Categoria removida!", null, JOptionPane.INFORMATION_MESSAGE);
						modeloCategorias.removeRow(linhaSelecionada);
					}
					
				}else {
					JOptionPane.showMessageDialog(janela, "Selecione uma categoria!", null, JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao == botaoRemoverTamanho) {
				int linhaSelecionada =tabelaTamanhos.getSelectedRow();
				
				if(linhaSelecionada!=-1) {
					
					
					int opcao = JOptionPane.showConfirmDialog(janela, "Tem certeza que quer excluir esse tamanho?","Excluir Tamanho",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						String tamanho = (String) tabelaTamanhos.getValueAt(linhaSelecionada, 0);
						TamanhoDTO tamanhodto= new TamanhoDTO(); 
						tamanhodto.setTamanho(tamanho);
						pControl.removerTamanho(tamanhodto);				
						JOptionPane.showMessageDialog(janela, "Tamanho removido!", null, JOptionPane.INFORMATION_MESSAGE);
						modeloTamanhos.removeRow(linhaSelecionada);
						
					}
				}else {
					JOptionPane.showMessageDialog(janela, "Selecione um tamanho!", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}
//	public static void main(String[] args) {
//		new TelaConfiguracoesProduto(1);
//	}
}
