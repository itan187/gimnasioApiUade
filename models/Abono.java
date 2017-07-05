package models;

import java.util.Date;

import persistence.AbonoAbm;

public class Abono {
	private int codigo;
	private String nombre;
	private float precio;
	private Date vigencia;
	
	public Abono(int codigo, String nombre, float precio, Date vigencia) {
		super();
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setPrecio(precio);
		this.setVigencia(vigencia);
	}
	
	public int getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public Date getVigencia() {
		return vigencia;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}

	/**
	 * esAbono
	 * 
	 * Validamos que exista un abono puntual buscando por su
	 * código identificador.
	 * 
	 * @param codigo
	 * @return boolean
	 */
	public boolean esAbono(int codigo) {
		return this.codigo == codigo;
	}
	
	/**
	 * abonoVigente
	 * 
	 * Validamos que el abono en el que nos encontramos
	 * su fecha de vigencia sea anterior a la actual.
	 * 
	 * @return boolean
	 */
	public boolean abonoVigente () {
		Date hoy = new Date();
		/**
		 *  El abono está dentro de su periodo validado
		 *  ya que lo comparamos con la fecha actual y es
		 *  anterior.
		 */
		return this.getVigencia().before(hoy);
	}
	
	public void eliminarAbono() {
		AbonoAbm.getInstancia().delete(this);
	}
	public void actualizarAbono() {
		AbonoAbm.getInstancia().update(this);
	}
    public void insert() {
		AbonoAbm.getInstancia().insert(this);
    }
}
