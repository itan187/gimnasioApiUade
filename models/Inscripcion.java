package models;

import java.util.Vector;

public abstract class Inscripcion {
	private boolean estado;
	private int numero;
	private Vector<Actividad> clases;
	
	public Inscripcion(boolean estado, int numero, Vector<Actividad> clases) {
		super();
		this.setEstado(estado);
		this.setNumero(numero);
		this.setClases(clases);
	}
	public boolean getEstado() {
		return estado;
	}
	public int getNumero() {
		return numero;
	}
	public Vector<Actividad> getClases() {
		return clases;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setClases(Vector<Actividad> clases) {
		this.clases = clases;
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
