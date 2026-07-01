package eCommerce;

public class DatosCliente {
	private String email;
    private Integer telefono;
    
    public DatosCliente(String email, Integer telefono) {
    	this.email = email;
    	this.telefono = telefono;
    }
    
    public String getEmail() {
		return email;
	}

	public Integer getTelefono() {
		return telefono;
	}
    
    
}
