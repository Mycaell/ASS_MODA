package Model;

import DAO.BandeiraAdapterPostegreSQL;
import DTO.BandeiraDTO;

public class Bandeira {

	private int id; 
	private String nome;
	
	private BandeiraAdapterPostegreSQL bandeiraAdapterPostegreSQL;
	
	public Bandeira() {
		this.bandeiraAdapterPostegreSQL = new BandeiraAdapterPostegreSQL();
	}

	public void adicionarBandeira(BandeiraDTO bdto) {
		bandeiraAdapterPostegreSQL.adicionarBandeira(bdto);
	}

	public void removerBandeira(BandeiraDTO bdto) {
		bandeiraAdapterPostegreSQL.removerBandeira(bdto);
	}

	public BandeiraDTO getBandeiras() {
		return bandeiraAdapterPostegreSQL.getBandeiras();
	}
	
	public BandeiraDTO getIdBandeira(BandeiraDTO bdto) {
		return bandeiraAdapterPostegreSQL.getIdBandeira(bdto);
	}
}
