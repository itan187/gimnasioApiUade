package models;

import java.util.Vector;

import persistence.InscripcionNormalAbm;

public class Normal extends Inscripcion {

	public Normal(boolean estado, int numero, Vector<Actividad> clases) {
		super(estado, numero, clases);
	}
	public void insert() {
		InscripcionNormalAbm.getInstancia().insert(this);
	}
	public void eliminarInscripcion() {
		InscripcionNormalAbm.getInstancia().delete(this);
	}
	public void actualizarInscripcion() {
		InscripcionNormalAbm.getInstancia().update(this);
	}

}
