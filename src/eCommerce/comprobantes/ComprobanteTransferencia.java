package eCommerce.comprobantes;

public class ComprobanteTransferencia extends Comprobante {
	    private String cbu;

	    public ComprobanteTransferencia(String cbu, String numeroDeOperacion) {
	        super(numeroDeOperacion);
	        this.cbu = cbu;
	    }

	    public String getCbu() {
	        return this.cbu;
	    }
}