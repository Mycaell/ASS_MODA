package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.ProdutoControl;
import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.ProdutoDTO;
import DTO.TamanhoDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaEditarProduto extends TelaComMenu{

	private int codigoDoGerenteLogado;
	private int idDoProduto;
	private JTextField nome;
	private JComboBox<Object> tamanhos;
	private JTextField preco;
	private JTextField marca;
	private JTextField quantidade;
	private JComboBox<Object> cor;
	private JComboBox<Object> categoria;
	private JTextArea descricao;
	private JButton botaoSalvar;
	private JButton botaoCancelar;	 		
	
	private String identificacao;
	
	public TelaEditarProduto(int codigoDoGerenteLogado, int idDoProduto, String identificacao) {
		super("Editar Peça");		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.idDoProduto = idDoProduto;
		adicionarFields();
		
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
	}

	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		adicionarBotoes();
	}

	private void adicionarLabels() {
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "EDITAR PEÇA", 150, 15, 700, 80);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 110, 135, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Tamanho", 440, 135, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Preço", 110, 170, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Categoria", 440, 170, 180, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Cor", 110, 200, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Descrição", 440, 200, 100, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Marca", 110, 230, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Quantidade", 110, 260, 100, 20);
		
	}
	
	private void adicionarFields() {
		
		ProdutoControl produtoControl = new ProdutoControl();
		ProdutoDTO produto = new ProdutoDTO();
		produto.setId(idDoProduto);
		ProdutoDTO produtodto = produtoControl.getProduto(produto);
		
		nome = AdicionadorDeComponentes.adicionarJTextField(this, 170, 135, 110, 20);
		nome.setText(produtodto.getNome());
		preco = AdicionadorDeComponentes.adicionarJTextField(this, 170, 170, 60, 20);
		preco.setText(Float.toString(produtodto.getPreco()));
		marca = AdicionadorDeComponentes.adicionarJTextField(this, 170, 230, 150, 20);
		marca.setText(produtodto.getMarca());
		quantidade = AdicionadorDeComponentes.adicionarJTextField(this, 220, 260, 60, 20);
		quantidade.setText(Integer.toString(produtodto.getQuantEstoque()));
//		descricao = AdicionadorDeComponentes.adicionarJTextArea(this, 550, 200, 160, 110);
		 descricao = new JTextArea();
		 descricao.setText(produtodto.getDescricao());
		 descricao.setLineWrap(true);
		 descricao.setWrapStyleWord(true);
		
		 JScrollPane scroll = new JScrollPane(descricao);		
		 scroll.setBounds(550, 200, 160, 110);
		 add(scroll);
		
		 
		 
		 tamanhos.setSelectedItem(produtodto.getTamanho());
		 cor.setSelectedItem(produtodto.getCor());
		 categoria.setSelectedItem(produtodto.getCategoria()); 
		 
		 
		
		
		
	}
	
	private void adicionarBotoes() {
				
		botaoSalvar = AdicionadorDeComponentes.adicionarJButton(this, "Salvar", 300, 375, 90, 20);
		botaoSalvar.setIcon(Icones.ICONE_SALVAR);
		botaoSalvar.addActionListener(new Ouvinte(this));
		
		botaoCancelar = AdicionadorDeComponentes.adicionarJButton(this, "Cancelar", 400, 375, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(new Ouvinte(this));

		ProdutoControl produtoControl = new ProdutoControl();
		
		ArrayList<String> vertorTamanhos = new ArrayList<String>();			
		for (TamanhoDTO tam : produtoControl.getTamanhos().getTamanhos()) {
			vertorTamanhos.add(tam.getTamanho());	
		}
		tamanhos = new JComboBox<>(vertorTamanhos.toArray());
		tamanhos.setBounds(550, 135, 115, 20);
		add(tamanhos);
		
		ArrayList<String> vertorCores = new ArrayList<String>();		
		for (CorDTO cor : produtoControl.getCores().getCores()) {
			vertorCores.add(cor.getNomeCor());	
		}		
		cor = new JComboBox<>(vertorCores.toArray());
	    cor.setBounds(170, 200, 110, 20);	    
	    add(cor);
	    
	    ArrayList<String> vertorCategoria = new ArrayList<String>();
	    for (CategoriaDTO categoria : produtoControl.getCategorias().getCategorias()) {
	    	vertorCategoria.add(categoria.getCategoria());	
		}

	    categoria = new JComboBox<>(vertorCategoria.toArray());
	    categoria.setBounds(550, 170, 115, 20);
	    add(categoria); 
	}
	
	private class Ouvinte implements ActionListener{

		private TelaEditarProduto janela;
		public Ouvinte(TelaEditarProduto janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == botaoSalvar) {
				ProdutoDTO produtoDTO = new ProdutoDTO(nome.getText(), cor.getSelectedItem().toString(),
				marca.getText(), descricao.getText(), tamanhos.getSelectedItem().toString(),
				categoria.getSelectedItem().toString(), Integer.parseInt(quantidade.getText()), 
				codigoDoGerenteLogado, Float.parseFloat(preco.getText()));
			
				ProdutoControl produtoControl = new ProdutoControl();
				produtoDTO.setId(idDoProduto);
				produtoControl.editarProduto(produtoDTO);
				JOptionPane.showMessageDialog(janela, "Produto atualizado", null, JOptionPane.INFORMATION_MESSAGE);
			
			}
			TelaDePecas telaProdutos = new TelaDePecas(codigoDoGerenteLogado, false, identificacao);
			telaProdutos.setLocationRelativeTo(janela);
			janela.dispose();		
		}		
	}
	
//	public static void main(String[] args) {
//		new TelaEditarProduto(1,1);
//	}
}
