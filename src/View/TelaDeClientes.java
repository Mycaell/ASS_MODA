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

import Controller.ClienteControl;
import DTO.ClienteDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.Iterator;
import View_Utilidades.IteratorClientes;

public class TelaDeClientes extends TelaPadrao{

	private DefaultTableModel modelo;
	private JTable tabela;
	
	private JTabbedPane aba;
	private JPanel painelClientes;
	
	private int codigoDoFuncionarioLogado;
	private String identificacao;
	
	public TelaDeClientes(int codigoDoFuncionarioLogado, String identificacao) {
		super("Clientes");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.codigoDoFuncionarioLogado = codigoDoFuncionarioLogado;

		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
	}


	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		adicionarAba();
		
	}

	private void adicionarLabels() {
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "CLIENTES", 200, 35, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
	}

	
	private void adicionarAba() {
		aba = new JTabbedPane();
		aba = new JTabbedPane();

		adicionarTabela();
		adicionarBotoes();
		
		
//		\/ linha responsável por adicionar a aba "dados dos clientes"
		aba.addTab("Dados dos clientes", painelClientes);
		aba.setBounds(70, 115, 635, 240);
		add(aba);
		
	}
	
	
	
	private void adicionarTabela() {
		
	
		painelClientes = new JPanel(null);
		painelClientes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		
		
//		***
//		utilizando ITERATOR
//		***
		
//		Iterator clientes = new IteratorConcreto(gerenteController.recuperarDadosDeTodosOsClientes().getDadosDosClientes());
//		
//		modelo.addColumn("Nome");
//		modelo.addColumn("CPF");
//		modelo.addColumn("Telefone");
//		modelo.addColumn("Endereço (B,R,N)");
//		
//		while(clientes.hasNext()) {
//			
//			ClienteDTO cli = (ClienteDTO) clientes.next();
//
//			Object[] linha = {cli.getNome(), cli.getCPF(), cli.getTelefone(), cli.getEndereço()};
//			modelo.addRow(linha);
//			
//			tabela.addRowSelectionInterval(0, 0);		
//			
//		}
//		
//		
//		JScrollPane scroll = new JScrollPane(tabela);
//		scroll.setBounds(0, 0, 620, 165);
//		painelClientes.add(scroll);
		
		
		ClienteControl clienteController = new ClienteControl();		
		
		ArrayList<ClienteDTO> clientes = clienteController.getClientes().getClientes();
		
		if(clientes.size() != 0) {
			
			modelo.addColumn("id");
			modelo.addColumn("Nome");
			modelo.addColumn("Sobrenome");
			modelo.addColumn("Sexo");
			modelo.addColumn("CPF");
			modelo.addColumn("Telefone");
			modelo.addColumn("Nascimento");
			modelo.addColumn("Bairro");
			modelo.addColumn("Rua");
			modelo.addColumn("Nº Casa");
			modelo.addColumn("id_endereco");
			

			
			tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(85);
			
			tabela.getColumnModel().getColumn(9).setPreferredWidth(60);
			

			tabela.getColumnModel().getColumn(10).setMinWidth(0);
			tabela.getColumnModel().getColumn(10).setMaxWidth(0);

			tabela.getColumnModel().getColumn(0).setMinWidth(0);
			tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
			
			Iterator iterator = new IteratorClientes(clientes);
			
			while(iterator.hasNext()){
				ClienteDTO cliente = (ClienteDTO) iterator.next();
				Object[] linha = {cliente.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getSexo(), cliente.getCpf(), cliente.getTelefone(), formataData.format(cliente.getDataNascimento()), cliente.getBairro(), cliente.getRua(), cliente.getnCasa(), cliente.getIdEndereco()};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há clientes");
		}

		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 633, 165);
		painelClientes.add(scroll);
		
	}
	
	private void adicionarBotoes() {
		
		Ouvint ouvinte = new Ouvint(this);

		JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(painelClientes, "Editar", 0, 180, 90, 20);
		botaoEditar.setIcon(Icones.ICONE_EDICAO);
		botaoEditar.addActionListener(ouvinte);
		

		JButton botaoExcluir = AdicionadorDeComponentes.adicionarJButton(painelClientes, "Excluir", 210, 180, 95, 20);
		botaoExcluir.setIcon(Icones.ICONE_LIXO);
		botaoExcluir.addActionListener(ouvinte);
	
		JButton botaoHistoricoDePedidos = AdicionadorDeComponentes.adicionarJButton(painelClientes, "Histórico de Compras", 438, 180, 185, 20);
		botaoHistoricoDePedidos.setIcon(Icones.ICONE_HISTORICO);
		botaoHistoricoDePedidos.addActionListener(ouvinte);
				
		
		
	}
	
	private class Ouvint implements ActionListener{

		private TelaDeClientes telaDeClientes;
		
		public Ouvint(TelaDeClientes telaDeClientes) {
			this.telaDeClientes = telaDeClientes;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			int linha = tabela.getSelectedRow(); 
			
			if(botao.equals("Excluir")){
				if(linha != -1) {
					int idCliente = (int)tabela.getValueAt(linha, 0);
					int idEndereco = (int)tabela.getValueAt(linha, 10);
					int opcao = JOptionPane.showConfirmDialog(telaDeClientes, "Você tem certeza que quer excluir esse cliente?","Excluir Cliente",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						ClienteControl clienteControl = new ClienteControl();
						ClienteDTO clienteDTO = new ClienteDTO();
						clienteDTO.setId(idCliente);
						clienteDTO.setIdEndereco(idEndereco);
						clienteControl.removerCliente(clienteDTO);
						
						JOptionPane.showMessageDialog(telaDeClientes, "Cliente excluído!");
						
						modelo.removeRow(linha);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeClientes, "Selecione um cliente!", "Nenhum cliente selecionado", JOptionPane.ERROR_MESSAGE);
				}
					
			}else if(botao.equals("Histórico de Compras")) {

				
				if(linha != -1) {
					
					String nomeDoCliente = (String) tabela.getValueAt(linha, 1);
					int idDoCliente = (int) tabela.getValueAt(linha, 0);

					
					TelaDeComprasDoCliente telaDeComprasDoCliente = new TelaDeComprasDoCliente(codigoDoFuncionarioLogado, nomeDoCliente, idDoCliente, identificacao);
					telaDeComprasDoCliente.setLocationRelativeTo(telaDeClientes);	
					telaDeClientes.dispose();
				}else {
					JOptionPane.showMessageDialog(telaDeClientes, "Selecione um cliente!", "Nenhum cliente selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Editar")) {
				if(linha != -1) {
					int idCliente = (int) tabela.getValueAt(linha, 0);
					int idEndereco= (int) tabela.getValueAt(linha, 10);
					
					
					TelaEdicaoCliente telaEdicaoCliente = new TelaEdicaoCliente(codigoDoFuncionarioLogado, null,idCliente, idEndereco, identificacao);
					telaEdicaoCliente.setLocationRelativeTo(telaDeClientes);
					telaDeClientes.dispose();
					
				}else {
					JOptionPane.showMessageDialog(telaDeClientes, "Selecione um cliente!", "Nenhum cliente selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}

	
	
	
	
	
//	public static void main(String[] args) {
//		new TelaDeClientes(666);
//	}
	
}


