package eCommerce;

import eCommerce.item.ItemCatalogo;

public class ResumenVentaItem {
	
    private ItemCatalogo item;
    private Integer cantidadVendida;
    private Double precioPromedioBase;
    private Double precioPromedioFinal;

    public ResumenVentaItem(ItemCatalogo item, Integer cantidadVendida,
                             Double precioPromedioBase, Double precioPromedioFinal) {
        this.item = item;
        this.cantidadVendida = cantidadVendida;
        this.precioPromedioBase = precioPromedioBase;
        this.precioPromedioFinal = precioPromedioFinal;
    }

    public ItemCatalogo getItem() { 
    	return item; 
   	}

    public Integer getCantidadVendida() { 
    	return cantidadVendida; 
   	}
    
    public Double getPrecioPromedioBase() { 
    	return precioPromedioBase; 
  	}

    public Double getPrecioPromedioFinal() { 
    	return precioPromedioFinal; 
    }
	
}
