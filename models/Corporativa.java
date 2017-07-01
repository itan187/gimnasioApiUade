package models;

import java.util.Date;
import java.util.Vector;

import persistence.InscripcionCorporativoAbm;

public class Corporativa extends Inscripcion {
	private String empresa;
	private Date vigencia;
	
	public Corporativa(boolean estado, int numero, Vector<Actividad> clases, String empresa, Date vigencia) {
		super(estado, numero, clases);
		this.setEmpresa(empresa);
		this.setVigencia(vigencia);
		
		InscripcionCorporativoAbm.getInstancia().insert(this);
	}
	
	public String getEmpresa() {
		return empresa;
	}
	public Date getVigencia() {
		return vigencia;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}
	
	public void eliminarInscripcion() {
		InscripcionCorporativoAbm.getInstancia().delete(this);
	}
	public void actualizarInscripcion() {
		InscripcionCorporativoAbm.getInstancia().update(this);
	}
}
