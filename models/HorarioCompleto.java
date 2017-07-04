package models;

import persistence.EmpleadoHorarioCompletoAbm;

public class HorarioCompleto extends Profesor {
	private float sueldoBasico;
	
	public HorarioCompleto(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float sueldoBasico) {
		super(nombre, documento, mail, telefono, domicilio, escalaSalarial);
		this.sueldoBasico = sueldoBasico;
	}

	public void setSueldoBasico(float sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public float getSueldoBasico() {
		return sueldoBasico;
	}
	
	/**
	 * getCalcularSueldo
	 * 
	 * 
	 * 
	 * @return
	 */
	public float getCalcularSueldo () {
		return 0;
	}
	
	public void insert() {
		EmpleadoHorarioCompletoAbm.getInstancia().insert(this);
	}
	public void eliminarEmpleado() {
		EmpleadoHorarioCompletoAbm.getInstancia().delete(this);
	}
	public void actualizarEmpleado(String nombre, int documento, String mail, String telefono, String domicilio,
			String escalaSalarial, float sueldoBasico) {
		EmpleadoHorarioCompletoAbm.getInstancia().update(new HorarioCompleto(nombre, documento, mail, telefono, domicilio, escalaSalarial, sueldoBasico));
	}
	
}
