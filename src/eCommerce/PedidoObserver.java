package eCommerce;

import eCommerce.comprobantes.Comprobante;
import eCommerce.comprobantes.NotaDeCredito;

public interface PedidoObserver {
	public void addNotaDeCredito(NotaDeCredito notaDeCredito);
	public void addComprobanteDePago(Comprobante comprobanteDePago);
}
