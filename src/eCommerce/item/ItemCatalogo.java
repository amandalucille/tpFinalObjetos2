package eCommerce.item;

import eCommerce.visitor.ReporteVisitor;

public interface ItemCatalogo {
	public String  getNombre();
	public String  getCategoria();
	public Integer stockDisponible();
	public String  getDescripcion();
	public Double  getPrecioBase();
	public Double  getPrecioFinal();
	public void    decrementarStock(Integer cantidad);
	public void    aumentarStock(Integer cantidad);
	public Double  getPeso();
	public void    validarItem();
	public Boolean hayStock(Integer cantidad);
	public void	   aceptar(ReporteVisitor visitor);
}
