package models;

import persistence.EmpleadoHorarioPartAbm;

public class Particular extends Profesor {
	private float valorHora;
	private int horas;

	public Particular(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float valorHora, int horas) {
		super(nombre, documento, mail, telefono, domicilio, escalaSalarial);
		this.valorHora = valorHora;
		this.horas = horas;
	}

	public void insert() {
		EmpleadoHorarioPartAbm.getInstancia().insert(this);
	}
	public float getValorHora() {
		return valorHora;
	}
	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
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
	public void actualizarEmpleado(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float valor) {
		EmpleadoHorarioPartAbm.getInstancia().update(new Particular(nombre, documento, mail, telefono, domicilio, escalaSalarial, valor, documento));
	}
}
