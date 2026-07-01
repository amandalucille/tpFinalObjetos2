package eCommerce.errores;

public class ItemInvalidoException extends IllegalStateException{

	// Recomendado en Java cuando implementás Serializable (las excepciones lo son)
    private static final long serialVersionUID = 1L;
    
    private String nombreItem;

    // Constructor simple solo con el mensaje
    public ItemInvalidoException(String mensaje) {
        super(mensaje);
    }

    // Constructor ideal para saber exactamente qué ítem falló y por qué
    public ItemInvalidoException(String nombreItem, String motivo) {
        super("El ítem '" + nombreItem + "' es inválido: " + motivo);
        this.nombreItem = nombreItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }
}
