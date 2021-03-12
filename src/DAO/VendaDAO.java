package DAO;

import DTO.ClienteDTO;
import DTO.PagamentoDTO;
import DTO.VendaDTO;

public interface VendaDAO {

	
	public VendaDTO getNumVenda();
	
	public VendaDTO getProdutosVenda(VendaDTO vdto);
	
	void efetuarVendaAVista(PagamentoDTO pdto);
	
	void efetuarVendaCartao(PagamentoDTO pdto);
	
	void editarVenda(VendaDTO vdto);
	
	void cancelarVenda(VendaDTO vdto, PagamentoDTO pdto);
	
	VendaDTO getVendas();
	
	public VendaDTO getComprasDoCliente(ClienteDTO clienteDTO);
	
	VendaDTO getVenda(VendaDTO vdto, PagamentoDTO pdto);
	
	void finalizaVenda(VendaDTO vdto);
	
	void finalizaVendaProduto(VendaDTO vdto);
}
