package app;

import java.util.Vector;

import controllers.*;
import models.Actividad;

public class Utiles {

    /**
     * Obtener Dia de La Semana
     *
     * @param diaSemana
     * @return String
     */
    static public String getDiaDeLaSemana (int diaSemana){
        String day = "";
        switch(diaSemana){
            case 1:
                day = "Domingo";
                break;
            case 2:
                day = "Lunes";
                break;
            case 3:
                day = "Martes";
                break;
            case 4:
                day = "Miércoles";
                break;
            case 5:
                day = "Jueves";
                break;
            case 6:
                day = "Viernes";
                break;
            case 7:
                day = "Sábado";
                break;
        }
        return day;
    }
    
    /**
	 * Convertir String a Vector<Clase>
	 * 
	 * Convertimos un string de números separados por coma
	 * a un Vector del tipo Clase
	 * 
	 * @param clases
	 * @return Vector<Clase>
	 */
	public static Vector<Actividad> convertStringToClases (String clases) {
		/**
		 * Generamos un vector donde vamos alojar las clases del socio
		 */
		Vector<Actividad> cl = new Vector<>();
		
		/**
		 * Separamos los números de las clases del socio, y luego
		 * recorremos el vector para ir buscando la Clase para almacenarla
		 * en un vector del tipo Clase
		 */
		String[] split = clases.split(", ");
		
		for (int i = 0; i < split.length; i++) {
			Actividad clase = ActividadController.buscarClase(Integer.parseInt(split[i]));
			if (clase != null) {
				cl.add(clase);
			}
		}
		
		return cl;
	}
}
