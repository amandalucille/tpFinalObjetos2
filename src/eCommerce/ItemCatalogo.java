package eCommerce;

public interface ItemCatalogo {
	public String  getDescripcion();
	public Double  getPrecioBase();
	public Double  getPrecioFinal();
	public void    decrementarStock();
	public void    aumentarStock();
	public Double  getPeso();
	public void    validarItem();
	public Boolean esItemValido();
	public Boolean hayStock();
}
