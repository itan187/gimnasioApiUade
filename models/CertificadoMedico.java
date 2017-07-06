package models;

import java.util.Date;

import persistence.CertificadoMedicoAbm;

public class CertificadoMedico {
	private Socio socio;
	private int numAptoMedico;
	private Date fechaCreacion;
	private Date vencimiento;
	private String profesional;
	private String observaciones;
	private boolean estado;
	
	public CertificadoMedico(int numAptoMedico, Socio socio, Date fechaCreacion, Date vencimiento, String profesional, String observaciones,
			boolean estado) {
		super();
		this.setSocio(socio);
		this.setNumAptoMedico(numAptoMedico);
		this.setFechaCreacion(fechaCreacion);
		this.setVencimiento(vencimiento);
		this.setProfesional(profesional);
		this.setObservaciones(observaciones);
		this.setEstado(estado);
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
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
	public void insert() {
		CertificadoMedicoAbm.getInstancia().insert(this);
	}
	public void eliminarAptoMedico() {
		CertificadoMedicoAbm.getInstancia().delete(this);
	}
	public void actualizarAptoMedico() {
		CertificadoMedicoAbm.getInstancia().update(this);
	}
	
}
