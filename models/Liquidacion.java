package models;

import persistence.LiquidacionAbm;

public class Liquidacion {
	private int					numero;
	private int 				anio;
	private int 				mes;
	
	public Liquidacion(int numero, int anio, int mes) {
		super();
		this.setNumero(numero);
		this.setAnio(anio);
		this.setMes(mes);
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	
  public void insert() {
		LiquidacionAbm.getInstancia().insert(this);
    }
}
