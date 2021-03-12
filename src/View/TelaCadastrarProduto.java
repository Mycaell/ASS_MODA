package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.postgresql.jdbc3.Jdbc3SimpleDataSource;

import Controller.ProdutoControl;
import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.ProdutoDTO;
import DTO.TamanhoDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaCadastrarProduto extends TelaComMenu{
	
	private int codigoDoFuncionarioLogado;
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
	public TelaCadastrarProduto(int codigoDoFuncionarioLogado, String identificacao) {
		super("Cadastramento de Produto");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "CADASTRO DE PEÇA", 15, 15, 800, 80);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 110, 135, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Tamanho", 440, 135, 105, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Preço", 110, 170, 55, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Categoria", 440, 170, 180, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Cor", 110, 205, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Descrição", 440, 205, 100, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Marca", 110, 240, 80, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Quantidade", 110, 275, 100, 20);
		
	}
	
	private void adicionarFields() {
		nome = AdicionadorDeComponentes.adicionarJTextField(this, 170, 135, 110, 20);
		preco = AdicionadorDeComponentes.adicionarJTextField(this, 170, 170, 60, 20);
		marca = AdicionadorDeComponentes.adicionarJTextField(this, 170, 240, 150, 20);
		quantidade = AdicionadorDeComponentes.adicionarJTextField(this, 220, 275, 60, 20);
//		descricao = AdicionadorDeComponentes.adicionarJTextArea(this, 550, 200, 160, 110);
		
//		ah
		 descricao = new JTextArea();
		
		descricao.setLineWrap(true);
		descricao.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(descricao);		
		scroll.setBounds(550, 205, 165, 110);
		add(scroll);
		
		
		
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
	    cor.setBounds(170, 205, 110, 20);
	    add(cor);
	    
	    ArrayList<String> vertorCategoria = new ArrayList<String>();
	    for (CategoriaDTO categoria : produtoControl.getCategorias().getCategorias()) {
	    	vertorCategoria.add(categoria.getCategoria());	
		}

	    categoria = new JComboBox<>(vertorCategoria.toArray());
	    categoria.setBounds(550, 170, 115, 20);
	    add(categoria); 
	}
	
//	public static void main(String[] args) {
//		new TelaCadastrarProduto(1);
//	}
	
	private class Ouvinte implements ActionListener{

		private TelaCadastrarProduto janela;
		public Ouvinte(TelaCadastrarProduto janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == botaoSalvar) {
				ProdutoDTO produtoDTO = new ProdutoDTO(nome.getText(), cor.getSelectedItem().toString(),
						marca.getText(), descricao.getText(), tamanhos.getSelectedItem().toString(),
						categoria.getSelectedItem().toString(), Integer.parseInt(quantidade.getText()), 
						codigoDoFuncionarioLogado, Float.parseFloat(preco.getText()));
			
			ProdutoControl produtoControl = new ProdutoControl();
			produtoControl.cadastrarProduto(produtoDTO);
			JOptionPane.showMessageDialog(janela, "Novo produto adicionado", null, JOptionPane.INFORMATION_MESSAGE);
			
			TelaDePecas telaProdutos = new TelaDePecas(codigoDoFuncionarioLogado, false, identificacao);
			telaProdutos.setLocationRelativeTo(janela);
			janela.dispose();
			
			}else if(e.getSource() == botaoCancelar) {
				TelaDePecas telaProdutos = new TelaDePecas(codigoDoFuncionarioLogado, false, identificacao);
				telaProdutos.setLocationRelativeTo(janela);
				janela.dispose();
			}
		}
		
	}
}
