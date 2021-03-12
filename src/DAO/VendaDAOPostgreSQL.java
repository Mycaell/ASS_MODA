package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ClienteDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class VendaDAOPostgreSQL{

	private Connection con=null;
	private PreparedStatement pstm=null;
	private ResultSet rset=null;
	
	
	public VendaDTO getNumVenda() {

		String sql = "SELECT max(v.num_venda) as id from venda as v";
		
		VendaDTO vendaDTO = new VendaDTO();
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			int id_venda = 0;
			
			while(rset.next()) {
				id_venda = rset.getInt("id");
			}

			
			vendaDTO.setNumVenda(id_venda);;
			
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vendaDTO;
	
	
	
	
	}
	
	public VendaDTO getComprasDoCliente(ClienteDTO clienteDTO) {
		
		
//		String sql = "select v.num_venda, sum(pro.preco * v_P.quant_produto) as preco, v.data_venda, pag.tipo as pagamento from venda as v, pagamento as pag, produto as pro, venda_produto as v_p where v.id_cliente = '"+clienteDTO.getId()+"' and pag.id = v.id_pagamento and v.num_venda = v_p.num_venda and pro.id = v_p.id_produto group by (v.num_venda, pag.tipo) order by v.num_venda desc";

//		String sql = "select v.num_venda, sum(pro.preco * v_P.quant_produto) as preco, v.data_venda, pag.tipo as pagamento, pag.tipo_de_cartao from venda as v, pagamento as pag, produto as pro, venda_produto as v_p where v.id_cliente = '"+clienteDTO.getId()+"' and pag.id = v.id_pagamento and v.num_venda = v_p.num_venda and pro.id = v_p.id_produto group by (v.num_venda, pag.tipo, pag.tipo_de_cartao) order by v.num_venda desc";
		
		String sql = "select v.num_venda, v.valor, v.data_venda, pag.tipo as pagamento, pag.tipo_de_cartao from venda as v, pagamento as pag where v.id_cliente = '"+clienteDTO.getId()+"' and pag.id = v.id_pagamento order by v.num_venda desc";
		
		ArrayList<VendaDTO> compras = new ArrayList<VendaDTO>();	
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				VendaDTO compra = new VendaDTO();
				
				compra.setNumVenda(rset.getInt("num_venda"));
				compra.setPreco(rset.getFloat("valor"));
				compra.setPagamento(rset.getInt("pagamento"));
				compra.setTipoDeCartao(rset.getString("tipo_de_cartao"));
				compra.setDataCriacao(rset.getTimestamp("data_venda"));
				compras.add(compra);
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
		
		VendaDTO vendaDTO = new VendaDTO();
		vendaDTO.setVendas(compras);
		return vendaDTO;
			
	}
	
	public void efetuarVendaCartao(PagamentoDTO pdto) {
		
		String sql = "INSERT INTO pagamento(tipo, qtd_parcelas, data_pagamento, hora_pagamento, num_cartao, tipo_de_cartao, id_bandeira) VALUES(?,?,?,?,?,?,?)";
		
		try{
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);

			pstm.setInt(1, pdto.getTipo());
			pstm.setInt(2, pdto.getQuantParcelas());
			pstm.setDate(3, pdto.getDataPagamento());
			pstm.setTime(4, pdto.getHoraPagamento());
			pstm.setString(5, pdto.getNumCartao());
			pstm.setString(6, pdto.getTipoCartao());
			pstm.setInt(7, pdto.getIdBandeira());
			
			pstm.execute();		

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
			
			
	public void efetuarVendaAVista(PagamentoDTO pdto) {
				
		String sql = "INSERT INTO pagamento(tipo, qtd_parcelas, data_pagamento, hora_pagamento, num_cartao, tipo_de_cartao, id_bandeira) VALUES(?,null,?,?,null,null,null)";
		
		try{
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, pdto.getTipo());
			pstm.setDate(2, pdto.getDataPagamento());
			pstm.setTime(3, pdto.getHoraPagamento());
			
			pstm.execute();		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void finalizaVenda(VendaDTO vdto) {
		
		try {
			String sql = "select max(p.id) as id from pagamento as p";
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();
			int id_pagamento = 0;
			
			while(rset.next()) {
				id_pagamento = rset.getInt("id");
			}

			sql = "INSERT INTO venda(valor, data_venda, id_cliente, id_funcionario, id_pagamento) VALUES(?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			
			pstm.setFloat(1, vdto.getPreco());
			pstm.setTimestamp(2, vdto.getDataCriacao());
			pstm.setInt(3, vdto.getIdCliente());
			pstm.setInt(4, vdto.getIdFuncionario());
			pstm.setInt(5, id_pagamento);
	
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void finalizaVendaProduto(VendaDTO vdto) {
		
		try {
			String sql = "SELECT max(v.num_venda) as id from venda as v";
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
	
			rset = pstm.executeQuery();
	
			int id_venda = 0;
			
			while(rset.next()) {
				id_venda = rset.getInt("id");
			}
			
			sql = "INSERT INTO venda_produto(num_venda, id_produto, quant_produto) VALUES (?,?,?)";
			pstm = con.prepareStatement(sql);	
			
			String sqlUpdate = "UPDATE produto set quant_estoque = ? where id = ?";
			PreparedStatement preparador = con.prepareStatement(sqlUpdate);	
			
			for (ProdutoDTO peca : vdto.getProdutosDaVenda()) {
				
				int idDaPeca = peca.getId();
				int quantidade = peca.getQuantidade();
				
				pstm.setInt(1, id_venda);
				pstm.setInt(2, idDaPeca);
				pstm.setInt(3, quantidade);
				
				pstm.execute();
				
				preparador.setInt(1, peca.getQuantEstoque() - quantidade); 
				preparador.setInt(2, idDaPeca);
				
				preparador.executeUpdate();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	
	public void editarVenda(VendaDTO vdto) {
		// TODO Auto-generated method stub
		
	}

	public void cancelarVenda(VendaDTO vdto, PagamentoDTO pdto) {
		// TODO Auto-generated method stub
		
	}

	public VendaDTO getVendas() {
		
//		String sql = "select v.num_venda, sum(pro.preco * v_P.quant_produto) as preco, c.cpf, v.data_venda, pag.tipo as pagamento from venda as v, pagamento as pag, produto as pro, cliente as c, venda_produto as v_p where v.id_cliente = 1 and v.id_cliente = c.id and pag.id = v.id_pagamento and v.num_venda = v_p.num_venda and pro.id = v_p.id_produto group by (v.num_venda,c.cpf,pag.tipo) order by v.num_venda desc";
		
		String sql = "select v.num_venda, v.valor, c.cpf, v.data_venda, pag.tipo as pagamento, pag.tipo_de_cartao from venda as v, pagamento as pag, cliente as c where v.id_cliente = c.id and pag.id = v.id_pagamento order by v.num_venda desc";
		
		ArrayList<VendaDTO> compras = new ArrayList<VendaDTO>();	
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				VendaDTO compra = new VendaDTO();
				
				compra.setNumVenda(rset.getInt("num_venda"));
				compra.setPreco(rset.getFloat("valor"));
				compra.setCpfCliente(rset.getString("cpf"));
				compra.setPagamento(rset.getInt("pagamento"));
				compra.setTipoDeCartao(rset.getString("tipo_de_cartao"));
				compra.setDataCriacao(rset.getTimestamp("data_venda"));
				
				compras.add(compra);
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
		VendaDTO vendaDTO = new VendaDTO();
		vendaDTO.setVendas(compras);
		return vendaDTO;
	}

	public VendaDTO getVenda(VendaDTO vdto, PagamentoDTO pdto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public VendaDTO getProdutosVenda(VendaDTO vdto) {
		String sql = "select v.num_venda, pro.nome, pro.preco from venda as v, produto as pro, venda_produto as v_p where pro.id = v_p.id_produto and v.num_venda = v_p.num_venda and v.num_venda=?";
		
		
		VendaDTO venda = new VendaDTO();
		ArrayList<ProdutoDTO> produtosDaVenda = new ArrayList<ProdutoDTO>();	
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, vdto.getNumVenda());
			rset = pstm.executeQuery();
			
			

			while(rset.next()) {
				
				ProdutoDTO produto = new ProdutoDTO();
				
				produto.setNome(rset.getString("nome"));
				produto.setPreco(rset.getFloat("preco"));
				
				produtosDaVenda.add(produto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		venda.setProdutosDaVenda(produtosDaVenda);
		return venda;
	}

}
