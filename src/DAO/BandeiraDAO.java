package DAO;

import DTO.BandeiraDTO;

public interface BandeiraDAO {

	void adicionarBandeira(BandeiraDTO bdto);
	
	void removerBandeira(BandeiraDTO bdto);
	
	BandeiraDTO getBandeiras();
	
	BandeiraDTO getIdBandeira(BandeiraDTO bdto);
}
