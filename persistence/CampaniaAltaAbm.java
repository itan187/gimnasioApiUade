package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.Campania;

public class CampaniaAltaAbm extends CampaniaAltaPersistence {
	private static CampaniaAltaAbm instancia;
	
	public static CampaniaAltaAbm getInstancia() {
		if (instancia == null) {
			instancia = new CampaniaAltaAbm();
		}
		return instancia;
	}

	public void insert(Object o) {
		try {
			Campania a = (Campania)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".Campania values (?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1,			a.getNumeroCampania());
			s.setString(2, 		a.getAsunto());
			s.setDate(3,		new java.sql.Date(a.getFechaDeEnvio().getTime()));
			s.setInt(4, 		(a.getEstado()) ? 1 : 0);
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}
}
