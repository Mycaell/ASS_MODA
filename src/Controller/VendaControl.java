package Controller;

import DTO.ClienteDTO;
import DTO.PagamentoDTO;
import DTO.VendaDTO;
import Model.Venda;

public class VendaControl {

	private Venda venda = new Venda();
	
	public VendaDTO getNumVenda() {
		return venda.getNumVenda();
	}
	
	public VendaDTO getVendas(){
		return venda.getVendas();
	}
	
	public VendaDTO getProdutosVenda(VendaDTO vdto) {
		return venda.getProdutosVenda(vdto);
	}
	
	
	public VendaDTO getComprasDoCliente(ClienteDTO clienteDTO) {
		return venda.getComprasDoCliente(clienteDTO);
	}
	
	public void efetuarVendaAVista(VendaDTO vdto, PagamentoDTO pdto) {
		venda.efetuarVendaAVista(vdto, pdto);
	}
	
	public void efetuarVendaCartao(VendaDTO vdto, PagamentoDTO pdto) {
		venda.efetuarVendaCartao(vdto, pdto);
	}
	
	
	
}
