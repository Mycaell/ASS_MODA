package DAO;

import DTO.BandeiraDTO;

public class BandeiraAdapterPostegreSQL extends BandeiraDAOPostegreSQL implements BandeiraDAO{

	@Override
	public void adicionarBandeira(BandeiraDTO bdto) {
		super.adicionarBandeira(bdto);
	}

	@Override
	public void removerBandeira(BandeiraDTO bdto) {
		super.removerBandeira(bdto);
	}

	@Override
	public BandeiraDTO getBandeiras() {
		return super.getBandeiras();
	}

	@Override
	public BandeiraDTO getIdBandeira(BandeiraDTO bdto) {
		return super.getIdBandeira(bdto);
	}
}
