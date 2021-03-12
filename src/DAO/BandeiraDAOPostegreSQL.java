package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.BandeiraDTO;

public class BandeiraDAOPostegreSQL{

	private Connection con=null;
	private PreparedStatement pstm=null;
	private ResultSet rset=null;
	
	public void adicionarBandeira(BandeiraDTO bdto) {
		String sql = "INSERT INTO bandeira(nome) VALUES(?)"; 
		 
		 try {
			 con= Conexao.getConnection();
			 pstm = con.prepareStatement(sql);
			 pstm.setString(1, bdto.getNomeBandeira());
			 pstm.execute();
		
		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}
	}

	public void removerBandeira(BandeiraDTO bdto) {
		String sql = "DELETE FROM bandeira WHERE nome = ?";
		 
		try {
			con = Conexao.getConnection();
			 
			pstm = con.prepareStatement(sql);
			 
			pstm.setString(1, bdto.getNomeBandeira());
			 
			pstm.execute();
			 
		} catch (Exception e) {			 
		    e.printStackTrace();
		} 
	}

	public BandeiraDTO getBandeiras() {
		String sql = "SELECT * FROM bandeira";
		 
		ArrayList<BandeiraDTO> bandeiras = new ArrayList<>();
		try {
			con= Conexao.getConnection();
			pstm = con.prepareStatement(sql);		 
			 
			rset = pstm.executeQuery();
			 
			while(rset.next()){
				BandeiraDTO bandeira= new BandeiraDTO();
				bandeira.setId(rset.getInt("id"));
				bandeira.setNomeBandeira(rset.getString("nome"));
				bandeiras.add(bandeira);
			 }
			 
		} catch (Exception e) {
			System.out.println("Erro!");
		}
						
		BandeiraDTO bandeiraDTO = new BandeiraDTO();
		bandeiraDTO.setBandeiras(bandeiras);
		return bandeiraDTO;
	}

	public BandeiraDTO getIdBandeira(BandeiraDTO bdto) {
		String sql = "select * from bandeira where nome = ?";
		BandeiraDTO bandeiraDTO =new BandeiraDTO();		 
			try {
				con= Conexao.getConnection();
				pstm = con.prepareStatement(sql);
				pstm.setString(1, bdto.getNomeBandeira());
				rset = pstm.executeQuery();			 

				 while(rset.next()) {					
					 bandeiraDTO.setId(rset.getInt("id"));
					 break;
				 }
				
			} catch (Exception e) {
					 
				System.out.println("Erro!");
			}
			return bandeiraDTO;
	}
}
