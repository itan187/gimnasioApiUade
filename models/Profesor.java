package models;

public abstract class Profesor extends Empleado {
	
	public Profesor(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial) {
		super(nombre, documento, mail, telefono, domicilio, escalaSalarial);
	}

	public abstract float getCalcularSueldo();
}
