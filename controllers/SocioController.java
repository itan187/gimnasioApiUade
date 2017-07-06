package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import models.*;

import persistence.AbonoAbm;
import persistence.ActividadAbm;

import persistence.InscripcionCorporativoAbm;
import persistence.InscripcionNormalAbm;
import persistence.SocioAbm;

public class SocioController {
	public Vector<Socio> 				socios;
	public Vector<Abono> 				abonos;
	public Vector<Normal> 				inscripcionesNormales;
	public Vector<Corporativa> 			inscripcionesCorpo;
	public Vector<Ingreso> 				ingresos;
	public Vector<Liquidacion>			liquidaciones;
	public Vector<CertificadoMedico> 	aptosMedicos;
	
	private static SocioController instancia;

	static public SocioController getInstancia() {
		if(SocioController.instancia == null) {
			SocioController.instancia = new SocioController();
		}
		return SocioController.instancia;
	}
	
	private SocioController() {
		this.socios 				= new Vector<Socio>();
		this.abonos 				= new Vector<Abono>();
		this.inscripcionesNormales 	= new Vector<Normal>();
		this.inscripcionesCorpo 	= new Vector<Corporativa>();
		this.aptosMedicos			= new Vector<CertificadoMedico>();
	}
	
	/**
	 * Buscar Abono
	 * 
	 * Buscamos un abono a través de un código único que identifica
	 * a dicho abono.
	 * Si la búsqueda me devuelve null significa que no existe dicho
	 * abono.
	 * 
	 * @param codigo
	 * @return Abono
	 */
	public Abono buscarAbono (int codigo) {
		for (Abono a : abonos) {
			if (a.esAbono(codigo)) return a;
		}
		Abono a = AbonoAbm.getInstancia().buscarAbono(codigo);
		if (a != null) {
			abonos.add(a);
			return a;
		}
		
		return null;
	}
	
	/**
	 * Buscar inscripción
	 * 
	 * Buscamos un abono a través de un número único que identifica
	 * la inscripción de un socio.
	 * 
	 * Si la búsqueda devuelve null significa que no existe dicha 
	 * inscripción
	 * 
	 * @param numero
	 * @return Inscripcion
	 */
	public Inscripcion buscarInscripcion (int numero) {
		
		/**
		 * Buscamos en la Inscripción Normal 
		 */
		for (Normal n : inscripcionesNormales) {
			if (n.esInscripcion(numero)) return n;
		}
		Normal n = InscripcionNormalAbm.getInstancia().buscarInscripcion(numero);
		if (n != null) {
			inscripcionesNormales.add(n);
			return n;
		}
		
		/**
		 * Buscamos en la Inscripción Corporativa 
		 */
		for (Corporativa c : inscripcionesCorpo) {
			if (c.esInscripcion(numero)) return c;
		}
		Corporativa c = InscripcionCorporativoAbm.getInstancia().buscarInscripcion(numero);
		if (c != null) {
			inscripcionesCorpo.add(c);
			return c;
		}
		
		return null;
	}
	
	/**
	 * Abono al Día
	 * 
	 * Para saber si un abono esta al día, primero buscamos 
	 * dicho abono.
	 * Una vez que lo encontramos, validamos de que exista y
	 * que ese mismo Abono tiene una fecha de vigencia anterior
	 * a la de la fecha actual
	 * 
	 * @param codigo
	 * @return booelean
	 */
	public boolean abonoAlDia (int codigo) {
		Abono abonoSocio = buscarAbono(codigo);
		return (abonoSocio != null && abonoSocio.abonoVigente());
	}
	
	/**
	 * Buscar Socio
	 * 
	 * Buscamos un socio a través de su documento de identidad
	 * el cual es único.
	 * 
	 * @param documento
	 * @return Socio
	 */
	public Socio buscarSocio (int documento) {
		for (Socio s : socios) {
			if (s.esSocio(documento)) return s;
		}
		Socio s = SocioAbm.getInstancia().buscarSocio(documento);
		if (s != null) {
			socios.add(s);
			return s;
		}
		return null;
	}
	
	/**
	 * Apto médico no vencido
	 * 
	 * Buscamos el Socio a través de su documento de identidad.
	 * una vez que lo obtuvimos, validamos que exista y que su
	 * apto médico esté al día
	 * 
	 * @param documento
	 * @return boolean
	 */
	public boolean aptoMedicoNoVencido (int documento) {
		Socio socio = buscarSocio(documento);
		return (socio != null && socio.conAptoMedioAlDia());
	}
	
	/**
	 * Habiltar ingreso a un socio
	 *
	 * Cuando un socio llega al gimnasio tenemos que validar
	 * si la persona puede ingresar al local.
	 *
	 * Para ello debemos validar ciertas cuestiones:
	 * 		1) Verificamos que el socio exista
	 * 		2) Obtenemos las inscripciones registradas
	 * 			a su nombre
	 * 		3) Recorremos todas las inscripciones buscando la
	 * 			que este activa
	 * 		4) Si encontramos una activa, obtenemos las Clases
	 * 			a las cuales se inscribió
	 * 		5) De existir Clases las recorremos buscando los días
	 * 			que se dicta la misma.
	 * 		6) Obtenidos los días que se dicta, verificamos si alguno
	 * 			de ellos corresponde al día de la semana (Lunes, Martes,
	 * 			Miércoles, Jueves, Virnes, Sábado, Domingo)
	 * 		7) De encontrar un día igual al actual, habilitamos el ingreso
	 *
	 * @param documento
	 * @return boolean
	 */
	public boolean habilitadoParaIngresar (int documento) {
		
		Socio socio = buscarSocio(documento);
		if (socio != null && socio.conAptoMedioAlDia() && socio.conAbonoAlDia()) {
			Vector<Inscripcion> inscripciones = null; //EN NULL PARA SEGUIR = socio.getInscripciones();
			
			/**
			 *  Recorremos las inscripciones del socio
			 */
			for (Inscripcion i : inscripciones) {
				/** Si la inscripción esta activa **/

				if (i.getEstado()) {
					Vector<Actividad> actividades = i.getActividades();
					/** Recorremos las clases de dicha inscripcion **/

					for (Actividad a : actividades) {
						/** Días que esta la clase **/
						int horaInicioActividad = -1;
						

						Calendar c = Calendar.getInstance();
						/*
						switch (c.get(Calendar.DAY_OF_WEEK)) {
						   case Calendar.MONDAY:
							   horaInicioActividad = a.getLunes();
							   break;
						   case Calendar.TUESDAY:
							   horaInicioActividad = a.getMartes();
							   break;
						   case Calendar.WEDNESDAY:
							   horaInicioActividad = a.getMiercoles();
							   break;
						   case Calendar.THURSDAY:
							   horaInicioActividad = a.getJueves();
							   break;
						   case Calendar.FRIDAY:
							   horaInicioActividad = a.getViernes();
							   break;
						   case Calendar.SATURDAY:
							   horaInicioActividad = a.getSabado();
							   break;
						   case Calendar.SUNDAY:
							   horaInicioActividad = a.getDomingo();
							   break;
						}*/
						
						if (horaInicioActividad <= 0) {
							return false;
						} else {
							DateFormat horaDelMomento = new SimpleDateFormat("HHmm");
							int horaDelMomentoInt = Integer.parseInt(horaDelMomento.toString());

							int horaActAnt = horaInicioActividad - 15;
							int horaActDesp = horaInicioActividad + 15;
							
							return ((horaActAnt < horaDelMomentoInt) && (horaActDesp > horaDelMomentoInt));
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Alta Socio
	 * 
	 * Para crear un socio, recibimos los parámetros mínimos para
	 * crear un socio.
	 * Una vez creado lo incorporamos a nuestra colección.
	 * 
	 * @param nombre
	 * @param domicilio
	 * @param telefono
	 * @param email
	 * @param abono
	 * @param documento
	 */
	public void altaSocio (String nombre, String domicilio, String telefono, String email, int ab, int ins, int documento) {
		Abono abono = AbonoAbm.getInstancia().buscarAbono(ab);
		Inscripcion insNormal = InscripcionNormalAbm.getInstancia().buscarInscripcion(ins);
		Inscripcion insCorpo = InscripcionCorporativoAbm.getInstancia().buscarInscripcion(ins);
		
		Inscripcion inscripcion;
		if (insNormal != null) {
			inscripcion = insNormal;
		} else {
			inscripcion = insCorpo;
		}
		
		Socio s = new Socio(
				documento, 
				nombre, 
				domicilio,
				telefono, 
				email, 
				abono, 
				inscripcion, 
				new Vector<CertificadoMedico>(), 
				true
			);
		socios.add(s);
		s.insert();
	}
	
	/**
	 * Baja Socio
	 * 
	 * A la hora de dar de baja un socio, recibimos el documento
	 * único de identidad.
	 * Una vez encontrado lo quitamos de nuestra colección.
	 * 
	 * @param documento
	 */
	public void bajaSocio (int documento) {
		Socio socio = buscarSocio(documento);
		socio.eliminarSocio();
		socios.remove(socio);
	}
	
	/**
	 * Modificar Socio
	 * 
	 * Para modificar un socio recibimos todos los valores del socio y
	 * vamos a reemplazar los antiguos por los nuevos.
	 * 
	 * @param nombre
	 * @param domicilio
	 * @param telefono
	 * @param email
	 * @param documento
	 */
	public void modificarSocio(String nombre, String domicilio, String telefono, String email, int documento, boolean estado) {
		Socio socio = buscarSocio(documento);
		
		if (socio != null) {
			
			/**
			 * Recorremos todos los socios a fin de actualizar los datos
			 * del mismo
			 */
			for (Socio s : socios) {
				/**
				 * Validamos que el socio en el que estamos es el que
				 * queremos actualizar
				 */
				if (s.getDocumento() == documento) {
					if (s.getNombre() != nombre)			s.setNombre(nombre);
					if (s.getDomicilio() != domicilio)		s.setDomicilio(domicilio);
					if (s.getTelefono() != telefono)		s.setTelefono(telefono);
					if (s.getEmail() != email)				s.setEmail(email);
					if (s.getEstado() != estado)			s.setEstado(estado);
				}
			}
			/**
			 * Actualizamos el socio en la base de datos
			 */
			socio.actualizarSocio(documento, nombre, domicilio, telefono, email, estado);
		}
	}
	
	/**
	 * Alta Abono
	 * 
	 * Para crear un abono, recibimos los parámetros mínimos para
	 * crear un abono.
	 * Una vez creado lo incorporamos a nuestra colección.
	 * 
	 * @param codigo
	 * @param nombre
	 * @param precio
	 * @param vigencia
	 */
	public void altaAbono (int codigo, String nombre, float precio, Date vigencia) {
		Abono ab = new Abono(codigo, nombre, precio, vigencia);
		ab.insert();
		abonos.add(ab);
	}

	/**
	 * Baja Abono
	 * 
	 * A la hora de dar de baja un abono, recibimos el código.
	 * Una vez encontrado lo quitamos de nuestra colección.
	 * 
	 * @param codigo
	 */
	public void bajaAbono(int codigo) {
		Abono abono = buscarAbono(codigo);
		abono.eliminarAbono();
		abonos.remove(abono);
	}
	
	/**
	 * Modificar Abono
	 * 
	 * Para modificar un abono recibimos todos los valores del abono y
	 * vamos a reemplazar los antiguos por los nuevos.
	 * 
	 * @param codigo
	 * @param nombre
	 * @param precio
	 * @param vigencia
	 */
	public void modificarAbono (int codigo, String nombre, float precio, Date vigencia) {
		Abono abono = buscarAbono (codigo);
		
		if (abono != null) {
			
			/**
			 * Recorremos todos los abonos a fin de actualizar los datos
			 * del mismo
			 */
			for (Abono a : abonos) {
				if (a.getCodigo() == codigo) {
					if (a.getNombre() != nombre)		a.setNombre(nombre);
					if (a.getPrecio() != precio)		a.setPrecio(precio);
					if (a.getVigencia() != vigencia)	a.setVigencia(vigencia);
				}
			}
			/**
			 * Actualizamos el abono en la base de datos
			 */
			abono.actualizarAbono();
			
		}
	}
	
	/**
	 * Alta Inscripción Normal
	 * 
	 * Para crear una inscripción, recibimos los de la misma y vamos a
	 * tener que hacer un recorrido para encontrar las clases a las que se
	 * quiere inscribir. Una vez realizado, incorporamos la inscripción
	 * a nuestra colección.
	 * 
	 * @param numero
	 * @param estado
	 * @param act
	 */
	public void altaInscripcionNormal(int numero, boolean estado, Vector<Integer> a) {
		
		Vector<Actividad> actividadesDelSocio = new Vector<Actividad>();
		for (int act: a) {
			Actividad actividad = ActividadAbm.getInstancia().buscarActividad(act);
			actividadesDelSocio.add(actividad);
		}
		
		Normal n = new Normal(estado, numero, actividadesDelSocio);
		inscripcionesNormales.add(n);
		n.insert();
	}

	/**
	 * Alta Inscripción Corporativo
	 * 
	 * Para crear una inscripción, recibimos los de la misma y vamos a
	 * tener que hacer un recorrido para encontrar las actividades a las que se
	 * quiere inscribir. Una vez realizado, incorporamos la inscripción
	 * a nuestra colección.
	 * 
	 * @param numero
	 * @param estado
	 * @param clases
	 * @param empresa
	 * @param vigencia
	 */
	public void altaInscripcionCorpo (int numero, boolean estado, Vector<Integer> a, String empresa, Date vigencia) {
		
		Vector<Actividad> actividadesDelSocio = new Vector<Actividad>();
		for (int act: a) {
			Actividad actividad = ActividadAbm.getInstancia().buscarActividad(act);
			actividadesDelSocio.add(actividad);
		}
		Corporativa c = new Corporativa(estado, numero, actividadesDelSocio, empresa, vigencia);
		inscripcionesCorpo.add(c);
		c.insert();
	}

	/**
	 * Baja Inscripción (Normal | Corporativa)
	 * 
	 * A la hora de dar de baja una inscripción, recibimos el número.
	 * Una vez encontrado lo quitamos de nuestra colección.
	 * 
	 * @param numero
	 */
	public void bajaInscripcion(int numero) {
		for (Normal n : inscripcionesNormales) {
			if (n.esInscripcion(numero)) {
				inscripcionesNormales.remove(n);
				n.eliminarInscripcion();
			}
		}
		for (Corporativa c : inscripcionesCorpo) {
			if (c.esInscripcion(numero)) {
				inscripcionesCorpo.remove(c);
				c.eliminarInscripcion();
			}
		}
	}

	/**
	 * Modificar Inscripcion Normal
	 * 
	 * Para modificar una inscripción normal recibimos todos los 
	 * valores de la inscripción y vamos a reemplazar los antiguos 
	 * por los nuevos guardandolos en la colección inscripciones
	 * 
	 * @param numero
	 * @param estado
	 * @param clases
	 */
	public void modificarInscripcionNormal(int numero, boolean estado, Vector<Actividad> act) {

		Normal inscripcion = InscripcionNormalAbm.getInstancia().buscarInscripcion(numero);
		
		if (inscripcion != null) {
			/**
			 * Recorremos todos los abonos a fin de actualizar los datos
			 * del mismo
			 */
			for (Normal n : inscripcionesNormales) {
				if (n.getNumero() == numero) {
					if (n.getEstado() != estado)			n.setEstado(estado);
					if (n.getActividades() != act)			n.setActividades(act);
				}
			}
			/**
			 * Actualizamos la inscripción normal
			 */
			inscripcion.actualizarInscripcion(new Normal(estado, numero, act));
		}
	}
	/**
	 * Alta certificado médico
	 * 
	 * @param numAptoMedico
	 * @param numSocio
	 * @param fechaCreacion
	 * @param profesional
	 * @param observaciones
	 */
	public void altaCertificado (int numAptoMedico, int numSocio, Date fechaCreacion, String profesional, String observaciones) {
		/**
		 * El sistema calcula la fecha de fin de vigencia
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaCreacion);
		
		/**
		 * Fecha Fin de vigencia 1 anio después de ser entregado.
		 * 
		 */
		calendar.add(Calendar.MONTH, 12);
		Date fechaFinVigencia = calendar.getTime();
		
		Socio soc = buscarSocio(numSocio);
		
		CertificadoMedico cm = new CertificadoMedico(numAptoMedico, soc, fechaCreacion, fechaFinVigencia,  profesional,  observaciones, true);
		aptosMedicos.add(cm);
		cm.insert();

	}
	/**
	 * Modificar Inscripcion Corporativo
	 * 
	 * Para modificar una inscripción normal recibimos todos los 
	 * valores de la inscripción y vamos a reemplazar los antiguos 
	 * por los nuevos guardandolos en la colección inscripciones
	 * 
	 * @param numero
	 * @param estado
	 * @param clases
	 */
	public void modificarInscripcionCorporativo (int numero, boolean estado, String clases, String empresa, Date vigencia) {

		/*Inscripcion inscripcion = buscarInscripcion (numero);
		
		if (inscripcion != null) {
			/**
			 * Recorremos todos los abonos a fin de actualizar los datos
			 * del mismo
			 */
			/*
			for (Corporativa c : inscripcionesCorpo) {
				if (c.getNumero() == numero) {
					
					Vector<Actividad> cl = Utiles.convertStringToClases(clases);
					
					if (c.getEstado() != estado) 			c.setEstado(estado);
					if (c.getActividades() != cl) 			c.setActividades(cl);
					if (c.getEmpresa() != empresa)			c.setEmpresa(empresa);
					if (c.getVigencia() != vigencia)		c.setVigencia(vigencia);
					
					c.actualizarInscripcion();
				}
			}
		}*/


		
	}
}
