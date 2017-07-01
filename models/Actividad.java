package models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import persistence.ActividadAbm;

public class Actividad {
	private int numeroActividad;
	private Vector<Profesor> 	profesores;
	private int[][] 			horaInicio;
	private int[][]				horaFin;
	
	public Actividad(int numeroActividad, Vector<Profesor> profesores, int dia, Date horaI, Date horaF) {
		super();
		this.setNumeroActividad(numeroActividad);
		this.setProfesores(profesores);
		
		horaInicio 	= new int[2][7];
		Calendar horaDeInicio = GregorianCalendar.getInstance();
		horaDeInicio.setTime(horaI);
		horaInicio[1][dia] = horaDeInicio.get(Calendar.HOUR);
		horaInicio[2][dia] = horaDeInicio.get(Calendar.MINUTE);
		
		horaFin		= new int[2][7];
		Calendar horaDeFin = GregorianCalendar.getInstance();
		horaDeFin.setTime(horaF); 
		horaFin[1][dia] = horaDeFin.get(Calendar.HOUR);
		horaFin[2][dia] = horaDeFin.get(Calendar.MINUTE);
		
		ActividadAbm.getInstancia().insert(this);
	}
	
	public int[][] getHoraInicio() {
		return horaInicio;
	}
	public int[][] getHoraFin() {
		return horaFin;
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
