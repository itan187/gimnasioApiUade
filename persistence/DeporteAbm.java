package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Deporte;
import models.Socio;

public class DeporteAbm extends DeportePersistence {
	private String nombreTabla;
	private static DeporteAbm instancia;
	
	private DeporteAbm() {
		this.nombreTabla = PoolConnection.dbName + ".Deporte";
	}
	
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
			PreparedStatement s = con.prepareStatement("delete from " + this.nombreTabla + " where codigo = ?");
			s.setLong(1, a.getCodigo());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
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

	public Vector<Object> select(Object o) {
		return null;
	}

	public void update(Object o) {
		try {
			Deporte a = (Deporte)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + this.nombreTabla + " " +
					"titulo = ?," +
					"descripcion =?) where codigo = ?"
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
			PreparedStatement s = con.prepareStatement("select * from " + this.nombreTabla + " where codigo = ?");
			s.setInt(1, codigo);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int cod 			= result.getInt(1);
				String titulo 		= result.getString(2);
				String descripcion 	= result.getString(3);
				
				a = new Deporte(cod, titulo, descripcion);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}

}
