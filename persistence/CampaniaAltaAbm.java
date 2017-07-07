package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.Actividad;
import models.Campania;
import models.Socio;

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
			
			/*
			for (Socio socio: a.getSocio()) {
				PreparedStatement ss = con.prepareStatement("insert into " + PoolConnection.dbName + ".CampaniaSocio values (?,?)");
				ss.setInt(1,			a.getNumeroCampania());
				ss.setInt(2,			socio.getDocumento());
				ss.execute();
			}
			*/
			for (Actividad actividades: a.getActividades()) {
				PreparedStatement sss = con.prepareStatement("insert into " + PoolConnection.dbName + ".CampaniaFiltro values (?,?)");
				sss.setInt(1,			a.getNumeroCampania());
				sss.setInt(2,			actividades.getNumeroActividad());
				sss.execute();
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}
}
