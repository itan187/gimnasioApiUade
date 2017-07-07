package controllers;

import java.util.Vector;

import models.Campania;

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
	
	
}
