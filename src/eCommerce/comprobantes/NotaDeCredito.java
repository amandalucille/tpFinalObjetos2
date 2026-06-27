package eCommerce.comprobantes;

import eCommerce.Pedido;

public class NotaDeCredito {
	private Double monto;
	private Pedido pedido;
		
	public NotaDeCredito(Double monto, Pedido pedido) {
		this.setMonto(monto);
		this.setPedido(pedido);
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
