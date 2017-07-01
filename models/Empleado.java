package models;

public abstract class Empleado {
	private String nombre;
	private int documento;
	private String mail;
	private String telefono;
	private String domicilio;
	private String escalaSalarial;
	
	public Empleado(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial) {
		super();
		this.setNombre(nombre);
		this.setDocumento(documento);
		this.setMail(mail);
		this.setTelefono(telefono);
		this.setDomicilio(domicilio);
		this.setEscalaSalarial(escalaSalarial);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getEscalaSalarial() {
		return this.escalaSalarial;
	}
	public String setEscalaSalarial(String escalaSalarial) {
		return this.escalaSalarial = escalaSalarial;
	}
}
