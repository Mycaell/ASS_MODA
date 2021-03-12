package View_Utilidades;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class GeradorDeRelatorios {
//	cria um main aqui pra testar amor e mais rapido é n rsr claro q e ai toda vez e so rodar epra entao é q eu acho q eu tô pegando uns dados q nao tem
	
	public void gerarNotaFiscal(VendaDTO venda, PagamentoDTO pagamento) {
	
		Document documento = new Document(PageSize.A4, 72,72,72,72);		
		
			try {
				PdfWriter.getInstance(documento, new FileOutputStream("Nota Fiscal da compra Nº "+venda.getNumVenda()+".pdf"));
				
				
				documento.open();
				
				String cpfCliente = venda.getCpfCliente(); 
//				poe o a rua da loja?acho q n ta bm assim ja
				
				Paragraph p1 = new Paragraph("ASS MODA\nRua Luiz Barbosa de Oliveira\nMonteiro - PB");
				p1.setAlignment(Element.ALIGN_CENTER);
				documento.add(p1);

				
//				Paragraph p1 = new Paragraph("Rua Luiz Barbosa de Oliveira");
//				p1.setAlignment(Element.ALIGN_CENTER);
//				documento.add(p1);

				
				
				
				Paragraph tracejado1 = new Paragraph("\n----------------------------------------------------------------------------------------------------------------\n");
				documento.add(tracejado1);
				
//				"                                        "
				Paragraph p2 = new Paragraph("Cliente: "+ venda.getCpfCliente()+"                                                         Data: "+new SimpleDateFormat("dd/MM/yyyy HH:mm").format(venda.getDataCriacao())+"\n");
				documento.add(p2);
				
				
				String tipoPagamento = null;
				if(pagamento.getTipo() == 0) {
					tipoPagamento = "À vista";
				}else {
//							tipoPagamento = "Cartão";
					tipoPagamento = "Cartão ("+pagamento.getTipoCartao()+")";
				}
				
				
				Paragraph p3 = new Paragraph("Forma de Pagamento: "+tipoPagamento+"                                 Parcelas: "+pagamento.getQuantParcelas());
				documento.add(p3);
				
				documento.add(tracejado1);
				
				Paragraph p4 = new Paragraph("codigo                       nome                                     v/unidade                       qtd\n");
				documento.add(p4);
				
//				amor?
				
				
				for (ProdutoDTO produto : venda.getProdutosDaVenda()) {
					
					int codigo = produto.getId();
					String nome = produto.getNome();
					float preco = produto.getPreco();
					int qtd = produto.getQuantidade();
					
					
					Paragraph p5 = new Paragraph(codigo+"                  "+nome+"                                              "+preco+"                            "+qtd);
					documento.add(p5);

					documento.add(tracejado1);
					
				}
				
				
				
				Paragraph p = new Paragraph("                                                                                                TOTAL:R$ "+venda.getPreco());
				documento.add(p);
				
				
				documento.close();
			
			
			
			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
			}
		
			


		
	}
	
//	public static void main(String[] args) {
//		new GeradorDeRelatorios().gerarRelatorios("tst", "bla", "aaaa");
//		System.out.println("relatorio gerado");
//	}
	public void gerarRelatorios(String nomepdf, String titulo, String texto) {
		
		Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String sdf = new SimpleDateFormat("dd/MM/yyyy").format(timestamp.getTime());
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream(nomepdf+".pdf"));
			doc.open();
			
			
			Paragraph p1 = new Paragraph("ASS MODA\nRua Luiz Barbosa de Oliveira\nMonteiro - PB");
			p1.setAlignment(Element.ALIGN_CENTER);
			doc.add(p1);
			
			Paragraph tracejado1 = new Paragraph("\n----------------------------------------------------------------------------------------------------------------\n");
			doc.add(tracejado1);
			Font font = new Font(FontFamily.COURIER, 15, Font.BOLD);
			Paragraph cabecalho = new Paragraph("\n"+titulo, font);
			cabecalho.setAlignment(Element.ALIGN_CENTER);
			doc.add(cabecalho);	
			Paragraph paragrafo = new Paragraph("\n"+texto);
			doc.add(paragrafo);	
			Paragraph data = new Paragraph("\nDATA: "+sdf);
			doc.add(data);	
			doc.close();

		} catch (DocumentException | IOException e) {
		}		
	}
	
}
