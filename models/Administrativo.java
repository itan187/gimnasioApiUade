package models;

import persistence.EmpleadoAdminAbm;

public class Administrativo extends Empleado {
	private float sueldo;
	
	public Administrativo(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float sueldo) {
		super(nombre, documento, mail, telefono, domicilio, escalaSalarial);
		this.setSueldo(sueldo);
		EmpleadoAdminAbm.getInstancia().insert(this);
	}

	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
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
	
	public void eliminarEmpleado() {
		System.out.println(this);
		System.out.println("Hola");
		EmpleadoAdminAbm.getInstancia().delete(this);
	}
	public void actualizarEmpleado() {
		EmpleadoAdminAbm.getInstancia().update(this);
	}
	
}
