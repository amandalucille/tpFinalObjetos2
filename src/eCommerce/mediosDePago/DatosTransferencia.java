package eCommerce.mediosDePago;

public class DatosTransferencia implements DatosDePago {
	private String CBU;
	private String alias;
	
	public DatosTransferencia(String CBU, String alias) {
		this.setCBU(CBU);
		this.setAlias(alias);
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
