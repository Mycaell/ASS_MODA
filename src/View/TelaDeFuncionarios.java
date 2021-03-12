package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.FuncionarioControl;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.Iterator;
import View_Utilidades.IteratorFuncionarios;

public class TelaDeFuncionarios extends TelaComMenu{

	private DefaultTableModel modelo;
	private JTable tabela;

	private int codigoDoGerenteLogado;
	
	
	private JTabbedPane aba;
	private JPanel painelFuncionarios;
	
	private String identificacao;
	
	public TelaDeFuncionarios(int codigoDoGerenteLogado, String identificacao) {
		super("Funcionários");
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;	

		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
	}
	
	
	@Override
	public void adicionarComponentesGraficos() {
		adicionarLabels();
		
		
		
		adicionarAba();
				
	}
	
	
	private void adicionarLabels() {


		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "FUNCIONÁRIOS", 110, 20, 590, 75);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
		
	}

	private void adicionarAba() {
		aba = new JTabbedPane();
		adicionarTabela();
		adicionarBotoes();
		
		aba.addTab("Dados dos funcionários", painelFuncionarios);
		
		aba.setBounds(80, 115, 625, 240);
		add(aba);
	}
	
	
	private void adicionarTabela() {

		FuncionarioControl funcionarioController = new FuncionarioControl();
		
		painelFuncionarios = new JPanel(null);
		painelFuncionarios.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
	
		
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<FuncionarioDTO> funcionarios = funcionarioController.getFuncionarios().getFuncionarios();
		
		if(funcionarios.size() != 0) {
			
			modelo.addColumn("id");
			modelo.addColumn("Nome");
			modelo.addColumn("Sobrenome");
			modelo.addColumn("Sexo");
			modelo.addColumn("CPF");
			modelo.addColumn("Telefone");
			modelo.addColumn("Nascimento");
			modelo.addColumn("Cargo");
			modelo.addColumn("Bairro");
			modelo.addColumn("Rua");
			modelo.addColumn("Nº Casa");
			modelo.addColumn("id_endereco");
			
//			obs: login e senha devem ficar escondidos
			
			tabela.getColumnModel().getColumn(0).setMinWidth(0);
			tabela.getColumnModel().getColumn(0).setMaxWidth(0);
			
			tabela.getColumnModel().getColumn(11).setMinWidth(0);
			tabela.getColumnModel().getColumn(11).setMaxWidth(0);

			Iterator iterator = new IteratorFuncionarios(funcionarios);
			
			while(iterator.hasNext()){
				FuncionarioDTO funcionario = (FuncionarioDTO) iterator.next();
				Object[] linha = {funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getSexo(), funcionario.getCpf(), funcionario.getTelefone(), formataData.format(funcionario.getDataNascimento()), funcionario.getCargo(), funcionario.getBairro(), funcionario.getRua(), funcionario.getNumCasa(), funcionario.getIdEndereco()};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);	
			
		}else {
			modelo.addColumn("Não há funcionários");
		}
		
	

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 623, 165);
//		add(scroll);
		painelFuncionarios.add(scroll);
	}

	private void adicionarBotoes() {

		OuvinteTelaDeFuncionarios ouvint = new OuvinteTelaDeFuncionarios(this);

		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Voltar", 0, 180, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(ouvint);
		

		JButton botaoExcluir = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Excluir", 140, 180, 95, 20);
		botaoExcluir.setIcon(Icones.ICONE_LIXO);
		botaoExcluir.addActionListener(ouvint);
		

		JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Editar", 285, 180, 115, 20);
		botaoEditar.setIcon(Icones.ICONE_DETALHES);
		botaoEditar.addActionListener(ouvint);

		
		
		JButton botaoAdicionarFuncionario = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Cadastrar Funcionário", 434, 180, 180, 20);
		botaoAdicionarFuncionario.setIcon(Icones.ICONE_USUARIO);
		botaoAdicionarFuncionario.addActionListener(ouvint);

		
	}

	private class OuvinteTelaDeFuncionarios implements ActionListener{

		private TelaDeFuncionarios telaDeFuncionarios;
		
		public OuvinteTelaDeFuncionarios(TelaDeFuncionarios telaDeFuncionarios) {
			this.telaDeFuncionarios = telaDeFuncionarios;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			int linha = tabela.getSelectedRow();

			if (botao.equals("Excluir")){
				
				if(linha != -1) {			

					int opcao = JOptionPane.showConfirmDialog(telaDeFuncionarios, "Tem certeza que quer excluir esse funcionário?","Excluir Funionário",JOptionPane.YES_NO_OPTION);
						
					if(opcao == JOptionPane.YES_OPTION) {
						int idFuncionario = (int) tabela.getValueAt(linha, 0);						
						int idEndereco = (int) tabela.getValueAt(linha, 11);
						FuncionarioControl funcionarioController = new FuncionarioControl();						
						FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
						funcionarioDTO.setId(idFuncionario);
						funcionarioDTO.setIdEndereco(idEndereco);
						funcionarioController.removerFuncionario(funcionarioDTO);
						JOptionPane.showMessageDialog(telaDeFuncionarios, "Funcionário excluído!");
						modelo.removeRow(linha);
					}	
									
				}else {
					JOptionPane.showMessageDialog(telaDeFuncionarios, "Selecione um funcionário!", "Nenhum funcionário selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Editar")){

				if(linha != -1) {
					int idFuncionario = (int) tabela.getValueAt(linha, 0);
					int idEndereco= (int) tabela.getValueAt(linha, 11);
					
//					TelaEdicaoFuncionario telaEdicaoFuncionario = new TelaEdicaoFuncionario(codigoDoGerenteLogado, Integer.parseInt(idFuncionario), Integer.parseInt(idEndereco));
					TelaEdicaoFuncionario telaEdicaoFuncionario = new TelaEdicaoFuncionario(codigoDoGerenteLogado, idFuncionario, idEndereco, identificacao);
					telaEdicaoFuncionario.setLocationRelativeTo(telaDeFuncionarios);
					telaDeFuncionarios.dispose();
					
				}else {
					JOptionPane.showMessageDialog(telaDeFuncionarios, "Selecione um funcionário!", "Nenhum funcionário selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Voltar")){
				
				TelaDeGerenciamento telaDeGerenciamento = new TelaDeGerenciamento(codigoDoGerenteLogado, identificacao);
				telaDeGerenciamento.setLocationRelativeTo(telaDeFuncionarios);
				telaDeFuncionarios.dispose();
				
				
			}else {
//				lógica que será executada quando o botão "cadastrar funcionários for clicado
				TelaCadastroFuncionario telaDeCadastro = new TelaCadastroFuncionario(codigoDoGerenteLogado, identificacao);
				telaDeCadastro.setLocationRelativeTo(telaDeFuncionarios);
				telaDeFuncionarios.dispose();
				
			}
			
			
		}
		
	}

	
}
