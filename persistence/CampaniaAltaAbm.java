package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Actividad;
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
			
			for (Actividad actividades: a.getActividades()) {
				PreparedStatement ss = con.prepareStatement("insert into " + PoolConnection.dbName + ".CampaniaFiltro values (?,?)");
				ss.setInt(1,			a.getNumeroCampania());
				ss.setInt(2,			actividades.getNumeroActividad());
				ss.execute();
				
				PreparedStatement x = con.prepareStatement("select * from " + PoolConnection.dbName + ".InscripcionActividades where numeroActividad =" + actividades.getNumeroActividad());
				ResultSet res = x.executeQuery();
				
				while (res.next()) {
					
					PreparedStatement xx = con.prepareStatement("select * from " + PoolConnection.dbName + ".SocioInscripcion where inscripcionNumero =" + res.getInt(1));
					ResultSet result = xx.executeQuery();
					
					while (result.next()) {
						PreparedStatement sss = con.prepareStatement("insert into " + PoolConnection.dbName + ".CampaniaSocio values (?,?,?)");
						sss.setInt(1,			a.getNumeroCampania());
						sss.setInt(2,			result.getInt(1));
						sss.setString(3, 		SocioAbm.getInstancia().buscarSocio(result.getInt(1)).getEmail());
						sss.execute();
					}
				}
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}
}
