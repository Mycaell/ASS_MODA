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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.BandeiraControl;
import DTO.BandeiraDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.Iterator;
import View_Utilidades.IteratorBandeiras;

public class TelaBandeira extends TelaComMenu{

	private int codigoDoFuncionarioLogado;
	private DefaultTableModel modelo;
	private JTable tabela;	
	private JTabbedPane aba;
	private JPanel painelBandeiras;
	
	public TelaBandeira(int codigoDoFuncionarioLogado, String identificacao) {
		super("Bandeira");		
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.codigoDoFuncionarioLogado = codigoDoFuncionarioLogado;
		
		super.adicionarIdentificacao(identificacao);
		
	}

	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		adicionarAba();
	}

	private void adicionarLabels() {
		

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "BANDEIRAS", 175, 25, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
	}
	
	private void adicionarAba() {
		aba = new JTabbedPane();
		aba = new JTabbedPane();

		adicionarTabela();
		adicionarBotoes();
		
		
//		\/ linha responsável por adicionar a aba "dados dos clientes"
		aba.addTab("Dados das Bandeiras", painelBandeiras);
		aba.setBounds(70, 115, 635, 240);
		add(aba);
		
	}
	
	private void adicionarTabela() {
	
		painelBandeiras = new JPanel(null);
		painelBandeiras.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
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
		
		BandeiraControl bandeiraControl = new BandeiraControl();		
		
		ArrayList<BandeiraDTO> bandeiras = bandeiraControl.getBandeiras().getBandeiras();
		modelo.addColumn("Nome da Bandeira");

		
		if(bandeiras.size() != 0) {
			
//			modelo.addColumn("id");

//			tabela.getColumnModel().getColumn(0).setMinWidth(0);
//			tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			Iterator iterator = new IteratorBandeiras(bandeiras);
			
			while(iterator.hasNext()){
				BandeiraDTO bandeira = (BandeiraDTO) iterator.next();
				Object[] linha = {bandeira.getNomeBandeira()};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}

		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 633, 165);
		painelBandeiras.add(scroll);
		
	}
	
	private void adicionarBotoes() {
		
		Ouvinte ouvinte = new Ouvinte(this);

		JButton botaoAdicionar = AdicionadorDeComponentes.adicionarJButton(painelBandeiras, "Adicionar", 510, 180, 90, 20);
		botaoAdicionar.setIcon(Icones.ICONE_MAIS);
		botaoAdicionar.addActionListener(ouvinte);
		

		JButton botaoExcluir = AdicionadorDeComponentes.adicionarJButton(painelBandeiras, "Excluir", 260, 180, 95, 20);
		botaoExcluir.setIcon(Icones.ICONE_LIXO);
		botaoExcluir.addActionListener(ouvinte);
		
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelBandeiras, "Voltar", 30, 180, 95, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(ouvinte);
	
	}
	
	private class Ouvinte implements ActionListener{

		private TelaBandeira telaBandeira;
		
		public Ouvinte(TelaBandeira telaBandeira) {
			this.telaBandeira = telaBandeira;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			BandeiraControl bandeiraControl = new BandeiraControl();
			
			if(botao.equals("Voltar")){
				TelaDeGerenciamento telaDeGerenciamento = new TelaDeGerenciamento(codigoDoFuncionarioLogado, telaBandeira.getIdentificacao());
				telaDeGerenciamento.setLocationRelativeTo(telaBandeira);
				telaBandeira.dispose();
					
			}else if(botao.equals("Adicionar")) {
				String bandeira = JOptionPane.showInputDialog(telaBandeira, "Digite o nome da bandeira", "NOVA BANDEIRA", JOptionPane.QUESTION_MESSAGE);
				if(bandeira!=null) {
					BandeiraDTO bandeiradto = new BandeiraDTO();
					bandeiradto.setNomeBandeira(bandeira);
					bandeiraControl.adicionarBandeira(bandeiradto);
					modelo = (DefaultTableModel) tabela.getModel();
					modelo.addRow(new String[] {bandeira});
					JOptionPane.showMessageDialog(telaBandeira, "Nova bandeira adicionada", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}else if(botao.equals("Excluir")){
				
				int linha = tabela.getSelectedRow(); 
				
				if(linha != -1) {
					String bandeira = (String) tabela.getValueAt(tabela.getSelectedRow(), 0);

					int opcao = JOptionPane.showConfirmDialog(telaBandeira, "Você tem certeza que quer excluir essa bandeira?","Excluir Bandeira",JOptionPane.YES_NO_OPTION);
					if(opcao == JOptionPane.YES_OPTION) {
						
						BandeiraDTO bandeiradto = new BandeiraDTO();
						bandeiradto.setNomeBandeira(bandeira);
						JOptionPane.showMessageDialog(telaBandeira, "Bandeira excluída!");
						
						bandeiraControl.removerBandeira(bandeiradto);
						
						modelo.removeRow(linha);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaBandeira, "Selecione uma bandeira!", "Nenhuma bandeira selecionada", JOptionPane.ERROR_MESSAGE);
				}
					
			}
		}
	}
//	public static void main(String[] args) {
//		new TelaBandeira(1);
//	}
}
