package models;

public abstract class Cargo {
	private String descripcion;
	private String escalaSalarial;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEscalaSalarial() {
		return escalaSalarial;
	}
	public void setEscalaSalarial(String escalaSalarial) {
		this.escalaSalarial = escalaSalarial;
	}
	
	
}
