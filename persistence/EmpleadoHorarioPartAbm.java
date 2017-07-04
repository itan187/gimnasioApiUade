package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Particular;

public class EmpleadoHorarioPartAbm extends EmpleadoHorarioPartPersistence {

	private static EmpleadoHorarioPartAbm instancia;
	
	private EmpleadoHorarioPartAbm() {
	}
	
	public static EmpleadoHorarioPartAbm getInstancia() {
		if (instancia == null) {
			instancia = new EmpleadoHorarioPartAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Particular a = (Particular)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".EmpleadoHorarioPartTime where documento = ?");
			s.setInt(1, a.getDocumento());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			Particular a = (Particular)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".EmpleadoHorarioPartTime values (?,?,?,?,?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1,		a.getDocumento());
			s.setString(2, 	a.getNombre());
			s.setString(3, 	a.getMail());
			s.setString(4, 	a.getTelefono());
			s.setString(5, 	a.getDomicilio());
			s.setString(6, 	a.getEscalaSalarial());
			s.setFloat(7, 	a.getValorHora());
			s.setInt(8,	 	a.getHoras());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public void update(Object o) {
		try {
			Particular a = (Particular)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".EmpleadoHorarioPartTime " +
					"set nombre = ?," +
					"mail = ?," +
					"telefono = ?," +
					"domicilio = ?," +
					"escalaSalarial = ?," +
					"valorHora = ?," +
					"horas = ? where documento = ?"
			);

			/**
			 * Agregando los campos
			 */
			s.setString(1, 	a.getNombre());
			s.setString(2, 	a.getMail());
			s.setString(3, 	a.getTelefono());
			s.setString(4, 	a.getDomicilio());
			s.setString(5,	a.getEscalaSalarial());
			s.setFloat(6, 	a.getValorHora());
			s.setFloat(7, 	a.getHoras());
			s.setInt(8,		a.getDocumento());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Particular buscarEmpleado (int documento) {
		try {
			Particular a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".EmpleadoHorarioPartTime where documento = ?");
			s.setInt(1, documento);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int doc 			= result.getInt(1);
				String nombre 		= result.getString(2);
				String mail 		= result.getString(3);
				String telefono		= result.getString(4);
				String domicilio	= result.getString(5);
				String escala		= result.getString(6);
				Float valor_hora	= result.getFloat(7);
				int horas			= result.getInt(8);
				
				a = new Particular(nombre, doc, mail, telefono, domicilio, escala, valor_hora, horas);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
