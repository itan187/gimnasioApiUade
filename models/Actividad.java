package models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import persistence.ActividadAbm;

public class Actividad {
	private int numeroActividad;
	private Vector<Profesor> 	profesores;
	
	private int					lunesInicio;
	private int					lunesFin;
	private int					martesInicio;
	private int					martesFin;
	private int					miercolesInicio;
	private int					miercolesFin;
	private int					juevesInicio;
	private int					juevesFin;
	private int					viernesInicio;
	private int					viernesFin;
	private int					sabadoInicio;
	private int					sabadoFin;
	private int					domingoInicio;
	private int					domingoFin;
	
	public Actividad(
			int numeroActividad, 
			Vector<Profesor> profesores, 
			int lunesInicio, 
			int LunesFin,
			int martesInicio, 
			int martesFin, 
			int miercolesInicio,
			int miercolesFin,
			int juevesInicio,
			int juevesFin,
			int viernesInicio,
			int viernesFin,
			int sabadoInicio,
			int sabadoFin,
			int domingoInicio,
			int domingoFin) {
		super();
		this.setNumeroActividad(numeroActividad);
		this.setProfesores(profesores);
		this.setLunesInicio(lunesInicio);
		this.setLunesFin(lunesFin);
		this.setMartesInicio(martesInicio);
		this.setMartesFin(martesFin);
		this.setMiercolesInicio(miercolesInicio);
		this.setMiercolesFin(miercolesFin);
		this.setJuevesInicio(juevesInicio);
		this.setJuevesFin(juevesFin);
		this.setViernesInicio(viernesInicio);
		this.setViernesFin(viernesFin);
		this.setSabadoInicio(sabadoInicio);
		this.setSabadoFin(sabadoFin);
		this.setDomingoInicio(domingoInicio);
		this.setDomingoFin(domingoFin);
		
		ActividadAbm.getInstancia().insert(this);
	}
	
	public int getLunesInicio() {
		return lunesInicio;
	}
	public void setLunesInicio(int lunesInicio) {
		this.lunesInicio = lunesInicio;
	}
	public int getLunesFin() {
		return lunesFin;
	}
	public void setLunesFin(int lunesFin) {
		this.lunesFin = lunesFin;
	}
	public int getMartesInicio() {
		return martesInicio;
	}
	public void setMartesInicio(int martesInicio) {
		this.martesInicio = martesInicio;
	}
	public int getMartesFin() {
		return martesFin;
	}
	public void setMartesFin(int martesFin) {
		this.martesFin = martesFin;
	}
	public int getMiercolesInicio() {
		return miercolesInicio;
	}
	public void setMiercolesInicio(int miercolesInicio) {
		this.miercolesInicio = miercolesInicio;
	}
	public int getMiercolesFin() {
		return miercolesFin;
	}
	public void setMiercolesFin(int miercolesFin) {
		this.miercolesFin = miercolesFin;
	}
	public int getJuevesInicio() {
		return juevesInicio;
	}
	public void setJuevesInicio(int juevesInicio) {
		this.juevesInicio = juevesInicio;
	}
	public int getJuevesFin() {
		return juevesFin;
	}
	public void setJuevesFin(int juevesFin) {
		this.juevesFin = juevesFin;
	}
	public int getViernesInicio() {
		return viernesInicio;
	}
	public void setViernesInicio(int viernesInicio) {
		this.viernesInicio = viernesInicio;
	}
	public int getViernesFin() {
		return viernesFin;
	}
	public void setViernesFin(int viernesFin) {
		this.viernesFin = viernesFin;
	}
	public int getSabadoInicio() {
		return sabadoInicio;
	}
	public void setSabadoInicio(int sabadoInicio) {
		this.sabadoInicio = sabadoInicio;
	}
	public int getSabadoFin() {
		return sabadoFin;
	}
	public void setSabadoFin(int sabadoFin) {
		this.sabadoFin = sabadoFin;
	}
	public int getDomingoInicio() {
		return domingoInicio;
	}
	public void setDomingoInicio(int domingoInicio) {
		this.domingoInicio = domingoInicio;
	}
	public int getDomingoFin() {
		return domingoFin;
	}
	public void setDomingoFin(int domingoFin) {
		this.domingoFin = domingoFin;
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
