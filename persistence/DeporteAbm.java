package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Deporte;

public class DeporteAbm extends DeportePersistence {
	private static DeporteAbm instancia;
	
	public static DeporteAbm getInstancia() {
		if (instancia == null) {
			instancia = new DeporteAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Deporte a = (Deporte)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Deporte where codigo = ?");
			s.setLong(1, a.getCodigo());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public void insert(Object o) {
		try {
			Deporte a = (Deporte)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into "+ PoolConnection.dbName + ".Deporte values (?,?,?)");
			
			/**
			 * Agregando los campos
			 */
			s.setInt(1,		a.getCodigo());
			s.setString(2, 	a.getTitulo());
			s.setString(3,	a.getDescripcion());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	
	}

	public void update(Object o) {
		try {
			Deporte a = (Deporte)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update "+ PoolConnection.dbName + ".Deporte " +
					"set titulo = ?, " +
					"descripcion =? where codigo = ?"
			);

			/**
			 * Agregando los campos
			 */
			s.setString(1, 	a.getTitulo());
			s.setString(2,	a.getDescripcion());
			s.setInt(3,		a.getCodigo());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}

	}
	
	public Deporte buscarDeporte(int codigo) {
		try {
			Deporte a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+ PoolConnection.dbName + ".Deporte where codigo = ?");
			s.setInt(1, codigo);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				String titulo 		= result.getString(2);
				String descripcion 	= result.getString(3);
				
				a = new Deporte(codigo, titulo, descripcion);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
