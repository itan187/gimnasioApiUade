package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Actividad;
import models.Corporativa;

public class InscripcionCorporativoAbm extends InscripcionCorporativoPersistence {
	
	private static InscripcionCorporativoAbm instancia;
	
	private InscripcionCorporativoAbm() {
	}
	
	public static InscripcionCorporativoAbm getInstancia() {
		if (instancia == null) {
			instancia = new InscripcionCorporativoAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Corporativa a = (Corporativa)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".InscripcionCorporativa where numero = ?");
			s.setInt(1, a.getNumero());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			Corporativa a = (Corporativa)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".InscripcionCorporativa values (?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1, 	a.getNumero());
			s.setBoolean(2, a.getEstado());
			s.setString(3, 	a.getEmpresa());
			s.setDate(4, new java.sql.Date(a.getVigencia().getTime()));
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
			PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".InscripcionCorporativa");
			ResultSet res = x.executeQuery();
			
			Vector<String> listado = new Vector<String>();
			while (res.next()) {
				listado.add(res.getInt(1) + " - " + res.getString(3));
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
			Corporativa a = (Corporativa)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".InscripcionCorporativa " +
					"set numero = ?," +
					"set estado = ?," +
					"set empresa = ?," +
					"set vigencia =?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1, a.getNumero());
			s.setBoolean(2,a.getEstado());
			s.setString(3, a.getEmpresa());
			s.setDate(4, (Date) a.getVigencia());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Corporativa buscarInscripcion(int numero) {
		try {
			Corporativa a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".InscripcionCorporativa where numero = ?");
			s.setInt(1, numero);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				Boolean estado 	= result.getBoolean(2);
				String empresa	= result.getString(3);
				Date vigencia	= result.getDate(4);

				PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".InscripcionActividades where numero =" + numero);
				ResultSet res = x.executeQuery();
				
				/**
				 * Obtenemos todas las actividades de la inscripción
				 */
				Vector<Actividad> actividades = new Vector<Actividad>();
				while (res.next()) {
					actividades.add(ActividadAbm.getInstancia().buscarActividad(result.getInt(1)));
				}
				
				a = new Corporativa(estado, numero, actividades, empresa, vigencia);
	
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
