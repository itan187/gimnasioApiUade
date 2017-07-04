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
			for (int i = 0; i < a.getClases().size(); i++) {
				PreparedStatement x = con.prepareStatement("insert into " + PoolConnection.dbName + ".InscripcionActividades values (?,?)");
				x.setInt(1, a.getNumero());
				x.setInt(2, a.getClases().elementAt(i).getNumeroActividad());
				x.execute();
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Vector<Object> select(Object o) {
		return null;
	}

	public void update(Object o) {
		try {
			Normal a = (Normal)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".InscripcionNormal " +
					"set numero = ?," +
					"set estado = ?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1, a.getNumero());
			s.setBoolean(2,a.getEstado());
			
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
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Inscripcion where numero = ?");
			s.setInt(1, numero);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int num 		= result.getInt(1);
				Boolean estado 	= result.getBoolean(2);
				
				Vector<Actividad> clases = null;

				PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".InscripcionActividades where numero =" + numero);
				ResultSet res = x.executeQuery();
				
				/**
				 * Obtenemos todas las actividades de la inscripción
				 */
				while (res.next()) {
					//int clase  = result.getInt(2);
					
					//clases = new Clase(numero, profesores, dias, horaInicio, horaFin)
				}
				
				a = new Normal(estado, num, clases);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
