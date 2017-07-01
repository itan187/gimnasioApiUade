package models;

import persistence.EmpleadoHorarioPartAbm;

public class Particular extends Profesor {
	private float valorHora;

	public Particular(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float valorHora) {
		super(nombre, documento, mail, telefono, domicilio, escalaSalarial);
		this.valorHora = valorHora;
		EmpleadoHorarioPartAbm.getInstancia().insert(this);
	}
	
	public float getValorHora() {
		return valorHora;
	}
	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	
	/**
	 * getCalcularSueldo
	 * 
	 * 
	 * @return
	 */
	public float getCalcularSueldo () {
		return 0;
	}
	
	public void eliminarEmpleado() {
		EmpleadoHorarioPartAbm.getInstancia().delete(this);
	}
	public void actualizarEmpleado() {
		EmpleadoHorarioPartAbm.getInstancia().update(this);
	}
}
