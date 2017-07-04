package controllers;

import java.util.Vector;

import models.Administrativo;
import models.Empleado;
import models.HorarioCompleto;
import models.Particular;
import persistence.EmpleadoAdminAbm;
import persistence.EmpleadoHorarioCompletoAbm;
import persistence.EmpleadoHorarioPartAbm;

public class RrhhController {
	public Vector<Administrativo> 	empleadosAdmin;
	public Vector<HorarioCompleto> 	empleadosProfFull;
	public Vector<Particular> 		empleadosProfPart;
	
	private static RrhhController instancia;

	static public RrhhController getInstancia() {
		if(RrhhController.instancia == null) {
			RrhhController.instancia = new RrhhController();
		}
		return RrhhController.instancia;
	}
	
	private RrhhController() {
		this.empleadosAdmin 		= new Vector<Administrativo>();
		this.empleadosProfFull 		= new Vector<HorarioCompleto>();
		this.empleadosProfPart		= new Vector<Particular>();
	}
	
	/**
	 * Buscar Empleado
	 * 
	 * @param documento
	 * @return Empleado
	 */
	public Empleado buscarEmpleado (int documento) {
		
		for (Administrativo a : empleadosAdmin) {
			if (a.getDocumento() == documento) return a;
		}
		Administrativo a = EmpleadoAdminAbm.getInstancia().buscarEmpleado(documento);
		if (a != null) return a;
		
		for (HorarioCompleto h : empleadosProfFull) {
			if (h.getDocumento() == documento) return h;
		}
		HorarioCompleto h = EmpleadoHorarioCompletoAbm.getInstancia().buscarEmpleado(documento);
		if (h != null)	return h;
			
		
		for (Particular par : empleadosProfPart) {
			if (par.getDocumento() == documento) return par;
		}
		Particular par = EmpleadoHorarioPartAbm.getInstancia().buscarEmpleado(documento);
		if (par != null) return par;
		
		return null;
	}
	
	/**
	 * Buscar Empleado Administrativo
	 * 
	 * @param documento
	 * @return Administrativo
	 */
	public Administrativo buscarEmpleadoAdmin (int documento) {
		
		for (Administrativo a : empleadosAdmin) {
			if (a.getDocumento() == documento) return a;
		}
		Administrativo a = EmpleadoAdminAbm.getInstancia().buscarEmpleado(documento);
		if (a != null) return a;
		return null;
	}
	
	/**
	 * Buscar Empleado Full Time
	 * 
	 * @param documento
	 * @return HorarioCompleto
	 */
	public HorarioCompleto buscarEmpleadoFullTime (int documento) {
		for (HorarioCompleto h : empleadosProfFull) {
			if (h.getDocumento() == documento) return h;
		}
		HorarioCompleto h = EmpleadoHorarioCompletoAbm.getInstancia().buscarEmpleado(documento);
		if (h != null)	return h;
		return null;
	}
	
	/**
	 * Buscar Empleado Part Time
	 * 
	 * @param documento
	 * @return Particular
	 */
	public Particular buscarEmpleadoPartTime (int documento) {
		for (Particular par : empleadosProfPart) {
			if (par.getDocumento() == documento) return par;
		}
		Particular par = EmpleadoHorarioPartAbm.getInstancia().buscarEmpleado(documento);
		if (par != null) return par;
		return null;
	}
	
	/**
	 * Alta Empleado Administrativo
	 * 
	 * Para crear un empleado administrativo, recibimos todos los datos y
	 * construimos para luego incorporarlo en nuestra colección.
	 * 
	 * @param nombre
	 * @param documento
	 * @param mail
	 * @param telefono
	 * @param domicilio
	 * @param sueldo
	 */
	public void altaEmpleadoAdmin (String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float sueldo) {
		Administrativo admin = new Administrativo(nombre, documento, mail, telefono, domicilio, escalaSalarial, sueldo);
		empleadosAdmin.add(admin);
		admin.insert();
	}

	/**
	 * Alta Empleado Profesor Full Time
	 * 
	 * Para crear un profesor de horario completo, recibimos los datos y
	 * contstruimos para luego incorporarlo en nuestra colección.
	 * 
	 * @param nombre
	 * @param documento
	 * @param mail
	 * @param telefono
	 * @param domicilio
	 * @param sueldo
	 */
	public void altaEmpleadoProfFull (String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float sueldo) {
		HorarioCompleto profFulltime = new HorarioCompleto(nombre, documento, mail, telefono, domicilio, escalaSalarial, sueldo);
		empleadosProfFull.add(profFulltime);
		profFulltime.insert();
	}

	/**
	 * Alta Empleado Profesor Part-time
	 * 
	 * Para crear un profesor de horario part-time, recibimos los datos y
	 * contstruimos para luego incorporarlo en nuestra colección.
	 * 
	 * @param nombre
	 * @param documento
	 * @param mail
	 * @param telefono
	 * @param domicilio
	 * @param sueldo
	 */
	public void altaEmpleadoProfPartTime(String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float valorHora, int horas) {
		Particular profPartTime = new Particular(nombre, documento, mail, telefono, domicilio, escalaSalarial, valorHora, horas);
		empleadosProfPart.add(profPartTime);
		profPartTime.insert();
	}

	/**
	 * Baja de Empleados
	 * 
	 * Recibimos el documento del empleado y vamos a recorrer las colecciones
	 * de Administrativos y Profesores (Full time / Part time)
	 * 
	 * @param documento
	 */
	public void bajaEmpleados (int documento) {

		/**
		 * Baja de Empleados administrativos
		 */
		for (Administrativo a : empleadosAdmin) {
			if (a.getDocumento() == documento) {
				empleadosAdmin.remove(a);
			}
		}
		Administrativo a = buscarEmpleadoAdmin(documento);
		if (a != null) a.eliminarEmpleado();
		
		/**
		 * Baja de Empleados full time
		 */
		for (HorarioCompleto h : empleadosProfFull) {
			if (h.getDocumento() == documento) {
				empleadosProfFull.remove(h);
			}
		}
		HorarioCompleto h = buscarEmpleadoFullTime(documento);
		if (h != null) h.eliminarEmpleado();
		
		/**
		 * Baja de Empleados part time
		 */
		for (Particular p : empleadosProfPart) {
			if (p.getDocumento() == documento) {
				empleadosProfPart.remove(p);
			}
		}
		Particular p = buscarEmpleadoPartTime(documento);
		if (p != null) p.eliminarEmpleado();
		
	}
	
	/**
	 * Modificar Empleados
	 * 
	 * Recibimos los atributos del empleado, buscamos si 
	 * existe el mismo. De existir, recorremos la colección de empleados
	 * (respectivamente) para encontrar a nuestro empleado.
	 * Una vez que encontramos el nuestro, verificamos si modificaron el
	 * dato, de ser así guardamos la nueva información.
	 * 
	 * @param nombre
	 * @param documento
	 * @param mail
	 * @param telefono
	 * @param domicilio
	 * @param valor (sueldo | sueldoBasico | valorHora)
	 */
	public void modificarEmpleado (String nombre, int documento, String mail, String telefono, String domicilio, String escalaSalarial, float valor) {
		Administrativo e = buscarEmpleadoAdmin(documento);
		
		/**
		 * Buscamos que exista el empleado
		 */
		if (e != null) {
			/**
			 * Recorremos los empleados administrativos en busca
			 * del empleado y así modificar los valores
			 */
			for (Administrativo a : empleadosAdmin) {
				if (a.getDocumento() == documento) {
					if (a.getNombre() != nombre) 					a.setNombre(nombre);
					if (a.getMail() != mail) 						a.setMail(mail);
					if (a.getTelefono() != telefono) 				a.setTelefono(telefono);
					if (a.getDomicilio() != domicilio) 				a.setDomicilio(domicilio);
					if (a.getEscalaSalarial() != escalaSalarial) 	a.setEscalaSalarial(escalaSalarial);
					if (a.getSueldo() != valor) 					a.setSueldo(valor);
				}
			}
			/**
			 * Actualizamos los campos en la base de datos
			 */
			e.actualizarEmpleado(nombre, documento, mail, telefono, domicilio, escalaSalarial, valor);
		}
		
		HorarioCompleto h = buscarEmpleadoFullTime(documento);
		
		/**
		 * Buscamos que exista el empleado
		 */
		if (h != null) {
		
			/**
			 * Recorremos los profesores full time en busca
			 * del empleado y así modificar los valores
			 */
			for (HorarioCompleto c : empleadosProfFull) {
				if (c.getDocumento() == documento) {
					if (c.getNombre() != nombre) 					c.setNombre(nombre);
					if (c.getMail() != mail) 						c.setMail(mail);
					if (c.getTelefono() != telefono) 				c.setTelefono(telefono);
					if (c.getDomicilio() != domicilio) 				c.setDomicilio(domicilio);
					if (c.getEscalaSalarial() != escalaSalarial) 	c.setEscalaSalarial(escalaSalarial);
					if (c.getSueldoBasico() != valor) 				c.setSueldoBasico(valor);
				}
			}
			/**
			 * Actualizamos los campos en la base de datos
			 */
			h.actualizarEmpleado(nombre, documento, mail, telefono, domicilio, escalaSalarial, valor);
		}
		
		Particular part = buscarEmpleadoPartTime(documento);
		
		/**
		 * Buscamos que exista el empleado
		 */
		if (part != null) {
			/**
			 * Recorremos los profesores part time en busca
			 * del empleado y así modificar los valores
			 */
			for (Particular p : empleadosProfPart) {
				if (p.getDocumento() == documento) {
					if (p.getNombre() != nombre) 					p.setNombre(nombre);
					if (p.getMail() != mail) 						p.setMail(mail);
					if (p.getTelefono() != telefono) 				p.setTelefono(telefono);
					if (p.getDomicilio() != domicilio) 				p.setDomicilio(domicilio);
					if (p.getEscalaSalarial() != escalaSalarial) 	p.setEscalaSalarial(escalaSalarial);
					if (p.getValorHora() != valor) 					p.setValorHora(valor);
				}
			}
			part.actualizarEmpleado(nombre, documento, mail, telefono, domicilio, escalaSalarial, valor);
			
		}
	}
}
