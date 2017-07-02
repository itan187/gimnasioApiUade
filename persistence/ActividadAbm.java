package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Actividad;
import models.Deporte;
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
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Actividad where numeroClase = ?");
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
			PreparedStatement s = con.prepareStatement("insert into "+ PoolConnection.dbName + ".Actividad values (?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1, a.getNumeroActividad());
			s.setInt(2, a.getDeporte().getCodigo());
			s.setInt(3, a.getLunes());
			s.setInt(4, a.getMartes());
			s.setInt(5, a.getMiercoles());
			s.setInt(6, a.getJueves());
			s.setInt(7, a.getViernes());
			s.setInt(8, a.getSabado());
			s.setInt(9, a.getDomingo());
			
			s.execute();
			
			/**
			 * Guardando la vinculación entre la clase y los profesores
			 */
			for (Profesor p: a.getProfesores()) {
				PreparedStatement sp = con.prepareStatement("insert into "+ PoolConnection.dbName + ".ActividadProfesor values (?,?)");
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
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".Actividad " +
					"set numeroActividad = ?," +
					"set deporte = ?," +
					"set lunes = ?," +
					"set martes = ?," +
					"set miercoles = ?," +
					"set jueves = ?," +
					"set viernes = ?," +
					"set sabado = ?," +
					"set domingo = ?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1, a.getNumeroActividad());
			s.setInt(2, a.getDeporte().getCodigo());
			s.setInt(3, a.getLunes());
			s.setInt(4, a.getMartes());
			s.setInt(5, a.getMiercoles());
			s.setInt(6, a.getJueves());
			s.setInt(7, a.getViernes());
			s.setInt(8, a.getSabado());
			s.setInt(9, a.getDomingo());
			
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
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Actividad where numeroActividad = ?");
			s.setInt(1, numeroActividad);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int num 			= result.getInt(1);
				int numeroDeporte	= result.getInt(2);
				int lunes			= result.getInt(3);
				int martes			= result.getInt(4);
				int miercoles		= result.getInt(5);
				int jueves			= result.getInt(6);
				int viernes			= result.getInt(7);
				int sabado			= result.getInt(8);
				int domingo			= result.getInt(9);
				
				Vector<Profesor> profesores = null;
				
				PreparedStatement sp = con.prepareStatement("select * from " + PoolConnection.dbName + ".ActividadProfesores where numeroActividad = ?");
				s.setInt(1, numeroActividad);
				ResultSet res = sp.executeQuery();
				
				/**
				 * Si existen profesores para esa actividad los vamos a recorrer
				 * buscando el empleado en horario completo o particular
				 */
				while (res.next()) {
					int documento = res.getInt(2);
					
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
				
				Deporte deporte = DeporteAbm.getInstancia().buscarDeporte(numeroDeporte);
				
				a = new Actividad(
						num, 
						deporte, 
						profesores,
						lunes,
						martes,
						miercoles,
						jueves,
						viernes,
						sabado,
						domingo
					);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}
	
}
