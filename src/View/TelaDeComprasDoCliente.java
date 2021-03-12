package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.VendaControl;
import DTO.ClienteDTO;
import DTO.VendaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeComprasDoCliente extends TelaPadrao {

	
	private int codigoDoGerenteLogado;
	private String nomeDoCliente;
	private int idDoCliente;
	
	
	private JTabbedPane aba;
	private JPanel painelPedidos;
	
	private JTable tabela;
	
	private String identificacao;
	
	public TelaDeComprasDoCliente(int codigoDoGerenteLogado, String nomeDoCliente, int idDoCliente, String identificacao) {
		super("Histórico de Compras");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.nomeDoCliente = nomeDoCliente;
		this.idDoCliente = idDoCliente;
		
	
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);	
		adicionarAba();

	}  
	
	@Override
	public void adicionarComponentesGraficos() {
		painelPedidos = new JPanel(null);
		painelPedidos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		aba = new JTabbedPane();
		adicionarBotoes();
	}
	
	
	
	private void adicionarAba() {
		adicionarLabels();
		adicionarTabela();
		
		
		aba.setBounds(60, 115, 655, 240);
		add(aba);
	}
	
	
	private void adicionarBotoes() {
		
		Ouvinte ouvinte = new Ouvinte(this);
		
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelPedidos, "Voltar", 0, 180, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(ouvinte);
		
		JButton botaoVerPecas = AdicionadorDeComponentes.adicionarJButton(painelPedidos, "Ver Peças", 155, 180, 135, 20);
		botaoVerPecas.setIcon(Icones.ICONE_OLHO_ABERTO);
		botaoVerPecas.addActionListener(ouvinte);
	
	
		JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(painelPedidos, "Editar", 350, 180, 130, 20);
		botaoEditar.setIcon(Icones.ICONE_EDICAO);
		botaoEditar.addActionListener(ouvinte);
	
	
		JButton botaoCancelar = AdicionadorDeComponentes.adicionarJButton(painelPedidos, "Cancelar", 515, 180, 130, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(ouvinte);
	}

	private void adicionarLabels() {
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "HISTÓRICO DE COMPRAS",20, 40, 900, 60);
		
//		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "COMPRAS de "+nomeDoCliente.toUpperCase(), 80, 40, 900, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 60));
		L.setForeground(Color.DARK_GRAY);
		
		
	}


	public void adicionarTabela() {
	
		VendaControl vendaControl = new VendaControl();
		
//		painelPedidos = new JPanel(null);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(idDoCliente);
		
		
		ArrayList<VendaDTO> compras = vendaControl.getComprasDoCliente(clienteDTO).getVendas();
		
		if(compras.size() == 0) {
			modelo.addColumn("Não há Compras");
		}else {
			
			modelo.addColumn("Nº da Compra");
			modelo.addColumn("Valor");
			modelo.addColumn("Pagamento");
			modelo.addColumn("Data da Compra");
		


			tabela.getColumnModel().getColumn(0).setPreferredWidth(55);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
			

			SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String tipoPagamento = null;
			
			for (VendaDTO compra : compras) {
				
				if(compra.getPagamento() == 0) {
					tipoPagamento = "À vista";
				}else {
					tipoPagamento = "Cartão ("+compra.getTipoDeCartao()+")";
//					tipoPagamento = "Cartão";
					
				}
				
				Object[] linha = {compra.getNumVenda(), compra.getPreco(), tipoPagamento, formataData.format(compra.getDataCriacao())};
				modelo.addRow(linha);
			}
		}
		
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 653, 164);
		painelPedidos.add(scroll);
		
	
		
		
		aba.addTab("COMPRAS DE "+nomeDoCliente.toUpperCase(), painelPedidos);
//		aba.addTab("Compras", painelPedidos);
	}
	
	
	
	private class Ouvinte implements ActionListener{

		private TelaDeComprasDoCliente telaDeHistoricoDePedidos;
		
		public Ouvinte(TelaDeComprasDoCliente telaDeHistoricoDePedidos) {
			this.telaDeHistoricoDePedidos = telaDeHistoricoDePedidos;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			int linha = tabela.getSelectedRow(); 

			if(botao.equals("Voltar")) {
				TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoGerenteLogado, identificacao);
				telaDeClientes.setLocationRelativeTo(telaDeHistoricoDePedidos);
				telaDeHistoricoDePedidos.dispose();
			}else if(botao.equals("Ver Peças")) {
				
				if(linha != -1) {
					
					int idCompra = (int) tabela.getValueAt(linha, 0);
					
					TelaDePecasDeUmaCompra telaDePecasDeUmaCompra = new TelaDePecasDeUmaCompra(idCompra);
					telaDePecasDeUmaCompra.setLocationRelativeTo(telaDeHistoricoDePedidos);
					
				}else {
					JOptionPane.showMessageDialog(telaDeHistoricoDePedidos, "Selecione uma compra!", "Nenhuma compra selecionada", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Editar")) {
				if(linha != -1) {
					
					int idCompra = (int) tabela.getValueAt(linha, 0);
					
//					TelaDePecasDeUmaCompra telaDePecasDeUmaCompra = new TelaDePecasDeUmaCompra(idCompra);
//					telaDePecasDeUmaCompra.setLocationRelativeTo(telaDeHistoricoDePedidos);
					
				}else {
					JOptionPane.showMessageDialog(telaDeHistoricoDePedidos, "Selecione uma compra!", "Nenhuma compra selecionada", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Cancelar")) {
				if(linha != -1) {
					int idCompra = (int) tabela.getValueAt(linha, 0);
					
//					TelaDePecasDeUmaCompra telaDePecasDeUmaCompra = new TelaDePecasDeUmaCompra(idCompra);
//					telaDePecasDeUmaCompra.setLocationRelativeTo(telaDeHistoricoDePedidos);
					
				}else {
					JOptionPane.showMessageDialog(telaDeHistoricoDePedidos, "Selecione uma compra!", "Nenhuma compra selecionada", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
//			TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoGerenteLogado);
//			telaDeClientes.setLocationRelativeTo(telaDeHistoricoDePedidos);
//			telaDeHistoricoDePedidos.dispose();
			
		}
		
	}




	
	
	
	
}
