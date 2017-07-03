package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Administrativo;

public class EmpleadoAdminAbm extends EmpleadoAdminPersistence {

	private static EmpleadoAdminAbm instancia;
	
	private EmpleadoAdminAbm() {
	}
	
	public static EmpleadoAdminAbm getInstancia() {
		if (instancia == null) {
			instancia = new EmpleadoAdminAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Administrativo a = (Administrativo)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".EmpleadoAdministrativo where documento = ?");
			System.out.println(a.getDocumento());
			s.setInt(1, a.getDocumento());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println("NO funciona");
		}
		
	}

	public void insert(Object o) {
		try {
			Administrativo a = (Administrativo)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".EmpleadoAdministrativo values (?,?,?,?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1 ,a.getDocumento());
			s.setString(2, a.getNombre());
			s.setString(3, a.getMail());
			s.setString(4, a.getTelefono());
			s.setString(5, a.getDomicilio());
			s.setString(6, a.getEscalaSalarial());
			s.setFloat(7, a.getSueldo());
			
			s.execute();
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
			Administrativo a = (Administrativo)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".EmpleadoAdministrativo " +
					"set documento = ?," +
					"set nombre = ?," +
					"set mail = ?," +
					"set telefono = ?," +
					"set domicilio = ?," +
					"set escala_salarial = ?," +
					"set sueldo =?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1 ,a.getDocumento());
			s.setString(2, a.getNombre());
			s.setString(3, a.getMail());
			s.setString(4, a.getTelefono());
			s.setString(5, a.getDomicilio());
			s.setString(6, a.getEscalaSalarial());
			s.setFloat(7, a.getSueldo());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}

	}
	
	public Administrativo buscarEmpleado (int documento) {
		try {
			Administrativo a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".EmpleadoAdministrativo where documento = ?");
			s.setInt(1, documento);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int doc 			= result.getInt(1);
				String nombre 		= result.getString(2);
				String mail 		= result.getString(3);
				String telefono		= result.getString(4);
				String domicilio	= result.getString(5);
				String escala		= result.getString(6);
				Float sueldo		= result.getFloat(7);
				
				a = new Administrativo(nombre, doc, mail, telefono, domicilio, escala, sueldo);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}

}
