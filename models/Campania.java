package models;

import java.util.Date;
import java.util.Vector;

public class Campania {
	private int 				numeroCampania;
	private String 				asunto;
	private Vector<Socio> 		socios;
	private Date 				fechaDeEnvio;
	private Vector<Actividad> 	actividades;
	private boolean 			estado;
	
	public Campania (int numeroCampania, String asunto, Vector<Socio> socios, Date fechaDeEnvio, Vector<Actividad> actividades, boolean estado) {
		super();
		this.setNumeroCampania(numeroCampania);
		this.setAsunto(asunto);
		this.setSocios(socios);
		this.setFechaDeEnvio(fechaDeEnvio);
		this.setActividades(actividades);
		this.setEstado(estado);
	}
	public int getNumeroCampania() {
		return this.numeroCampania;
	}
	public void setNumeroCampania (int numeroCampania) {
		this.numeroCampania = numeroCampania;
	}
	public Vector<Actividad> getActividad() {
		return actividades;
	}
	public void setActividades(Vector<Actividad> actividades) {
		this.actividades = actividades;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Vector<Socio> getSocio() {
		return socios;
	}
	public void setSocios(Vector<Socio> socios) {
		this.socios = socios;
	}
	public Date getFechaDeEnvio() {
		return fechaDeEnvio;
	}
	public void setFechaDeEnvio(Date fechaDeEnvio) {
		this.fechaDeEnvio = fechaDeEnvio;
	}
	
	/**
	 * getEmailSocios
	 * 
	 * 
	 * 
	 * @return
	 */
	public Vector<String> getEmailSocios() {
		Vector<String> emailSocios = new Vector<String>();
		
		return emailSocios;
	}
	
}
