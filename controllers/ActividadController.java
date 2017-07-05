package controllers;

import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import models.Actividad;
import models.Cronograma;
import models.Deporte;
import models.Profesor;
import persistence.ActividadAbm;
import persistence.DeporteAbm;

public class ActividadController {
	public Vector<Deporte> 			deportes;
	public static Vector<Actividad> actividades;
	public Vector<Cronograma> 		cronogramas;
	
	private static ActividadController instancia;

	static public ActividadController getInstancia() {
		if (ActividadController.instancia == null) {
			ActividadController.instancia = new ActividadController();
		}
		return ActividadController.instancia;
	}
	
	private ActividadController () {
		this.deportes 				= new Vector<Deporte>();
		this.actividades 			= new Vector<Actividad>();
		this.cronogramas 			= new Vector<Cronograma>();
	}
	
	/**
	 * Buscar Deporte
	 * 
	 * Buscamos un deporte o a través de un código que lo identifica.
	 * Si la búsqueda me devuelve null significa que no existe dicho
	 * deporte
	 * 
	 * @param codigo
	 * @return Deporte
	 */
	public Deporte buscarDeporte (int codigo) {

		for (Deporte d : deportes) {
			if (d.esDeporte(codigo)) {
				return d;
			}
		}
		Deporte d = DeporteAbm.getInstancia().buscarDeporte(codigo);
		if (d != null) return d;
		
		return null;
	}
	
	/**
	 * Buscar una actividad
	 * 
	 * Buscamos una actividad puntual a través de su número de 
	 * identificación
	 * 
	 * @param numero
	 * @return Actividad
	 */
	public static Actividad buscarActividad(int numero) {
		
		for (Actividad c : actividades) {
			if (c.esClase(numero)) {
				return c;
			}
		}
		Actividad c = ActividadAbm.getInstancia().buscarActividad(numero);
		if (c != null) {
			return c;
		}
		return null;
	}
	
	/**
	 * Alta Deporte
	 * 
	 * Para crear un deporte nuevo, recibimos un código,
	 * un título y una descripcion.
	 * 
	 * Una vez creado lo incorporamos a la colección.
	 * 
	 * @param codigo
	 * @param titulo
	 * @param descripcion
	 */
	public void altaDeporte (int codigo, String titulo, String descripcion) {
		Deporte d = new Deporte(codigo, titulo, descripcion);
		d.insert();
		deportes.add(d);
	}
	
	/**
	 * Baja Deporte
	 * 
	 * A la hora de dar de baja un deporte, recibimos el código.
	 * Una vez encontrado lo quitamos de nuestra colección.
	 * 
	 * @param codigo
	 */
	public void bajaDeporte (int codigo) {
		Deporte deporte = buscarDeporte(codigo);
		if (deporte != null) {
			deportes.remove(deporte);
			deporte.eliminarDeporte();
		}		
	}
	
	/**
	 * Modificar Deporte
	 * 
	 * Para modificar un deporte recibimos todos los valores del deporte y
	 * vamos a reemplazar los antiguos por los nuevos.
	 * 
	 * @param codigo
	 * @param titulo
	 * @param descripcion
	 */
	public void modificarDeporte(int codigo, String titulo, String descripcion) {
		Deporte deporte = buscarDeporte(codigo);
		if (deporte != null) {
			
			for (Deporte d : deportes) {
				if (d.getCodigo() == codigo) {
					if (d.getTitulo() != titulo) 			d.setTitulo(titulo);
					if (d.getDescripcion() != descripcion) 	d.setDescripcion(descripcion);
				}
			}
			
			/**
			 * Modificamos el deporte en la base de datos
			 */
			deporte.actualizarDeporte(codigo, titulo, descripcion);
		}
	}
	
	/**
	 * Alta Actividad
	 * 
	 * Para crear una nueva actividad, recibimos un número,
	 * un descripción, deporte, duración, hora de inicio y día.
	 * 
	 * Una vez creado lo incorporamos a la colección.
	 * 
	 */
	public void altaActividad (int numero, String descripcion, int deporte, int duracion, int dia, int horaDeInicio) {
	
		Vector<Profesor> profesores = new Vector<Profesor>();
		
		Actividad a = new Actividad(
				numero, 
				buscarDeporte(deporte), 
				profesores, 
				descripcion, 
				duracion, 
				dia, 
				horaDeInicio
			);

		actividades.add(a);
		a.insert();
	}

}
