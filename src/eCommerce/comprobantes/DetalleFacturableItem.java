package eCommerce.comprobantes;

import eCommerce.item.ItemCatalogo;

public class DetalleFacturableItem {
	private ItemCatalogo item;
	private Integer cantidadVendida;
	private Double precioUnitarioFinal;
	private Double precioUnitarioBase;
	
	public DetalleFacturableItem(ItemCatalogo item, Integer cantidadVendido) {
		this.item = item;
		this.cantidadVendida = cantidadVendido;
		this.precioUnitarioFinal = item.getPrecioFinal();
		this.precioUnitarioBase = item.getPrecioBase();
	}
	
	public ItemCatalogo getItem() {
		return item;
	}

	public Integer getCantidadVendida() {
		return cantidadVendida;
	}

	public Double getPrecioUnitarioFinal() {
		return precioUnitarioFinal;
	}
	
	public Double getPrecioUnitarioBase() {
		return precioUnitarioBase;
	}
	
	public Double getPrecioTotalBase() {
		return getPrecioUnitarioBase() * getCantidadVendida();
	}
	
	public Double getPrecioTotalFinal() {
		return getPrecioUnitarioFinal() * getCantidadVendida();
	}

}
