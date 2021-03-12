package DAO;

import DTO.ClienteDTO;
import DTO.PagamentoDTO;
import DTO.VendaDTO;

public class VendaAdapterPostgreSQL extends VendaDAOPostgreSQL implements VendaDAO {

	@Override
	public VendaDTO getNumVenda() {
		return super.getNumVenda();
	}
	
	@Override
	public VendaDTO getProdutosVenda(VendaDTO vdto) {
		return super.getProdutosVenda(vdto);
	}
	
	
	@Override
	public VendaDTO getComprasDoCliente(ClienteDTO clienteDTO) {
		return super.getComprasDoCliente(clienteDTO);
	}
	
	
	@Override
	public void efetuarVendaAVista(PagamentoDTO pdto) {
		super.efetuarVendaAVista(pdto);
	}

	@Override
	public void efetuarVendaCartao(PagamentoDTO pdto) {
		super.efetuarVendaCartao(pdto);
	}
	
	@Override
	public void editarVenda(VendaDTO vdto) {
		super.editarVenda(vdto);
	}

	@Override
	public void cancelarVenda(VendaDTO vdto, PagamentoDTO pdto) {
		super.cancelarVenda(vdto, pdto);
	}

	@Override
	public VendaDTO getVendas() {
		return super.getVendas();
	}

	@Override
	public VendaDTO getVenda(VendaDTO vdto, PagamentoDTO pdto) {
		return super.getVenda(vdto, pdto);
	}


	@Override
	public void finalizaVenda(VendaDTO vdto) {
		super.finalizaVenda(vdto);
	}


	@Override
	public void finalizaVendaProduto(VendaDTO vdto) {
		super.finalizaVendaProduto(vdto);
	}

}
