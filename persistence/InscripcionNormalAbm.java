package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Actividad;
import models.Normal;

public class InscripcionNormalAbm extends InscripcionNormalPersistence {
	
	private static InscripcionNormalAbm instancia;
	
	private InscripcionNormalAbm() {
	}
	
	public static InscripcionNormalAbm getInstancia() {
		if (instancia == null) {
			instancia = new InscripcionNormalAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Normal a = (Normal)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".InscripcionNormal where numero = ?");
			s.setInt(1, a.getNumero());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			Normal a = (Normal)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".InscripcionNormal values (?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1, a.getNumero());
			s.setBoolean(2, a.getEstado());
			
			s.execute();
			
			/**
			 * Guardando las clases de la inscripción
			 */
			for (int i = 0; i < a.getActividades().size(); i++) {
				PreparedStatement x = con.prepareStatement("insert into " + PoolConnection.dbName + ".InscripcionActividades values (?,?)");
				x.setInt(1, a.getNumero());
				x.setInt(2, a.getActividades().elementAt(i).getNumeroActividad());
				x.execute();
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Vector<String> listado() {
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".InscripcionNormal");
			ResultSet res = x.executeQuery();
			
			Vector<String> listado = new Vector<String>();
			while (res.next()) {
				listado.add(res.getInt(1) + " - " + "Normal");
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return listado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void update(Object o) {
		try {
			Normal a = (Normal)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".InscripcionNormal " +
					"set estado = ? where numero= ?"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1,		(a.getEstado())? 1 : 0);
			s.setInt(2, 	a.getNumero());
			s.execute();
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Normal buscarInscripcion(int numero) {
		try {
			Normal a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".InscripcionNormal where numero = ?");
			s.setInt(1, numero);
			ResultSet result = s.executeQuery();
			
			while (result.next()) {
				int num 		= result.getInt(1);
				Boolean estado 	= result.getBoolean(2);

				PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".InscripcionActividades where numeroInscripcion =" + numero);
				ResultSet res = x.executeQuery();
				
				/**
				 * Obtenemos todas las actividades de la inscripción
				 */
				Vector<Actividad> actividades = new Vector<Actividad>();
				while (res.next()) {
					actividades.add(ActividadAbm.getInstancia().buscarActividad(res.getInt(2)));
				}
				
				a = new Normal(estado, num, actividades);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
