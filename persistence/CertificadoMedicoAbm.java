package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.CertificadoMedico;
import models.Socio;

public class CertificadoMedicoAbm extends CertificadoMedicoPersistence {
	private static CertificadoMedicoAbm instancia;
	
	private CertificadoMedicoAbm() {
	}
	
	public static CertificadoMedicoAbm getInstancia() {
		if (instancia == null) {
			instancia = new CertificadoMedicoAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			CertificadoMedico a = (CertificadoMedico)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".CertificadoMedico where numAptoMedico = ?");
			s.setInt(1, a.getNumAptoMedico());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			CertificadoMedico a = (CertificadoMedico)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".CertificadoMedico values (?,?,?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1, 		a.getNumAptoMedico());
			s.setInt(2, 		a.getSocio().getDocumento());
			s.setDate(3, 		(Date) a.getFechaCreacion());
			s.setDate(4, 		(Date) a.getVencimiento());
			s.setString(5, 		a.getProfesional());
			s.setString(6, 		a.getObservaciones());
			s.setInt(7, 		(a.getEstado()) ? 1 : 0);
			
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
			CertificadoMedico a = (CertificadoMedico)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".CertificadoMedico " +
					"set numSocio =?," +
					"fechaCreacion = ?," +
					"vencimiento =?," +
					"profesional =?," +
					"observaciones =?," +
					"estado =? where numAptoMedico = ?"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1, 		a.getSocio().getDocumento());
			s.setDate(2, 		new java.sql.Date(a.getFechaCreacion().getTime()));
			s.setDate(3, 		new java.sql.Date(a.getVencimiento().getTime()));
			s.setString(4, 		a.getProfesional());
			s.setString(5, 		a.getObservaciones());
			s.setInt(6, 		(a.getEstado()) ? 1 : 0);
			s.setInt(7, 		a.getNumAptoMedico());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public CertificadoMedico buscarCertificado (int numero) {
		try {
			CertificadoMedico a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".CertificadoMedico where numAptoMedico = ?");
			s.setInt(1, numero);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				int numSocio			= result.getInt(2);
				Date fechaCreacion 		= result.getDate(3);
				Date vencimiento 		= result.getDate(4);
				String profesional 		= result.getString(5);
				String observaciones 	= result.getString(6);
				boolean estado			= result.getBoolean(7);
				
				Socio socio = SocioAbm.getInstancia().buscarSocio(numSocio);
				
				a = new CertificadoMedico(numero, socio, fechaCreacion, vencimiento, profesional, observaciones, estado);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
