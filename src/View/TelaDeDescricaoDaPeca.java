package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Controller.VendaControl;
import DTO.ProdutoDTO;
import DTO.VendaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeDescricaoDaPeca extends JDialog{

	private String descricao;
	
	private JPanel painelPecas;
	
	public TelaDeDescricaoDaPeca(String descricao) {
		setTitle("Descrição da Peça");
		setSize(300,201);
		setResizable(false);
		setLayout(null);
		
		
		
		this.descricao = descricao;
		
		adicionarComponentes();
		
		setVisible(true);
	}

	
	public void adicionarComponentes() {
		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 270, 190);
		setContentPane(label);

		JLabel L = new JLabel("Descrição");
		L.setBounds(95, 5, 270, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		add(L);

		
		painelPecas = new JPanel(null);

		painelPecas.setBounds(10, 40, 270, 125);
		painelPecas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		
		
		JTextArea areaDescricao = new JTextArea();
		areaDescricao.setText(descricao);
		areaDescricao.setEditable(false);
//		areaDescricao.setBounds(x, y, c, a);
		areaDescricao.setLineWrap(true);
		areaDescricao.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(areaDescricao);
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

		private TelaDeDescricaoDaPeca telaDeDescricaoDaPeca;
		
		public OuvinteBotaoOK(TelaDeDescricaoDaPeca telaDeDescricaoDaPeca) {
			this.telaDeDescricaoDaPeca = telaDeDescricaoDaPeca;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			telaDeDescricaoDaPeca.dispose();
		}
		
	}
	
}
