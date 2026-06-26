package eCommerce.mediosDePago;

import java.util.Date;

public class DatosTarjeta implements DatosDePago {
	private Integer nroTarjeta;
	private Integer CVV;
	private Date fechaDeVencimiento;
	
	public DatosTarjeta(Integer nroTarjeta, Integer CVV,  Date fechaDeVencimiento) {
		this.setNroTarjeta(nroTarjeta);
		this.setCVV(CVV);
		this.setFechaDeVencimiento(fechaDeVencimiento);
	}

	public Integer getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(Integer nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Integer getCVV() {
		return CVV;
	}

	public void setCVV(Integer CVV) {
		this.CVV = CVV;
	}

	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(Date fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

}
