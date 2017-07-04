package models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import persistence.ActividadAbm;

public class Actividad {
	private int numeroActividad;
	private Deporte				deporte;
	private Vector<Profesor> 	profesores;
	private String				description;
	private int					duracion;
	private int					lunes;
	private int					martes;
	private int					miercoles;
	private int					jueves;
	private int					viernes;
	private int					sabado;
	private int					domingo;
	
	public Actividad(
			int numeroActividad, 
			Deporte deporte,
			Vector<Profesor> profesores,
			String descripcion,
			int duracion,
			int lunes, 
			int martes,  
			int miercoles,
			int jueves,
			int viernes,
			int sabado,
			int domingo) {
		super();
		this.setNumeroActividad(numeroActividad);
		this.setDeporte(deporte);
		this.setProfesores(profesores);
		this.setDescription(descripcion);
		this.setDuracion(duracion);
		this.setLunes(lunes);
		this.setMartes(martes);
		this.setMiercoles(miercoles);
		this.setJueves(jueves);
		this.setViernes(viernes);
		this.setSabado(sabado);
		this.setDomingo(domingo);
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
	public int getLunes() {
		return lunes;
	}
	public void setLunes(int lunes) {
		this.lunes = lunes;
	}
	public int getMartes() {
		return martes;
	}
	public void setMartes(int martes) {
		this.martes = martes;
	}
	public int getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(int miercoles) {
		this.miercoles = miercoles;
	}
	public int getJueves() {
		return jueves;
	}
	public void setJueves(int jueves) {
		this.jueves = jueves;
	}
	public int getViernes() {
		return viernes;
	}
	public void setViernes(int viernes) {
		this.viernes = viernes;
	}
	public int getSabado() {
		return sabado;
	}
	public void setSabado(int sabado) {
		this.sabado = sabado;
	}
	public int getDomingo() {
		return domingo;
	}
	public void setDomingo(int domingo) {
		this.domingo = domingo;
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
	
	public void eliminarClase() {
		ActividadAbm.getInstancia().delete(this);
	}
	public void actualizarClase() {
		ActividadAbm.getInstancia().update(this);
	}
	
}
