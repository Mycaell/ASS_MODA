package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.VendaControl;
import DTO.ProdutoDTO;
import DTO.VendaDTO;
import View_Utilidades.Icones;

public class TelaDePecasDeUmaCompra extends JDialog{

	private int idDaCompra;
	
	private JPanel painelPecas;
	
	public TelaDePecasDeUmaCompra(int idDaCompra) {
		setTitle("Peças da Compra Nº "+idDaCompra);
		setSize(300,201);
		setResizable(false);
		setLayout(null);
		
		
		
		this.idDaCompra = idDaCompra;
		
		adicionarComponentes();
		
		setVisible(true);
	}

	
	public void adicionarComponentes() {
		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 270, 190);
		setContentPane(label);

		JLabel L = new JLabel("Peças");
		L.setBounds(95, 5, 270, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		add(L);

		
		painelPecas = new JPanel(null);

		painelPecas.setBounds(10, 40, 270, 125);
		painelPecas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		JTable tabela = new JTable(modelo);
		
		
		modelo.addColumn("Nome");
		modelo.addColumn("Valor");
		

		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId(idDaCompra);
		
		
		VendaDTO vendaDTO = new VendaDTO();
		vendaDTO.setNumVenda(idDaCompra);

		VendaControl vendaControl = new VendaControl();

		ArrayList<ProdutoDTO> pecas = vendaControl.getProdutosVenda(vendaDTO).getProdutosDaVenda();
		
		for (ProdutoDTO peca : pecas) {
			Object[] linha = {peca.getNome(), peca.getPreco()};
			modelo.addRow(linha);
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 270, 95);
		painelPecas.add(scroll);
		
		JButton botaoOk = new JButton("OK");
		botaoOk.setBounds(103, 100, 80, 20);
		botaoOk.setFocusPainted(false);
		botaoOk.addActionListener(new OuvinteBotaoOK(this));
		painelPecas.add(botaoOk);
		
		
		this.add(painelPecas);
		
	}
	

	private class OuvinteBotaoOK implements ActionListener{

		private TelaDePecasDeUmaCompra telaDePecasDeUmaCompra;
		
		public OuvinteBotaoOK(TelaDePecasDeUmaCompra telaDePecasDeUmaCompra) {
			this.telaDePecasDeUmaCompra = telaDePecasDeUmaCompra;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			telaDePecasDeUmaCompra.dispose();
		}
		
	}
	
}
