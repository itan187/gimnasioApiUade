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
			System.out.println();
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
			s.setInt(1, a.getNumero());
			s.setBoolean(2, a.getEstado());
			s.setString(3, a.getEmpresa());
			s.setDate(4, (Date) a.getVigencia());
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
			System.out.println();
		}
	
	}

	public Vector<Object> select(Object o) {
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
			System.out.println();
		}

	}
	
	public Corporativa buscarInscripcion(int numero) {
		try {
			Corporativa a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".InscripcionCorporativo where numero = ?");
			s.setInt(1, numero);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int num 		= result.getInt(1);
				Boolean estado 	= result.getBoolean(2);
				String empresa	= result.getString(3);
				Date vigencia	= result.getDate(4);
				
				Vector<Actividad> clases = null;

				PreparedStatement x = con.prepareStatement("Select * from " + PoolConnection.dbName + ".InscripcionClases where numero =" + numero);
				ResultSet res = x.executeQuery();
				
				/**
				 * Obtenemos todas las clases de la inscripción
				 */
				while (res.next()) {
					//int clase  = result.getInt(2);
					
					//clases = new Clase(numero, profesores, dias, horaInicio, horaFin)
				}
				
				a = new  Corporativa(estado, num, clases, empresa, vigencia);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}

}
