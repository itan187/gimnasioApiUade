package models;

import java.util.Vector;

import persistence.ActividadAbm;

public class Actividad {
	private int numeroActividad;
	private Deporte				deporte;
	private Vector<Profesor> 	profesores;
	private String				description;
	private int					duracion;
	private int					dia;
	private int					horaDeInicio;
	
	public Actividad(
			int numeroActividad, 
			Deporte deporte,
			Vector<Profesor> profesores,
			String descripcion,
			int duracion,
			int dia,
			int horaDeInicio) {
		super();
		this.setNumeroActividad(numeroActividad);
		this.setDeporte(deporte);
		this.setProfesores(profesores);
		this.setDescription(descripcion);
		this.setDuracion(duracion);
		this.setDia(dia);
		this.setHoraDeInicio(horaDeInicio);
	}
	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getHoraDeInicio() {
		return horaDeInicio;
	}
	public void setHoraDeInicio(int horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public Deporte getDeporte() {
		return deporte;
	}
	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}
	
	public int getNumeroActividad() {
		return numeroActividad;
	}
	public Vector<Profesor> getProfesores() {
		return profesores;
	}
	public void setNumeroActividad(int numeroActividad) {
		this.numeroActividad = numeroActividad;
	}
	public void setProfesores(Vector<Profesor> profesores) {
		this.profesores = profesores;
	}

	/**
	 * esClase
	 * Validamos que exista dicha clase puntual buscando por su
	 * número identificador
	 * 
	 * @param numero
	 * @return boolean
	 */
	public boolean esClase(int numero) {
		return this.getNumeroActividad() == numero;
	}
	
	public void insert() {
		ActividadAbm.getInstancia().insert(this);
	}
	public void eliminarActividad() {
		ActividadAbm.getInstancia().delete(this);
	}
	public void actualizarActividad() {
		ActividadAbm.getInstancia().update(this);
	}
	
}
