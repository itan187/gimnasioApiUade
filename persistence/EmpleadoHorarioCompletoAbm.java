package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.HorarioCompleto;

public class EmpleadoHorarioCompletoAbm extends EmpleadoHorarioCompletoPersistence {

	private static EmpleadoHorarioCompletoAbm instancia;
	
	private EmpleadoHorarioCompletoAbm() {
	}
	
	public static EmpleadoHorarioCompletoAbm getInstancia() {
		if (instancia == null) {
			instancia = new EmpleadoHorarioCompletoAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			HorarioCompleto a = (HorarioCompleto)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".EmpleadoHorarioCompleto where documento = ?");
			s.setInt(1, a.getDocumento());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			HorarioCompleto a = (HorarioCompleto)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".EmpleadoHorarioCompleto values (?,?,?,?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1 ,a.getDocumento());
			s.setString(2, a.getNombre());
			s.setString(3, a.getMail());
			s.setString(4, a.getTelefono());
			s.setString(5, a.getDomicilio());
			s.setString(6, a.getEscalaSalarial());
			s.setFloat(7, a.getSueldoBasico());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}

	public void update(Object o) {
		try {
			HorarioCompleto a = (HorarioCompleto)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".EmpleadoHorarioCompleto " +
					"set nombre = ?," +
					"mail = ?," +
					"telefono = ?," +
					"domicilio = ?," +
					"escalaSalarial = ?," +
					"sueldoBasico = ? where documento = ?"
			);

			/**
			 * Agregando los campos
			 */
			
			s.setString(1, a.getNombre());
			s.setString(2, a.getMail());
			s.setString(3, a.getTelefono());
			s.setString(4, a.getDomicilio());
			s.setString(5, a.getEscalaSalarial());
			s.setFloat(6, a.getSueldoBasico());
			s.setInt(7 ,a.getDocumento());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public HorarioCompleto buscarEmpleado (int documento) {
		try {
			HorarioCompleto a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".EmpleadoHorarioCompleto where documento = ?");
			s.setInt(1, documento);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				String nombre 		= result.getString(2);
				String mail 		= result.getString(3);
				String telefono		= result.getString(4);
				String domicilio	= result.getString(5);
				String escala		= result.getString(6);
				Float sueldo		= result.getFloat(7);
				
				a = new HorarioCompleto(nombre, documento, mail, telefono, domicilio, escala, sueldo);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<HorarioCompleto> select() {
		Vector<HorarioCompleto> full = new Vector<HorarioCompleto>();
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".EmpleadoHorarioCompleto");
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int documento			= result.getInt(1);
				String nombre 			= result.getString(2);
				String mail				= result.getString(3);
				String telefono			= result.getString(4);
				String domicilio		= result.getString(5);
				String escalaSalarial	= result.getString(6);
				Float sueldo			= result.getFloat(7);
				
				full.add(new HorarioCompleto(nombre, documento, mail, telefono, domicilio, escalaSalarial, sueldo));
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return full;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<String> listado() {
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".EmpleadoHorarioCompleto");
			ResultSet res = x.executeQuery();
			
			Vector<String> listado = new Vector<String>();
			while (res.next()) {
				listado.add(res.getInt(1) + " - " + res.getString(2));
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
