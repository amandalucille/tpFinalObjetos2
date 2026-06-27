package eCommerce.item;

public interface ItemCatalogo {
	public String  getNombre();
	public Integer stockDisponible();
	public String  getDescripcion();
	public Double  getPrecioBase();
	public Double  getPrecioFinal();
	public void    decrementarStock(Integer cantidad);
	public void    aumentarStock(Integer cantidad);
	public Double  getPeso();
	public void    validarItem();
	public Boolean esItemValido();
	public Boolean hayStock(Integer cantidad);
}
