package models;

public class Cronograma {
	private Actividad clases;

	public Cronograma(Actividad clases) {
		super();
		this.setClases(clases);
	}
	public Actividad getClases() {
		return clases;
	}
	public void setClases(Actividad clases) {
		this.clases = clases;
	}
	
	
}
