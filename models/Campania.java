package models;

import java.util.Date;
import java.util.Vector;

import persistence.CampaniaAltaAbm;

public class Campania {
	private int 				numeroCampania;
	private String 				asunto;
	private Date 				fechaDeEnvio;
	private Vector<Actividad> 	actividades;
	private boolean 			estado;
	
	public Campania (int numeroCampania, String asunto, Date fechaDeEnvio, Vector<Actividad> actividades, boolean estado) {
		super();
		this.setNumeroCampania(numeroCampania);
		this.setAsunto(asunto);
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
	public Vector<Actividad> getActividades() {
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
	public Date getFechaDeEnvio() {
		return fechaDeEnvio;
	}
	public void setFechaDeEnvio(Date fechaDeEnvio) {
		this.fechaDeEnvio = fechaDeEnvio;
	}
		
	public void insert() {
		CampaniaAltaAbm.getInstancia().insert(this);
    }
	
}
