package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Abono;

public class AbonoAbm extends AbonoPersistence {
	private static AbonoAbm instancia;
	
	public static AbonoAbm getInstancia() {
		if (instancia == null) {
			instancia = new AbonoAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Abono a = (Abono)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Abono where codigo = ?");
			s.setLong(1, a.getCodigo());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			Abono a = (Abono)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".Abono values (?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1,a.getCodigo());
			s.setString(2, a.getNombre());
			s.setFloat(3,a.getPrecio());
			s.setDate(4, new java.sql.Date(a.getVigencia().getTime()));
			
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
			Abono a = (Abono)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".Abono " +
					"set nombre = ?," +
					"precio =?," +
					"vigencia =? where codigo = " + a.getCodigo()
			);

			/**
			 * Agregando los campos
			 */
			s.setString(1,		a.getNombre());
			s.setFloat(2, 		a.getPrecio());
			s.setDate(3, new java.sql.Date(a.getVigencia().getTime()));
			s.execute();
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Abono buscarAbono(int codigo) {
		try {
			Abono a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Abono where codigo = ?");
			s.setInt(1, codigo);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int cod 		= result.getInt(1);
				String nombre 	= result.getString(2);
				Float precio 	= result.getFloat(3);
				Date vigencia 	= result.getDate(4);
				
				a = new Abono(cod, nombre, precio, vigencia);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<String> listado() {
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".Abono");
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
