package Model;

import java.sql.Timestamp;

import DAO.VendaAdapterPostgreSQL;
import DTO.ClienteDTO;
import DTO.PagamentoDTO;
import DTO.ProdutoDTO;
import DTO.VendaDTO;

public class Venda {
	
	private int numVenda;
	private Timestamp dataVenda ;

	
	
	private VendaAdapterPostgreSQL vendaAdapter;
	
	public Venda() {
		this.vendaAdapter = new VendaAdapterPostgreSQL();
	}

	public VendaDTO getNumVenda() {
		return vendaAdapter.getNumVenda();
	}
	
	public VendaDTO getProdutosVenda(VendaDTO vdto) {
		return vendaAdapter.getProdutosVenda(vdto);
	}	
	
	public VendaDTO getComprasDoCliente(ClienteDTO clienteDTO) {
		return vendaAdapter.getComprasDoCliente(clienteDTO);
	}
	
	public void efetuarVendaAVista(VendaDTO vdto, PagamentoDTO pdto) {
		vendaAdapter.efetuarVendaAVista(pdto);
		vendaAdapter.finalizaVenda(vdto);
		vendaAdapter.finalizaVendaProduto(vdto);
	}
	
	public void efetuarVendaCartao(VendaDTO vdto, PagamentoDTO pdto) {
		vendaAdapter.efetuarVendaCartao(pdto);
		vendaAdapter.finalizaVenda(vdto);
		vendaAdapter.finalizaVendaProduto(vdto);
	}
	
	public void editarVenda(VendaDTO vdto) {
		vendaAdapter.editarVenda(vdto);
	}

	public void cancelarVenda(VendaDTO vdto, PagamentoDTO pdto) {
		vendaAdapter.cancelarVenda(vdto, pdto);
	}

	public VendaDTO getVendas() {
		return vendaAdapter.getVendas();
	}

	public VendaDTO getVenda(VendaDTO vdto, PagamentoDTO pdto) {
		return vendaAdapter.getVenda(vdto, pdto);
	}
	
	
}
