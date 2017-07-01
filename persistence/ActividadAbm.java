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
			s.setInt(1, a.getNumeroActividad());
			s.setInt(2, a.getDeporte().getCodigo());
			s.setInt(3, a.getLunesInicio());
			s.setInt(4, a.getLunesFin());
			s.setInt(5, a.getMartesInicio());
			s.setInt(6, a.getMartesFin());
			s.setInt(7, a.getMiercolesInicio());
			s.setInt(8, a.getMiercolesFin());
			s.setInt(9, a.getJuevesInicio());
			s.setInt(10, a.getJuevesFin());
			s.setInt(11, a.getViernesInicio());
			s.setInt(12, a.getViernesFin());
			s.setInt(13, a.getSabadoInicio());
			s.setInt(14, a.getSabadoFin());
			s.setInt(15, a.getDomingoInicio());
			s.setInt(16, a.getDomingoFin());
			
			s.execute();
			
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
					"set numeroActividad = ?," +
					"set deporte = ?," +
					"set lunesInicio = ?," +
					"set lunesFin = ?," +
					"set martesInicio = ?," +
					"set martesFin = ?," +
					"set miercolesInicio = ?," +
					"set miercolesFin = ?," +
					"set juevesInicio = ?," +
					"set juevesFin = ?," +
					"set viernesInicio = ?," +
					"set viernesFin = ?," +
					"set sabadoInicio = ?," +
					"set sabadoFin = ?," +
					"set domingoInicio = ?," +
					"set domingoFin = ?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1, a.getNumeroActividad());
			s.setInt(2, a.getDeporte().getCodigo());
			s.setInt(3, a.getLunesInicio());
			s.setInt(4, a.getLunesFin());
			s.setInt(5, a.getMartesInicio());
			s.setInt(6, a.getMartesFin());
			s.setInt(7, a.getMiercolesInicio());
			s.setInt(8, a.getMiercolesFin());
			s.setInt(9, a.getJuevesInicio());
			s.setInt(10, a.getJuevesFin());
			s.setInt(11, a.getViernesInicio());
			s.setInt(12, a.getViernesFin());
			s.setInt(13, a.getSabadoInicio());
			s.setInt(14, a.getSabadoFin());
			s.setInt(15, a.getDomingoInicio());
			s.setInt(16, a.getDomingoFin());
			
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
				int numeroDeporte	= result.getInt(2);
				int lunesInicio		= result.getInt(3);
				int lunesFin		= result.getInt(4);
				int martesInicio	= result.getInt(5);
				int martesFin		= result.getInt(6);
				int miercolesInicio	= result.getInt(7);
				int miercolesFin	= result.getInt(8);
				int juevesInicio	= result.getInt(9);
				int juevesFin		= result.getInt(10);
				int viernesInicio	= result.getInt(11);
				int viernesFin		= result.getInt(12);
				int sabadoInicio	= result.getInt(13);
				int sabadoFin		= result.getInt(14);
				int domingoInicio	= result.getInt(15);
				int domingoFin		= result.getInt(16);
				
				Vector<Profesor> profesores = null;
				
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
				
				Deporte deporte = DeporteAbm.getInstancia().buscarDeporte(numeroDeporte);
				
				a = new Actividad(
						num, 
						deporte, 
						profesores,
						lunesInicio,
						lunesFin,
						martesInicio,
						martesFin,
						miercolesInicio,
						miercolesFin,
						juevesInicio,
						juevesFin,
						viernesInicio,
						viernesFin,
						sabadoInicio,
						sabadoFin,
						domingoInicio,
						domingoFin
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
