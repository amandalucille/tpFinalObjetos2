package eCommerce;

import eCommerce.mediosDePago.Comprobante;

public interface PedidoObserver {
	public void addNotaDeCredito(NotaDeCredito notaDeCredito);
	public void addComprobanteDePago(Comprobante comprobanteDePago);
}
