package models;

import java.util.Date;
import java.util.Vector;

public class Campania {
	private String asunto;
	private Vector<String> emails;
	private Date fechaDeEnvio;
	private Vector<String> filtros;
	private boolean estado;
	
	public Campania (String asunto, Vector<String> emails, Date fechaDeEnvio, Vector<String> filtros, boolean estado) {
		super();
		this.setAsunto(asunto);
		this.setEmails(emails);
		this.setFechaDeEnvio(fechaDeEnvio);
		this.setFiltros(filtros);
		this.setEstado(estado);
	}
	public Vector<String> getFiltros() {
		return filtros;
	}
	public void setFiltros(Vector<String> filtros) {
		this.filtros = filtros;
	}
	public boolean isEstado() {
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
	public Vector<String> getEmails() {
		return emails;
	}
	public void setEmails(Vector<String> emails) {
		this.emails = emails;
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
