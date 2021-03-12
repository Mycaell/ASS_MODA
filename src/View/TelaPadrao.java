package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public abstract class TelaPadrao extends JFrame{
	
	
	public TelaPadrao(String titulo) {
		super(titulo);

		metodoPadrao();
//		this.setIconImage(Icones.ICONE_JANELA.getImage());
//		this.getContentPane().setBackground(Color.lightGray);
		
//		this.getContentPane().setBackground(Color.DARK_GRAY);
//		this.getContentPane().setBackground(Color.orange);

//		
		
//		adicionarComponentesGraficos();
//		this.setVisible(true);
		
////        // SETA LOOK AND FEEL
		
	}

	public final void metodoPadrao() {
		this.setSize(800,460);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 800, 400);
		setContentPane(label);

		
        try {
            // AQUI VOCÊ SETA O NOME DA CLASSE REFERENTE A CADA TEMA !
				
				String tema = "com.jtattoo.plaf.mint.MintLookAndFeel";
				
            // AQUI VC SETA O LOOK AND FEEL
            UIManager.setLookAndFeel(tema);
        } catch (InstantiationException | IllegalAccessException  |
                     UnsupportedLookAndFeelException | ClassNotFoundException e) {
            System.out.println("Erro LAF : " + e.getMessage());
        }

		
		adicionarComponentesGraficos();
		
		
		this.setVisible(true);
	}
	
	public abstract void adicionarComponentesGraficos();

	private String identificacao;
	
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public void adicionarIdentificacao(String identificacao) {
		AdicionadorDeComponentes.adicionarJLabel(this, identificacao,  5, 5, 1000, 25);
		setIdentificacao(identificacao);
	}
	
}
