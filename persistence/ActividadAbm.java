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
			PreparedStatement ss = con.prepareStatement("delete from " + PoolConnection.dbName + ".ActividadProfesores where numeroActividad = ?");
			ss.setInt(1, a.getNumeroActividad());
			ss.execute();
			
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Actividad where numeroActividad = ?");
			s.setInt(1, a.getNumeroActividad());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			Actividad a = (Actividad)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into "+ PoolConnection.dbName + ".Actividad values (?,?,?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1, 	a.getNumeroActividad());
			s.setString(2, 	a.getDescription());
			s.setInt(3, 	a.getDeporte().getCodigo());
			s.setInt(4, 	a.getDuracion());
			s.setInt(5, 	a.getDia());
			s.setInt(6, 	a.getHoraDeInicio());
			s.execute();
			/**
			 * Guardando la vinculación entre la actividad y los profesores
			 */
			for (Profesor p: a.getProfesores()) {
				PreparedStatement sp = con.prepareStatement("insert into "+ PoolConnection.dbName + ".ActividadProfesores values (?,?)");
				sp.setInt(1, a.getNumeroActividad());
				sp.setInt(2, p.getDocumento());
				sp.execute();
			}
						
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
					"set description = ?," +
					"deporte = ?," +
					"duracion = ?," +
					"dia = ?," +
					"horario = ? where numeroActividad = ?"
			);

			/**
			 * Agregando los campos
			 */
			s.setString(1, 	a.getDescription());
			s.setInt(2, 	a.getDeporte().getCodigo());
			s.setInt(3,		a.getDuracion());
			s.setInt(4, 	a.getDia());
			s.setInt(5, 	a.getHoraDeInicio());
			s.setInt(6, 	a.getNumeroActividad());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
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
				
				String descripcion	= result.getString(2);
				int numeroDeporte	= result.getInt(3);
				int duracion		= result.getInt(4);
				int dia				= result.getInt(5);
				int horarioInicio	= result.getInt(6);
				
				Vector<Profesor> profesores = new Vector<Profesor>();
				
				PreparedStatement sp = con.prepareStatement("select * from " + PoolConnection.dbName + ".ActividadProfesores where numeroActividad = ?");
				sp.setInt(1, numeroActividad);
				ResultSet res = sp.executeQuery();
				
				/**
				 * Si existen profesores para esa actividad los vamos a recorrer
				 * buscando el empleado en horario completo o particular
				 */
				while (res.next()) {
					int documento = res.getInt(2);
					
					HorarioCompleto c 	= EmpleadoHorarioCompletoAbm.getInstancia().buscarEmpleado(documento);
					Particular p 		= EmpleadoHorarioPartAbm.getInstancia().buscarEmpleado(documento);
					
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
						int horas				= p.getHoras();
						
						Particular prof = new Particular(nombre, documento, mail, telefono, domicilio, escalaSalarial, valorHora, horas);
						profesores.addElement(prof);
					}
				}
				
				Deporte deporte = DeporteAbm.getInstancia().buscarDeporte(numeroDeporte);
				
				a = new Actividad(
						numeroActividad, 
						deporte, 
						profesores,
						descripcion,
						duracion,
						dia,
						horarioInicio
					);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
