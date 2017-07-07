package controllers;

import java.util.Date;
import java.util.Vector;

import models.Actividad;
import models.Campania;

import persistence.ActividadAbm;

public class CampaniaController {
	public Vector<Campania>	campanias;
	
	private static CampaniaController instancia;

	static public CampaniaController getInstancia() {
		if(CampaniaController.instancia == null) {
			CampaniaController.instancia = new CampaniaController();
		}
		return CampaniaController.instancia;
	}
	
	private CampaniaController() {
		this.campanias = new Vector<Campania>();
	}
	
	public void altaCampania (int numero, String asunto, Date fechaDeEnvio, Vector<Integer> a, boolean estado) {
		
		Vector<Actividad> actividades = new Vector<Actividad>();
		for (int actividad: a) {
			Actividad act = ActividadAbm.getInstancia().buscarActividad(actividad);
			actividades.add(act);
		}
		Campania campania = new Campania(numero, asunto, fechaDeEnvio, actividades, estado);
		campanias.add(campania);
		campania.insert();
	}
}
