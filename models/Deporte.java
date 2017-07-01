package models;

import persistence.DeporteAbm;

public class Deporte {
	private int codigo;
	private String titulo;
	private String descripcion;
	
	public Deporte(int codigo, String titulo, String descripcion) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		
		DeporteAbm.getInstancia().insert(this);
		
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * esDeporte
	 * 
	 * Validamos que el deporte en el que nos encontramos
	 * sea el que buscamos.
	 * 
	 * @return boolean
	 */
	public boolean esDeporte(int codigo) {
		return this.codigo == codigo;
	}
	
	public void eliminarDeporte() {
		DeporteAbm.getInstancia().delete(this);
	}
	public void actualizarDeporte() {
		DeporteAbm.getInstancia().update(this);
	}
}
