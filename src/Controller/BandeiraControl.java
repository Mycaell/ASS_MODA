package Controller;

import DTO.BandeiraDTO;
import Model.Bandeira;

public class BandeiraControl {

	Bandeira bandeira = new Bandeira();
	
	public void adicionarBandeira(BandeiraDTO bdto) {
		bandeira.adicionarBandeira(bdto);
	}

	public void removerBandeira(BandeiraDTO bdto) {
		bandeira.removerBandeira(bdto);
	}

	public BandeiraDTO getBandeiras() {
		return bandeira.getBandeiras();
	}
	
	public BandeiraDTO getIdBandeira(BandeiraDTO bdto) {
		return bandeira.getIdBandeira(bdto);
	}
}
