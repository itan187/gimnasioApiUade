package models;

import java.util.Date;

public class Liquidacion {
	private Date fechaDeLiq;

	
	public Liquidacion(Date fechaDeLiq) {
		super();
		this.setFechaDeLiq(fechaDeLiq);
	}
	public Date getFechaDeLiq() {
		return fechaDeLiq;
	}
	public void setFechaDeLiq(Date fechaDeLiq) {
		this.fechaDeLiq = fechaDeLiq;
	}
	
	
}
