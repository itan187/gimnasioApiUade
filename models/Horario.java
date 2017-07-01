package models;

import java.sql.Time;

public class Horario {
	private Time horario;
	
	public Horario(Time horario) {
		super();
		this.setHorario(horario);
	}
	public void setHorario(Time horario) {
		this.horario = horario;
	}
	public Time getHorario() {
		return horario;
	}
	
}
