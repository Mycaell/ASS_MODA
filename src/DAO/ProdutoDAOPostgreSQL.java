package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.CategoriaDTO;
import DTO.CorDTO;
import DTO.ProdutoDTO;
import DTO.TamanhoDTO;

public class ProdutoDAOPostgreSQL {

	private Connection con=null;
	private PreparedStatement pstm=null;
	private ResultSet rset=null;
	
//	apenas eu tenho esse método
	public ProdutoDTO recuperarIdDeTodasPecas(ProdutoDTO produtoDTO) {
//		String sql = "SELECT id FROM produto WHERE id LIKE '"+Integer.toString(produtoDTO.getId())+"%' ORDER BY id";

		String sql = "SELECT id FROM produto WHERE cast (id as text) like '"+Integer.toString(produtoDTO.getId())+"%' ORDER BY id";
		
//		String sql = "select id from produto";
		
		ArrayList<String> ids = new ArrayList<String>();
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			ResultSet resultado = pstm.executeQuery();
			
			while(resultado.next()) {
				ids.add(resultado.getString("id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		produtoDTO.setIdDeTodosProdutos(ids);
		return produtoDTO;
	}
	
	
	
	public void cadastrarProduto(ProdutoDTO pdto) {
		String sql = "INSERT INTO produto(nome, preco, id_funcionario, quant_estoque, cor, tamanho, marca,"
				+ "descricao, categoria) VALUES(?,?,?,?,?,?,?,?,?)"; 
		 
		 try {
			 con= Conexao.getConnection();
			 pstm = con.prepareStatement(sql);
			 pstm.setString(1, pdto.getNome());
			 pstm.setFloat(2, pdto.getPreco());
			 pstm.setInt(3, pdto.getIdFuncionario());
			 pstm.setInt(4, pdto.getQuantEstoque());
			 pstm.setString(5, pdto.getCor());
			 pstm.setString(6, pdto.getTamanho());
			 pstm.setString(7, pdto.getMarca());
			 pstm.setString(8, pdto.getDescricao());
			 pstm.setString(9, pdto.getCategoria());
			 pstm.execute();

		 } catch (Exception e) {
			System.out.println("Erro ao adicionar produto!");
			e.printStackTrace();
		}
		
	}

	public void removerProduto(ProdutoDTO pdto) {
		String sql = "DELETE FROM produto WHERE id = ?";
		 
		try {
			con = Conexao.getConnection();
			 
			pstm = con.prepareStatement(sql);
			 
			pstm.setInt(1, pdto.getId());
			 
			pstm.execute();
			 
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}

	public void editarProduto(ProdutoDTO pdto) {
		String sql = "UPDATE produto SET nome=?, preco=?, quant_estoque=?, cor=?, tamanho=?, marca=?,"
				+ "descricao=?, categoria=? WHERE id = ?";

			try {
				 con = Conexao.getConnection();
				 pstm = con.prepareStatement(sql);

				 pstm.setString(1, pdto.getNome()); 
				 pstm.setFloat(2, pdto.getPreco());
				 pstm.setInt(3, pdto.getQuantEstoque());
				 pstm.setString(4, pdto.getCor());
				 pstm.setString(5, pdto.getTamanho());
				 pstm.setString(6, pdto.getMarca());
				 pstm.setString(7, pdto.getDescricao());
				 pstm.setString(8, pdto.getCategoria());
				 pstm.setInt(9, pdto.getId());
				 pstm.execute();
				 
			} catch (Exception e) {				 
				 System.out.println("Erro!");
				 e.printStackTrace();
				 }
	}

	public ProdutoDTO getProduto(ProdutoDTO pdto) {
		String sql = "select * from produto where id = ?";
		ProdutoDTO p =new ProdutoDTO();		 
			try {
				con= Conexao.getConnection();
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, pdto.getId());
				rset = pstm.executeQuery();			 

				 while(rset.next()) {					
					 p.setId(rset.getInt("id"));
					 p.setNome(rset.getString("nome"));
					 p.setPreco(rset.getFloat("preco"));
					 p.setIdFuncionario(rset.getInt("id_funcionario"));
					 p.setQuantEstoque(rset.getInt("quant_estoque"));
					 p.setCor(rset.getString("cor"));
					 p.setTamanho(rset.getString("tamanho"));
					 p.setMarca(rset.getString("marca"));
					 p.setDescricao(rset.getString("descricao"));
					 p.setCategoria(rset.getString("categoria"));
					 break;
				 }
				
			} catch (Exception e) {
					 
				System.out.println("Erro!");
			}
			return p;
	}
	

	

public ProdutoDTO getProdutos() {
		
		String sql = "select * from produto";
		
		ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		
		try {
			con = Conexao.getConnection();
			pstm = con.prepareStatement(sql);
			ResultSet resultado = pstm.executeQuery();
			
			while(resultado.next()) {
				ProdutoDTO produto = new ProdutoDTO();
				
				produto.setId(resultado.getInt("id"));
				produto.setNome(resultado.getString("nome"));
				produto.setPreco(resultado.getFloat("preco"));
				produto.setCategoria(resultado.getString("categoria"));
				produto.setQuantEstoque(resultado.getInt("quant_estoque"));
				produto.setCor(resultado.getString("cor"));
				produto.setTamanho(resultado.getString("tamanho"));
				produto.setMarca(resultado.getString("marca"));
				produto.setDescricao(resultado.getString("descricao"));
				
				produtos.add(produto);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setProdutos(produtos);
		return produtoDTO;
		
		
	}




	public void adicionarCor(CorDTO cdto) {
//		o jeito e remover pelo nome msm isso q achei q vc tava fznd 
		String sql = "INSERT INTO cores_produtos(nome) VALUES(?)"; 
		 
		 try {
			 con= Conexao.getConnection();
			 pstm = con.prepareStatement(sql);
			 pstm.setString(1, cdto.getNomeCor());
			 pstm.execute();
		
		} catch (Exception e) {
			System.out.println("Erro ao adicionar cor!");
			e.printStackTrace();
		}
	}

	public void removerCor(CorDTO cdto) {
		String sql = "DELETE FROM cores_produtos WHERE nome = ?";
		 
		try {
			con = Conexao.getConnection();
			 
			pstm = con.prepareStatement(sql);
			 
			pstm.setString(1, cdto.getNomeCor());
			 
			pstm.execute();
			 
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}

	public CorDTO getCores() {
		String sql = "SELECT * FROM cores_produtos";
		 
		ArrayList<CorDTO> cores = new ArrayList<>();
		try {
			con= Conexao.getConnection();
			pstm = con.prepareStatement(sql);		 
			 
			rset = pstm.executeQuery();
			 
			while(rset.next()){
				CorDTO c= new CorDTO();
				c.setNomeCor(rset.getString("nome"));
				c.setId(rset.getInt("id"));
				cores.add(c);
			 }
			 
		} catch (Exception e) {
			System.out.println("Erro ao recuperar pizzas!");
		}
						
		CorDTO cordto = new CorDTO();
		cordto.setCores(cores);
		return cordto;
	}

	public void adicionarTamanho(TamanhoDTO tdto) {
		String sql = "INSERT INTO tamanhos_produtos(tamanho) VALUES(?)"; 
		 
		 try {
			 con= Conexao.getConnection();
			 pstm = con.prepareStatement(sql);
			 pstm.setString(1, tdto.getTamanho());
			 pstm.execute();
		
		} catch (Exception e) {
			System.out.println("Erro ao adicionar tamanho!");
			e.printStackTrace();
		}
	}

	public void removerTamanho(TamanhoDTO tdto) {
		String sql = "DELETE FROM tamanhos_produtos WHERE tamanho = ?";
		 
		try {
			con = Conexao.getConnection();
			 
			pstm = con.prepareStatement(sql);
			 
			pstm.setString(1, tdto.getTamanho());
			 
			pstm.execute();
			 
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}

	public TamanhoDTO getTamanhos() {
		String sql = "SELECT * FROM tamanhos_produtos";
		 
		ArrayList<TamanhoDTO> tamanhos = new ArrayList<>();
		try {
			con= Conexao.getConnection();
			pstm = con.prepareStatement(sql);		 
			 
			rset = pstm.executeQuery();
			 
			while(rset.next()){
				TamanhoDTO t= new TamanhoDTO();
				t.setTamanho(rset.getString("tamanho"));
				t.setId(rset.getInt("id"));
				tamanhos.add(t);
			 }
			 
		} catch (Exception e) {
			System.out.println("Erro ao recuperar tamanhos!");
		}
						
		TamanhoDTO tamanhosdto = new TamanhoDTO();
		tamanhosdto.setTamanhos(tamanhos);
		return tamanhosdto;
	}

	public void adicionarCategoria(CategoriaDTO cdto) {
		String sql = "INSERT INTO categoria_produtos(categoria) VALUES(?)"; 
		 
		 try {
			 con= Conexao.getConnection();
			 pstm = con.prepareStatement(sql);
			 pstm.setString(1, cdto.getCategoria());
			 pstm.execute();
		
		} catch (Exception e) {
			System.out.println("Erro ao adicionar categoria!");
			e.printStackTrace();
		}
		
	}

	public void removerCategoria(CategoriaDTO cdto) {
		
		String sql = "DELETE FROM categoria_produtos WHERE categoria = ?";
		 
		try {
			con = Conexao.getConnection();
			 
			pstm = con.prepareStatement(sql);
			 
			pstm.setString(1, cdto.getCategoria());
			 
			pstm.execute();
			 
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}

	public CategoriaDTO getCategorias() {
		String sql = "SELECT * FROM categoria_produtos";
		 
		ArrayList<CategoriaDTO> categorias = new ArrayList<>();
		try {
			con= Conexao.getConnection();
			pstm = con.prepareStatement(sql);		 
			 
			rset = pstm.executeQuery();
			 
			while(rset.next()){
				CategoriaDTO c= new CategoriaDTO();
				c.setCategoria(rset.getString("categoria"));
				c.setId(rset.getInt("id"));
				categorias.add(c);
			 }
			 
		} catch (Exception e) {
			System.out.println("Erro ao recuperar categorias!");
		}
						
		CategoriaDTO categoriadto = new CategoriaDTO();
		categoriadto.setCategorias(categorias);
		return categoriadto;
	}

}

	

