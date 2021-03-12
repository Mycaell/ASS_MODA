package View_Utilidades;

import java.util.ArrayList;

import DTO.BandeiraDTO;

public class IteratorBandeiras implements Iterator{

	private ArrayList<BandeiraDTO> bandeiras;
	private int posicao=0;
	
	
	public IteratorBandeiras(ArrayList<BandeiraDTO> bandeiras) {
		this.bandeiras = bandeiras;
	}

	@Override
	public boolean hasNext() {
		if(bandeiras.size()<=posicao || bandeiras.get(posicao)==null) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		Object bandeira = bandeiras.get(posicao);
		posicao++;
		return bandeira;
	}

}
