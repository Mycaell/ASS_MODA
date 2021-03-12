package View_Utilidades;

import java.util.ArrayList;

import DTO.ProdutoDTO;

public class IteratorProdutos implements Iterator{

	private ArrayList<ProdutoDTO> produtos;
	private int posicao=0;
	
	
	public IteratorProdutos(ArrayList<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}

	@Override
	public boolean hasNext() {
		if(produtos.size()<=posicao || produtos.get(posicao)==null) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		Object produto = produtos.get(posicao);
		posicao++;
		return produto;
	}

}
