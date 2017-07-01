package models;

import java.util.Date;

public class Ingreso {
	private Date fecha;

	public Ingreso(Date fecha) {
		super();
		this.setFecha(fecha);
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
