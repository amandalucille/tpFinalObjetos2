package eCommerce.errores;

public class StockInsuficienteException extends IllegalStateException {
	private static final long serialVersionUID = 1L;
	private String nombreItem;
	private Integer stockDisponible;
	
	
	public StockInsuficienteException(String nombreItem, Integer stockDisponible) {
		super("No hay stock suficiente de " + nombreItem + ", quedan " + stockDisponible);
		this.nombreItem = nombreItem;
		this.stockDisponible = stockDisponible;
	}


	public String getNombreItem() {
		return nombreItem;
	}


	public Integer getStockDisponible() {
		return stockDisponible;
	}
	

}
