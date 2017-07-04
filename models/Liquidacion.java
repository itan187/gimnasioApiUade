package models;

import java.util.Date;
import java.util.Vector;

public class Liquidacion {
	private int 				anio;
	private int 				mes;
	private Vector<Empleado> 	empleados;
	
	public Liquidacion(int anio, int mes, Vector<Empleado> empleados) {
		super();
		this.setAnio(anio);
		this.setMes(mes);
		this.setEmpleados(empleados);
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public Vector<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(Vector<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	/**
	 * Es Liquidacion
	 * 
	 * @param anio
	 * @param mes
	 * @return
	 */
	public boolean esLiquidacion (int anio, int mes) {
		return (this.anio == anio && this.mes == mes);
	}
	
}
