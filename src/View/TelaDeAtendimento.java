package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ClienteControl;
import Controller.ProdutoControl;
import Controller.VendaControl;
import DTO.ClienteDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.Iterator;
import View_Utilidades.IteratorVendas;
import View_Utilidades.TeclasPermitidas;

public class TelaDeAtendimento extends TelaPadrao{
	
	private int codigoDoFuncionarioLogado;
	private boolean isGerente;

	private JFormattedTextField cpf;
	private JTextField peca;
		
	private DefaultListModel modeloListaCPF;
	private JList<String> listaCPF;
	private JScrollPane scrollCPF;	
	
	private DefaultListModel modeloListaPeca;
	private JList<String> listaPeca;
	private JScrollPane scrollPeca;
	
	
	private JTable tabela;
	private DefaultTableModel modelo1;
	
	private JTable tabelaDePecasQueCompoemOPedido;
	private DefaultTableModel modelo2;
	
	private JLabel LabelPreco;
	private JTextField fieldQuantidade;
	
	private JButton botaoMenos;
	private JButton botaoMais;
	
	private String identificacao;
	
	public TelaDeAtendimento(int codigoDoAtendenteLogado, String identificacao) {
		super("Atendimento");

		this.setSize(990,700);
		this.setLocationRelativeTo(null);		
		this.codigoDoFuncionarioLogado = codigoDoAtendenteLogado;
		this.identificacao = identificacao;
		super.adicionarIdentificacao(identificacao);
		adicionarBotoes();
	
	}
	
	
	@Override
	public void adicionarComponentesGraficos() {
		adicionarMenu(); // o menu não está vindo da telaComMenu devido a um problema possivelmente do template
		adicionarLabels();
		adicionarFields();
		adicionarTabelaDePedidos();
		adicionarTabelaDePecasQueEstaoCompondoOPedido();
	}
	
	private void adicionarMenu() {
		
		OuvinteBotoesTelaAtendimento ouvinte = new OuvinteBotoesTelaAtendimento(this);

		JMenuBar barraDeMenu = new JMenuBar();

		JMenu opcoes = new JMenu();
		opcoes.setIcon(Icones.ICONE_ENGRENAGEM);
		barraDeMenu.add(opcoes);
		
		
		JMenuItem itemSair = new JMenuItem("Sair");
		itemSair.setIcon(Icones.ICONE_SAIR);
		itemSair.addActionListener(ouvinte);
		opcoes.add(itemSair);

//		JMenu menu = barraDeMenu.getMenu(0);
		
		JMenu menuCliente = new JMenu("Cliente");
		menuCliente.setIcon(Icones.ICONE_USUARIO);
		
		JMenu menuPecas = new JMenu("Peças - ASS MODA");
		menuPecas.setIcon(Icones.ICONE_PECAS1);
				
		JMenuItem itemListaDeClientes = new JMenuItem("Lista de Clientes");
		itemListaDeClientes.setIcon(Icones.ICONE_HISTORICO);
		itemListaDeClientes.addActionListener(ouvinte);
		menuCliente.add(itemListaDeClientes);
				
		JMenuItem itemNovoCliente = new JMenuItem("Cadastrar Cliente");
		itemNovoCliente.setIcon(Icones.ICONE_MAIS);
		itemNovoCliente.addActionListener(ouvinte);
		menuCliente.add(itemNovoCliente);		
		
		JMenuItem itemPecas = new JMenuItem("Peças");
		itemPecas.setIcon(Icones.ICONE_PECAS1);
		itemPecas.addActionListener(ouvinte);
		menuPecas.add(itemPecas);		
		
		barraDeMenu.add(menuCliente);
		barraDeMenu.add(menuPecas);
		
		this.setJMenuBar(barraDeMenu);
	}	
	
	private void adicionarLabels() {
		
//		"<html><u> MEU TEXTO </u></html>"
//		GerenteController gerenteController = new GerenteController();

		JLabel label = new JLabel(Icones.FUNDO_ATENDIMENTO);
		label.setBounds(0, 0, 990, 600);
		setContentPane(label);


		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "ATENDIMENTO", 195, 40, 590, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);				
	
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>Histórico de Pedidos</u></html>", 205, 130, 195, 20);

		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>Pedido</u></html>", 775, 130, 80, 20);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 635, 170, 50, 20);
		
//		***********
//		ESSAS DUAS LISTAS (listaCPF e listaPeca) DEVERIAM ESTAR NO MÉTODO "ADICIONAR FIELDS" POIS NÃO SE TRATAM DE LABELS
		modeloListaCPF = new DefaultListModel();
		
		listaCPF = new JList<String>(modeloListaCPF);
		
		listaCPF.addMouseListener(new OuvinteDeCliqueDaListaDeCPFEPeca(this));

		scrollCPF = new JScrollPane(listaCPF);
		scrollCPF.setBounds(680, 190, 110, 75);
		scrollCPF.setVisible(false);
		add(scrollCPF);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Peça", 635, 195, 90, 30);		
		
		modeloListaPeca = new DefaultListModel();
		
		listaPeca = new JList<String>(modeloListaPeca);
		
		listaPeca.addMouseListener(new OuvinteDeCliqueDaListaDeCPFEPeca(this));
		
		scrollPeca = new JScrollPane(listaPeca);
		scrollPeca.setBounds(680, 220, 110, 75);
		scrollPeca.setVisible(false);
		add(scrollPeca);
		
		JLabel la = AdicionadorDeComponentes.adicionarJLabel(this, "Peça(s) que compõem o pedido:", 635, 230, 310, 25);
		
		JLabel labelCifrao = AdicionadorDeComponentes.adicionarJLabel(this, "Total: R$", 700, 545, 95, 20);
		
		LabelPreco = AdicionadorDeComponentes.adicionarJLabel(this, "00.00", 800, 545, 140, 20); 
		
	}		

	private void adicionarFields() {
		
		fieldQuantidade = AdicionadorDeComponentes.adicionarJTextField(this, 833, 200, 35, 20);
		fieldQuantidade.setText("1");
		fieldQuantidade.setEditable(false);
		
		OuvinteDeFocoCampoCPFEPeca ouvinteDeFoco = new OuvinteDeFocoCampoCPFEPeca(this);
		
		peca = AdicionadorDeComponentes.adicionarJTextField(this, 680, 200, 110, 20);
		peca.setDocument(new TeclasPermitidas());
		
		peca.addKeyListener(new KeyListenerCampoPeca(this));
		peca.addFocusListener(ouvinteDeFoco);
		
		try {
			cpf = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 680, 170, 110, 20);
			cpf.addKeyListener(new KeyListenerCampoCPF(this));
			cpf.addFocusListener(ouvinteDeFoco);
		}catch (ParseException e) {
		}				
	}


	private void adicionarBotoes() {

		OuvinteBotoesTelaAtendimento ouvinte = new OuvinteBotoesTelaAtendimento(this);
						
		botaoMenos =  AdicionadorDeComponentes.adicionarJButton(this, "", 800, 200, 30, 20);
		botaoMenos.setIcon(Icones.ICONE_MENOS);
		botaoMenos.addActionListener(ouvinte);
		
		botaoMais = AdicionadorDeComponentes.adicionarJButton(this, "", 872, 200, 30, 20);
		botaoMais.setIcon(Icones.ICONE_MAIS);
		botaoMais.addActionListener(ouvinte);
		
//		System.out.println(identificacao);
		
		String iden[] = identificacao.split(" "); 
		
		String cargo = iden[1];
		
		if(cargo.equals("Gerente") || cargo.equals("root")) {
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 10, 600, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(ouvinte);
		}

		JButton botaoOK = AdicionadorDeComponentes.adicionarJButton(this, "OK", 910, 200, 55, 20);
		botaoOK.addActionListener(ouvinte);
		
		JButton botaoDetalhes = AdicionadorDeComponentes.adicionarJButton(this, "Detalhes", 505, 600, 105, 20);
		botaoDetalhes.setIcon(Icones.ICONE_DETALHES);
		botaoDetalhes.addActionListener(ouvinte);
		
		JButton botaoRemover = AdicionadorDeComponentes.adicionarJButton(this, "Remover", 680, 490, 240, 20);
		botaoRemover.addActionListener(ouvinte);
		
		JButton botaoAvancar = AdicionadorDeComponentes.adicionarJButton(this, "Avançar", 855, 600, 105, 20);
		botaoAvancar.setIcon(Icones.ICONE_AVANCAR);
		botaoAvancar.addActionListener(ouvinte);		
		
	}
	
	
	private void adicionarTabelaDePedidos() {
		
		modelo1 = new DefaultTableModel();
		tabela = new JTable(modelo1);	

		VendaControl vendaControl = new VendaControl();
		
		ArrayList<VendaDTO> vendas = vendaControl.getVendas().getVendas();
		
		if(vendas.size() == 0) {
			modelo1.addColumn("Não há vendas");
		}else {
			modelo1.addColumn("Nº da Venda");
			modelo1.addColumn("CPF do cliente");
			modelo1.addColumn("Valor");
			modelo1.addColumn("Pagamento");
			modelo1.addColumn("Data da Venda");			
			
//		\/ altera o tamanho de uma determinada célula do JTable
//			tabela.getColumnModel().getColumn(0).setPreferredWidth(85);
//			tabela.getColumnModel().getColumn(1).setPreferredWidth(103);
//			tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
//			tabela.getColumnModel().getColumn(5).setPreferredWidth(49);
			
			SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			String tipoPagamento = null;
			
			Iterator iterator = new IteratorVendas(vendas);
			
			while(iterator.hasNext()){
				VendaDTO venda = (VendaDTO) iterator.next();
				if(venda.getPagamento() == 0) {
					tipoPagamento = "À vista";
				}else {
//					tipoPagamento = "Cartão";
					tipoPagamento = "Cartão ("+venda.getTipoDeCartao()+")";
				}
				
				Object[] linha = {venda.getNumVenda(), venda.getCpfCliente(), venda.getPreco(), tipoPagamento, formataData.format(venda.getDataCriacao())};
				
				modelo1.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 170, 600, 404);
		add(scroll);
		
	}

	private void adicionarTabelaDePecasQueEstaoCompondoOPedido() {

		modelo2 = new DefaultTableModel();
		modelo2.addColumn("ID");
		modelo2.addColumn("Nome");
		modelo2.addColumn("Preco");
		modelo2.addColumn("Quantidade");
		modelo2.addColumn("Quantidade_estoque");				

		tabelaDePecasQueCompoemOPedido = new JTable(modelo2);
		
//		escondendo a coluna ID
		tabelaDePecasQueCompoemOPedido.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaDePecasQueCompoemOPedido.getColumnModel().getColumn(0).setMaxWidth(0);
		
		tabelaDePecasQueCompoemOPedido.getColumnModel().getColumn(4).setMinWidth(0);
		tabelaDePecasQueCompoemOPedido.getColumnModel().getColumn(4).setMaxWidth(0);		
		
		JScrollPane scroll = new JScrollPane(tabelaDePecasQueCompoemOPedido);
		scroll.setBounds(645, 260, 310, 220);
		add(scroll);
		
	}
	
	private class OuvinteBotoesTelaAtendimento implements ActionListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteBotoesTelaAtendimento(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}


		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			if(botao.equals("OK")) {

				String pecaDigitada = peca.getText();
				
				if(!pecaDigitada.equals("")) {
					
					ProdutoDTO produtoDTO = new ProdutoDTO();
					produtoDTO.setId(Integer.parseInt(pecaDigitada));
					
					ProdutoControl produtoControl = new ProdutoControl();
					
					produtoDTO = produtoControl.getProduto(produtoDTO);
					
//					"gambiarra" para determinar se ouve resultado na consulta, tendo em vista que o nome de um produto é NOT NULL ele não pode estar null no objeto DTO que está sendo retornado (no caso o null seria o valor padrão para variavel String nome desse objeto DTO)
 
					if(produtoDTO.getNome() == null) {
						JOptionPane.showMessageDialog(telaDeAtendimento, "Essa peça não existe!", "erro", JOptionPane.ERROR_MESSAGE);
					}else {
						
						int quantidadeNoEstoque = produtoDTO.getQuantEstoque();
						int quantidadeSelecionada = Integer.parseInt(fieldQuantidade.getText());
						
						if(quantidadeNoEstoque == 0) {
							JOptionPane.showMessageDialog(telaDeAtendimento, "Peça indisponível no estoque!", "erro", JOptionPane.ERROR_MESSAGE);
						}else if(quantidadeNoEstoque < quantidadeSelecionada) {
							JOptionPane.showMessageDialog(telaDeAtendimento, "Só existem "+quantidadeNoEstoque+" peças no estoque!", "erro", JOptionPane.ERROR_MESSAGE);
						}else {
							
							modelo2.addRow(new Object[] {produtoDTO.getId(), produtoDTO.getNome(), produtoDTO.getPreco(), quantidadeSelecionada, quantidadeNoEstoque});
							
							float preco = Float.parseFloat(LabelPreco.getText()) + produtoDTO.getPreco() * Integer.parseInt(fieldQuantidade.getText());
							
							LabelPreco.setText(Float.toString(preco));
							
							fieldQuantidade.setText("1");
							peca.setText("");
						}
						
					}
					
					
				}else {
					JOptionPane.showMessageDialog(telaDeAtendimento, "Digite o número de uma peça!", "Campo vazio", JOptionPane.ERROR_MESSAGE);			}
				
				
			}else if(botao.equals("Remover")) {

				int linha = tabelaDePecasQueCompoemOPedido.getSelectedRow();
				
				if(linha != -1) {
					float valorASerSubtraido = (float) tabelaDePecasQueCompoemOPedido.getValueAt(linha, 2);
					
					float preco = Float.parseFloat(LabelPreco.getText()) - valorASerSubtraido * (int) tabelaDePecasQueCompoemOPedido.getValueAt(linha, 3);
					
					LabelPreco.setText(Float.toString(preco));
					
					modelo2.removeRow(linha);
					
				}else {
					JOptionPane.showMessageDialog(telaDeAtendimento, "Selecione uma peça!", "Nenhuma peça selecionada", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}else if(botao.equals("Avançar")) {
//				é necessário verificar se o cpf realmente existe no banco tem q ver isso o pq do erro e poe um botao pra voltar pra tela do gerente s  o erro ali do lucro diario?e  uphemga o sql e poe no post
				if(!cpf.getText().equals("   .   .   -  ")) {
					if(tabelaDePecasQueCompoemOPedido.getRowCount() == 0) {
						JOptionPane.showMessageDialog(telaDeAtendimento, "Adicione peças à compra!", "Compra vazia", JOptionPane.ERROR_MESSAGE);
					}else {
						
						ArrayList<ProdutoDTO> produtosEscolhidos = new ArrayList<ProdutoDTO>();
						
						for(int i = 0; i < modelo2.getRowCount(); i++) {
							ProdutoDTO produtoDTO = new ProdutoDTO();;
							
//							System.out.println("id: "+ tabelaDePecasQueCompoemOPedido.getValueAt(i, 0)+" - nome: "+tabelaDePecasQueCompoemOPedido.getValueAt(i, 1));
							produtoDTO.setId((int)tabelaDePecasQueCompoemOPedido.getValueAt(i, 0));
							produtoDTO.setNome((String)tabelaDePecasQueCompoemOPedido.getValueAt(i, 1));
							produtoDTO.setPreco((float)tabelaDePecasQueCompoemOPedido.getValueAt(i, 2));
							produtoDTO.setQuantidade((int)tabelaDePecasQueCompoemOPedido.getValueAt(i, 3));
							produtoDTO.setQuantEstoque((int)tabelaDePecasQueCompoemOPedido.getValueAt(i, 4));
							
							produtosEscolhidos.add(produtoDTO);
							
//							
							
							
						}
						
						
						VendaDTO vendaDTO = new VendaDTO();
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//						se for possível excluir a vnda deve-se pegar  o id da venda de outra forma
						
						VendaControl vendaControl = new VendaControl();
						int numVenda = vendaControl.getNumVenda().getNumVenda();
						
						vendaDTO.setNumVenda(numVenda );
						vendaDTO.setProdutosDaVenda(produtosEscolhidos);
						vendaDTO.setCpfCliente(cpf.getText());
						vendaDTO.setPreco(Float.parseFloat(LabelPreco.getText()));
						vendaDTO.setIdFuncionario(codigoDoFuncionarioLogado);
							
						
						TelaDePagamento telaDePagamento = new TelaDePagamento(codigoDoFuncionarioLogado, vendaDTO, telaDeAtendimento, identificacao);
						telaDePagamento.setLocationRelativeTo(telaDeAtendimento);
					}
				}else {
					JOptionPane.showMessageDialog(telaDeAtendimento, "Preencha o campo CPF!", "Campo vazio", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Detalhes")) {

				
				int linha = tabela.getSelectedRow(); 
				
				if(linha != -1) {
					
					int idCompra = (int) tabela.getValueAt(linha, 0);
					
					TelaDePecasDeUmaCompra telaDePecasDeUmaCompra = new TelaDePecasDeUmaCompra(idCompra);
					telaDePecasDeUmaCompra.setLocationRelativeTo(telaDeAtendimento);
					
				}else {
					JOptionPane.showMessageDialog(telaDeAtendimento, "Selecione uma compra!", "Nenhuma compra selecionada", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}else if(botao.equals("Voltar")) {
				TelaDeGerenciamento telaDeGerenciamento = new TelaDeGerenciamento(codigoDoFuncionarioLogado,identificacao);
				telaDeGerenciamento.setLocationRelativeTo(telaDeAtendimento);
				telaDeAtendimento.dispose();
			}else if(botao.equals("Lista de Clientes")) {
				TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoFuncionarioLogado, identificacao);
				telaDeClientes.setLocationRelativeTo(telaDeAtendimento);
			}else if(botao.equals("Cadastrar Cliente")) {
				TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente(codigoDoFuncionarioLogado, cpf, identificacao);
				telaCadastroCliente.setLocationRelativeTo(telaDeAtendimento);
			}else if(botao.equals("Peças")) {
//				se a atendente apenas vizualizar os produtos não é necessário passar o id no constrtor já q os produtos só seram cadastrados pelo root
				TelaDePecas telaDeProdutos = new TelaDePecas(codigoDoFuncionarioLogado, true, identificacao);
				telaDeProdutos.setLocationRelativeTo(telaDeAtendimento);
			}else if(botao.equals("Sair")) {
				TelaDeLogin telaDeLogin = new TelaDeLogin();
				telaDeLogin.setLocationRelativeTo(telaDeAtendimento);
				telaDeAtendimento.dispose();
			}else {
//				ELSE PARA VERIFICAR O OBJETO QUE LANÇOU O EVENTO E NÃO O TEXTO DESSE OBJETO
				Object b = e.getSource();
				
				int qtdAtual = Integer.parseInt(fieldQuantidade.getText());
				
				if(b.equals(botaoMais)) {
					fieldQuantidade.setText(Integer.toString(qtdAtual + 1));
				}else {
					if(qtdAtual != 1) {
						fieldQuantidade.setText(Integer.toString(qtdAtual - 1));
					}					
					
				}				
				
			}								
		
		}
		
	}
	
//	FOCUS LISTENER CAMPO PEÇA E CPF
	
	private class OuvinteDeFocoCampoCPFEPeca implements FocusListener{

		private TelaDeAtendimento telaDeAtendimento;

		public OuvinteDeFocoCampoCPFEPeca(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		@Override
		public void focusGained(FocusEvent e) {
				
		}

		@Override
		public void focusLost(FocusEvent e) {
			scrollCPF.setVisible(false);
			scrollPeca.setVisible(false);
		}
		
	}
	
//	KEY LISTENER
	
	private class KeyListenerCampoPeca implements KeyListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public KeyListenerCampoPeca(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
//			retirando a mascara de um JFormattedTextField
//			seuJFormattedTextField.setFormatterFactory(null);

			int pecaDigitada = 0 ;
			try {
				pecaDigitada = Integer.parseInt(peca.getText());
			}catch (NumberFormatException er) {
			}

//			System.out.println("PEÇA: "+PecaDigitada);
			

			ProdutoControl produtoController = new ProdutoControl();
			
			
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.setId(pecaDigitada);
			

			ArrayList<String> pecas = produtoController.recuperarIdDeTodasPecas(produtoDTO).getIdDeTodosProdutos();

//			ArrayList<String> pecas = clienteController.recuperarCPFDeTodosClientes(clienteDTO).getCpfDeTodosClientes();

			modeloListaPeca.removeAllElements();
			scrollPeca.setVisible(false);
			
			for(String peca : pecas) {
				modeloListaPeca.addElement(peca);
			}

			if(pecas.size() != 0) {
				scrollPeca.setVisible(true);
			}
		}
	}
			
	private class KeyListenerCampoCPF implements KeyListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public KeyListenerCampoCPF(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
//			retirando a mascara de um JFormattedTextField
//			seuJFormattedTextField.setFormatterFactory(null);

			String cpfDigitado = cpf.getText().replace(".","").replace("-","").trim();

//			System.out.println("CPF: "+cpfDigitado);
			
			ClienteControl clienteController = new ClienteControl();
			
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setCpf(cpfDigitado);
			
			ArrayList<String> cpfs = clienteController.recuperarCPFDeTodosClientes(clienteDTO).getCpfDeTodosClientes();

			modeloListaCPF.removeAllElements();
			scrollCPF.setVisible(false);
			
			for(String cpf : cpfs) {
				modeloListaCPF.addElement(cpf);
			}

			if(cpfs.size() != 0) {
				scrollCPF.setVisible(true);
			}
		}
	}
		
//	MOUSE LISTENER
	
	private class OuvinteDeCliqueDaListaDeCPFEPeca implements MouseListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteDeCliqueDaListaDeCPFEPeca(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
//			 TODO Auto-generated method stub	
			 cpf.setText(listaCPF.getSelectedValue());
			 peca.setText(listaPeca.getSelectedValue());
//			System.out.println("CPF selecionado: "+lista.getSelectedValue());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
//	public static void main(String[] args) {
//		new TelaDeAtendimento(3);
//	}
	

}
