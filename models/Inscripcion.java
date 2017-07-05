package models;

import java.util.Vector;

public abstract class Inscripcion {
	private boolean 			estado;
	private int 				numero;
	private Vector<Actividad> 	actividades;
	
	public Inscripcion(boolean estado, int numero, Vector<Actividad> actividades) {
		super();
		this.setEstado(estado);
		this.setNumero(numero);
		this.setActividades(actividades);
	}
	public boolean getEstado() {
		return estado;
	}
	public int getNumero() {
		return numero;
	}
	public Vector<Actividad> getActividades() {
		return actividades;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setActividades(Vector<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	/**
	 * esInscripcion
	 * 
	 * Validamos que exista la inscripción de tal socio a través
	 * del número identificador.
	 * 
	 * @param numero
	 * @return boolean
	 */
	public boolean esInscripcion(int numero) {
		return this.numero == numero;
	}
}
