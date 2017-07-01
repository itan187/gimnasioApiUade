package models;

import java.util.Date;

public class CertificadoMedico {
	private int numAptoMedico;
	private Date fechaCreacion;
	private Date vencimiento;
	private String profesional;
	private String observaciones;
	private boolean estado;
	
	public CertificadoMedico(int numAptoMedico, Date fechaCreacion, Date vencimiento, String profesional, String observaciones,
			boolean estado) {
		super();
		this.setNumAptoMedico(numAptoMedico);
		this.setFechaCreacion(fechaCreacion);
		this.setVencimiento(vencimiento);
		this.setProfesional(profesional);
		this.setObservaciones(observaciones);
		this.setEstado(estado);
	}
	
	public int getNumAptoMedico() {
		return numAptoMedico;
	}
	public void setNumAptoMedico(int numAptoMedico) {
		this.numAptoMedico = numAptoMedico;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public String getProfesional() {
		return profesional;
	}
	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
