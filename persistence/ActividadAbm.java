package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import models.Actividad;
import models.HorarioCompleto;
import models.Particular;
import models.Profesor;

public class ActividadAbm extends ActividadPersistence {
	private static ActividadAbm instancia;
	
	private ActividadAbm() {
	}
	
	public static ActividadAbm getInstancia() {
		if (instancia == null) {
			instancia = new ActividadAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Actividad a = (Actividad)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Actividades where numeroClase = ?");
			s.setInt(1, a.getNumeroActividad());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}
		
	}

	public void insert(Object o) {
		try {
			Actividad a = (Actividad)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into "+ PoolConnection.dbName + ".Actividades values (?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1,a.getNumeroActividad());
			
			
			a.getHoraFin();
			
			int[][] horaI = a.getHoraInicio();
			for (int i = 1; i <= 7; i++) {
				/**
				 * Validamos que en el día de la semana tenga horario 
				 */
				if (horaI[1][i]) {
					
				}
			}
			
			/**
			 * horaInicio[1][dia] = horaDeInicio.get(Calendar.HOUR);
				horaInicio[2][dia] = horaDeInicio.get(Calendar.MINUTE);
			 */
			
			s.execute();
			
			//private Vector<String> dias;
			
			/**
			 * Guardando la vinculación entre la clase y los profesores
			 */
			for (Profesor p: a.getProfesores()) {
				PreparedStatement sp = con.prepareStatement("insert into "+ PoolConnection.dbName + ".ActividadesProfesores values (?,?)");
				sp.setInt(1, a.getNumeroActividad());
				sp.setInt(2, p.getDocumento());
				sp.execute();
			}
						
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
	
	}

	public Vector<Object> select(Object o) {
		return null;
	}

	public void update(Object o) {
		try {
			Actividad a = (Actividad)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".Actividades " +
					"set numeroClase = ?," +
					"set horaInicio = ?," +
					"set horaFin =?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1,a.getNumeroActividad());
			/*s.setDate(2,(Date) a.getHoraInicio());
			s.setDate(3,(Date)  a.getHoraFin());*/
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}

	}
	
	public Actividad buscarActividad(int numeroActividad) {
		try {
			Actividad a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Actividades where numeroActividad = ?");
			s.setInt(1, numeroActividad);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int num 			= result.getInt(1);
				Date horaInicio 	= result.getDate(2);
				Date horaFin 		= result.getDate(3);
				
				Vector<Profesor> profesores = null;
				Vector<String> dias = null;
				
				PreparedStatement sp = con.prepareStatement("select * from " + PoolConnection.dbName + ".ActividadesProfesores where numeroActividad = ?");
				s.setInt(1, numeroActividad);
				ResultSet res = sp.executeQuery();
				
				/**
				 * Si existen profesores para esa actividad los vamos a recorrer
				 * buscando el empleado en horario completo o particular
				 */
				while (res.next()) {
					int documento = res.getInt(1);
					
					HorarioCompleto c = EmpleadoHorarioCompletoAbm.getInstancia().buscarEmpleado(documento);
					Particular p = EmpleadoHorarioPartAbm.getInstancia().buscarEmpleado(documento);
					
					if (c != null) {
						String nombre			= c.getNombre();
						String mail				= c.getMail();
						String telefono			= c.getTelefono();
						String domicilio		= c.getDomicilio();
						String escalaSalarial	= c.getEscalaSalarial();
						float sueldoBasico		= c.getSueldoBasico();
						
						HorarioCompleto prof = new HorarioCompleto(nombre, documento, mail, telefono, domicilio, escalaSalarial, sueldoBasico);
						profesores.add(prof);
						
					} else if (p != null) {
						String nombre			= p.getNombre();
						String mail				= p.getMail();
						String telefono			= p.getTelefono();
						String domicilio		= p.getDomicilio();
						String escalaSalarial	= p.getEscalaSalarial();
						float valorHora			= p.getValorHora();
						
						Particular prof = new Particular(nombre, documento, mail, telefono, domicilio, escalaSalarial, valorHora);
						profesores.addElement(prof);
					}
					
				}
				//a = new Actividad(num, profesores, dias, horaInicio, horaFin);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}
	
}
