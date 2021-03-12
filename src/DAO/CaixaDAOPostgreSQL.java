package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.CaixaDTO;
import DTO.ProdutoDTO;

public class CaixaDAOPostgreSQL{

	private Connection con=null;
	private PreparedStatement pstm=null;
	private ResultSet rset=null;
	
	public CaixaDTO lucroDiario() {
		String sql = "select sum(v.valor) as lucro_diario from venda as v where  cast(data_venda as date)=CURRENT_DATE group by (current_date)"; 

		CaixaDTO caixadto = new CaixaDTO();
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {	
				caixadto.setLucroDiario(rset.getFloat("lucro_diario"));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return caixadto;
	}

	public CaixaDTO lucroMensal() {
		String sql = "select sum(v.valor) as lucro_mensal from venda as v where EXTRACT(month FROM v.data_venda)>= Extract(month from Now()) "
				+ "and EXTRACT(year FROM v.data_venda)>= Extract(year from Now()) group by (EXTRACT(month FROM v.data_venda))";
		
		CaixaDTO caixadto = new CaixaDTO();
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {	
				caixadto.setLucroMensal(rset.getFloat("lucro_mensal"));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return caixadto;
	}

	public CaixaDTO valorDeCaixa() {
		String sql = "select sum(v.valor) as valor_caixa from venda as v";
		
		CaixaDTO caixadto = new CaixaDTO();
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {	
				caixadto.setValorDeCaixa(rset.getFloat("valor_caixa"));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return caixadto;
	}
	
	public ProdutoDTO getProdutoMaisVendidos() {
		String sql = "select nome, marca, cor, categoria from produto where id in(select id from venda_produto group by produto.id order by sum(quant_produto) desc) limit 5;";
		ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();	
		try {
				con= Conexao.getConnection();
				pstm = con.prepareStatement(sql);
				rset = pstm.executeQuery();			 

				 while(rset.next()) {
					 ProdutoDTO produto = new ProdutoDTO();
					 produto.setNome(rset.getString("nome"));
					 produto.setMarca(rset.getString("marca"));
					 produto.setCor(rset.getString("cor"));
					 produto.setCategoria(rset.getString("categoria"));
					 produtos.add(produto);
				 }
				
			} catch (Exception e) {
					 
				System.out.println("Erro!");
			}
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setProdutos(produtos);
		return produtoDTO;		
	}
	
}
